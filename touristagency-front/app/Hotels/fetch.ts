import {
    Hotel,
    HotelsApi,
    Configuration,
    ListHotelsRequest,
    ConfigurationParameters,
    DeleteHotelByIdRequest,
    CreateHotelRequest,
    HotelIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let hotelsApi = new HotelsApi(configuration);

export function create(hotelIn: HotelIn): Promise<Hotel> {
    let createHotelRequest: CreateHotelRequest = {hotelIn: hotelIn}
    let createdHotel: Promise<Hotel> = hotelsApi.createHotel(createHotelRequest);
    return createdHotel;
}

export function getHotels(): Promise<Array<Hotel>> {
    let listHotelsRequest: ListHotelsRequest = {limit: 100}
    let hotels: Promise<Array<Hotel>> = hotelsApi.listHotels(listHotelsRequest);
    return hotels;
}

export function deleteById(hotelId: String): Promise<void> {
    let deleteHotelByIdRequest: DeleteHotelByIdRequest = {id: hotelId}
    let deletePromise: Promise<void> = hotelsApi.deleteHotelById(deleteHotelByIdRequest);
    return deletePromise;
}