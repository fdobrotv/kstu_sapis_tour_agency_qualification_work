import React from "react";
// import { Table } from '@mantine/core';
import { Navbar } from "flowbite-react";

// let elements = [{
//     name: "name",
//     position: "position",
//     symbol: "symbol",
//     mass: "mass",
// },];
// const rows = elements.map((element) => (
//     <Table.Tr key={element.name}>
//         <Table.Td>{element.position}</Table.Td>
//         <Table.Td>{element.name}</Table.Td>
//         <Table.Td>{element.symbol}</Table.Td>
//         <Table.Td>{element.mass}</Table.Td>
//     </Table.Tr>
// ));
export default function App() {
    return (
        <div className="relative overflow-x-auto">
            <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" className="px-6 py-3">
                        Product name
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Color
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Category
                    </th>
                    <th scope="col" className="px-6 py-3">
                        Price
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        Apple MacBook Pro 17"
                    </th>
                    <td className="px-6 py-4">
                        Silver
                    </td>
                    <td className="px-6 py-4">
                        Laptop
                    </td>
                    <td className="px-6 py-4">
                        $2999
                    </td>
                </tr>
                <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        Microsoft Surface Pro
                    </th>
                    <td className="px-6 py-4">
                        White
                    </td>
                    <td className="px-6 py-4">
                        Laptop PC
                    </td>
                    <td className="px-6 py-4">
                        $1999
                    </td>
                </tr>
                <tr className="bg-white dark:bg-gray-800">
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        Magic Mouse 2
                    </th>
                    <td className="px-6 py-4">
                        Black
                    </td>
                    <td class="px-6 py-4">
                        Accessories
                    </td>
                    <td class="px-6 py-4">
                        $99
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        // <Table>
        //     <Table.Thead>
        //         <Table.Tr>
        //             <Table.Th>Element position</Table.Th>
        //             <Table.Th>Element name</Table.Th>
        //             <Table.Th>Symbol</Table.Th>
        //             <Table.Th>Atomic mass</Table.Th>
        //         </Table.Tr>
        //     </Table.Thead>
        //     <Table.Tbody>{rows}</Table.Tbody>
        // </Table>
    );
}
