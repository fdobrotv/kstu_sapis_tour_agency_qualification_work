"use client"

import { useMemo, useState } from 'react';
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
    Hotel,
    HotelIn,
    ServiceClass,
} from "@/generated";

import { create, deleteById, getHotels } from './fetch';
import { validateRequired } from '../Validators/validation';
import HeaderTabs from '../Menu/Menu';

const Hotels = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Hotel>[]>(
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
                    //remove any previous validation errors when hotel focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'address',
                header: 'Address',
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when hotel focuses on the input
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
                      if (isCreatingHotel) {
                        table.setCreatingRow(row);
                      } else if (isUpdatingHotel) {
                        table.setEditingRow(row);
                      }
                    };
                    const onChange = (event) => {
                        console.log("onChange");
                        console.log(event);
                    };
                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Hotel color"
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
                accessorKey: 'isGuideIncluded',
                header: 'Is Guide Included?',
                mantineEditTextInputProps: {
                    type: 'checkbox',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when hotel focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
                Cell: ({ cell }) =>  {
                    let checked = cell.getValue<boolean>();
                    return <Checkbox disabled checked = {checked}
                        placeholder="Decide"
                    />;
                    }
                  ,
                Edit: ({ cell, column, row, table }) => {
                    const onBlur = (event) => {
                        const hTMLInputElement: HTMLInputElement = event.target;
                        console.log(event);

                        row._valuesCache[column.id] = hTMLInputElement.checked;
                        if (isCreatingHotel) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingHotel) {
                            table.setEditingRow(row);
                        }
                    };

                    return <Checkbox onBlur={onBlur}
                        label="Is Guide Included?"
                        placeholder="Decide"
                    />;
                },
            },
        ],
        [validationErrors],
    );

    //call CREATE hook
    const { mutateAsync: createHotel, isLoading: isCreatingHotel } =
        useCreateHotel();
    //call READ hook
    const {
        data: fetchedHotels = [],
        isError: isLoadingHotelsError,
        isFetching: isFetchingHotels,
        isLoading: isLoadingHotels,
    } = useGetHotels();
    //call UPDATE hook
    const { mutateAsync: updateHotel, isLoading: isUpdatingHotel } =
        useUpdateHotel();
    //call DELETE hook
    const { mutateAsync: deleteHotel, isLoading: isDeletingHotel } =
        useDeleteHotel();

    //CREATE action
    const handleCreateHotel: MRT_TableOptions<Hotel>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let hotelIn: HotelIn = {
            name : values.name,
            address : values.address,
            serviceClass : values.serviceClass,
            isGuideIncluded : values.isGuideIncluded ? true : false,
        }
        console.log("handleCreateHotel");
        console.log(hotelIn);
        const newValidationErrors = validateHotelIn(hotelIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createHotel(hotelIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveHotel: MRT_TableOptions<Hotel>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateHotel(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateHotel(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Hotel>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this hotel?',
            children: (
                <Text>
                    Are you sure you want to delete hotel 
                    {row.original.name} {' '} {row.original.serviceClass}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteHotel(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedHotels,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingHotelsError
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
        onCreatingRowSave: handleCreateHotel,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveHotel,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New Hotel</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Hotel</Title>
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
                Create New Hotel
            </Button>
        ),
        state: {
            isLoading: isLoadingHotels,
            isSaving: isCreatingHotel || isUpdatingHotel || isDeletingHotel,
            showAlertBanner: isLoadingHotelsError,
            showProgressBars: isFetchingHotels,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new hotel to api)
function useCreateHotel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (hotelIn: HotelIn) => {
            return Promise.resolve(create(hotelIn));
        },
        //client side optimistic update
        onMutate: (newHotelInfo: HotelIn) => {
            queryClient.setQueryData(
                ['hotels'],
                (prevHotels: any) =>
                    [
                        ...prevHotels,
                        {
                            ...newHotelInfo,
                            // id: newHotelInfo.id,
                        },
                    ] as HotelIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['hotels'] }),
    });
}

//READ hook (get hotels from api)
export function useGetHotels() {
    console.log("useGetHotels")
    return useQuery<Array<Hotel>>({
        queryKey: ['hotels'],
        queryFn: async () => {
            return Promise.resolve(getHotels());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put hotel in api)
function useUpdateHotel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (hotel: Hotel) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newHotelInfo: Hotel) => {
            queryClient.setQueryData(
                ['hotels'],
                (prevHotels: any) =>
                    prevHotels?.map((prevHotel: Hotel) =>
                        prevHotel.id === newHotelInfo.id ? newHotelInfo : prevHotel,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['hotels'] }), //refetch hotels after mutation, disabled for demo
    });
}

//DELETE hook (delete hotel in api)
function useDeleteHotel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (hotelId: string) => {
            return Promise.resolve(deleteById(hotelId));
        },
        //client side optimistic update
        onMutate: (hotelId: string) => {
            queryClient.setQueryData(
                ['hotels'],
                (prevHotels: any) =>
                    prevHotels?.filter((hotel: Hotel) => hotel.id !== hotelId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['hotels'] }),
    });
}

const queryClient = new QueryClient();

const HotelsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <HeaderTabs />
            <Hotels />
        </ModalsProvider>
    </QueryClientProvider>
);

export default HotelsWithProviders;

function validateHotel(hotel: Hotel) {
    return {
        name: !validateRequired(hotel.name)
            ? 'Hotel name is Required'
            : '',
            address: !validateRequired(hotel.address)
            ? 'Hotel address is Required'
            : '',
            serviceClass: !validateRequired(hotel.serviceClass)
            ? 'Service class is Required'
            : '',
    };
};

function validateHotelIn(hotelIn: HotelIn) {
    console.log("validateHotelIn")
    console.log(hotelIn)
    return {
        name: !validateRequired(hotelIn.name)
            ? 'Hotel name is Required'
            : '',
            address: !validateRequired(hotelIn.address)
            ? 'Hotel address is Required'
            : '',
            serviceClass: !validateRequired(hotelIn.serviceClass)
            ? 'Service class is Required'
            : '',
    };
};
