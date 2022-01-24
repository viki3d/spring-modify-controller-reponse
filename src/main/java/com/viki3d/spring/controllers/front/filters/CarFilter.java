package com.viki3d.spring.controllers.front.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * A servlet filter, which modifies a controller response. Works conditionally for a specific url.
 *
 * @author viki3d
 */
@Component
public class CarFilter implements Filter {

  private final Logger logger = LoggerFactory.getLogger(CarFilter.class);
  
  private static String JSON_NAME_YEAR = "year";
  private static int JSON_VALUE_YEAR = 2020;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    if (httpRequest.getRequestURI().equals("/modcar")) {
      ByteArrayPrinter pw = new ByteArrayPrinter();
      HttpServletResponse wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
        @Override
        public PrintWriter getWriter() {
          return pw.getWriter();
        }

        @Override
        public ServletOutputStream getOutputStream() {
          return pw.getStream();
        }
      };

      // Forward execution
      chain.doFilter(servletRequest, wrappedResponse);

      // Get response
      byte[] bytes = pw.toByteArray();
      String respBody = new String(bytes); //{"brand":"Toyota","model":"Corolla"}
      logger.debug("Original response: " + respBody);

      // Convert response as JSON
      JsonReader jsonReader = Json.createReader(new StringReader(respBody));
      JsonObject carOriginalJson = jsonReader.readObject();
      jsonReader.close();

      // Modify the response JSON
      String modRespBody;
      JsonObjectBuilder job = Json.createObjectBuilder();
      // - clone the original car JSON
      for (String key : carOriginalJson.keySet()) {
        job.add(key, carOriginalJson.get(key));
      }
      // - add additional attribute to car's JSON:
      job.add(JSON_NAME_YEAR, JSON_VALUE_YEAR);
      modRespBody = job.build().toString();
      logger.debug("Modified response: " + modRespBody);

      // Change the `Content-Type` header if needed:
      //httpResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
      //httpResponse.setHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE);
      httpResponse.setContentLength(modRespBody.length());

      // Write the new modified response
      httpResponse.getOutputStream().write(modRespBody.toString().getBytes());
      httpResponse.getOutputStream().flush();
    } else {
      // Forward execution
      chain.doFilter(servletRequest, servletResponse);
    }
  }

}

