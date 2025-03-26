package com.ens.taskhelper.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateDataDto {
  private LocalDate date;
  private String company;
  private String stack;
  private LocalTime time;
  private double referenceValue;
  private double variableValue;

  public CreateDataDto() {}

  public CreateDataDto(LocalDate date, String company, String stack, LocalTime time, double referenceValue, double variableValue) {
    this.date = date;
    this.company = company;
    this.stack = stack;
    this.time = time;
    this.referenceValue = referenceValue;
    this.variableValue = variableValue;
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

  public double getReferenceValue() {
    return referenceValue;
  }

  public void setReferenceValue(double referenceValue) {
    this.referenceValue = referenceValue;
  }

  public double getVariableValue() {
    return variableValue;
  }

  public void setVariableValue(double variableValue) {
    this.variableValue = variableValue;
  }

  @Override
  public String toString() {
    return "CreateDataDto{" +
        "date=" + date +
        ", company='" + company + '\'' +
        ", stack='" + stack + '\'' +
        ", time=" + time +
        ", referenceValue=" + referenceValue +
        ", variableValue=" + variableValue +
        '}';
  }
}
