import {
    CarModel,
    CarModelIn,
    CarModelsApi,
    Configuration,
    ListCarMarksRequest,
    ConfigurationParameters,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carModelsApi = new CarModelsApi(configuration);
let listCarModelsRequest: ListCarMarksRequest = {limit: 100}
        
export function getCarModels(): Promise<Array<CarModel>> {
    let carModels: Promise<Array<CarModel>> = carModelsApi.listCarModels(listCarModelsRequest);
    return carModels;
}