package com.fdobrotv.touristagency.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.swing.event.CaretEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfer")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @ManyToOne
    private CarEntity car;

    @Column(name = "departureCoordinates", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String departureCoordinates;

    @Column(name = "arrivalCoordinates", nullable = false, length = 50)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String arrivalCoordinates;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private BigInteger price;

    @Column(name = "departureDateTime", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private Instant departureDateTime;

    @Column(name = "arrivalDateTime", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private Instant arrivalDateTime;

    @Column(name = "isGuideIncluded")
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private Boolean isGuideIncluded;
}