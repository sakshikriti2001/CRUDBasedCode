package com.crud.code.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SqlQueryLoader {
    private static Properties sqlProperties;
    static {
        try{
            sqlProperties = PropertiesLoaderUtils.loadAllProperties("sql.properties");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load SQL properties",e);
        }
    }
    public static String get(String key)
    {
        return (String) sqlProperties.get(key);
    }
}
