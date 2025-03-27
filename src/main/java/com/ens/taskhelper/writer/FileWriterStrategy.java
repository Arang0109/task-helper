package com.ens.taskhelper.writer;

import com.ens.taskhelper.dto.TvaMeasurementDto;

import java.io.File;
import java.util.List;

public interface FileWriterStrategy {
  void write(File targetFile, TvaMeasurementDto dto, List<String[]> rowData);
}
