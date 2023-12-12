package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.Room;
import com.fdobrotv.touristagency.dto.Transfer;
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
 * Tour
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class Tour {

  private UUID id;

  private Flight departureFlight;

  private Flight arrivalFlight;

  private Transfer transferToHotel;

  private Transfer transferFromHotel;

  private String description;

  private BigDecimal price;

  private Room room;

  private FoodOption selectedFoodOption;

  public Tour() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Tour(UUID id, Flight departureFlight, Flight arrivalFlight, Transfer transferToHotel, Transfer transferFromHotel, String description, BigDecimal price, Room room, FoodOption selectedFoodOption) {
    this.id = id;
    this.departureFlight = departureFlight;
    this.arrivalFlight = arrivalFlight;
    this.transferToHotel = transferToHotel;
    this.transferFromHotel = transferFromHotel;
    this.description = description;
    this.price = price;
    this.room = room;
    this.selectedFoodOption = selectedFoodOption;
  }

  public Tour id(UUID id) {
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

  public Tour departureFlight(Flight departureFlight) {
    this.departureFlight = departureFlight;
    return this;
  }

  /**
   * Get departureFlight
   * @return departureFlight
  */
  @NotNull @Valid 
  @Schema(name = "departureFlight", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("departureFlight")
  public Flight getDepartureFlight() {
    return departureFlight;
  }

  public void setDepartureFlight(Flight departureFlight) {
    this.departureFlight = departureFlight;
  }

  public Tour arrivalFlight(Flight arrivalFlight) {
    this.arrivalFlight = arrivalFlight;
    return this;
  }

  /**
   * Get arrivalFlight
   * @return arrivalFlight
  */
  @NotNull @Valid 
  @Schema(name = "arrivalFlight", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrivalFlight")
  public Flight getArrivalFlight() {
    return arrivalFlight;
  }

  public void setArrivalFlight(Flight arrivalFlight) {
    this.arrivalFlight = arrivalFlight;
  }

  public Tour transferToHotel(Transfer transferToHotel) {
    this.transferToHotel = transferToHotel;
    return this;
  }

  /**
   * Get transferToHotel
   * @return transferToHotel
  */
  @NotNull @Valid 
  @Schema(name = "transferToHotel", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transferToHotel")
  public Transfer getTransferToHotel() {
    return transferToHotel;
  }

  public void setTransferToHotel(Transfer transferToHotel) {
    this.transferToHotel = transferToHotel;
  }

  public Tour transferFromHotel(Transfer transferFromHotel) {
    this.transferFromHotel = transferFromHotel;
    return this;
  }

  /**
   * Get transferFromHotel
   * @return transferFromHotel
  */
  @NotNull @Valid 
  @Schema(name = "transferFromHotel", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transferFromHotel")
  public Transfer getTransferFromHotel() {
    return transferFromHotel;
  }

  public void setTransferFromHotel(Transfer transferFromHotel) {
    this.transferFromHotel = transferFromHotel;
  }

  public Tour description(String description) {
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

  public Tour price(BigDecimal price) {
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

  public Tour room(Room room) {
    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
  */
  @NotNull @Valid 
  @Schema(name = "room", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("room")
  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Tour selectedFoodOption(FoodOption selectedFoodOption) {
    this.selectedFoodOption = selectedFoodOption;
    return this;
  }

  /**
   * Get selectedFoodOption
   * @return selectedFoodOption
  */
  @NotNull @Valid 
  @Schema(name = "selectedFoodOption", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("selectedFoodOption")
  public FoodOption getSelectedFoodOption() {
    return selectedFoodOption;
  }

  public void setSelectedFoodOption(FoodOption selectedFoodOption) {
    this.selectedFoodOption = selectedFoodOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tour tour = (Tour) o;
    return Objects.equals(this.id, tour.id) &&
        Objects.equals(this.departureFlight, tour.departureFlight) &&
        Objects.equals(this.arrivalFlight, tour.arrivalFlight) &&
        Objects.equals(this.transferToHotel, tour.transferToHotel) &&
        Objects.equals(this.transferFromHotel, tour.transferFromHotel) &&
        Objects.equals(this.description, tour.description) &&
        Objects.equals(this.price, tour.price) &&
        Objects.equals(this.room, tour.room) &&
        Objects.equals(this.selectedFoodOption, tour.selectedFoodOption);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, departureFlight, arrivalFlight, transferToHotel, transferFromHotel, description, price, room, selectedFoodOption);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tour {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    departureFlight: ").append(toIndentedString(departureFlight)).append("\n");
    sb.append("    arrivalFlight: ").append(toIndentedString(arrivalFlight)).append("\n");
    sb.append("    transferToHotel: ").append(toIndentedString(transferToHotel)).append("\n");
    sb.append("    transferFromHotel: ").append(toIndentedString(transferFromHotel)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    selectedFoodOption: ").append(toIndentedString(selectedFoodOption)).append("\n");
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

