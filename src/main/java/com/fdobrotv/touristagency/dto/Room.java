package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fdobrotv.touristagency.dto.Hotel;
import com.fdobrotv.touristagency.dto.ServiceClass;
import java.math.BigDecimal;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Room
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class Room {

  private UUID id;

  private String name;

  private ServiceClass serviceClass;

  private BigDecimal pricePerNight;

  private Hotel hotel;

  public Room() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Room(UUID id, String name, ServiceClass serviceClass, BigDecimal pricePerNight, Hotel hotel) {
    this.id = id;
    this.name = name;
    this.serviceClass = serviceClass;
    this.pricePerNight = pricePerNight;
    this.hotel = hotel;
  }

  public Room id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Room name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Номер на двоих с видом на море", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Room serviceClass(ServiceClass serviceClass) {
    this.serviceClass = serviceClass;
    return this;
  }

  /**
   * Get serviceClass
   * @return serviceClass
  */
  @NotNull @Valid 
  @Schema(name = "serviceClass", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("serviceClass")
  public ServiceClass getServiceClass() {
    return serviceClass;
  }

  public void setServiceClass(ServiceClass serviceClass) {
    this.serviceClass = serviceClass;
  }

  public Room pricePerNight(BigDecimal pricePerNight) {
    this.pricePerNight = pricePerNight;
    return this;
  }

  /**
   * Get pricePerNight
   * @return pricePerNight
  */
  @NotNull @Valid 
  @Schema(name = "pricePerNight", example = "5670", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("pricePerNight")
  public BigDecimal getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(BigDecimal pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  public Room hotel(Hotel hotel) {
    this.hotel = hotel;
    return this;
  }

  /**
   * Get hotel
   * @return hotel
  */
  @NotNull @Valid 
  @Schema(name = "hotel", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hotel")
  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Room room = (Room) o;
    return Objects.equals(this.id, room.id) &&
        Objects.equals(this.name, room.name) &&
        Objects.equals(this.serviceClass, room.serviceClass) &&
        Objects.equals(this.pricePerNight, room.pricePerNight) &&
        Objects.equals(this.hotel, room.hotel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, serviceClass, pricePerNight, hotel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Room {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    serviceClass: ").append(toIndentedString(serviceClass)).append("\n");
    sb.append("    pricePerNight: ").append(toIndentedString(pricePerNight)).append("\n");
    sb.append("    hotel: ").append(toIndentedString(hotel)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

