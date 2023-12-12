import {
    Transfer,
    TransfersApi,
    Configuration,
    ListTransfersRequest,
    ConfigurationParameters,
    DeleteTransferByIdRequest,
    CreateTransferRequest,
    TransferIn,
} from "@/generated";

let configurationParameters: ConfigurationParameters =
{basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let transfersApi = new TransfersApi(configuration);

export function create(transferIn: TransferIn): Promise<Transfer> {
    let createTransferRequest: CreateTransferRequest = {transferIn: transferIn}
    let createdTransfer: Promise<Transfer> = transfersApi.createTransfer(createTransferRequest);
    return createdTransfer;
}

export function getTransfers(): Promise<Array<Transfer>> {
    let listTransfersRequest: ListTransfersRequest = {limit: 100}
    let transfers: Promise<Array<Transfer>> = transfersApi.listTransfers(listTransfersRequest);
    return transfers;
}

export function deleteById(transferId: String): Promise<void> {
    let deleteTransferByIdRequest: DeleteTransferByIdRequest = {id: transferId}
    let deletePromise: Promise<void> = transfersApi.deleteTransferById(deleteTransferByIdRequest);
    return deletePromise;
}