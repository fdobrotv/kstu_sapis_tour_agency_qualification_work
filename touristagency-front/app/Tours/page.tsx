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
    Flight,
    FoodOption,
    Room,
    Tour,
    TourIn,
    Transfer,
} from "@/generated";

import { create, deleteById, getTours } from './fetch';
import { validateRequired, validateRequiredNumber } from '../Validators/validation';
import { UUID } from 'crypto';
import { getFlights } from '../Flights/fetch';
import { getTransfers } from '../Transfers/fetchTransfers';
import { getRooms } from '../Rooms/fetch';
import { getFoodOptions } from '../FoodOptions/fetch';

const Tours = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Tour>[]>(
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
                accessorKey: 'departureFlight',
                header: 'Departure Flight',
                Cell: ({ cell }) =>  {
                    let flight = cell.getValue<Flight>();
                    return <Text>
                      {flight?.departureAirport + " " + flight?.arrivalAirport + " " + 
                      flight?.departureDateTime + " " + flight?.arrivalDateTime } 
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
                        getFlights()
                        .then((response: Array<Flight>) => {
                            return response.map( (flight: Flight) => {
                                const items = {
                                    value: flight.id,
                                    label: flight.departureAirport + " " + flight.arrivalAirport
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No flights data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Departure Flight"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'arrivalFlight',
                header: 'Arrival Flight',
                Cell: ({ cell }) =>  {
                    let flight = cell.getValue<Flight>();
                    return <Text>
                      {flight.departureAirport + " " + flight.arrivalAirport + " " + 
                      flight.departureDateTime + " " + flight.arrivalDateTime } 
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
                        getFlights()
                        .then((response: Array<Flight>) => {
                            return response.map( (flight: Flight) => {
                                const items = {
                                    value: flight.id,
                                    label: flight.departureAirport + " " + flight.arrivalAirport
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No flights data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Arrival Flight"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'transferToHotel',
                header: 'Transfer to hotel',
                Cell: ({ cell }) =>  {
                    let transfer = cell.getValue<Transfer>();
                    return <Text>
                      {transfer.departureDateTime + " " + transfer.arrivalDateTime + " " + 
                      transfer.departureCoordinates + " " + transfer.arrivalCoordinates } 
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
                        getTransfers()
                        .then((response: Array<Transfer>) => {
                            return response.map( (transfer: Transfer) => {
                                const items = {
                                    value: transfer.id,
                                    label: transfer.departureCoordinates + " " + transfer.arrivalCoordinates + " " +
                                    transfer.departureDateTime + " " + transfer.arrivalDateTime
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No transfers data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Transfer to hotel"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'transferFromHotel',
                header: 'Transfer from hotel',
                Cell: ({ cell }) =>  {
                    let transfer = cell.getValue<Transfer>();
                    return <Text>
                      {transfer.departureDateTime + " " + transfer.arrivalDateTime + " " + 
                      transfer.departureCoordinates + " " + transfer.arrivalCoordinates } 
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
                        getTransfers()
                        .then((response: Array<Transfer>) => {
                            return response.map( (transfer: Transfer) => {
                                const items = {
                                    value: transfer.id,
                                    label: transfer.departureCoordinates + " " + transfer.arrivalCoordinates + " " +
                                    transfer.departureDateTime + " " + transfer.arrivalDateTime
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No transfers data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Transfer from hotel"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'description',
                header: 'Description',
                mantineEditTextInputProps: {
                    type: 'text',
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
                accessorKey: 'price',
                header: 'Price',
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
                accessorKey: 'room',
                header: 'Room',
                Cell: ({ cell }) =>  {
                    let room = cell.getValue<Room>();
                    return <Text>
                      {"Hotel: " + room.hotel.name + " " + room.name + " " + 
                      room.serviceClass + " " + room.pricePerNight } 
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
                        getRooms()
                        .then((response: Array<Room>) => {
                            return response.map( (room: Room) => {
                                const items = {
                                    value: room.id,
                                    label: room.hotel.name + " " + room.name
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No rooms data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Room name and price"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'selectedFoodOption',
                header: 'Food option',
                Cell: ({ cell }) =>  {
                    let foodOption = cell.getValue<FoodOption>();
                    return <Text>
                      {foodOption.name + ", цена: " + foodOption.price} 
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
                        getFoodOptions()
                        .then((response: Array<FoodOption>) => {
                            return response.map( (foodOption: FoodOption) => {
                                const items = {
                                    value: foodOption.id,
                                    label: foodOption.name + " " + foodOption.price
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

                        row._valuesCache[column.id] = selectedId;
                        if (isCreatingTour) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTour) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No rooms data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Food option with price"
                        placeholder="Pick value"
                        data={data}
                    />;
                },
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when tour focuses on the input
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
    const { mutateAsync: createTour, isLoading: isCreatingTour } =
        useCreateTour();
    //call READ hook
    const {
        data: fetchedTours = [],
        isError: isLoadingToursError,
        isFetching: isFetchingTours,
        isLoading: isLoadingTours,
    } = useGetTours();
    //call UPDATE hook
    const { mutateAsync: updateTour, isLoading: isUpdatingTour } =
        useUpdateTour();
    //call DELETE hook
    const { mutateAsync: deleteTour, isLoading: isDeletingTour } =
        useDeleteTour();

    //CREATE action
    const handleCreateTour: MRT_TableOptions<Tour>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let tourIn: TourIn = {
            departureFlightId : values['departureFlight.id'],
            arrivalFlightId : values['arrivalFlight.id'],
            transferToHotelId :  values['transferToHotel.id'],
            transferFromHotelId :  values['transferFromHotel.id'],
            description : values.description,
            price : values.price,
            roomId : values['room.id'],
            selectedFoodOptionId : values['selectedFoodOption.id'],
        }
        console.log("handleCreateTour");
        console.log(tourIn);
        const newValidationErrors = validateTourIn(tourIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createTour(tourIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveTour: MRT_TableOptions<Tour>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateTour(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateTour(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Tour>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this tour?',
            children: (
                <Text>
                    Are you sure you want to delete tour 
                    {row.original.description} {' '} {row.original.price}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteTour(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedTours,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingToursError
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
        onCreatingRowSave: handleCreateTour,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveTour,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New Tour</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Tour</Title>
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
                Create New Tour
            </Button>
        ),
        state: {
            isLoading: isLoadingTours,
            isSaving: isCreatingTour || isUpdatingTour || isDeletingTour,
            showAlertBanner: isLoadingToursError,
            showProgressBars: isFetchingTours,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new tour to api)
function useCreateTour() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (tourIn: TourIn) => {
            return Promise.resolve(create(tourIn));
        },
        //client side optimistic update
        onMutate: (newTourInfo: TourIn) => {
            queryClient.setQueryData(
                ['tours'],
                (prevTours: any) =>
                    [
                        ...prevTours,
                        {
                            ...newTourInfo,
                            // id: newTourInfo.id,
                        },
                    ] as TourIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['tours'] }),
    });
}

//READ hook (get tours from api)
export function useGetTours() {
    console.log("useGetTours")
    return useQuery<Array<Tour>>({
        queryKey: ['tours'],
        queryFn: async () => {
            return Promise.resolve(getTours());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put tour in api)
function useUpdateTour() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (tour: Tour) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newTourInfo: Tour) => {
            queryClient.setQueryData(
                ['tours'],
                (prevTours: any) =>
                    prevTours?.map((prevTour: Tour) =>
                        prevTour.id === newTourInfo.id ? newTourInfo : prevTour,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['tours'] }), //refetch tours after mutation, disabled for demo
    });
}

//DELETE hook (delete tour in api)
function useDeleteTour() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (tourId: string) => {
            return Promise.resolve(deleteById(tourId));
        },
        //client side optimistic update
        onMutate: (tourId: string) => {
            queryClient.setQueryData(
                ['tours'],
                (prevTours: any) =>
                    prevTours?.filter((tour: Tour) => tour.id !== tourId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['tours'] }),
    });
}

const queryClient = new QueryClient();

const ToursWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <Tours />
        </ModalsProvider>
    </QueryClientProvider>
);

export default ToursWithProviders;

//TODO: Finish validation!
function validateTour(tour: Tour) {
    return {
        // departureFlightId: !validateRequired(tour.departureFlight)
        //     ? 'Departure flight is Required'
        //     : '',
        //     arrivalFlightId: !validateRequired(tour.arrivalFlight)
        //     ? 'Arrival flight is Required'
        //     : '',
        //     transferToHotelId: !validateRequired(tour.transferToHotel)
        //     ? 'Transfer to hotel is Required'
        //     : '',
        //     transferFromHotelId: !validateRequired(tour.transferFromHotel)
        //     ? 'Transfer from hotel is Required'
        //     : '',
            description: !validateRequired(tour.description)
            ? 'Description is Required'
            : '',
            price: !validateRequiredNumber(tour.price)
            ? 'Price is Required'
            : '',
            // roomId: !validateRequired(tour.room)
            // ? 'Room is Required'
            // : '',
            // selectedFoodOptionId: !validateRequired(tour.selectedFoodOption)
            // ? 'Food option is Required'
            // : '',
    };
};

function validateTourIn(tourIn: TourIn) {
    console.log("validateTourIn")
    console.log(tourIn)
    return {
        // departureFlightId: !validateRequired(tourIn.departureFlightId)
        //     ? 'Departure flight is Required'
        //     : '',
        //     arrivalFlightId: !validateRequired(tourIn.arrivalFlightId)
        //     ? 'Arrival flight is Required'
        //     : '',
        //     transferToHotelId: !validateRequired(tourIn.transferToHotelId)
        //     ? 'Transfer to hotel is Required'
        //     : '',
        //     transferFromHotelId: !validateRequired(tourIn.transferFromHotelId)
        //     ? 'Transfer from hotel is Required'
        //     : '',
            description: !validateRequired(tourIn.description)
            ? 'Description is Required'
            : '',
            price: !validateRequiredNumber(tourIn.price)
            ? 'Price is Required'
            : '',
            // roomId: !validateRequired(tourIn.roomId)
            // ? 'Room is Required'
            // : '',
            // selectedFoodOptionId: !validateRequired(tourIn.selectedFoodOptionId)
            // ? 'Food option is Required'
            // : '',
    };
};
