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
    CarMark,
} from "@/generated";

import {getCarMarks, createCarMark, deleteCarMark} from "./fetchMarks";
import { validateRequired } from '../Validators/validation';
import HeaderTabs from '../Menu/Menu';

const CarMarks = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<CarMark>[]>(
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
                header: 'Наименование',
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
    const { mutateAsync: createCarMark, isLoading: isCreatingCarMark } =
        useCreateCarMark();
    //call READ hook
    const {
        data: fetchedCarMarks = [],
        isError: isLoadingCarMarksError,
        isFetching: isFetchingCarMarks,
        isLoading: isLoadingCarMarks,
    } = useGetCarMarks();
    //call UPDATE hook
    const { mutateAsync: updateCarMark, isLoading: isUpdatingCarMark } =
        useUpdateCarMark();
    //call DELETE hook
    const { mutateAsync: deleteCarMark, isLoading: isDeletingCarMark } =
        useDeleteCarMark();

    //CREATE action
    const handleCreateCarMark: MRT_TableOptions<CarMark>['onCreatingRowSave'] = async ({
                                                                                     values,
                                                                                     exitCreatingMode,
                                                                                 }) => {
        const newValidationErrors = validateCarMark(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createCarMark(values);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveCarMark: MRT_TableOptions<CarMark>['onEditingRowSave'] = async ({
                                                                                  values,
                                                                                  table,
                                                                              }) => {
        const newValidationErrors = validateCarMark(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateCarMark(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<CarMark>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this car mark?',
            children: (
                <Text>
                    Are you sure you want to delete {row.original.name}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteCarMark(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedCarMarks,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingCarMarksError
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
        onCreatingRowSave: handleCreateCarMark,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveCarMark,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Создать новую марку машины</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit CarMark</Title>
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
                Создать новую марку машины
            </Button>
        ),
        state: {
            isLoading: isLoadingCarMarks,
            isSaving: isCreatingCarMark || isUpdatingCarMark || isDeletingCarMark,
            showAlertBanner: isLoadingCarMarksError,
            showProgressBars: isFetchingCarMarks,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new car mark to api)
function useCreateCarMark() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carMark: CarMark) => {
            return Promise.resolve(createCarMark(carMark));
        },
        //client side optimistic update
        onMutate: (newCarMarkInfo: CarMark) => {
            queryClient.setQueryData(
                ['carmarks'],
                (prevCarMarks: any) =>
                    [
                        ...prevCarMarks,
                        {
                            ...newCarMarkInfo,
                            id: newCarMarkInfo.id,
                        },
                    ] as CarMark[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmarks'] }),
    });
}

//READ hook (get car marks from api)
export function useGetCarMarks() {
    console.log("useGetCarMarks")
    return useQuery<CarMark[]>({
        queryKey: ['carmarks'],
        queryFn: async () => {
            return Promise.resolve(getCarMarks());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put car mark in api)
function useUpdateCarMark() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carMark: CarMark) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newCarMarkInfo: CarMark) => {
            queryClient.setQueryData(
                ['carmarks'],
                (prevCarMarks: any) =>
                    prevCarMarks?.map((prevCarMark: CarMark) =>
                        prevCarMark.id === newCarMarkInfo.id ? newCarMarkInfo : prevCarMark,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmarks'] }), //refetch car marks after mutation, disabled for demo
    });
}

//DELETE hook (delete car mark in api)
function useDeleteCarMark() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carMarkId: string) => {
            return Promise.resolve(deleteCarMark(carMarkId));
        },
        //client side optimistic update
        onMutate: (carMarkId: string) => {
            queryClient.setQueryData(
                ['carmarks'],
                (prevCarMarks: any) =>
                    prevCarMarks?.filter((carMark: CarMark) => carMark.id !== carMarkId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmarks'] }),
    });
}

const queryClient = new QueryClient();

const CarMarksWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <HeaderTabs />
            <CarMarks />
        </ModalsProvider>
    </QueryClientProvider>
);

export default CarMarksWithProviders;

function validateCarMark(carMark: CarMark) {
    return {
        name: !validateRequired(carMark.name)
            ? 'Name is Required'
            : '',
    };
}
