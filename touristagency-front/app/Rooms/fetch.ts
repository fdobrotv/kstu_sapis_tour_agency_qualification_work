import {
    Room,
    RoomsApi,
    Configuration,
    ListRoomsRequest,
    ConfigurationParameters,
    DeleteRoomByIdRequest,
    CreateRoomRequest,
    RoomIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let roomsApi = new RoomsApi(configuration);

export function create(roomIn: RoomIn): Promise<Room> {
    let createRoomRequest: CreateRoomRequest = {roomIn: roomIn}
    let createdRoom: Promise<Room> = roomsApi.createRoom(createRoomRequest);
    return createdRoom;
}

export function getRooms(): Promise<Array<Room>> {
    let listRoomsRequest: ListRoomsRequest = {limit: 100}
    let rooms: Promise<Array<Room>> = roomsApi.listRooms(listRoomsRequest);
    return rooms;
}

export function deleteById(roomId: String): Promise<void> {
    let deleteRoomByIdRequest: DeleteRoomByIdRequest = {id: roomId}
    let deletePromise: Promise<void> = roomsApi.deleteRoomById(deleteRoomByIdRequest);
    return deletePromise;
}