package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
//Тесты на получение списка заказов
public class GetOrdersListTest {
    private Orders orders;
    private NewOrder newOrder;
    private OrderGenerator orderGenerator;
    private int trackNumber;


    //Перед каждым тестом создаем один заказ (на случай, если в базе не было заказов)
    @Before
    public void setUp() {
        orders = new Orders();
        orderGenerator = new OrderGenerator();
        newOrder = orderGenerator.getDefault();

        trackNumber = orders.create(newOrder).extract().path("track"); //извлекаем трек номер заказа для его отмены
    }

    @After //после теста отменяем заказ
   public void cleanUp() {
        orders.cancel(trackNumber);
    }

    @Test   //запрос на получение списка заказов возращает непустой список заказов
    public void getAllOrdersReturnsNotEmptyBody() {
        ValidatableResponse response = orders.getAllOrders();      //вызываем метод для получения списка заказов
        ArrayList<String> ordersList = response.extract().path("orders"); //извлекаем значение ключа orders

        Assert.assertNotNull("Orders List shouldn't be empty", ordersList); //проверяем, что оно не пустое
    }
    @Test  //запрос на получение списка заказов возвращает непустое поле pageInfo
    public void getAllOrdersReturnsNotEmptyPageInfo() {
        ValidatableResponse response = orders.getAllOrders();       //вызываем метод для получения списка заказов
        LinkedHashMap<String, Integer> pageInfo = response.extract().path("pageInfo");   //извлекаем значение ключа pageInfo

        Assert.assertNotNull("Page info shouldn't be empty", pageInfo);  //проверяем, что оно не пустое
    }
    @Test
    public void getAllOrdersReturnsNotEmptyAvailableStations() {
        ValidatableResponse response = orders.getAllOrders();   //вызываем метод для получения списка заказов
        ArrayList<String> availableStations = response.extract().path("availableStations");     //извлекаем значение ключа availableStations

        Assert.assertNotNull("Available Stations shouldn't be empty", availableStations);//проверяем, что оно не пустое
    }

    @Test
    public void getAllOrdersFromNearestStationReturnsNotEmptyBody() {
        ValidatableResponse response = orders.getOrdersFromNearestStation();   //вызываем метод для получения списка заказов по ближайшей станции метро
        ArrayList<String> ordersList = response.extract().path("orders");     //извлекаем значение ключа orders

        Assert.assertNotNull("Orders list shouldn't be empty", ordersList); //проверяем, что оно не пустое 
    }
}
