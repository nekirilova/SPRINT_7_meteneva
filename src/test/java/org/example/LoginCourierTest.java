package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {

    private Courier courier;
    private CourierData courierData;
    private int id;
    private int expectedStatusCode;
    private int actualStatusCode;

    private ValidatableResponse responseCreate;

    @Before
    public void setUp() {
        courier = new Courier();
        courierData = CourierGenerator.getDefault();
        responseCreate = courier.create(courierData);
    }

    @After
    public void cleanUp() {
        courier.delete(id);
    }

    @Test //Авторизация с корректными данными возвращает ответ 200 ОК
    public void courierLoginReturnsStatusCode200() {
        expectedStatusCode = 200; // ожидаемый статус код

        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData));
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа
        actualStatusCode = responseLogin.extract().statusCode(); //извлекаем фактический статус код из ответа

        //сравниваем фактический и ожидаемый статус код
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }
}
