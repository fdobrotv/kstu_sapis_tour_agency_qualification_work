import { Car, CarMark, CarColor } from "@/generated";
import {v4 as uuidv4} from 'uuid';

export var arrayMock: Array<any> = [
    { value: '1', label: 'Peugeot' }, 
    { value: '2', label: 'BMW' }, 
    { value: '3', label: 'Volkswagen' }, 
    { value: '4', label: 'Mercedes' }
]

type LabelValue = {
    label: string;
    value: string;
}

export const fakeCarMarksData: Array<LabelValue> = [
    {
        label: uuidv4(),
        value: 'Peugeot',
    },
    {
        label: uuidv4(),
        value: 'BMW',
    },
]

export const fakeData: Car[] = [
    {
        id: uuidv4(),
        mark: 'Peugeot',
        model: 'Traveller',
        plateNumber: 'T543AE123',
        color: CarColor.Silver
    },
    {
        id: uuidv4(),
        mark: 'Ford',
        model: 'Transit',
        plateNumber: 'T986AE746',
        color: CarColor.Red
    },
    {
        id: uuidv4(),
        mark: 'Huyndai',
        model: 'H1',
        plateNumber: 'T543AE123',
        color: CarColor.Black
    },
    {
        id: uuidv4(),
        mark: 'BMW',
        model: 'X7',
        plateNumber: 'T543AE123',
        color: CarColor.White
    },
    {
        id: uuidv4(),
        mark: 'Volkswagen',
        model: 'Caravella',
        plateNumber: 'T543AE123',
        color: CarColor.Brown
    },
    {
        id: uuidv4(),
        mark: 'Mercedes',
        model: 'Vito',
        plateNumber: 'T543AE123',
        color: CarColor.Silver
    },
    {
        id: uuidv4(),
        mark: 'Kelvin',
        model: 'Traveller',
        plateNumber: 'T543AE123',
        color: CarColor.Silver
    },
    {
        id: uuidv4(),
        mark: 'Kelvin',
        model: 'Traveller',
        plateNumber: 'T543AE123',
        color: CarColor.Silver
    },
];
