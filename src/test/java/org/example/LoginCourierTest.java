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

    private LoginData loginData;


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

    @Test
    //Авторизация с корректными данными возвращает ответ 200 ОК
    public void courierLoginReturnsStatusCode200() {
        expectedStatusCode = 200; // ожидаемый статус код

        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData));
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа
        actualStatusCode = responseLogin.extract().statusCode(); //извлекаем фактический статус код из ответа

        //сравниваем фактический и ожидаемый статус код
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    //Успешная авторизация возвращает id курьера
    public void courierLoginSuccessfullReturnsCourierId() {
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData));
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа
                //проверяем, что id курьера в принципе возвращается
        Assert.assertNotNull("ID shouldn't be null", id);
    }

    @Test
    //Авторизация с неправильным логином
    public void setIncorrectLoginReturnsStatusCode404() {
        String incorrectLogin = "sunny";
        expectedStatusCode = 404;
        loginData = new LoginData();
        loginData.setLogin(incorrectLogin);
        loginData.setPassword(courierData.getPassword());
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    //Авторизация с неправильным паролем
    public void setIncorrectPasswordReturnsStatusCode404() {
        String incorrectPassword = "4321";
        expectedStatusCode = 404;
        loginData = new LoginData();
        loginData.setLogin(courierData.getLogin());
        loginData.setPassword(incorrectPassword);
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    //Авторизация без логина
    public void loginWithoutLoginReturnsStatusCode400() {
        expectedStatusCode = 400;
        loginData = new LoginData();
        loginData.setLogin(null);
        loginData.setPassword(courierData.getPassword());
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    //Авторизация без пароля
    public void loginWithoutPasswordReturnsStatusCode400() {
        expectedStatusCode = 400;
        loginData = new LoginData();
        loginData.setLogin(courierData.getLogin());
        loginData.setPassword(null);
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }
}
