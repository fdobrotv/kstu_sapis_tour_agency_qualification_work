package com.fdobrotv.touristagency.entity;

import com.fdobrotv.touristagency.entity.enums.CarColor;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    private CarMarkEntity mark;

    @ManyToOne
    private CarModelEntity model;

    @Column(name = "plateNumber", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String plateNumber;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private CarColor color;
}