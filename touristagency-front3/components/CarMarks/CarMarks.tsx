import {Title, Text, Anchor} from '@mantine/core';
import {Table} from '@mantine/core';
import {Link} from "@nextui-org/react";
import classes from './CarMarks.module.css';
import {
    ApiResponse,
    CarMark,
    CarMarksApi,
    Configuration,
    ConfigurationParameters,
    DeleteCarMarkByIdRequest,
    ListCarMarksRequest
} from "@/generated";
import * as runtime from "@/generated/runtime";
import {useState, useEffect} from 'react'
import { randomUUID } from 'crypto';


let configurationParameters: ConfigurationParameters =
    {basePath: "http://127.0.0.1:8080/v1"};
let configuration = new Configuration(configurationParameters);
let carMarksApi = new CarMarksApi(configuration);

const elements = [
    {position: 6, mass: 12.011, symbol: 'C', name: 'Carbon'},
    {position: 7, mass: 14.007, symbol: 'N', name: 'Nitrogen'},
    {position: 39, mass: 88.906, symbol: 'Y', name: 'Yttrium'},
    {position: 56, mass: 137.33, symbol: 'Ba', name: 'Barium'},
    {position: 58, mass: 140.12, symbol: 'Ce', name: 'Cerium'},
];

export function CarMarks() {
    const rows = elements.map((element) => (
        <Table.Tr key={element.name}>
            <Table.Td>{element.position}</Table.Td>
            <Table.Td>{element.name}</Table.Td>
            <Table.Td>{element.symbol}</Table.Td>
            <Table.Td>{element.mass}</Table.Td>
        </Table.Tr>
    ));

    return (
        <>
            <Link color="foreground" href="/CarMarks2" aria-current="carMarks2">
                Бронирования
            </Link>
            <CarMarksList></CarMarksList>

            {/*<div>*/}
            {/*    {data &&*/}
            {/*        data.map((carMark: CarMark) => (*/}
            {/*            <CarMark carMark={carMark} key={carMark.id} />*/}
            {/*        ))}*/}
            {/*</div>*/}
        </>
    );
}

// export async function getStaticProps() {
//     // const searchResults = await getProducts('coffee')
//
//     let configurationParameters: ConfigurationParameters =
//         {basePath: "http://127.0.0.1:8080/v1"};
//     let configuration = new Configuration(configurationParameters);
// // const dataFromDB: Promise<runtime.ApiResponse<Array<CarMark>>> =
//     let carMarksApi = new CarMarksApi(configuration);
//
//     let listCarMarksRequest: ListCarMarksRequest = {limit: 100}
//     const res =
//         await carMarksApi.listCarMarksRaw(listCarMarksRequest);
//
//
//     // await promise.then((r: ApiResponse<Array<CarMark>>) => r.value());
//
//     // let results: ApiResponse<Array<CarMark>> = await promise;
//     return {
//         props: {
//             data: res,
//         },
//     }
// }

// let deleteCarMarkByIdRequest: DeleteCarMarkByIdRequest = {id: randomUUID()}
// let deletedPromise: Promise<void> = carMarksApi.deleteCarMarkById(deleteCarMarkByIdRequest);


function CarMarksList() {
    const [data, setData] = useState<Array<CarMark>>([])
    const [isLoading, setLoading] = useState(true)

    useEffect(() => {
        let listCarMarksRequest: ListCarMarksRequest = {limit: 100}
        let carMarks: Promise<Array<CarMark>> = carMarksApi.listCarMarks(listCarMarksRequest);

        carMarks.then((data) => {
            setData(data)
            setLoading(false)
        })
        ;
    }, [])


    if (isLoading) return <p>Loading...</p>
    if (data.length == 0) return <p>No car marks data</p>

    const rows = data.map((element, index) => (
        <Table.Tr key={element.id}>
            <Table.Td>{element.id}</Table.Td>
            <Table.Td>{element.name}</Table.Td>
        </Table.Tr>
    ));

    return (
        <Table striped highlightOnHover stickyHeader stickyHeaderOffset={60}>
            <Table.Thead>
                <Table.Tr>
                    <Table.Th>DB ID</Table.Th>
                    <Table.Th>Name</Table.Th>
                </Table.Tr>
            </Table.Thead>
            <Table.Tbody>{rows}</Table.Tbody>
            <Table.Caption>Scroll page to see sticky thead</Table.Caption>
        </Table>
    )
}