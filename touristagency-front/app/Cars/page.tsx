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
} from '@mantine/core';
import { ModalsProvider, modals } from '@mantine/modals';
import { IconEdit, IconTrash } from '@tabler/icons-react';
import {
    QueryClient,
    QueryClientProvider,
    UseMutationResult,
    useMutation,
    useQuery,
    useQueryClient,
} from '@tanstack/react-query';
import { CreateCarRequest, ListCarsRequest } from '../../generated/apis/CarsApi'

import {
    CarColor,
    Car,
    CarIn,
    CarMark,
    CarsApi,
    CarModel,
    Configuration,
    ConfigurationParameters,
    DeleteCarByIdRequest,
} from "@/generated";
import { Select } from '@mantine/core';
import { getCars } from './fetchCars';
import { getCarMarks } from '../CarMarks/fetchMarks';
import { getCarModels } from '../CarModels/fetchModels';
import { UUID } from 'crypto';
import { validateRequired } from '../Validators/validation';

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carsApi = new CarsApi(configuration);

const Cars = () => {

    const [validationErrors, setValidationErrors] = useState<
        Record<string, string | undefined>
    >({});

    const columns = useMemo<MRT_ColumnDef<Car>[]>(
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
                accessorKey: 'mark',
                header: 'Mark',
                Edit: ({ cell, column, row, table }) => {
                    interface Item {
                        value: string; 
                        label: string; 
                    }

                    const [data, setData] = useState<Array<Item>>([])
                    const [isLoading, setLoading] = useState(true)
                    const [selectedId, setSelectedId] = useState<UUID>()

                    useEffect(() => {
                        getCarMarks()
                        .then((response: Array<CarMark>) => {
                            return response.map( (carMark: CarMark) => {
                                const items = {
                                    value: carMark.id,
                                    label: carMark.name
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
                        if (isCreatingCar) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingCar) {
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

                    return <Select onChange={onChange} onBlur={onBlur} //ref={ref}
                        label="Car mark"
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
                accessorKey: 'model',
                header: 'Model',
                Edit: ({ cell, column, row, table }) => {
                    interface Item {
                        value: string; 
                        label: string; 
                    }

                    const [data, setData] = useState<Array<Item>>([])
                    const [isLoading, setLoading] = useState(true)
                    const [selectedId, setSelectedId] = useState<UUID>()

                    useEffect(() => {
                        getCarModels()
                        .then((response: Array<CarModel>) => {
                            return response.map( (carModel: CarModel) => {
                                const items = {
                                    value: carModel.id,
                                    label: carModel.name
                                }
                                return items;
                            });
                        })
                        .then((data) => {
                            setData(data)
                            setLoading(false)
                        })
                      }, [])

                    //HTMLInputElement type, but event.target??
                    const onBlur = (event) => {
                        console.log("onBlur event type: " + typeof event);
                        console.log("onBlur event: " + event);
                    //   row._valuesCache[column.id] = event.target.value;
                        row._valuesCache[column.id] = selectedId
                        if (isCreatingCar) {
                            table.setCreatingRow(row);
                        } else if (isUpdatingCar) {
                            table.setEditingRow(row);
                        }
                    };

                    if (isLoading) return <p>Loading...</p>
                    if (!data) return <p>No car models data</p>
                
                    const onChange = (event) => {
                        console.log("handleChange");
                        console.log(event);
                        setSelectedId(event);
                    }

                    return <Select onChange={onChange} onBlur={onBlur}
                        label="Car model"
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
                accessorKey: 'plateNumber',
                header: 'Plate Number',
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
                accessorKey: 'color',
                header: 'Color',
                Edit: ({ cell, column, row, table }) => {
                    const data: Array<string> = Object.values(CarColor);
                    const onBlur = (event) => {
                        console.log("onBlur event type: " + typeof event);
                        console.log("onBlur event: " + event);
                      row._valuesCache[column.id] = event.target.value;
                      if (isCreatingCar) {
                        console.log("isCreatingCar row type: " + typeof row);
                        console.log("isCreatingCar row: " + row);
                        table.setCreatingRow(row);
                      } else if (isUpdatingCar) {
                        table.setEditingRow(row);
                      }
                    };
                    return <Select onBlur = {onBlur}
                        label="Car color"
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
        ],
        [validationErrors],
    );

    //call CREATE hook
    const { mutateAsync: createCar, isLoading: isCreatingCar } =
        useCreateCar();
    //call READ hook
    const {
        data: fetchedCars = [],
        isError: isLoadingCarsError,
        isFetching: isFetchingCars,
        isLoading: isLoadingCars,
    } = useGetCars();

    //call UPDATE hook
    const { mutateAsync: updateCar, isLoading: isUpdatingCar } =
        useUpdateCar();
    //call DELETE hook
    const { mutateAsync: deleteCar, isLoading: isDeletingCar } =
        useDeleteCar();

    //CREATE action
    const handleCreateCar: MRT_TableOptions<Car>['onCreatingRowSave'] = async ({
                                                                                     values,
                                                                                     exitCreatingMode,
                                                                        }) => {     
        // fetchCarMarks();
        console.log("handleCreateCar");
        console.log(typeof values);
        console.log(values);

        let carIn: CarIn = {
            color : values.color,
            markId : values.mark,
            modelId : values.model,
            plateNumber : values.plateNumber,
        }

        const newValidationErrors = validateCarIn(carIn);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await createCar(carIn);
        exitCreatingMode();
    };

    //UPDATE action
    const handleSaveCar: MRT_TableOptions<Car>['onEditingRowSave'] = async ({
                                                                                  values,
                                                                                  table,
                                                                              }) => {
        const newValidationErrors = validateCar(values);
        if (Object.values(newValidationErrors).some((error) => error)) {
            setValidationErrors(newValidationErrors);
            return;
        }
        setValidationErrors({});
        await updateCar(values);
        table.setEditingRow(null); //exit editing mode
    };

    //DELETE action
    const openDeleteConfirmModal = (row: MRT_Row<Car>) =>
        modals.openConfirmModal({
            title: 'Are you sure you want to delete this car mark?',
            children: (
                <Text>
                    Are you sure you want to delete {row.original.mark}{' '}
                    {row.original.model}{' '}{row.original.plateNumber}? 
                    This action cannot be undone.
                </Text>
            ),
            labels: { confirm: 'Delete', cancel: 'Cancel' },
            confirmProps: { color: 'red' },
            onConfirm: () => deleteCar(row.original.id),
        });

    const table = useMantineReactTable({
        columns,
        data: fetchedCars,
        createDisplayMode: 'modal', //default ('row', and 'custom' are also available)
        editDisplayMode: 'modal', //default ('row', 'cell', 'table', and 'custom' are also available)
        enableEditing: true,
        getRowId: (row) => row.id,
        mantineEditTextInputProps: ({ cell }) => ({
            onBlur: (event) => {
              console.info(event, cell.id);
            },
          }),
        mantineToolbarAlertBannerProps: isLoadingCarsError
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
        onCreatingRowSave: handleCreateCar,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleSaveCar,
        renderCreateRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Создать новую машину</Title>
                {internalEditComponents} 
                {/* {
                    <>
                    test
                    </>
                } */}
                <Flex justify="flex-end" mt="xl">
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </Flex>
            </Stack>
        ),
        renderEditRowModalContent: ({ table, row, internalEditComponents }) => (
            <Stack>
                <Title order={3}>Edit Car</Title>
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
                Создать новую машину
            </Button>
        ),
        state: {
            isLoading: isLoadingCars,
            isSaving: isCreatingCar || isUpdatingCar || isDeletingCar,
            showAlertBanner: isLoadingCarsError,
            showProgressBars: isFetchingCars,
        },
    });

    return <MantineReactTable table={table} />;
};

//CREATE hook (post new car mark to api)
function useCreateCar(): UseMutationResult<Car, Error, CarIn, void> {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carIn: CarIn) => {
            console.log("useCreateCar");
            console.log("carIn: " + carIn);
            let createCarRequest: CreateCarRequest = {carIn: carIn}
            let createdCar: Promise<Car> = carsApi.createCar(createCarRequest);
            return Promise.resolve(createdCar);
        },
        //client side optimistic update
        onMutate: (newCarInfo: CarIn) => {
            console.log("useCreateCar onMutate");
            console.log("onMutate newCarInfo: " + newCarInfo);
            queryClient.setQueryData(
                ['cars'],
                (prevCars: any) =>
                    [
                        ...prevCars,
                        {
                            ...newCarInfo,
                            id: (Math.random() + 1).toString(36).substring(7),
                        },
                    ] as Car[],
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['cars'] }), //refetch car marks after mutation, disabled for demo
    });
}

//READ hook (get car marks from api)
function useGetCars() {
    return useQuery<Car[]>({
        queryKey: ['cars'],
        queryFn: async () => {
            return Promise.resolve(getCars());
        },
        refetchOnWindowFocus: false,
    });
}

//UPDATE hook (put car mark in api)
function useUpdateCar() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (car: Car) => {
            //send api update request here
            await new Promise((resolve) => setTimeout(resolve, 1000)); //fake api call
            return Promise.resolve();
        },
        //client side optimistic update
        onMutate: (newCarInfo: Car) => {
            queryClient.setQueryData(
                ['cars'],
                (prevCars: any) =>
                    prevCars?.map((prevCar: Car) =>
                        prevCar.id === newCarInfo.id ? newCarInfo : prevCar,
                    ),
            );
        },
        // onSettled: () => queryClient.invalidateQueries({ queryKey: ['cars'] }), //refetch car marks after mutation, disabled for demo
    });
}

//DELETE hook (delete car mark in api)
function useDeleteCar() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (carId: string) => {
            let deleteCarByIdRequest: DeleteCarByIdRequest = {id: carId}
            let deletePromise: Promise<void> = carsApi.deleteCarById(deleteCarByIdRequest);

            return Promise.resolve(deletePromise);
        },
        //client side optimistic update
        onMutate: (carId: string) => {
            queryClient.setQueryData(
                ['cars'],
                (prevCars: any) =>
                    prevCars?.filter((car: Car) => car.id !== carId),
            );
        },
        onSettled: () => queryClient.invalidateQueries({ queryKey: ['cars'] }), //refetch car marks after mutation, disabled for demo
    });
}

const queryClient = new QueryClient();

const CarsWithProviders = () => (
    //Put this with your other react-query providers near root of your app
    <QueryClientProvider client={queryClient}>
        <ModalsProvider>
            <Cars />
        </ModalsProvider>
    </QueryClientProvider>
);

export default CarsWithProviders;

function validateCar(car: Car) {
    return {
        mark: !validateRequired(car.mark)
            ? 'Mark is Required'
            : '',
    };
}

function validateCarIn(carIn: CarIn) {
    console.log("validateCarIn carIn: " + carIn);
    return {
        mark: !validateRequired(carIn.markId)
            ? 'Mark id is Required'
            : '',
        model: !validateRequired(carIn.modelId)
            ? 'Model id is Required'
            : '',
        plateNumber: !validateRequired(carIn.plateNumber)
            ? 'Plate number value is Required'
            : '',
        color: !validateRequired(carIn.color)
            ? 'Color value is Required'
            : '',
    };
}
