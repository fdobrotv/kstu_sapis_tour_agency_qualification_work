package com.fdobrotv.touristagency;

import com.fdobrotv.touristagency.controller.*;
import com.fdobrotv.touristagency.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.fdobrotv.touristagency.TestDataHelper.tourDescription;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest
@Rollback
@ExtendWith(SpringExtension.class)
//@Import(TestRestTemplate.class)
class ControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CarMarkController carMarkController;

    @Autowired
    private CarModelController carModelController;

    @Autowired
    private CarController carController;

    @Autowired
    private TransferController transferController;

    @Autowired
    private TourController tourController;

    @Autowired
    private FlightController flightController;

    @Autowired
    private HotelController hotelController;

    @Autowired
    private RoomController roomController;

    @Autowired
    private FoodOptionController foodOptionController;

    @Test
    void getTourByIdTest() {
        CarMark carMark = createCarMark("Peugeot");

        CarModel carModel = createCarModel("Traveller");

        Car car = createCar(carMark, carModel);
        Transfer transferToHotel = createTransfer(
                car,
                "55.746181, 37.624566",
                "36.891727, 30.689625");

        Transfer transferFromHotel = createTransfer(
                car,
                "36.891727, 30.689625",
                "55.746181, 37.624566");

        //TODO: Fix timezone shift bug
        Flight departureFlight = createFlight(
                "ANT",
                "MSK",
                "2023-01-13T14:14:59.000Z",
                "2023-01-13T16:34:59.000Z");

        Flight arrivalFlight = createFlight(
                "MSK",
                "ANT",
                "2023-01-02T18:14:59.000Z",
                "2023-01-02T22:24:59.000Z");

        Hotel hotel = createHotel();

        Room room = createRoom(hotel);

        FoodOption foodOption = createFoodOption();

        Tour tour = createTour(transferToHotel,
                transferFromHotel,
                arrivalFlight,
                departureFlight,
                room,
                foodOption);

		assertThat(tour.getId()).isNotNull();
		assertThat(tour.getPrice()).isEqualTo(new BigDecimal(104570));
		assertThat(tour.getDescription()).isEqualTo(tourDescription);
		assertThat(tour.getDepartureFlight()).isEqualTo(departureFlight);
		assertThat(tour.getSelectedFoodOption()).isEqualTo(foodOption);

        cleanup(carMark, carModel, car, transferToHotel,
                transferFromHotel, arrivalFlight, departureFlight,
                hotel, room, foodOption, tour);
    }

    private void cleanup(CarMark carMark,
                         CarModel carModel,
                         Car car,
                         Transfer transferToHotel,
                         Transfer transferFromHotel,
                         Flight arrivalFlight,
                         Flight departureFlight,
                         Hotel hotel,
                         Room room,
                         FoodOption foodOption,
                         Tour tour) {
        deleteTour(tour);

        deleteFoodOption(foodOption);

        deleteRoom(room);

        deleteHotel(hotel);

        deleteFlight(departureFlight);
        deleteFlight(arrivalFlight);

        deleteTransfer(transferFromHotel);
        deleteTransfer(transferToHotel);

        deleteCar(car);
        deleteCarModel(carModel);
        deleteCarMark(carMark);
    }

    private Tour createTour(Transfer transferToHotel,
                            Transfer transferFromHotel,
                            Flight arrivalFlight,
                            Flight departureFlight,
                            Room room,
                            FoodOption foodOption) {
        TourIn tourIn = new TourIn();
        tourIn.transferToHotelId(transferToHotel.getId());
        tourIn.transferFromHotelId(transferFromHotel.getId());
        tourIn.setPrice(new BigDecimal("104570"));
        tourIn.description(tourDescription);
        tourIn.departureFlightId(departureFlight.getId());
        tourIn.arrivalFlightId(arrivalFlight.getId());
        tourIn.roomId(room.getId());
        tourIn.selectedFoodOptionId(foodOption.getId());

        ResponseEntity<Tour> response =
                testRestTemplate.postForEntity("/v1/tours", tourIn, Tour.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private FoodOption createFoodOption() {
        FoodOptionIn foodOptionIn = new FoodOptionIn();
        foodOptionIn.setName("AI");
        foodOptionIn.setPrice(new BigDecimal("1450"));
        ResponseEntity<FoodOption> response =
                testRestTemplate.postForEntity("/v1/foodOptions", foodOptionIn, FoodOption.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }
    
    private Room createRoom(Hotel hotel) {
        RoomIn roomIn = new RoomIn();
        roomIn.setServiceClass(ServiceClass.FIVE);
        roomIn.setName("Люкс с 2мя кроватями на четверых");
        roomIn.setPricePerNight(new BigDecimal("7150"));
        roomIn.setHotelId(hotel.getId());

        ResponseEntity<Room> response =
                testRestTemplate.postForEntity("/v1/rooms", roomIn, Room.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private Hotel createHotel() {
        HotelIn hotelIn = new HotelIn();
        hotelIn.setName("Sunrise Resort Hotel");
        hotelIn.setServiceClass(ServiceClass.FIVE);
        hotelIn.setAddress("Kizilagac Turizm Merkezi Manavgat Antalya, 07600 Kizilagac, Turkey");
        hotelIn.setIsGuideIncluded(true);

        ResponseEntity<Hotel> response =
                testRestTemplate.postForEntity("/v1/hotels", hotelIn, Hotel.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private Transfer createTransfer(Car car,
                                    String departureCoordinates,
                                    String arrivalCoordinates) {
        TransferIn transferIn = new TransferIn();
        transferIn.setName("Туристический автобус");
        transferIn.setCarId(car.getId());
        transferIn.setDepartureCoordinates(departureCoordinates);
        transferIn.setArrivalCoordinates(arrivalCoordinates);
        //TODO: Add not null constraint
        transferIn.setDepartureDateTime(OffsetDateTime.now().plusDays(3));
        transferIn.setArrivalDateTime(OffsetDateTime.now().plusDays(3).plusHours(3));
        transferIn.setPrice(new BigDecimal("1230"));
        transferIn.setIsGuideIncluded(true);

        ResponseEntity<Transfer> response =
                testRestTemplate.postForEntity("/v1/transfers", transferIn, Transfer.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }


    //
    private void deleteCarMark(CarMark carMark) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/carMarks/" + carMark.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteTour(Tour tour) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/tours/" + tour.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteFoodOption(FoodOption foodOption) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/foodOptions/" + foodOption.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteRoom(Room room) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/rooms/" + room.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteHotel(Hotel hotel) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/hotels/" + hotel.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteFlight(Flight flight) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/flights/" + flight.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteTransfer(Transfer transfer) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/transfers/" + transfer.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteCar(Car car) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/cars/" + car.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private void deleteCarModel(CarModel carModel) {
        ResponseEntity<Void> exchange =
                testRestTemplate.exchange(
                        "/v1/carModels/" + carModel.getId(),
                        HttpMethod.DELETE,
                        null,
                        Void.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());
    }

    private Flight createFlight(String departureAirport, String arrivalAirport,
                                String departureDateTime, String arrivalDateTime) {
        Instant departureInstant = DateTimeFormatter.ISO_INSTANT.parse(departureDateTime, Instant::from);
        Instant arrivalInstant = DateTimeFormatter.ISO_INSTANT.parse(arrivalDateTime, Instant::from);
        return createFlight(
                departureAirport, arrivalAirport,
                departureInstant, arrivalInstant);
    }

    private Flight createFlight(String departureAirport, String arrivalAirport, Instant departureDateTime, Instant arrivalDateTime) {
        FlightIn flightIn = new FlightIn();
        flightIn.setDepartureAirport(departureAirport);
        flightIn.setArrivalAirport(arrivalAirport);
        flightIn.setDepartureDateTime(departureDateTime.atOffset(ZoneOffset.UTC));
        flightIn.setArrivalDateTime(arrivalDateTime.atOffset(ZoneOffset.UTC));

        ResponseEntity<Flight> response =
                testRestTemplate.postForEntity("/v1/flights", flightIn, Flight.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private Car createCar(CarMark carMark, CarModel carModel) {
        CarIn carIn = new CarIn();
        carIn.setColor(CarColor.BLACK);
        carIn.setMarkId(carMark.getId());
        carIn.setModelId(carModel.getId());
        carIn.setPlateNumber("A123TH716RUS");

        ResponseEntity<Car> response =
                testRestTemplate.postForEntity("/v1/cars", carIn, Car.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private CarMark createCarMark(String mark) {
        CarMarkIn carMarkIn = new CarMarkIn();
        carMarkIn.setName(mark);

        ResponseEntity<CarMark> response =
                testRestTemplate.postForEntity("/v1/carMarks", carMarkIn, CarMark.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

    private CarModel createCarModel(String modelName) {
        CarModelIn carModelIn = new CarModelIn();
        carModelIn.setName(modelName);
        ResponseEntity<CarModel> response =
                testRestTemplate.postForEntity("/v1/carModels", carModelIn, CarModel.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        return response.getBody();
    }

}
