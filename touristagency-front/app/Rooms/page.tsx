"use client"

import { useEffect, useMemo, useState } from 'react';
import {
    MRT_EditActionButtons,
    MantineReactTable,
    type MRT_ColumnDef,
    type MRT_Row,
    type MRT_TableOptions,
    useMantineReactTable,
} from 'mantine-react-table';
import {
    ActionIcon,
    Button,
    Checkbox,
    Flex,
    Select,
    Stack,
    Text,
    Title,
    Tooltip,
} from '@mantine/core';
import { ModalsProvider, modals } from '@mantine/modals';
import { IconEdit, IconTrash } from '@tabler/icons-react';
import {
    QueryClient,
    QueryClientProvider,
    useMutation,
    useQuery,
    useQueryClient,
} from '@tanstack/react-query';
import {
    Room,
    Hotel,
    RoomIn,
    ServiceClass,
} from "@/generated";

import { create, deleteById, getRooms } from './fetch';
import { getHotels } from '../Hotels/fetch';
import { validateRequired, validateRequiredNumber } from '../Validators/validation';
import { UUID } from 'crypto';

const Rooms = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Room>[]>(
        () => [
            {
                accessorKey: 'id',
                header: 'Id',
                enableEditing: false,
                size: 80,
                mantineEditTextInputProps: {
                    required: false,
                    disabled: true,
                    variant: 'default',
                  },
                Edit: ({ cell, column, row, table }) => {
                    return <div hidden = {true}></div>;
                },
            },
            {
                accessorKey: 'name',
                header: 'Name',
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when room focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'serviceClass',
                header: 'Service class',
                Edit: ({ cell, column, row, table }) => {
                    const data: Array<string> = Object.values(ServiceClass);
                    const onBlur = (event) => {
                      row._valuesCache[column.id] = event.target.value;
                      console.log("onBlur");
                        console.log(event);
                      if (isCreatingRoom) {
                        table.setCreatingRow(row);
                      } else if (isUpdatingRoom) {
                        table.setEditingRow(row);
                      }
                    };
                    const onChange = (event) => {
                        console.log("onChange");
                        console.log(event);
                    };
                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Room color"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                },
            },
            {
                accessorKey: 'pricePerNight',
                header: 'Price per night',
                mantineEditTextInputProps: {
                    type: 'number',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when transfer focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'hotel',
                header: 'Hotel',
                Cell: ({ cell }) =>  {
                    let hotel = cell.getValue<Hotel>();
                    return <Text>
                      {hotel?.name + " " + hotel?.serviceClass + " " + hotel?.address + " " + "Гид "} 
                      {hotel?.isGuideIncluded ? "включён":"не включён"}
                    </Text>
                    },
                Edit: ({ cell, column, row, table }) => {
                    interface Item {
                        value: string; 
                        label: string; 
                    }

                    const [data, setData] = useState<Array<Item>>([])
                    const [isLoading, setLoading] = useState(true)
                    const [selectedId, setSelectedId] = useState<UUID>()

                    useEffect(() => {
                        getHotels()
                        .then((response: Array<Hotel>) => {
                            return response.map( (hotel: Hotel) => {
                                const items = {
                                    value: hotel.id,
                                    label: hotel.name
                                }
                                return items;
                            });
                        })
                        .then((data) => {
                            setData(data)
                            setLoading(false)
                        })
                    }, [])

                    const onBlur = (event) => {
                        const hTMLInputElement: HTMLInputElement = event.target;
                        console.log(hTMLInputElement);

                        // row._valuesCache[column.id] = hTMLInputElement.value;
                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingRoom) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingRoom) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No car marks data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Hotel name"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when car mark focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
        ],
        [validationErrors],
    );

    //call CREATE hook
    const { mutateAsync: createRoom, isLoading: isCreatingRoom } =
        useCreateRoom();
    //call READ hook
    const {
        data: fetchedRooms = [],
        isError: isLoadingRoomsError,
        isFetching: isFetchingRooms,
        isLoading: isLoadingRooms,
    } = useGetRooms();
    //call UPDATE hook
    const { mutateAsync: updateRoom, isLoading: isUpdatingRoom } =
        useUpdateRoom();
    //call DELETE hook
    const { mutateAsync: deleteRoom, isLoading: isDeletingRoom } =
        useDeleteRoom();

    //CREATE action
    const handleCreateRoom: MRT_TableOptions<Room>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let roomIn: RoomIn = {
            name : values.name,
            serviceClass : values.serviceClass,
            pricePerNight : values.pricePerNight,
            hotelId : values.hotel,
        }
        console.log("handleCreateRoom");
        console.log(roomIn);
        const newValidationErrors = validateRoomIn(roomIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createRoom(roomIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveRoom: MRT_TableOptions<Room>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateRoom(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateRoom(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Room>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this room?',
            children: (
                <Text>
                    Are you sure you want to delete room 
                    {row.original.name} {' '} {row.original.serviceClass}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteRoom(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedRooms,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingRoomsError
            ? {
                color: 'red',
                children: 'Error loading data',
            }
            : undefined,
        mantineTableContainerProps: {
            sx: {
                minHeight: '500px',
            },
        },
        onCreatingRowCancel: () => setValidationErrors({}),
        onCreatingRowSave: handleCreateRoom,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveRoom,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New Room</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Room</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderRowActions: ({ row, table }) => (
            <Flex gap="md">
                <Tooltip label="Edit">
                    <ActionIcon onClick={() => table.setEditingRow(row)}>
                        <IconEdit />
                    </ActionIcon>
                </Tooltip>
                <Tooltip label="Delete">
                    <ActionIcon color="red" onClick={() => openDeleteConfirmModal(row)}>
                        <IconTrash />
                    </ActionIcon>
                </Tooltip>
            </Flex>
        ),
        renderTopToolbarCustomActions: ({ table }) => (
            <Button
                onClick={() => {
                    table.setCreatingRow(true); //simplest way to open the create row modal with no default values
                    //or you can pass in a row object to set default values with the `createRow` helper function
                    // table.setCreatingRow(
                    //   createRow(table, {
                    //     id: "",
                    //     departureAirport: "Moskow",
                    //     arrivalAirport: "Istabmul",
                    //     departureDateTime: new Date(),
                    //     arrivalDateTime: new Date(),
                    //     //optionally pass in default values for the new row, useful for nested data or other complex scenarios
                    //   }),
                    // );
                }}
            >
                Create New Room
            </Button>
        ),
        state: {
            isLoading: isLoadingRooms,
            isSaving: isCreatingRoom || isUpdatingRoom || isDeletingRoom,
            showAlertBanner: isLoadingRoomsError,
            showProgressBars: isFetchingRooms,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new room to api)
function useCreateRoom() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (roomIn: RoomIn) => {
            return Promise.resolve(create(roomIn));
        },
        //client side optimistic update
        onMutate: (newRoomInfo: RoomIn) => {
            queryClient.setQueryData(
                ['rooms'],
                (prevRooms: any) =>
                    [
                        ...prevRooms,
                        {
                            ...newRoomInfo,
                            // id: newRoomInfo.id,
                        },
                    ] as RoomIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['rooms'] }),
    });
}

//READ hook (get rooms from api)
export function useGetRooms() {
    console.log("useGetRooms")
    return useQuery<Array<Room>>({
        queryKey: ['rooms'],
        queryFn: async () => {
            return Promise.resolve(getRooms());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put room in api)
function useUpdateRoom() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (room: Room) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newRoomInfo: Room) => {
            queryClient.setQueryData(
                ['rooms'],
                (prevRooms: any) =>
                    prevRooms?.map((prevRoom: Room) =>
                        prevRoom.id === newRoomInfo.id ? newRoomInfo : prevRoom,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['rooms'] }), //refetch rooms after mutation, disabled for demo
    });
}

//DELETE hook (delete room in api)
function useDeleteRoom() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (roomId: string) => {
            return Promise.resolve(deleteById(roomId));
        },
        //client side optimistic update
        onMutate: (roomId: string) => {
            queryClient.setQueryData(
                ['rooms'],
                (prevRooms: any) =>
                    prevRooms?.filter((room: Room) => room.id !== roomId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['rooms'] }),
    });
}

const queryClient = new QueryClient();

const RoomsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <Rooms />
        </ModalsProvider>
    </QueryClientProvider>
);

export default RoomsWithProviders;

function validateRoom(room: Room) {
    return {
        name: !validateRequired(room.name)
            ? 'Room name is Required'
            : '',
            pricePerNight: !validateRequiredNumber(room.pricePerNight)
            ? 'Price per night is Required'
            : '',
            serviceClass: !validateRequired(room.serviceClass)
            ? 'Service class is Required'
            : '',
    };
};

function validateRoomIn(roomIn: RoomIn) {
    console.log("validateRoomIn")
    console.log(roomIn)
    return {
        name: !validateRequired(roomIn.name)
            ? 'Room name is Required'
            : '',
            pricePerNight: !validateRequiredNumber(roomIn.pricePerNight)
            ? 'Price per night is Required'
            : '',
            serviceClass: !validateRequired(roomIn.serviceClass)
            ? 'Service class is Required'
            : '',
    };
};
