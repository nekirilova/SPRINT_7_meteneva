package org.example;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Orders extends Client {
    private static final String PATH_ORDER = "/api/v1/orders"; //Эндпойнт для создания заказа
    private static final String PATH_TRACK = "/api/v1/orders/track?t="; //эндпойнт для получения заказа по его трек-номеру

//создание заказа через апи

    public ValidatableResponse create(NewOrder newOrder) {
        return  given()
                .spec(getSpecification())
                .body(newOrder)
                .when()
                .post(PATH_ORDER).then();
    }

    //получение заказа по его трек номеру

    public ValidatableResponse getOrder(int id) {
        return given()
                .spec(getSpecification())
                .when()
                .get(PATH_TRACK + Integer.toString(id)).then();
    }

}
