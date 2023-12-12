package com.fdobrotv.touristagency;

import com.fdobrotv.touristagency.entity.*;
import com.fdobrotv.touristagency.entity.enums.CarColor;
import com.fdobrotv.touristagency.entity.enums.ServiceClass;
import com.fdobrotv.touristagency.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigInteger;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
class RepositoryTests {

	@Autowired
	private CarMarkEntityRepository carMarkEntityRepository;

	@Autowired
	private CarModelEntityRepository carModelEntityRepository;

	@Autowired
	private CarEntityRepository carEntityRepository;

	@Autowired
	private TransferEntityRepository transferEntityRepository;

	@Autowired
	private TourEntityRepository tourEntityRepository;
	@Autowired
	private FlightEntityRepository flightEntityRepository;

	@Autowired
	private HotelEntityRepository hotelEntityRepository;
	@Autowired
	private RoomEntityRepository roomEntityRepository;

	String tourDescription = "Тур из Москвы в Анталию";
	@Autowired
	private FoodOptionEntityRepository foodOptionEntityRepository;

	@Test
	void getTourByIdTest() {
		CarMarkEntity carMarkEntity = createCarMark("Peugeot");
		CarModelEntity carModelEntity = createCarModel("Traveller");
		CarEntity carEntity = createCarEntity(carMarkEntity, carModelEntity);
		TransferEntity transferToHotelEntity = createTransfer(
				carEntity,
				"55.746181, 37.624566",
				"36.891727, 30.689625");
		TransferEntity transferFromHotelEntity = createTransfer(
				carEntity,
				"36.891727, 30.689625",
				"55.746181, 37.624566");

		FlightEntity arrivalFlightEntity = createFlightEntity(
				"MSK",
				"ANT",
				"2023-01-02T18:14:59.000+00:00",
				"2023-01-02T22:24:59.000+00:00");

		FlightEntity departureFlightEntity = createFlightEntity(
				"ANT",
				"MSK",
				"2023-01-13T14:14:59.000+00:00",
				"2023-01-13T16:34:59.000+00:00");

		HotelEntity hotelEntity = createHotelEntity();

		RoomEntity roomEntity = createRoomEntity(hotelEntity);

		FoodOptionEntity foodOptionEntity = createFoodOption(hotelEntity);

		TourEntity tourEntity = createTourEntity(transferToHotelEntity,
				transferFromHotelEntity,
				arrivalFlightEntity,
				departureFlightEntity,
				roomEntity,
				foodOptionEntity);

		assertThat(tourEntity.getId()).isNotNull();
		assertThat(tourEntity.getPrice()).isEqualTo(104570);
		assertThat(tourEntity.getDescription()).isEqualTo(tourDescription);
		assertThat(tourEntity.getDepartureFlight()).isEqualTo(departureFlightEntity);
		assertThat(tourEntity.getFoodOption()).isEqualTo(foodOptionEntity);

//		cleanup(carMarkEntity, carModelEntity, carEntity, transferToHotelEntity,
//				transferFromHotelEntity, arrivalFlightEntity, departureFlightEntity,
//				hotelEntity, roomEntity, foodOptionEntity);
	}

	private FoodOptionEntity createFoodOption(HotelEntity hotelEntity) {
		FoodOptionEntity entity = new FoodOptionEntity();
//		entity.setHotel(hotelEntity);
		entity.setName("AI");
		entity.setPrice(new BigInteger("1450"));
		FoodOptionEntity foodOptionEntity = foodOptionEntityRepository.save(entity);
		return foodOptionEntity;
	}

	private TourEntity createTourEntity(TransferEntity transferToHotelEntity,
										TransferEntity transferFromHotelEntity, FlightEntity arrivalFlightEntity, FlightEntity departureFlightEntity,
										RoomEntity roomEntity,
										FoodOptionEntity foodOptionEntity) {
		TourEntity entity = new TourEntity();
		entity.setTransferToHotel(transferToHotelEntity);
		entity.setTransferFromHotel(transferFromHotelEntity);
		entity.setPrice(new BigInteger("104570"));
		entity.setDescription(tourDescription);
		entity.setDepartureFlight(departureFlightEntity);
		entity.setArrivalFlight(arrivalFlightEntity);
		entity.setRoom(roomEntity);
		entity.setFoodOption(foodOptionEntity);
		return tourEntityRepository.save(entity);
	}

	private void cleanup(CarMarkEntity carMarkEntity,
						 CarModelEntity carModelEntity, CarEntity carEntity,
						 TransferEntity transferToHotelEntity, TransferEntity transferFromHotelEntity,
						 FlightEntity arrivalFlightEntity,
						 FlightEntity departureFlightEntity, HotelEntity hotelEntity,
						 RoomEntity roomEntity,
						 FoodOptionEntity foodOptionEntity) {
		deleteFoodOption(foodOptionEntity);
		deleteRoom(roomEntity);
		deleteHotel(hotelEntity);
		deleteFlight(departureFlightEntity);
		deleteFlight(arrivalFlightEntity);
		deleteTransfer(transferFromHotelEntity);
		deleteTransfer(transferToHotelEntity);
		deleteCar(carEntity);
		deleteCarModel(carModelEntity);
		deleteCarMark(carMarkEntity);
	}

