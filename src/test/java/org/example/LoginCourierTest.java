package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//тесты на авторизацию курьера
public class LoginCourierTest {

    private Courier courier;
    private CourierData courierData;
    private int id;
    private int expectedStatusCode;
    private int actualStatusCode;

    private LoginData loginData;

    private ValidatableResponse responseCreate;

    @Before //перед каждым тестом создаем курьера
    public void setUp() {
        courier = new Courier();
        courierData = CourierGenerator.getDefault();
        responseCreate = courier.create(courierData);
    }

    @After //удаляем курьера после каждого теста
    public void cleanUp() {
        courier.delete(id);
    }

    @Test

    @DisplayName("Авторизация с корректными данными возвращает ответ 200 ОК")
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
    @DisplayName( "Успешная авторизация возвращает id курьера")
    public void courierLoginSuccessfullReturnsCourierId() {
        //вызываем апи метод для авторизации курьера с теми же данными, что были при создании
        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData));
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа
                //проверяем, что id курьера в принципе возвращается
        Assert.assertNotNull("ID shouldn't be null", id);
    }

    @Test
    @DisplayName( "Авторизация с неправильным логином возвращает статус код 404")
    public void setIncorrectLoginReturnsStatusCode404() {
        String incorrectLogin = "sunny"; //несуществующий логин
        expectedStatusCode = 404; // ожидаемый статус код
        loginData = new LoginData();
        loginData.setLogin(incorrectLogin); //заменяем логин на неправильный
        loginData.setPassword(courierData.getPassword()); //пароль берем тот, который был при создании курьера перед тестом
        //вызываем апи метод для авторизации курьера с неправильным логином:
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    @DisplayName("Авторизация с неправильным паролем возвращает статус код 404")
    public void setIncorrectPasswordReturnsStatusCode404() {
        String incorrectPassword = "4321"; //неправильный пароль
        expectedStatusCode = 404; //ожидаемый статус код
        loginData = new LoginData();
        loginData.setLogin(courierData.getLogin()); //логин берем тот, что использовали при создании курьера
        loginData.setPassword(incorrectPassword); //пароль меняем на неправильный
        //вызываем апи метод для авторизации курьера с неправильным паролем
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    @DisplayName("Авторизация без логина возвращает статус код 400")
    public void loginWithoutLoginReturnsStatusCode400() {
        expectedStatusCode = 400; //ожидаемый статус код
        loginData = new LoginData();
        loginData.setLogin(null); //устанавливаем пустой логин
        loginData.setPassword(courierData.getPassword()); //пароль берем тот же, что был при создании курьера
        //вызываем апи метод для авторизации курьера без логина
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    @Test
    @DisplayName("Авторизация Без пароля возвращает статус код 400")
    public void loginWithoutPasswordReturnsStatusCode400() {
        expectedStatusCode = 400; //ожидаемый статус код
        loginData = new LoginData();
        loginData.setLogin(courierData.getLogin()); //берем тот же логин, что был при создании курьера
        loginData.setPassword(null); //устанавливаем пустой пароль
        //вызываем апи метод для авторизации курьера без пароля
        ValidatableResponse responseLogin = courier.loginOnly(loginData);
        actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }
}
