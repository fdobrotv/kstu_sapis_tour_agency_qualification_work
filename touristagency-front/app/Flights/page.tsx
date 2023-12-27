"use client"

import { useMemo, useState } from 'react';
import {
    MRT_EditActionButtons,
    MantineReactTable,
    type MRT_ColumnDef,
    type MRT_Row,
    type MRT_TableOptions,
    useMantineReactTable,
    createRow,
} from 'mantine-react-table';
import {
    ActionIcon,
    Button,
    Flex,
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
    FlightIn,
} from "@/generated";

import { UUID } from 'crypto';
import { create, deleteById, getFlights } from './fetch';
import { validateRequired, validateRequiredDate } from '../Validators/validation';
import HeaderTabs from '../Menu/Menu';

const Flights = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Flight>[]>(
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
                accessorKey: 'departureAirport',
                header: 'Departure Airport',
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when flight focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'arrivalAirport',
                header: 'Arrival Airport',
                mantineEditTextInputProps: {
                    type: 'email',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when flight focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
            },
            {
                accessorKey: 'departureDateTime',
                header: 'Departure Date Time',
                mantineEditTextInputProps: {
                    type: 'datetime-local',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when flight focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
                Cell: ({ cell }) =>  {
                    let date = cell.getValue<Date>();
                    return <Text>
                      {date.toISOString()}
                    </Text>
                    },
            },
            {
                accessorKey: 'arrivalDateTime',
                header: 'Arrival Date Time',
                mantineEditTextInputProps: {
                    type: 'datetime-local',
                    required: true,
                    error: validationErrors?.name,
                    //remove any previous validation errors when flight focuses on the input
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            name: undefined,
                        }),
                    //optionally add validation checking for onBlur or onChange
                },
                Cell: ({ cell }) =>  {
                    let date = cell.getValue<Date>();
                    return <Text>
                      {date.toISOString()}
                    </Text>
                    },
            },
        ],
        [validationErrors],
    );

    //call CREATE hook
    const { mutateAsync: createFlight, isLoading: isCreatingFlight } =
        useCreateFlight();
    //call READ hook
    const {
        data: fetchedFlights = [],
        isError: isLoadingFlightsError,
        isFetching: isFetchingFlights,
        isLoading: isLoadingFlights,
    } = useGetFlights();
    //call UPDATE hook
    const { mutateAsync: updateFlight, isLoading: isUpdatingFlight } =
        useUpdateFlight();
    //call DELETE hook
    const { mutateAsync: deleteFlight, isLoading: isDeletingFlight } =
        useDeleteFlight();

    //CREATE action
    const handleCreateFlight: MRT_TableOptions<Flight>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let flightIn: FlightIn = {
            departureAirport : values.departureAirport,
            arrivalAirport : values.arrivalAirport,
            departureDateTime : new Date(values.departureDateTime),
            arrivalDateTime : new Date(values.arrivalDateTime),
        }
        const newValidationErrors = validateFlightIn(flightIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createFlight(flightIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveFlight: MRT_TableOptions<Flight>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateFlight(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateFlight(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Flight>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this flight?',
            children: (
                <Text>
                    Are you sure you want to delete flight 
                    {row.original.departureAirport} {' '} {row.original.arrivalAirport}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteFlight(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedFlights,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingFlightsError
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
        onCreatingRowSave: handleCreateFlight,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveFlight,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New Flight</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Flight</Title>
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
                Create New Flight
            </Button>
        ),
        state: {
            isLoading: isLoadingFlights,
            isSaving: isCreatingFlight || isUpdatingFlight || isDeletingFlight,
            showAlertBanner: isLoadingFlightsError,
            showProgressBars: isFetchingFlights,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new flight to api)
function useCreateFlight() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (flightIn: FlightIn) => {
            return Promise.resolve(create(flightIn));
        },
        //client side optimistic update
        onMutate: (newFlightInfo: FlightIn) => {
            queryClient.setQueryData(
                ['flights'],
                (prevFlights: any) =>
                    [
                        ...prevFlights,
                        {
                            ...newFlightInfo,
                            // id: newFlightInfo.id,
                        },
                    ] as FlightIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['flights'] }),
    });
}

//READ hook (get flights from api)
export function useGetFlights() {
    console.log("useGetFlights")
    return useQuery<Array<Flight>>({
        queryKey: ['flights'],
        queryFn: async () => {
            return Promise.resolve(getFlights());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put flight in api)
function useUpdateFlight() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (flight: Flight) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newFlightInfo: Flight) => {
            queryClient.setQueryData(
                ['flights'],
                (prevFlights: any) =>
                    prevFlights?.map((prevFlight: Flight) =>
                        prevFlight.id === newFlightInfo.id ? newFlightInfo : prevFlight,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['flights'] }), //refetch flights after mutation, disabled for demo
    });
}

//DELETE hook (delete flight in api)
function useDeleteFlight() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (flightId: string) => {
            return Promise.resolve(deleteById(flightId));
        },
        //client side optimistic update
        onMutate: (flightId: string) => {
            queryClient.setQueryData(
                ['flights'],
                (prevFlights: any) =>
                    prevFlights?.filter((flight: Flight) => flight.id !== flightId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['flights'] }),
    });
}

const queryClient = new QueryClient();

const FlightsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <HeaderTabs />
            <Flights />
        </ModalsProvider>
    </QueryClientProvider>
);

export default FlightsWithProviders;

function validateFlight(flight: Flight) {
    return {
            departureAirport: !validateRequired(flight.departureAirport)
            ? 'Departure airport name is Required'
            : '',
            arrivalAirport: !validateRequired(flight.arrivalAirport)
            ? 'Arrival airport name is Required'
            : '',
    };
};

function validateFlightIn(flightIn: FlightIn) {
    console.log("validateFlightIn")
    console.log(flightIn)
    return {
            departureAirport: !validateRequired(flightIn.departureAirport)
            ? 'Departure airport name is Required'
            : '',
            arrivalAirport: !validateRequired(flightIn.arrivalAirport)
            ? 'Arrival airport name is Required'
            : '',
            departureDateTime: !validateRequiredDate(flightIn.departureDateTime)
            ? 'Departure Date-Time is Required and should be in future'
            : '',
            arrivalDateTime: !validateRequiredDate(flightIn.arrivalDateTime)
            ? 'Arrival Date-Time is Required and should be in future'
            : '',
    };
};
