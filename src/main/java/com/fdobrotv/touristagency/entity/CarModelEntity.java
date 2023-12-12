package com.fdobrotv.touristagency.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carModel")
public class CarModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;
}