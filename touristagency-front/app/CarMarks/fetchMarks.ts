import {
    CarMark,
    CarMarkIn,
    CarMarksApi,
    Configuration,
    ListCarMarksRequest,
    CreateCarMarkRequest,
    ConfigurationParameters,
    DeleteCarMarkByIdRequest,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carMarksApi = new CarMarksApi(configuration);

export function getCarMarks(): Promise<Array<CarMark>> {
    let listCarMarksRequest: ListCarMarksRequest = {limit: 100}
    let carMarks: Promise<Array<CarMark>> = carMarksApi.listCarMarks(listCarMarksRequest);
    return carMarks;
}

export function createCarMark(carMark: CarMarkIn): Promise<CarMark> {
    let carMarkIn: CarMarkIn = {name: carMark.name}
    let createCarMarkRequest: CreateCarMarkRequest = {carMarkIn: carMarkIn}
    let createdCarMark: Promise<CarMark> = carMarksApi.createCarMark(createCarMarkRequest);
    return createdCarMark;
}

export function deleteCarMark(carMarkId: string): Promise<void> {
    let deleteCarMarkByIdRequest: DeleteCarMarkByIdRequest = {id: carMarkId}
    let deletePromise: Promise<void> = carMarksApi.deleteCarMarkById(deleteCarMarkByIdRequest);
    return deletePromise;
}



