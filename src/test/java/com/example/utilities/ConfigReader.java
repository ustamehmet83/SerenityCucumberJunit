package com.example.utilities;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class ConfigReader {

    private static EnvironmentVariables environmentVariables ;
    static {
        environmentVariables =
                SystemEnvironmentVariables.createEnvironmentVariables();
    }

    public static String getProperty(String propertyName){

        return EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty(propertyName);

    }
}