	private void deleteFoodOption(FoodOptionEntity foodOptionEntity) {
		foodOptionEntityRepository.delete(foodOptionEntity);
	}

	private void deleteHotel(HotelEntity hotelEntity) {
		hotelEntityRepository.delete(hotelEntity);
	}

	private HotelEntity createHotelEntity() {
		HotelEntity hotelEntity = new HotelEntity();
		hotelEntity.setName("Sunrise Resort Hotel");
		hotelEntity.setServiceClass(ServiceClass.FIVE);
		hotelEntity.setAddress("Kizilagac Turizm Merkezi Manavgat Antalya, 07600 Kizilagac, Turkey");
		hotelEntity.setIsGuideIncluded(true);
		return hotelEntityRepository.save(hotelEntity);
	}

	private RoomEntity createRoomEntity(HotelEntity hotelEntity) {
		RoomEntity roomEntity = new RoomEntity();
		roomEntity.setServiceClass(ServiceClass.FIVE);
		roomEntity.setName("Люкс с 2мя кроватями на четверых");
		roomEntity.setPricePerNight(new BigInteger("7150"));
		roomEntity.setHotel(hotelEntity);
		return roomEntityRepository.save(roomEntity);
	}

	private void deleteRoom(RoomEntity roomEntity) {
		roomEntityRepository.delete(roomEntity);
	}

	private void deleteFlight(FlightEntity flightEntity) {
		flightEntityRepository.delete(flightEntity);
	}

	private FlightEntity createFlightEntity(String departureAirport, String arrivalAirport,
											String departureDateTime, String arrivalDateTime) {
		OffsetDateTime departureInstant = DateTimeFormatter.ISO_INSTANT.parse(departureDateTime, Instant::from).atOffset(UTC);
		OffsetDateTime arrivalInstant = DateTimeFormatter.ISO_INSTANT.parse(arrivalDateTime, Instant::from).atOffset(UTC);
		return createFlight(
				departureAirport, arrivalAirport,
				departureInstant, arrivalInstant);
	}

	private FlightEntity createFlight(String departureAirport,
									  String arrivalAirport,
									  OffsetDateTime departureDateTime,
									  OffsetDateTime arrivalDateTime) {
		FlightEntity entity = new FlightEntity();
		entity.setDepartureAirport(departureAirport);
		entity.setArrivalAirport(arrivalAirport);
		entity.setDepartureDateTime(departureDateTime);
		entity.setArrivalDateTime(arrivalDateTime);
		return flightEntityRepository.save(entity);
	}

	private void deleteTransfer(TransferEntity transferToHotelEntity) {
		transferEntityRepository.delete(transferToHotelEntity);
	}

	private TransferEntity createTransfer(CarEntity carEntity,
										  String departureCoordinates,
										  String arrivalCoordinates) {
		TransferEntity entity = new TransferEntity();
		entity.setName("Туристический автобус");
		entity.setCar(carEntity);
		entity.setDepartureCoordinates(departureCoordinates);
		entity.setArrivalCoordinates(arrivalCoordinates);
		entity.setDepartureDateTime(OffsetDateTime.now().plusDays(3).toInstant());
		entity.setArrivalDateTime(OffsetDateTime.now().plusDays(3).plusHours(3).toInstant());
		entity.setPrice(new BigInteger("1230"));
		entity.setIsGuideIncluded(true);
		return transferEntityRepository.save(entity);
	}

	private void deleteCar(CarEntity carEntity) {
		carEntityRepository.delete(carEntity);
	}

	private CarEntity createCarEntity(CarMarkEntity carMarkEntity, CarModelEntity carModelEntity) {
		CarEntity carEntity = new CarEntity();
		carEntity.setColor(CarColor.black);
		carEntity.setMark(carMarkEntity);
		carEntity.setModel(carModelEntity);
		carEntity.setPlateNumber("A123TH716RUS");
		return carEntityRepository.save(carEntity);
	}

	private CarMarkEntity createCarMark(String mark) {
		CarMarkEntity entity = new CarMarkEntity();
		entity.setName(mark);
		return carMarkEntityRepository.save(entity);
	}

	private CarModelEntity createCarModel(String model) {
		CarModelEntity entity = new CarModelEntity();
		entity.setName(model);
		return carModelEntityRepository.save(entity);
	}

	private void deleteCarMark(CarMarkEntity carMarkEntity) {
		carMarkEntityRepository.delete(carMarkEntity);
	}

	private void deleteCarModel(CarModelEntity carModelEntity) {
		carModelEntityRepository.delete(carModelEntity);
	}

}
