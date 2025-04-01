module com.ens.taskhelper {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires net.synedra.validatorfx;

  requires org.apache.commons.codec;
  requires org.apache.commons.compress;

  requires poi;
  requires poi.ooxml;
  requires poi.ooxml.schemas;
  requires xmlbeans;
  requires org.yaml.snakeyaml;

  opens com.ens.taskhelper.controller to javafx.fxml;

  exports com.ens.taskhelper;
  exports com.ens.taskhelper.controller;
  exports com.ens.taskhelper.service;
  exports com.ens.taskhelper.util;
}