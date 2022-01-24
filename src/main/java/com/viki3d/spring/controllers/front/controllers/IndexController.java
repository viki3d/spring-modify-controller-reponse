package com.viki3d.spring.controllers.front.controllers;

import com.viki3d.spring.controllers.front.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main controller for this application.
 *
 * @author viki3d
 */
@RestController
public class IndexController {

  private final Logger logger = LoggerFactory.getLogger(IndexController.class);

  private Car car = new Car("Toyota", "Corolla");

  /**
   * Index.
   *
   * @return the car as JSON.
   */
  @GetMapping(path = "/")
  public String index() {
    String response = "" 
            + "<a href='/car'>/car - the original car</a><br/>\r\n"
            + "<a href='/modcar'>/modcar - the modified car</a><br/>\r\n";
    return response;
  }

  /**
   * Generates a car object as JSON response.
   *
   * @return the car as JSON.
   */
  @GetMapping(path = "/car")
  public ResponseEntity<Car> car() {
    ResponseEntity<Car> responseEntity = ResponseEntity.ok(car);
    logger.debug(responseEntity.getBody().toJsonString()); //{"brand":"Toyota",
    //                                                        "model":"Corolla"}
    return responseEntity; 
  }

  /**
   * Generates a car object as JSON response. However, the response will be modified (from 
   * CarFilter) so the car will now have a new attribute: `year` with value `2020`.
   *
   * @return the car as JSON with additional attribute `year`.
   */
  @GetMapping(path = "/modcar")
  public ResponseEntity<Car> modifiedResponse() {
    ResponseEntity<Car> responseEntity = ResponseEntity.ok(car);
    logger.debug(responseEntity.getBody().toJsonString()); //{"brand":"Toyota",
    //                                                        "model":"Corolla","year":2020}
    return responseEntity;
  }

}
