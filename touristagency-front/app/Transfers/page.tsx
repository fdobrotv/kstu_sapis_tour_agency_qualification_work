"use client"

import { useMemo, useState, useEffect } from 'react';
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
    Stack,
    Text,
    Title,
    Tooltip,
    Checkbox
} from '@mantine/core';
import { ModalsProvider, modals } from '@mantine/modals';
import { IconEdit, IconTrash } from '@tabler/icons-react';
import { Select } from '@mantine/core';
import {
    QueryClient,
    QueryClientProvider,
    useMutation,
    useQuery,
    useQueryClient,
} from '@tanstack/react-query';
import {
    Car,
    Transfer,
    TransferIn,
} from "@/generated";

import { UUID } from 'crypto';

import { getCars } from "../Cars/fetchCars";
import { create, deleteById, getTransfers } from './fetchTransfers';
import { validateRequired, validateRequiredDate } from '../Validators/validation';
import HeaderTabs from '../Menu/Menu';

const Transfers = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Transfer>[]>(
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
                accessorKey: 'car',
                header: 'Car',
                Cell: ({ cell }) =>  {
                    let car = cell.getValue<Car>();
                    return <Text>
                      {car?.mark + " " + car?.model + " " + car?.plateNumber + " " + car?.color}
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
                        getCars()
                        .then((response: Array<Car>) => {
                            return response.map( (car: Car) => {
                                const items = {
                                    value: car.id,
                                    label: car.mark + ' ' + car.model + ' ' + car.plateNumber
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
                        if (isCreatingTransfer) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTransfer) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No cars data</p>

                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Car"
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
            {
                accessorKey: 'departureCoordinates',
                header: 'Departure Coordinates',
                mantineEditTextInputProps: {
                    type: 'email',
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
                accessorKey: 'arrivalCoordinates',
                header: 'Arrival Coordinates',
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
                accessorKey: 'departureDateTime',
                header: 'Departure Date Time',
                mantineEditTextInputProps: {
                    type: 'datetime-local',
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
                    //remove any previous validation errors when transfer focuses on the input
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
                accessorKey: 'isGuideIncluded',
                header: 'Is Guide Included?',
                mantineEditTextInputProps: {
                    type: 'checkbox',
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
                        console.log(hTMLInputElement);

                        row._valuesCache[column.id] = hTMLInputElement.checked;
                        if (isCreatingTransfer) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingTransfer) {
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
    const { mutateAsync: createTransfer, isLoading: isCreatingTransfer } =
        useCreateTransfer();
    //call READ hook
    const {
        data: fetchedTransfers = [],
        isError: isLoadingTransfersError,
        isFetching: isFetchingTransfers,
        isLoading: isLoadingTransfers,
    } = useGetTransfers();
    //call UPDATE hook
    const { mutateAsync: updateTransfer, isLoading: isUpdatingTransfer } =
        useUpdateTransfer();
    //call DELETE hook
    const { mutateAsync: deleteTransfer, isLoading: isDeletingTransfer } =
        useDeleteTransfer();

    //CREATE action
    const handleCreateTransfer: MRT_TableOptions<Transfer>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let transferIn: TransferIn = {
            name : values.name,
            carId : values.car,
            price : values.price,
            departureCoordinates : values.departureCoordinates,
            arrivalCoordinates : values.arrivalCoordinates,
            departureDateTime : new Date(values.departureDateTime),
            arrivalDateTime : new Date(values.arrivalDateTime),
            isGuideIncluded : values.isGuideIncluded ? true : false,
        }
        const newValidationErrors = validateTransferIn(transferIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createTransfer(transferIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveTransfer: MRT_TableOptions<Transfer>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateTransfer(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateTransfer(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Transfer>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this transfer?',
            children: (
                <Text>
                    Are you sure you want to delete {row.original.name}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteTransfer(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedTransfers,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingTransfersError
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
        onCreatingRowSave: handleCreateTransfer,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveTransfer,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New Transfer</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Transfer</Title>
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
                    //     //optionally pass in default values for the new row, useful for nested data or other complex scenarios
                    //   }),
                    // );
                }}
            >
                Create New Transfer
            </Button>
        ),
        state: {
            isLoading: isLoadingTransfers,
            isSaving: isCreatingTransfer || isUpdatingTransfer || isDeletingTransfer,
            showAlertBanner: isLoadingTransfersError,
            showProgressBars: isFetchingTransfers,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new transfer to api)
function useCreateTransfer() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (transferIn: TransferIn) => {
            return Promise.resolve(create(transferIn));
        },
        //client side optimistic update
        onMutate: (newTransferInfo: TransferIn) => {
            queryClient.setQueryData(
                ['transfers'],
                (prevTransfers: any) =>
                    [
                        ...prevTransfers,
                        {
                            ...newTransferInfo,
                            // id: newTransferInfo.id,
                        },
                    ] as TransferIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['transfers'] }),
    });
}

//READ hook (get transfers from api)
export function useGetTransfers() {
    console.log("useGetTransfers")
    return useQuery<Array<Transfer>>({
        queryKey: ['transfers'],
        queryFn: async () => {
            return Promise.resolve(getTransfers());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put transfer in api)
function useUpdateTransfer() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (transfer: Transfer) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newTransferInfo: Transfer) => {
            queryClient.setQueryData(
                ['transfers'],
                (prevTransfers: any) =>
                    prevTransfers?.map((prevTransfer: Transfer) =>
                        prevTransfer.id === newTransferInfo.id ? newTransferInfo : prevTransfer,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['transfers'] }), //refetch transfers after mutation, disabled for demo
    });
}

//DELETE hook (delete transfer in api)
function useDeleteTransfer() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (transferId: string) => {
            return Promise.resolve(deleteById(transferId));
        },
        //client side optimistic update
        onMutate: (transferId: string) => {
            queryClient.setQueryData(
                ['transfers'],
                (prevTransfers: any) =>
                    prevTransfers?.filter((transfer: Transfer) => transfer.id !== transferId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['transfers'] }),
    });
}

const queryClient = new QueryClient();

const TransfersWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <HeaderTabs />
            <Transfers />
        </ModalsProvider>
    </QueryClientProvider>
);

export default TransfersWithProviders;

function validateTransfer(transfer: Transfer) {
    return {
        name: !validateRequired(transfer.name)
            ? 'Name is Required'
            : '',
    };
};

function validateTransferIn(transferIn: TransferIn) {
    console.log("validateTransferIn")
    console.log(transferIn)
    return {
        name: !validateRequired(transferIn.name)
            ? 'Name is Required'
            : '',
            departureDateTime: !validateRequiredDate(transferIn.departureDateTime)
            ? 'Departure Date-Time is Required and should be in future'
            : '',
            arrivalDateTime: !validateRequiredDate(transferIn.arrivalDateTime)
            ? 'Arrival Date-Time is Required and should be in future'
            : '',
    };
};
