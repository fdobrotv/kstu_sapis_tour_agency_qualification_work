import {
    Flight,
    FlightsApi,
    Configuration,
    ListFlightsRequest,
    ConfigurationParameters,
    DeleteFlightByIdRequest,
    CreateFlightRequest,
    FlightIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let flightsApi = new FlightsApi(configuration);

export function create(flightIn: FlightIn): Promise<Flight> {
    let createFlightRequest: CreateFlightRequest = {flightIn: flightIn}
    let createdFlight: Promise<Flight> = flightsApi.createFlight(createFlightRequest);
    return createdFlight;
}

export function getFlights(): Promise<Array<Flight>> {
    let listFlightsRequest: ListFlightsRequest = {limit: 100}
    let flights: Promise<Array<Flight>> = flightsApi.listFlights(listFlightsRequest);
    return flights;
}

export function deleteById(flightId: String): Promise<void> {
    let deleteFlightByIdRequest: DeleteFlightByIdRequest = {id: flightId}
    let deletePromise: Promise<void> = flightsApi.deleteFlightById(deleteFlightByIdRequest);
    return deletePromise;
}