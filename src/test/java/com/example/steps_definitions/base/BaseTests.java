package com.example.steps_definitions.base;


import com.example.pages.api.Pet;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

import static net.serenitybdd.core.Serenity.getDriver;

public class BaseTests {
    public Response response;
    public Map<Object, Object> responseMap;
    public static int pet_id;
    public Pet requestPet;

    public static ThreadLocal<SoftAssertions> softAssertionsThread = new ThreadLocal<>();
    public static LocalDateTime localDateTime;
    public DateTimeFormatter formatterStateDate = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm a");
    public Actions action = new Actions(getDriver());

    public Random random = new Random();
    public static SoftAssertions softAssertions= new SoftAssertions();



}
