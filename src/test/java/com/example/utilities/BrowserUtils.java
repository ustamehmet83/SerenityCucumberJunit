package com.example.utilities;

/*
In this class only general utility methods that are not related to some specific page.
 */


import com.example.steps_definitions.base.BaseTests;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BrowserUtils {

    public static void getThreadAssert() {

        try {
            BaseTests.softAssertionsThread.get().assertAll();
        } catch (AssertionError e) {
            System.out.println("Assertion errors: " + e.getMessage());
            throw e; // Test framework'üne hatayı ilet
        } finally {
            BaseTests.softAssertionsThread.remove(); // Her senaryodan sonra temizleyin
        }

    }



}


