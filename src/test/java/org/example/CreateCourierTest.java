package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateCourierTest {
    @Test
    public void createCourierReturnsStatusCode200() {
        CourierData courierData = CourierGenerator.getDefault();
        //courierData.setPassword(); поменять данные, например цвет самоката, через сеттер
        Courier courier = new Courier();
        ValidatableResponse response = courier.create(courierData);
        response.extract().
    }
}
