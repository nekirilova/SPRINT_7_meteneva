package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class Courier extends Client {

    private static final String PATH = "/api/v1/courier/"; //Эндпойнт для создания курьера
    private static final String PATH_LOGIN = "/api/v1/courier/login"; //эндпойнт для логина курьера

    //Создание курьера через апи
    @Step("Создание курьера")
    public ValidatableResponse create(CourierData courierData) {
        return  given()
                .spec(getSpecification())
                .body(courierData)
                .when()
                .post(PATH)
                .then();

    }
    //Логин курьера через апи
    @Step("Авторизация логина")
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpecification())
                .body(credentials)
                .when()
                .post(PATH_LOGIN).then();
    }

    public ValidatableResponse loginOnly(LoginData loginData) {
        return given()
                .spec(getSpecification())
                .body(loginData)
                .when()
                .post(PATH_LOGIN).then();
    }

    //Удаление курьера через апи
    @Step("Удаление курьера")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpecification())
                .when()
                .delete(PATH + Integer.toString(id))
                .then();
    }
}
