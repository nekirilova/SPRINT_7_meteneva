package org.example;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Courier extends Client {

    private static final String PATH = "/api/v1/courier/";

    public ValidatableResponse create(CourierData courierData) {
        return  given()
                .spec(getSpecification())
                .body(courierData)
                .when()
                .post(PATH)
                .then();

    }

    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpecification())
                .when()
                .delete(PATH + Integer.toString(id))
                .then();
    }
}
