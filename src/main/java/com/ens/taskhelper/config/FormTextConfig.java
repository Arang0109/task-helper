package com.ens.taskhelper.config;

import java.util.List;

public class FormTextConfig {
  private List<String> header;
  private List<String> footer;

  public List<String> getHeader() {
    return header;
  }

  public void setHeader(List<String> header) {
    this.header = header;
  }

  public List<String> getFooter() {
    return footer;
  }

  public void setFooter(List<String> footer) {
    this.footer = footer;
  }
}
