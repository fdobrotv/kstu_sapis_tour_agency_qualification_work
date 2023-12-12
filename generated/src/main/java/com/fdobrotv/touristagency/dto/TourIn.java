package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * TourIn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class TourIn {

  private UUID departureFlightId;

  private UUID arrivalFlightId;

  private UUID transferToHotelId;

  private UUID transferFromHotelId;

  private String description;

  private BigDecimal price;

  private UUID roomId;

  private UUID selectedFoodOptionId;

  public TourIn() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TourIn(UUID departureFlightId, UUID arrivalFlightId, UUID transferToHotelId, UUID transferFromHotelId, String description, BigDecimal price, UUID roomId, UUID selectedFoodOptionId) {
    this.departureFlightId = departureFlightId;
    this.arrivalFlightId = arrivalFlightId;
    this.transferToHotelId = transferToHotelId;
    this.transferFromHotelId = transferFromHotelId;
    this.description = description;
    this.price = price;
    this.roomId = roomId;
    this.selectedFoodOptionId = selectedFoodOptionId;
  }

  public TourIn departureFlightId(UUID departureFlightId) {
    this.departureFlightId = departureFlightId;
    return this;
  }

  /**
   * Get departureFlightId
   * @return departureFlightId
  */
  @NotNull @Valid 
  @Schema(name = "departureFlightId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("departureFlightId")
  public UUID getDepartureFlightId() {
    return departureFlightId;
  }

  public void setDepartureFlightId(UUID departureFlightId) {
    this.departureFlightId = departureFlightId;
  }

  public TourIn arrivalFlightId(UUID arrivalFlightId) {
    this.arrivalFlightId = arrivalFlightId;
    return this;
  }

  /**
   * Get arrivalFlightId
   * @return arrivalFlightId
  */
  @NotNull @Valid 
  @Schema(name = "arrivalFlightId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrivalFlightId")
  public UUID getArrivalFlightId() {
    return arrivalFlightId;
  }

  public void setArrivalFlightId(UUID arrivalFlightId) {
    this.arrivalFlightId = arrivalFlightId;
  }

  public TourIn transferToHotelId(UUID transferToHotelId) {
    this.transferToHotelId = transferToHotelId;
    return this;
  }

  /**
   * Get transferToHotelId
   * @return transferToHotelId
  */
  @NotNull @Valid 
  @Schema(name = "transferToHotelId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transferToHotelId")
  public UUID getTransferToHotelId() {
    return transferToHotelId;
  }

  public void setTransferToHotelId(UUID transferToHotelId) {
    this.transferToHotelId = transferToHotelId;
  }

  public TourIn transferFromHotelId(UUID transferFromHotelId) {
    this.transferFromHotelId = transferFromHotelId;
    return this;
  }

  /**
   * Get transferFromHotelId
   * @return transferFromHotelId
  */
  @NotNull @Valid 
  @Schema(name = "transferFromHotelId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transferFromHotelId")
  public UUID getTransferFromHotelId() {
    return transferFromHotelId;
  }

  public void setTransferFromHotelId(UUID transferFromHotelId) {
    this.transferFromHotelId = transferFromHotelId;
  }

  public TourIn description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @NotNull 
  @Schema(name = "description", example = "Тур из Москвы в Анталию", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TourIn price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @NotNull @Valid 
  @Schema(name = "price", example = "104500.5", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public TourIn roomId(UUID roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * Get roomId
   * @return roomId
  */
  @NotNull @Valid 
  @Schema(name = "roomId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("roomId")
  public UUID getRoomId() {
    return roomId;
  }

  public void setRoomId(UUID roomId) {
    this.roomId = roomId;
  }

  public TourIn selectedFoodOptionId(UUID selectedFoodOptionId) {
    this.selectedFoodOptionId = selectedFoodOptionId;
    return this;
  }

  /**
   * Get selectedFoodOptionId
   * @return selectedFoodOptionId
  */
  @NotNull @Valid 
  @Schema(name = "selectedFoodOptionId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("selectedFoodOptionId")
  public UUID getSelectedFoodOptionId() {
    return selectedFoodOptionId;
  }

  public void setSelectedFoodOptionId(UUID selectedFoodOptionId) {
    this.selectedFoodOptionId = selectedFoodOptionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TourIn tourIn = (TourIn) o;
    return Objects.equals(this.departureFlightId, tourIn.departureFlightId) &&
        Objects.equals(this.arrivalFlightId, tourIn.arrivalFlightId) &&
        Objects.equals(this.transferToHotelId, tourIn.transferToHotelId) &&
        Objects.equals(this.transferFromHotelId, tourIn.transferFromHotelId) &&
        Objects.equals(this.description, tourIn.description) &&
        Objects.equals(this.price, tourIn.price) &&
        Objects.equals(this.roomId, tourIn.roomId) &&
        Objects.equals(this.selectedFoodOptionId, tourIn.selectedFoodOptionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departureFlightId, arrivalFlightId, transferToHotelId, transferFromHotelId, description, price, roomId, selectedFoodOptionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TourIn {\n");
    sb.append("    departureFlightId: ").append(toIndentedString(departureFlightId)).append("\n");
    sb.append("    arrivalFlightId: ").append(toIndentedString(arrivalFlightId)).append("\n");
    sb.append("    transferToHotelId: ").append(toIndentedString(transferToHotelId)).append("\n");
    sb.append("    transferFromHotelId: ").append(toIndentedString(transferFromHotelId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("    selectedFoodOptionId: ").append(toIndentedString(selectedFoodOptionId)).append("\n");
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

