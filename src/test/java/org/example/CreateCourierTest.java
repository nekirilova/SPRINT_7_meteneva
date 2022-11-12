package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//тесты на создание курьера
public class CreateCourierTest {

    private Courier courier;
    private CourierData courierData;
    private int id;

    @Before //создаем объект класса Courier перед каждым тестом
    public void setUp() {
        courier = new Courier();

    }

    @After //удаляем созданного курьера после каждого теста
    public void cleanUp() {
        courier.delete(id);
    }
    @Test //Создание курьера возвращает статус код  201
    public void createCourierReturnsStatusCode201() {
        courierData = CourierGenerator.getDefault(); //создаем объект класса с корректными данными
        ValidatableResponse responseCreate = courier.create(courierData); //отправляем запрос на создание курьера и записываем ответ в переменную

        int actualStatusCode = responseCreate.extract().statusCode(); //извлекаем статус код ответа при создании курьера
        int expectedStatusCode = 201;//ожидаемый статус код ответа

        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData)); //отправляем запрос на логин курьера и записываем в переменную
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа

       Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
        }

    @Test //создание курьера возвращает правильное сообщение
    public void createCourierReturnsCorrectMessage() {
        courierData = CourierGenerator.getDefault(); //создаем объект класса с корректными данными
        ValidatableResponse responseCreate = courier.create(courierData); //отправляем запрос на создание курьера и записываем ответ в переменную
        boolean isCourierCreated = responseCreate.extract().path("ok"); //извлекаем значение ключа "ok" в body

        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData)); //отправляем запрос на логин курьера и записываем в переменную
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа

        Assert.assertTrue("Incorrect message", isCourierCreated);//проверяем, что фактическое сообщение в ответе соответствует ожидаемому
    }

    @Test //Создание двух одинаковых курьеров возвращает статус код 409
    public void createSameCourierReturnsStatusCode409() {
        courierData = CourierGenerator.getDefault(); //создаем объект класса с корректными данными
        ValidatableResponse responseCreate1 = courier.create(courierData);//отправляем запрос на создание первого курьера
        ValidatableResponse responseCreate2 = courier.create(courierData); //отправляем запрос на создание второго такого же курьера

        int actualStatusCode = responseCreate2.extract().statusCode(); //извлекаем статус код ответа при создании дубля курьера
        int expectedStatusCode = 409;//ожидаемый статус код ответа

        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData)); //отправляем запрос на логин курьера и записываем в переменную
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа

        Assert.assertEquals("Incorrect Status code", expectedStatusCode, actualStatusCode);//проверяем, что фактический статус код соответствует ожидаемому
    }
    @Test //Создание двух одинаковых курьеров возвращает правильный текст сообщения
    public void createSameCourierReturnsCorrectMessage() {
        courierData = CourierGenerator.getDefault(); //создаем объект класса с корректными данными
        ValidatableResponse responseCreate1 = courier.create(courierData);//отправляем запрос на создание первого курьера
        ValidatableResponse responseCreate2 = courier.create(courierData); //отправляем запрос на создание второго такого же курьера

        String actualMessage = responseCreate2.extract().path("message"); //извлекаем текст сообщения при создании дубля курьера
        String expectedMessage = "Этот логин уже используется";//ожидаемое сообщение в ответе

        ValidatableResponse responseLogin = courier.login(Credentials.from(courierData)); //отправляем запрос на логин курьера и записываем в переменную
        id = responseLogin.extract().path("id"); //извлекаем id курьера из ответа

        Assert.assertEquals("Incorrect Status code", expectedMessage, actualMessage);//проверяем, что фактический статус код соответствует ожидаемому
    }

}
