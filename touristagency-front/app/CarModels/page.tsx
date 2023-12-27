"use client"

import { useMemo, useState } from 'react';
import {
    MRT_EditActionButtons,
    MantineReactTable,
    // createRow,
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
    CarModel,
    CarModelIn,
    CarModelsApi,
    Configuration,
    ListCarModelsRequest,
    CreateCarModelRequest,
    ConfigurationParameters,
    DeleteCarModelByIdRequest,
} from "@/generated";
import { validateRequired } from '../Validators/validation';
import HeaderTabs from '../Menu/Menu';

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carModelsApi = new CarModelsApi(configuration);

const CarModels = () => {
    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<CarModel>[]>(
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
                    //remove any previous validation errors when car model focuses on the input
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
    const { mutateAsync: createCarModel, isLoading: isCreatingCarModel } =
        useCreateCarModel();
    //call READ hook
    const {
        data: fetchedCarModels = [],
        isError: isLoadingCarModelsError,
        isFetching: isFetchingCarModels,
        isLoading: isLoadingCarModels,
    } = useGetCarModels();
    //call UPDATE hook
    const { mutateAsync: updateCarModel, isLoading: isUpdatingCarModel } =
        useUpdateCarModel();
    //call DELETE hook
    const { mutateAsync: deleteCarModel, isLoading: isDeletingCarModel } =
        useDeleteCarModel();

    //CREATE action
    const handleCreateCarModel: MRT_TableOptions<CarModel>['onCreatingRowSave'] = async ({
                                                                                     values,
                                                                                     exitCreatingMode,
                                                                                 }) => {
        const newValidationErrors = validateCarModel(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createCarModel(values);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveCarModel: MRT_TableOptions<CarModel>['onEditingRowSave'] = async ({
                                                                                  values,
                                                                                  table,
                                                                              }) => {
        const newValidationErrors = validateCarModel(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateCarModel(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<CarModel>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this car model?',
            children: (
                <Text>
                    Are you sure you want to delete {row.original.name}
                    ? This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteCarModel(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedCarModels,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineToolbarAlertBannerProps: isLoadingCarModelsError
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
        onCreatingRowSave: handleCreateCarModel,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveCarModel,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Создать новую модель машины</Title>
                {internalEditComponents}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit CarModel</Title>
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
                Создать новую модель машины
            </Button>
        ),
        state: {
            isLoading: isLoadingCarModels,
            isSaving: isCreatingCarModel || isUpdatingCarModel || isDeletingCarModel,
            showAlertBanner: isLoadingCarModelsError,
            showProgressBars: isFetchingCarModels,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new car model to api)
function useCreateCarModel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carModel: CarModel) => {
            let carModelIn: CarModelIn = {name: carModel.name}
            let createCarModelRequest: CreateCarModelRequest = {carModelIn: carModelIn}
            let createdCarModel: Promise<CarModel> = carModelsApi.createCarModel(createCarModelRequest);
            return Promise.resolve(createdCarModel);
        },
        //client side optimistic update
        onMutate: (newCarModelInfo: CarModel) => {
            queryClient.setQueryData(
                ['carmodels'],
                (prevCarModels: any) =>
                    [
                        ...prevCarModels,
                        {
                            ...newCarModelInfo,
                            id: newCarModelInfo.id,
                        },
                    ] as CarModel[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmodels'] }),
    });
}

//READ hook (get car models from api)
export function useGetCarModels() {
    console.log("useGetCarModels")
    return useQuery<CarModel[]>({
        queryKey: ['carmodels'],
        queryFn: async () => {
            let listCarModelsRequest: ListCarModelsRequest = {limit: 100}
            let carModels: Promise<Array<CarModel>> = carModelsApi.listCarModels(listCarModelsRequest);
            console.log("useGetCarModels2");
            return Promise.resolve(carModels);
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put car model in api)
function useUpdateCarModel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carModel: CarModel) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newCarModelInfo: CarModel) => {
            queryClient.setQueryData(
                ['carmodels'],
                (prevCarModels: any) =>
                    prevCarModels?.map((prevCarModel: CarModel) =>
                        prevCarModel.id === newCarModelInfo.id ? newCarModelInfo : prevCarModel,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmodels'] }), //refetch car models after mutation, disabled for demo
    });
}

//DELETE hook (delete car model in api)
function useDeleteCarModel() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carModelId: string) => {
            let deleteCarModelByIdRequest: DeleteCarModelByIdRequest = {id: carModelId}
            let deletePromise: Promise<void> = carModelsApi.deleteCarModelById(deleteCarModelByIdRequest);

            return Promise.resolve(deletePromise);
        },
        //client side optimistic update
        onMutate: (carModelId: string) => {
            queryClient.setQueryData(
                ['carmodels'],
                (prevCarModels: any) =>
                    prevCarModels?.filter((carModel: CarModel) => carModel.id !== carModelId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['carmodels'] }),
    });
}

const queryClient = new QueryClient();

const CarModelsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <HeaderTabs />
            <CarModels />
        </ModalsProvider>
    </QueryClientProvider>
);

export default CarModelsWithProviders;

function validateCarModel(carModel: CarModel) {
    return {
        name: !validateRequired(carModel.name)
            ? 'Name is Required'
            : '',
    };
}
