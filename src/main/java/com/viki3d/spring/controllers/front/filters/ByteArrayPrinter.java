package com.viki3d.spring.controllers.front.filters;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;

/**
 * Custom implementation, where we intercept writing in the OuputStream of the ServletResponse. Thus
 * we can read the ServletResponse in order to modify it if needed in a ServletFilter.
 */
public class ByteArrayPrinter {

  private ByteArrayOutputStream baos = new ByteArrayOutputStream();
  private PrintWriter pw = new PrintWriter(baos);
  private ServletOutputStream sos = new ByteArrayServletStream(baos);

  public PrintWriter getWriter() {
    return pw;
  }

  public ServletOutputStream getStream() {
    return sos;
  }

  byte[] toByteArray() {
    return baos.toByteArray();
  }

}