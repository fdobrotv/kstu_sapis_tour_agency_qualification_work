package com.fdobrotv.touristagency.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fdobrotv.touristagency.dto.ServiceClass;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * HotelIn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
public class HotelIn {

  private String name;

  private String address;

  private ServiceClass serviceClass;

  private Boolean isGuideIncluded;

  public HotelIn() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public HotelIn(String name, String address, ServiceClass serviceClass, Boolean isGuideIncluded) {
    this.name = name;
    this.address = address;
    this.serviceClass = serviceClass;
    this.isGuideIncluded = isGuideIncluded;
  }

  public HotelIn name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Тувана Отель", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HotelIn address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @NotNull 
  @Schema(name = "address", example = "Tuzcular Mah. Karanlik Sok. No 18 Kaleici, Анталья 07100 Турция", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public HotelIn serviceClass(ServiceClass serviceClass) {
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

  public HotelIn isGuideIncluded(Boolean isGuideIncluded) {
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
    HotelIn hotelIn = (HotelIn) o;
    return Objects.equals(this.name, hotelIn.name) &&
        Objects.equals(this.address, hotelIn.address) &&
        Objects.equals(this.serviceClass, hotelIn.serviceClass) &&
        Objects.equals(this.isGuideIncluded, hotelIn.isGuideIncluded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, serviceClass, isGuideIncluded);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HotelIn {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    serviceClass: ").append(toIndentedString(serviceClass)).append("\n");
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

