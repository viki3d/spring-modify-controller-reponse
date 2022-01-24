package com.viki3d.spring.controllers.front.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * An entity, representing a car.
 *
 * @author viki3d
 */
public class Car {

  private String brand;
  private String model;

  public Car() {

  }

  public Car(String brand, String model) {
    this.brand = brand;
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Convert this entity to a JSON string.
   *
   * @return The JSON string, representing this entity.
   */
  public String toJsonString() {
    JsonObjectBuilder job = Json.createObjectBuilder();
    job.add("brand", brand);
    job.add("model", model);
    return job.build().toString();
  }

}
