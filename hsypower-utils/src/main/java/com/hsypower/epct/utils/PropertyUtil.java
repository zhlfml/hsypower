package com.hsypower.epct.utils;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyUtil {

    private static CompositeConfiguration config = new CompositeConfiguration();

    static {
        try {
            config.addConfiguration(new PropertiesConfiguration("hsypower.properties"));
            config.addConfiguration(new PropertiesConfiguration("email.properties"));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String propertyKey) {
        return config.getString(propertyKey);
    }

    public static String getString(String propertyKey, String defaultValue) {
        return config.getString(propertyKey, defaultValue);
    }

    public static int getInteger(String propertyKey) {
        return config.getInt(propertyKey);
    }

    public static int getInteger(String propertyKey, int defaultValue) {
        return config.getInt(propertyKey, defaultValue);
    }

    public static Object getProperty(String propertyKey) {
        return config.getProperty(propertyKey);
    }

    public static List<Object> getList(String propertyKey) {
        return config.getList(propertyKey);
    }

}
