package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fdobrotv.touristagency.dto.CarColor;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Car
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class Car {

  private UUID id;

  private String mark;

  private String model;

  private String plateNumber;

  private CarColor color;

  public Car() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Car(UUID id, String mark, String model, String plateNumber, CarColor color) {
    this.id = id;
    this.mark = mark;
    this.model = model;
    this.plateNumber = plateNumber;
    this.color = color;
  }

  public Car id(UUID id) {
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

  public Car mark(String mark) {
    this.mark = mark;
    return this;
  }

  /**
   * Get mark
   * @return mark
  */
  @NotNull 
  @Schema(name = "mark", example = "Peugeot", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("mark")
  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public Car model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @NotNull 
  @Schema(name = "model", example = "Traveller", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Car plateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
    return this;
  }

  /**
   * Get plateNumber
   * @return plateNumber
  */
  @NotNull 
  @Schema(name = "plateNumber", example = "A123TH716RUS", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("plateNumber")
  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public Car color(CarColor color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  */
  @NotNull @Valid 
  @Schema(name = "color", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("color")
  public CarColor getColor() {
    return color;
  }

  public void setColor(CarColor color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return Objects.equals(this.id, car.id) &&
        Objects.equals(this.mark, car.mark) &&
        Objects.equals(this.model, car.model) &&
        Objects.equals(this.plateNumber, car.plateNumber) &&
        Objects.equals(this.color, car.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mark, model, plateNumber, color);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Car {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mark: ").append(toIndentedString(mark)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    plateNumber: ").append(toIndentedString(plateNumber)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
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

