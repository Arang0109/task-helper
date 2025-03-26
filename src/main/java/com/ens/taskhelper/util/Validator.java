package com.ens.taskhelper.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {
  public String check(LocalDate date, String company, String stack, String timeStr, String refStr, String varStr) {
    if (date == null) return "날짜를 선택하세요.";
    if (company == null || company.isBlank()) return "업체명을 입력하세요.";
    if (stack == null || stack.isBlank()) return "측정 시설을 입력하세요.";

    try {
      LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
    } catch (DateTimeParseException e) {
      return "시간 형식이 잘못되었습니다. 예: 08:15:00";
    }

    try {
      Double.parseDouble(refStr);
      Double.parseDouble(varStr);
    } catch (NumberFormatException e) {
      return "기준값 또는 변화값이 숫자가 아닙니다.";
    }

    return "OK"; // 모든 검증 통과
  }
}
