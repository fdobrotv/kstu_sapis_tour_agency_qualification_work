package com.fdobrotv.touristagency.entity;

import com.fdobrotv.touristagency.entity.enums.ServiceClass;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.math.BigInteger;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private ServiceClass serviceClass;

    @Column(name = "pricePerNight", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private BigInteger pricePerNight;

    @ManyToOne
    private HotelEntity hotel;

}