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
    FoodOption,
    FoodOptionIn,
} from "@/generated";

import { create, deleteById, getFoodOptions } from './fetch';
import { validateRequired, validateRequiredNumber } from '../Validators/validation';

const FoodOptions = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<FoodOption>[]>(
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
                    //remove any previous validation errors when foodOption focuses on the input
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
        ],
        [validationErrors],
    );

    //call CREATE hook
    const { mutateAsync: createFoodOption, isLoading: isCreatingFoodOption } =
        useCreateFoodOption();
    //call READ hook
    const {
        data: fetchedFoodOptions = [],
        isError: isLoadingFoodOptionsError,
        isFetching: isFetchingFoodOptions,
        isLoading: isLoadingFoodOptions,
    } = useGetFoodOptions();
    //call UPDATE hook
    const { mutateAsync: updateFoodOption, isLoading: isUpdatingFoodOption } =
        useUpdateFoodOption();
    //call DELETE hook
    const { mutateAsync: deleteFoodOption, isLoading: isDeletingFoodOption } =
        useDeleteFoodOption();

    //CREATE action
    const handleCreateFoodOption: MRT_TableOptions<FoodOption>['onCreatingRowSave'] = async ({
            values,
            exitCreatingMode,
        }) => {
        let foodOptionIn: FoodOptionIn = {
            name : values.name,
            price : values.price,
        }
        console.log("handleCreateFoodOption");
        console.log(foodOptionIn);
        const newValidationErrors = validateFoodOptionIn(foodOptionIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createFoodOption(foodOptionIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveFoodOption: MRT_TableOptions<FoodOption>['onEditingRowSave'] = async ({
            values,
            table,
        }) => {
        const newValidationErrors = validateFoodOption(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateFoodOption(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<FoodOption>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this foodOption?',
            children: (
                <Text>
                    Are you sure you want to delete foodOption 
                    {row.original.name} {' '} {row.original.price}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteFoodOption(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedFoodOptions,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingFoodOptionsError
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
        onCreatingRowSave: handleCreateFoodOption,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveFoodOption,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Create New FoodOption</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit FoodOption</Title>
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
                Create New FoodOption
            </Button>
        ),
        state: {
            isLoading: isLoadingFoodOptions,
            isSaving: isCreatingFoodOption || isUpdatingFoodOption || isDeletingFoodOption,
            showAlertBanner: isLoadingFoodOptionsError,
            showProgressBars: isFetchingFoodOptions,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new foodOption to api)
function useCreateFoodOption() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (foodOptionIn: FoodOptionIn) => {
            return Promise.resolve(create(foodOptionIn));
        },
        //client side optimistic update
        onMutate: (newFoodOptionInfo: FoodOptionIn) => {
            queryClient.setQueryData(
                ['foodOptions'],
                (prevFoodOptions: any) =>
                    [
                        ...prevFoodOptions,
                        {
                            ...newFoodOptionInfo,
                            // id: newFoodOptionInfo.id,
                        },
                    ] as FoodOptionIn[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['foodOptions'] }),
    });
}

//READ hook (get foodOptions from api)
export function useGetFoodOptions() {
    console.log("useGetFoodOptions")
    return useQuery<Array<FoodOption>>({
        queryKey: ['foodOptions'],
        queryFn: async () => {
            return Promise.resolve(getFoodOptions());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put foodOption in api)
function useUpdateFoodOption() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (foodOption: FoodOption) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newFoodOptionInfo: FoodOption) => {
            queryClient.setQueryData(
                ['foodOptions'],
                (prevFoodOptions: any) =>
                    prevFoodOptions?.map((prevFoodOption: FoodOption) =>
                        prevFoodOption.id === newFoodOptionInfo.id ? newFoodOptionInfo : prevFoodOption,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['foodOptions'] }), //refetch foodOptions after mutation, disabled for demo
    });
}

//DELETE hook (delete foodOption in api)
function useDeleteFoodOption() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (foodOptionId: string) => {
            return Promise.resolve(deleteById(foodOptionId));
        },
        //client side optimistic update
        onMutate: (foodOptionId: string) => {
            queryClient.setQueryData(
                ['foodOptions'],
                (prevFoodOptions: any) =>
                    prevFoodOptions?.filter((foodOption: FoodOption) => foodOption.id !== foodOptionId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['foodOptions'] }),
    });
}

const queryClient = new QueryClient();

const FoodOptionsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <FoodOptions />
        </ModalsProvider>
    </QueryClientProvider>
);

export default FoodOptionsWithProviders;

function validateFoodOption(foodOption: FoodOption) {
    return {
        name: !validateRequired(foodOption.name)
            ? 'FoodOption name is Required'
            : '',
            price: !validateRequiredNumber(foodOption.price)
            ? 'Price is Required'
            : '',
    };
};

function validateFoodOptionIn(foodOptionIn: FoodOptionIn) {
    console.log("validateFoodOptionIn")
    console.log(foodOptionIn)
    return {
        name: !validateRequired(foodOptionIn.name)
            ? 'FoodOption name is Required'
            : '',
            price: !validateRequiredNumber(foodOptionIn.price)
            ? 'Price is Required'
            : '',
    };
};
