package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * TransferIn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class TransferIn {

  private String name;

  private UUID carId;

  private String departureCoordinates;

  private String arrivalCoordinates;

  private BigDecimal price;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime departureDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime arrivalDateTime;

  private Boolean isGuideIncluded;

  public TransferIn() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TransferIn(String name, UUID carId, String departureCoordinates, String arrivalCoordinates, BigDecimal price, OffsetDateTime departureDateTime, OffsetDateTime arrivalDateTime, Boolean isGuideIncluded) {
    this.name = name;
    this.carId = carId;
    this.departureCoordinates = departureCoordinates;
    this.arrivalCoordinates = arrivalCoordinates;
    this.price = price;
    this.departureDateTime = departureDateTime;
    this.arrivalDateTime = arrivalDateTime;
    this.isGuideIncluded = isGuideIncluded;
  }

  public TransferIn name(String name) {
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

  public TransferIn carId(UUID carId) {
    this.carId = carId;
    return this;
  }

  /**
   * Get carId
   * @return carId
  */
  @NotNull @Valid 
  @Schema(name = "carId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("carId")
  public UUID getCarId() {
    return carId;
  }

  public void setCarId(UUID carId) {
    this.carId = carId;
  }

  public TransferIn departureCoordinates(String departureCoordinates) {
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

  public TransferIn arrivalCoordinates(String arrivalCoordinates) {
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

  public TransferIn price(BigDecimal price) {
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

  public TransferIn departureDateTime(OffsetDateTime departureDateTime) {
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

  public TransferIn arrivalDateTime(OffsetDateTime arrivalDateTime) {
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

  public TransferIn isGuideIncluded(Boolean isGuideIncluded) {
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
    TransferIn transferIn = (TransferIn) o;
    return Objects.equals(this.name, transferIn.name) &&
        Objects.equals(this.carId, transferIn.carId) &&
        Objects.equals(this.departureCoordinates, transferIn.departureCoordinates) &&
        Objects.equals(this.arrivalCoordinates, transferIn.arrivalCoordinates) &&
        Objects.equals(this.price, transferIn.price) &&
        Objects.equals(this.departureDateTime, transferIn.departureDateTime) &&
        Objects.equals(this.arrivalDateTime, transferIn.arrivalDateTime) &&
        Objects.equals(this.isGuideIncluded, transferIn.isGuideIncluded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, carId, departureCoordinates, arrivalCoordinates, price, departureDateTime, arrivalDateTime, isGuideIncluded);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransferIn {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    carId: ").append(toIndentedString(carId)).append("\n");
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

