package com.ens.taskhelper.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TvaMeasurementDto {
  private LocalDate date;
  private String company;
  private String stack;
  private LocalTime time;
  private double standardValue;
  private double changeAmount;

  public TvaMeasurementDto(LocalDate date, String company, String stack, LocalTime time, double standardValue, double changeAmount) {
    this.date = date;
    this.company = company;
    this.stack = stack;
    this.time = time;
    this.standardValue = standardValue;
    this.changeAmount = changeAmount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public double getStandardValue() {
    return standardValue;
  }

  public void setStandardValue(double standardValue) {
    this.standardValue = standardValue;
  }

  public double getChangeAmount() {
    return changeAmount;
  }

  public void setChangeAmount(double changeAmount) {
    this.changeAmount = changeAmount;
  }

  @Override
  public String toString() {
    return "TvaMeasurementDto{" +
        "date=" + date +
        ", company='" + company + '\'' +
        ", stack='" + stack + '\'' +
        ", time=" + time +
        ", standardValue=" + standardValue +
        ", changeAmount=" + changeAmount +
        '}';
  }
}
