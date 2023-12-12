package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * RoomIn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class RoomIn {

  private String name;

  private ServiceClass serviceClass;

  private BigDecimal pricePerNight;

  private UUID hotelId;

  public RoomIn() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RoomIn(String name, ServiceClass serviceClass, BigDecimal pricePerNight, UUID hotelId) {
    this.name = name;
    this.serviceClass = serviceClass;
    this.pricePerNight = pricePerNight;
    this.hotelId = hotelId;
  }

  public RoomIn name(String name) {
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

  public RoomIn serviceClass(ServiceClass serviceClass) {
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

  public RoomIn pricePerNight(BigDecimal pricePerNight) {
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

  public RoomIn hotelId(UUID hotelId) {
    this.hotelId = hotelId;
    return this;
  }

  /**
   * Get hotelId
   * @return hotelId
  */
  @NotNull @Valid 
  @Schema(name = "hotelId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hotelId")
  public UUID getHotelId() {
    return hotelId;
  }

  public void setHotelId(UUID hotelId) {
    this.hotelId = hotelId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoomIn roomIn = (RoomIn) o;
    return Objects.equals(this.name, roomIn.name) &&
        Objects.equals(this.serviceClass, roomIn.serviceClass) &&
        Objects.equals(this.pricePerNight, roomIn.pricePerNight) &&
        Objects.equals(this.hotelId, roomIn.hotelId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, serviceClass, pricePerNight, hotelId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomIn {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    serviceClass: ").append(toIndentedString(serviceClass)).append("\n");
    sb.append("    pricePerNight: ").append(toIndentedString(pricePerNight)).append("\n");
    sb.append("    hotelId: ").append(toIndentedString(hotelId)).append("\n");
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

