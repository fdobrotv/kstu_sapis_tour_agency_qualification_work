import {
    Car,
    CarsApi,
    Configuration,
    ListCarsRequest,
    ConfigurationParameters,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carsApi = new CarsApi(configuration);

export function getCars(): Promise<Array<Car>> {
    let listCarsRequest: ListCarsRequest = {limit: 100}
    let cars: Promise<Array<Car>> = carsApi.listCars(listCarsRequest);
    return cars;
}