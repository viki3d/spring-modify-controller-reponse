package com.viki3d.spring.controllers.front.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.viki3d.spring.controllers.front.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Testing non-modified and modified Car responses.
 *
 * @author viki3d
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCar() throws Exception {
    String expectedStringResult = new Car("Toyota", "Corolla").toJsonString();
    String stringResult = this.mockMvc.perform(get("/car")).andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    assertEquals(stringResult, expectedStringResult);
  }

  @Test
  void testModifiedCar() throws Exception {
    StringBuilder sb = new StringBuilder(new Car("Toyota", "Corolla").toJsonString());
    sb.deleteCharAt(sb.length()-1); //"}"
    sb.append(",\"year\":2020}");
    String expectedStringResult = sb.toString();
    String stringResult = this.mockMvc.perform(get("/modcar")).andDo(print())
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();
    assertEquals(stringResult, expectedStringResult);
  }

}
