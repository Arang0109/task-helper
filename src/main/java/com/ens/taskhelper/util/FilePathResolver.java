package com.ens.taskhelper.util;

import java.io.File;

public class FilePathResolver {
  public static String toExcelPath(File txtFile) {
    String base = txtFile.getAbsolutePath();
    return base.endsWith(".txt") ? base.replace(".txt", ".xlsx") : base + ".xlsx";
  }
}

