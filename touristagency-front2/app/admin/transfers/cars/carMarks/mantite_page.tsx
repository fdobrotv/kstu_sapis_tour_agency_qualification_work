import React from "react";
import { Table } from '@mantine/core';

let elements = [{
    name: "name",
    position: "position",
    symbol: "symbol",
    mass: "mass",
},];
const rows = elements.map((element) => (
    <Table.Tr key={element.name}>
        <Table.Td>{element.position}</Table.Td>
        <Table.Td>{element.name}</Table.Td>
        <Table.Td>{element.symbol}</Table.Td>
        <Table.Td>{element.mass}</Table.Td>
    </Table.Tr>
));
export default function App() {
    return (
        <Table>
            <Table.Thead>
                <Table.Tr>
                    <Table.Th>Element position</Table.Th>
                    <Table.Th>Element name</Table.Th>
                    <Table.Th>Symbol</Table.Th>
                    <Table.Th>Atomic mass</Table.Th>
                </Table.Tr>
            </Table.Thead>
            <Table.Tbody>{rows}</Table.Tbody>
        </Table>
    );
}
