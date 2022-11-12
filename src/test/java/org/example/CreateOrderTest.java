package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

//Тесты на создание заказа
@RunWith(Parameterized.class)

public class CreateOrderTest {
    private Orders orders;

    private static OrderGenerator orderGenerator;
    private int trackNumber; //трек номер заказа
    private final NewOrder newOrder;
    //конструктор для тестовых данных
    public CreateOrderTest(NewOrder newOrder){
    this.newOrder = newOrder;
    }

    //создаем многомерный объект с данными для тестов
    @Parameterized.Parameters
    public static Object[][] fillOrderFields() {
        orderGenerator = new OrderGenerator();

        return new Object[][] {
                {orderGenerator.getDefault()},   //дефолтный заказ со всеми параметрами и двумя выбранными цветами
                {orderGenerator.getDefaultColorBlack()}, //дефолтный заказ со всеми параметрами и выбранным черным цветом
                {orderGenerator.getDefaultColorGrey()}, //дефолтный заказ со всеми параметрами и выбранным серым цветом
                {orderGenerator.getDefaultWithoutColor()}, //дефолтный заказ со всеми параметрами, кроме цвета
        };
    }

    @After //после теста отменяем заказ по его трек номеру
    public void cleanUp() {
        orders.cancel(trackNumber);
    }

    //при успешном создании заказа возвращается статус код 201
    @Test
    public void createOrderWithCorrectFieldsReturnsStatusCode201() {
        orders = new Orders();
        ValidatableResponse responseCreateOrder = orders.create(newOrder); //создаем заказ
        trackNumber = responseCreateOrder.extract().path("track"); //извлекаем трек номер из ответа, чтобы потом отменить заказ

        int expectedStatusCode = 201; //ожидаемый статус код
        int actualStatusCode = responseCreateOrder.extract().statusCode(); //фактический статус код

        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode); //сравниваем ФР и ОР
    }

    //При создании заказа возращается трек номер
    @Test
    public void createOrderWithCorrectFieldsReturnsTrackNumber() {
        orders = new Orders();
        ValidatableResponse responseCreateOrder = orders.create(newOrder); //создаем заказ
        trackNumber = responseCreateOrder.extract().path("track"); //извлекаем трек номер из ответа
        Assert.assertNotNull("No track number was found", trackNumber); //проверяем, что трек номер есть
    }
}
