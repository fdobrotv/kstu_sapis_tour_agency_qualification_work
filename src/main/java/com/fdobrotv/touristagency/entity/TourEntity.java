package com.fdobrotv.touristagency.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "description", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String description;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private BigInteger price;

    @ManyToOne
    private FlightEntity departureFlight;

    @ManyToOne
    private FlightEntity arrivalFlight;

    @ManyToOne
    private TransferEntity transferToHotel;

    @ManyToOne
    private TransferEntity transferFromHotel;

    @ManyToOne
    @JoinColumn(name = "hotel_room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "selected_food_option_id")
    private FoodOptionEntity foodOption;
}
