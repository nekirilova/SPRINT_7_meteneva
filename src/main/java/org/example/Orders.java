package org.example;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Orders extends Client {
    private static final String PATH_ORDER = "/api/v1/orders"; //Эндпойнт для создания заказа
    private static final String PATH_TRACK = "/api/v1/orders/track?t="; //эндпойнт для получения заказа по его трек-номеру
    private static final String PATH_CANCEL = "/api/v1/orders/cancel?track="; //Эндпойнт для отмены заказа по его трек-номеру

    private static final String NEAREST_STATIONS = "{\"nearestStation\": [\"1\", \"2\"] }"; //body с номерами станций, которые надо передать в запросе

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

    //получение списка заказов
    public ValidatableResponse getAllOrders() {
        return given()
                .spec(getSpecification())
                .when()
                .get(PATH_ORDER).then();
    }
    //получение списка заказов по ближайшей станции
    public ValidatableResponse getOrdersFromNearestStation() {
        return given()
                .spec(getSpecification())
                .body(NEAREST_STATIONS)
                .when()
                .get(PATH_ORDER).then();
    }

    //отмена заказа по его трек номеру
    public ValidatableResponse cancel(int trackNumber) {
        return given()
                .spec(getSpecification())
                .when()
                .put(PATH_CANCEL + Integer.toString(trackNumber)).then();
    }

}
