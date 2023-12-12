import {
    FoodOption,
    FoodOptionsApi,
    Configuration,
    ListFoodOptionsRequest,
    ConfigurationParameters,
    DeleteFoodOptionByIdRequest,
    CreateFoodOptionRequest,
    FoodOptionIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let foodOptionsApi = new FoodOptionsApi(configuration);

export function create(foodOptionIn: FoodOptionIn): Promise<FoodOption> {
    let createFoodOptionRequest: CreateFoodOptionRequest = {foodOptionIn: foodOptionIn}
    let createdFoodOption: Promise<FoodOption> = foodOptionsApi.createFoodOption(createFoodOptionRequest);
    return createdFoodOption;
}

export function getFoodOptions(): Promise<Array<FoodOption>> {
    let listFoodOptionsRequest: ListFoodOptionsRequest = {limit: 100}
    let foodOptions: Promise<Array<FoodOption>> = foodOptionsApi.listFoodOptions(listFoodOptionsRequest);
    return foodOptions;
}

export function deleteById(foodOptionId: String): Promise<void> {
    let deleteFoodOptionByIdRequest: DeleteFoodOptionByIdRequest = {id: foodOptionId}
    let deletePromise: Promise<void> = foodOptionsApi.deleteFoodOptionById(deleteFoodOptionByIdRequest);
    return deletePromise;
}