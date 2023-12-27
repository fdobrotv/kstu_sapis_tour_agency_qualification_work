import { CarColor, Flight, Transfer } from "@/generated"
import { UUID } from "crypto"

export const colorToText = (carColor: CarColor): String =>  {
    switch (carColor) {
        case CarColor.Black:
            return 'Чёрный'
        case CarColor.Blue:
            return 'Синий'
        case CarColor.White:
            return 'Белый'
        case CarColor.Brown:
            return 'Коричневый'
        case CarColor.Yellow:
            return 'Желтый'
        case CarColor.Red:
            return 'Красный'
        case CarColor.Silver:
            return 'Серебристый'
        default:
            return 'Неизвестное значение!'
      }                          
}

export const dateToText = (transfer: Transfer): String =>  {
    if (transfer.departureDateTime.toLocaleDateString() === transfer.arrivalDateTime.toLocaleDateString())
        return `Отправление: ${transfer.departureDateTime.toLocaleDateString()} ${transfer.departureDateTime.toLocaleTimeString()}
                Прибытие ${transfer.arrivalDateTime.toLocaleTimeString()}`
    return `Отправление: ${transfer.departureDateTime.toLocaleString()} 
    Прибытие: ${transfer.arrivalDateTime.toLocaleString()}`         
}

export const transferFormatter = (transfer: Transfer): object =>  {
    var color = colorToText(transfer.car.color);
                                const items = {
                                    value: transfer.id,
                                    label: transferDisplayFormatter(transfer)
                                };
                                return items;                 
}

export const transferDisplayFormatter = (transfer: Transfer): String =>  {
    var color = colorToText(transfer.car.color);
    var dates = dateToText(transfer);
    return `${transfer.name} - ${color} "${transfer.car.mark} ${transfer.car.model}" ${transfer.car.plateNumber} - ${dates}`;                 
}

export const flightDateToText = (flight: Flight): String =>  {
    if (flight.departureDateTime.toLocaleDateString() === flight.arrivalDateTime.toLocaleDateString())
        return `Отправление: ${flight.departureDateTime.toLocaleDateString()} ${flight.departureDateTime.toLocaleTimeString()}
                Прибытие ${flight.arrivalDateTime.toLocaleTimeString()}`
    return `Отправление: ${flight.departureDateTime.toLocaleString()} 
    Прибытие: ${flight.arrivalDateTime.toLocaleString()}`         
}

export const flightDisplayFormatter = (flight: Flight): String =>  {
    var dates = flightDateToText(flight);
    return `${flight.departureAirport}  -  ${flight.arrivalAirport}: ${dates}`;                 
}