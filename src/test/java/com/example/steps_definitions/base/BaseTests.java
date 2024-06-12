package com.example.steps_definitions.base;


import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static net.serenitybdd.core.Serenity.getDriver;

public class BaseTests {


    public static ThreadLocal<SoftAssertions> softAssertionsThread = new ThreadLocal<>();
    public static LocalDateTime localDateTime;
    public DateTimeFormatter formatterStateDate = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm a");
    public Actions action = new Actions(getDriver());

    public Random random = new Random();
    public static SoftAssertions softAssertions= new SoftAssertions();



}
