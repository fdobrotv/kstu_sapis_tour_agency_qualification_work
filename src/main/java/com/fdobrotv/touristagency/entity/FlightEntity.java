package com.fdobrotv.touristagency.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "flight")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "departureAirport", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String departureAirport;

    @Column(name = "arrivalAirport", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String arrivalAirport;

    @Column(name = "departureDateTime", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
//    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    private OffsetDateTime departureDateTime;

    @Column(name = "arrivalDateTime", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
//    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    private OffsetDateTime arrivalDateTime;
}