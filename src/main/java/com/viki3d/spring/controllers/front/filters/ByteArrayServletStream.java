package com.viki3d.spring.controllers.front.filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * Custom implementation of ServletOutputStream, where we can intercept the writing activity.
 */
public class ByteArrayServletStream extends ServletOutputStream {

  private ByteArrayOutputStream baos;

  ByteArrayServletStream(ByteArrayOutputStream baos) {
    this.baos = baos;
  }

  @Override
  public void write(int param) throws IOException {
    baos.write(param);
  }

  @Override
  public boolean isReady() {
    return false;
  }

  @Override
  public void setWriteListener(WriteListener listener) {

  }
  
}
