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
 * CarIn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class CarIn {

  private UUID markId;

  private UUID modelId;

  private String plateNumber;

  private CarColor color;

  public CarIn() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CarIn(UUID markId, UUID modelId, String plateNumber, CarColor color) {
    this.markId = markId;
    this.modelId = modelId;
    this.plateNumber = plateNumber;
    this.color = color;
  }

  public CarIn markId(UUID markId) {
    this.markId = markId;
    return this;
  }

  /**
   * Get markId
   * @return markId
  */
  @NotNull @Valid 
  @Schema(name = "markId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("markId")
  public UUID getMarkId() {
    return markId;
  }

  public void setMarkId(UUID markId) {
    this.markId = markId;
  }

  public CarIn modelId(UUID modelId) {
    this.modelId = modelId;
    return this;
  }

  /**
   * Get modelId
   * @return modelId
  */
  @NotNull @Valid 
  @Schema(name = "modelId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("modelId")
  public UUID getModelId() {
    return modelId;
  }

  public void setModelId(UUID modelId) {
    this.modelId = modelId;
  }

  public CarIn plateNumber(String plateNumber) {
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

  public CarIn color(CarColor color) {
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
    CarIn carIn = (CarIn) o;
    return Objects.equals(this.markId, carIn.markId) &&
        Objects.equals(this.modelId, carIn.modelId) &&
        Objects.equals(this.plateNumber, carIn.plateNumber) &&
        Objects.equals(this.color, carIn.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(markId, modelId, plateNumber, color);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CarIn {\n");
    sb.append("    markId: ").append(toIndentedString(markId)).append("\n");
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
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

