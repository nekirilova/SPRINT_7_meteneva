package org.example;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class Courier extends Client {

    private static final String PATH = "/api/v1/courier/"; //Эндпойнт для создания курьера
    private static final String PATH_LOGIN = "/api/v1/courier/login"; //эндпойнт для логина курьера

    //Создание курьера через апи
    public ValidatableResponse create(CourierData courierData) {
        return  given()
                .spec(getSpecification())
                .body(courierData)
                .when()
                .post(PATH)
                .then();

    }
    //Логин курьера через апи
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpecification())
                .body(credentials) //Или Credentials.from?
                .when()
                .post(PATH_LOGIN).then();
    }
    //Удаление курьера через апи
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpecification())
                .when()
                .delete(PATH + Integer.toString(id))
                .then();
    }
}
