package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fdobrotv.touristagency.dto.Car;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Transfer
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class Transfer {

  private UUID id;

  private String name;

  private Car car;

  private String departureCoordinates;

  private String arrivalCoordinates;

  private BigDecimal price;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime departureDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime arrivalDateTime;

  private Boolean isGuideIncluded;

  public Transfer() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Transfer(UUID id, String name, Car car, String departureCoordinates, String arrivalCoordinates, BigDecimal price, OffsetDateTime departureDateTime, OffsetDateTime arrivalDateTime, Boolean isGuideIncluded) {
    this.id = id;
    this.name = name;
    this.car = car;
    this.departureCoordinates = departureCoordinates;
    this.arrivalCoordinates = arrivalCoordinates;
    this.price = price;
    this.departureDateTime = departureDateTime;
    this.arrivalDateTime = arrivalDateTime;
    this.isGuideIncluded = isGuideIncluded;
  }

  public Transfer id(UUID id) {
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

  public Transfer name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Тур из Москвы в Анталию", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Transfer car(Car car) {
    this.car = car;
    return this;
  }

  /**
   * Get car
   * @return car
  */
  @NotNull @Valid 
  @Schema(name = "car", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("car")
  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Transfer departureCoordinates(String departureCoordinates) {
    this.departureCoordinates = departureCoordinates;
    return this;
  }

  /**
   * Get departureCoordinates
   * @return departureCoordinates
  */
  @NotNull 
  @Schema(name = "departureCoordinates", example = "55.746181, 37.624566", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("departureCoordinates")
  public String getDepartureCoordinates() {
    return departureCoordinates;
  }

  public void setDepartureCoordinates(String departureCoordinates) {
    this.departureCoordinates = departureCoordinates;
  }

  public Transfer arrivalCoordinates(String arrivalCoordinates) {
    this.arrivalCoordinates = arrivalCoordinates;
    return this;
  }

  /**
   * Get arrivalCoordinates
   * @return arrivalCoordinates
  */
  @NotNull 
  @Schema(name = "arrivalCoordinates", example = "36.891727, 30.689625", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrivalCoordinates")
  public String getArrivalCoordinates() {
    return arrivalCoordinates;
  }

  public void setArrivalCoordinates(String arrivalCoordinates) {
    this.arrivalCoordinates = arrivalCoordinates;
  }

  public Transfer price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @NotNull @Valid 
  @Schema(name = "price", example = "5670", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Transfer departureDateTime(OffsetDateTime departureDateTime) {
    this.departureDateTime = departureDateTime;
    return this;
  }

  /**
   * Get departureDateTime
   * @return departureDateTime
  */
  @NotNull @Valid 
  @Schema(name = "departureDateTime", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("departureDateTime")
  public OffsetDateTime getDepartureDateTime() {
    return departureDateTime;
  }

  public void setDepartureDateTime(OffsetDateTime departureDateTime) {
    this.departureDateTime = departureDateTime;
  }

  public Transfer arrivalDateTime(OffsetDateTime arrivalDateTime) {
    this.arrivalDateTime = arrivalDateTime;
    return this;
  }

  /**
   * Get arrivalDateTime
   * @return arrivalDateTime
  */
  @NotNull @Valid 
  @Schema(name = "arrivalDateTime", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrivalDateTime")
  public OffsetDateTime getArrivalDateTime() {
    return arrivalDateTime;
  }

  public void setArrivalDateTime(OffsetDateTime arrivalDateTime) {
    this.arrivalDateTime = arrivalDateTime;
  }

  public Transfer isGuideIncluded(Boolean isGuideIncluded) {
    this.isGuideIncluded = isGuideIncluded;
    return this;
  }

  /**
   * Get isGuideIncluded
   * @return isGuideIncluded
  */
  @NotNull 
  @Schema(name = "isGuideIncluded", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isGuideIncluded")
  public Boolean getIsGuideIncluded() {
    return isGuideIncluded;
  }

  public void setIsGuideIncluded(Boolean isGuideIncluded) {
    this.isGuideIncluded = isGuideIncluded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transfer transfer = (Transfer) o;
    return Objects.equals(this.id, transfer.id) &&
        Objects.equals(this.name, transfer.name) &&
        Objects.equals(this.car, transfer.car) &&
        Objects.equals(this.departureCoordinates, transfer.departureCoordinates) &&
        Objects.equals(this.arrivalCoordinates, transfer.arrivalCoordinates) &&
        Objects.equals(this.price, transfer.price) &&
        Objects.equals(this.departureDateTime, transfer.departureDateTime) &&
        Objects.equals(this.arrivalDateTime, transfer.arrivalDateTime) &&
        Objects.equals(this.isGuideIncluded, transfer.isGuideIncluded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, car, departureCoordinates, arrivalCoordinates, price, departureDateTime, arrivalDateTime, isGuideIncluded);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transfer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    car: ").append(toIndentedString(car)).append("\n");
    sb.append("    departureCoordinates: ").append(toIndentedString(departureCoordinates)).append("\n");
    sb.append("    arrivalCoordinates: ").append(toIndentedString(arrivalCoordinates)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    departureDateTime: ").append(toIndentedString(departureDateTime)).append("\n");
    sb.append("    arrivalDateTime: ").append(toIndentedString(arrivalDateTime)).append("\n");
    sb.append("    isGuideIncluded: ").append(toIndentedString(isGuideIncluded)).append("\n");
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

