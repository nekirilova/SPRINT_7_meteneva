package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
//тесты на создание курьеров с неправильными данными или без обязательных полей
public class BadCreateCourierTest {

    private Courier courier;
    private CourierData courierData;
    private int id;

    @Before
    public void setUp() {
        courier = new Courier();

    }

    @Test //Создание курьера с пустым логином возвращает статус код 400
    public void createCourierWithNullLoginReturnsStatusCode400() {
        courierData = CourierGenerator.getCourierWithoutLogin(); //создаем объект класса без логина
        ValidatableResponse responseCreate = courier.create(courierData); //отправляем запрос на создание курьера и записываем ответ в переменную

        int actualStatusCode = responseCreate.extract().statusCode(); //извлекаем статус код ответа при создании курьера
        int expectedStatusCode = 400;//ожидаемый статус код ответа

        Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
    }
    @Test //Создание курьера с пустым паролем возвращает статус код 400
    public void createCourierWithNullPasswordReturnsStatusCode400() {
        courierData = CourierGenerator.getCourierWithoutPassword(); //создаем объект класса без пароля
        ValidatableResponse responseCreate = courier.create(courierData); //отправляем запрос на создание курьера и записываем ответ в переменную

        int actualStatusCode = responseCreate.extract().statusCode(); //извлекаем статус код ответа при создании курьера
        int expectedStatusCode = 400;//ожидаемый статус код ответа

        Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
    }

    @Test //Создание курьера без поля логин возвращает статус код 400
    public void createCourierWithoutLoginReturnsStatusCode400() {
        Client client = new Client();
        BadCourierData badCourierData = new BadCourierData();
        ValidatableResponse responseCreate = given() //отправляем запрос на создание курьера и записываем ответ в переменную
                .spec(client.getSpecification())
                .body(badCourierData.getPASSWORD())
                .when()
                .post(badCourierData.getPATH())
                .then();

        int actualStatusCode = responseCreate.extract().statusCode(); //извлекаем статус код ответа при создании курьера
        int expectedStatusCode = 400;//ожидаемый статус код ответа

        Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
    }

    @Test //Создание курьера без поля пароль возвращает статус код 400
    public void createCourierWithoutPasswordReturnsStatusCode400() {
        Client client = new Client();
        BadCourierData badCourierData = new BadCourierData();
        ValidatableResponse responseCreate = given() //отправляем запрос на создание курьера и записываем ответ в переменную
                .spec(client.getSpecification())
                .body(badCourierData.getLOGIN())
                .when()
                .post(badCourierData.getPATH())
                .then();

        int actualStatusCode = responseCreate.extract().statusCode(); //извлекаем статус код ответа при создании курьера
        int expectedStatusCode = 400;//ожидаемый статус код ответа

        Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
    }
}
