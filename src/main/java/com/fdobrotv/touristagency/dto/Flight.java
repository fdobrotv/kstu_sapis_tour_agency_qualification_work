package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Flight
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class Flight {

  private String departureAirport;

  private String arrivalAirport;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime departureDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime arrivalDateTime;

  private UUID id;

  public Flight() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Flight(String departureAirport, String arrivalAirport, OffsetDateTime departureDateTime, OffsetDateTime arrivalDateTime, UUID id) {
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
    this.departureDateTime = departureDateTime;
    this.arrivalDateTime = arrivalDateTime;
    this.id = id;
  }

  public Flight departureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
    return this;
  }

  /**
   * Get departureAirport
   * @return departureAirport
  */
  @NotNull 
  @Schema(name = "departureAirport", example = "Moscow", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("departureAirport")
  public String getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  public Flight arrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
    return this;
  }

  /**
   * Get arrivalAirport
   * @return arrivalAirport
  */
  @NotNull 
  @Schema(name = "arrivalAirport", example = "Antalia", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrivalAirport")
  public String getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  public Flight departureDateTime(OffsetDateTime departureDateTime) {
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

  public Flight arrivalDateTime(OffsetDateTime arrivalDateTime) {
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

  public Flight id(UUID id) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Flight flight = (Flight) o;
    return Objects.equals(this.departureAirport, flight.departureAirport) &&
        Objects.equals(this.arrivalAirport, flight.arrivalAirport) &&
        Objects.equals(this.departureDateTime, flight.departureDateTime) &&
        Objects.equals(this.arrivalDateTime, flight.arrivalDateTime) &&
        Objects.equals(this.id, flight.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Flight {\n");
    sb.append("    departureAirport: ").append(toIndentedString(departureAirport)).append("\n");
    sb.append("    arrivalAirport: ").append(toIndentedString(arrivalAirport)).append("\n");
    sb.append("    departureDateTime: ").append(toIndentedString(departureDateTime)).append("\n");
    sb.append("    arrivalDateTime: ").append(toIndentedString(arrivalDateTime)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

