import { CarModel } from "@/generated";
import {v4 as uuidv4} from 'uuid';

export const fakeData: CarModel[] = [
    {
        id: uuidv4(),
        name: 'Traveller',
    },
    {
        id: uuidv4(),
        name: 'Duster',
    },
    {
        id: uuidv4(),
        name: 'X7',
    },
    {
        id: uuidv4(),
        name: 'Caravella',
    },
    {
        id: uuidv4(),
        name: 'Transporter',
    },
];
