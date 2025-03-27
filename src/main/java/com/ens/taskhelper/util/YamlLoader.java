package com.ens.taskhelper.util;

import com.ens.taskhelper.config.AppConfig;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class YamlLoader {
  private static AppConfig config;

  static {
    try (InputStream input = YamlLoader.class.getResourceAsStream("/config.yml")) {
      LoaderOptions options = new LoaderOptions();
      Constructor constructor = new Constructor(AppConfig.class, options);
      Yaml yaml = new Yaml(constructor);
      config = yaml.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static AppConfig getConfig() {
    return config;
  }
}
