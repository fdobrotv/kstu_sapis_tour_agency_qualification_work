package com.fdobrotv.touristagency.entity;

import com.fdobrotv.touristagency.entity.enums.ServiceClass;
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
@Table(name = "hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "address", length = 10000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String address;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private ServiceClass serviceClass;

    @Column(name = "isGuideIncluded")
    private Boolean isGuideIncluded;
}