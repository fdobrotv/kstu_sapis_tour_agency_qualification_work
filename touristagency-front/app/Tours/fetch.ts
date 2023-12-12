import {
    Tour,
    ToursApi,
    Configuration,
    ListToursRequest,
    ConfigurationParameters,
    DeleteTourByIdRequest,
    CreateTourRequest,
    TourIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let toursApi = new ToursApi(configuration);

export function create(tourIn: TourIn): Promise<Tour> {
    let createTourRequest: CreateTourRequest = {tourIn: tourIn}
    let createdTour: Promise<Tour> = toursApi.createTour(createTourRequest);
    return createdTour;
}

export function getTours(): Promise<Array<Tour>> {
    let listToursRequest: ListToursRequest = {limit: 100}
    let tours: Promise<Array<Tour>> = toursApi.listTours(listToursRequest);
    return tours;
}

export function deleteById(tourId: String): Promise<void> {
    let deleteTourByIdRequest: DeleteTourByIdRequest = {id: tourId}
    let deletePromise: Promise<void> = toursApi.deleteTourById(deleteTourByIdRequest);
    return deletePromise;
}