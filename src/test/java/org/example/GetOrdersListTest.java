package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetOrdersListTest {
    private Orders orders;
    private NewOrder newOrder;
    private OrderGenerator orderGenerator;
    private int track;


    //Перед каждым тестом создаем один заказ
    @Before
    public void setUp() {
        orders = new Orders();
        orderGenerator = new OrderGenerator();
        newOrder = orderGenerator.getDefault();

        track = orders.create(newOrder).extract().path("track");
    }

    @Test
    public void getAllOrdersReturnsNotEmptyBody() {

        ValidatableResponse response = orders.getAllOrders();
        ArrayList<String> ordersList = response.extract().path("orders");

        Assert.assertNotNull("Orders List shouldn't be empty", ordersList);
    }
    @Test
    public void getAllOrdersReturnsNotEmptyPageInfo() {
        ValidatableResponse response = orders.getAllOrders();
        LinkedHashMap<String, Integer> pageInfo = response.extract().path("pageInfo");

        Assert.assertNotNull("Page info shouldn't be empty", pageInfo);
    }
    @Test
    public void getAllOrdersReturnsNotEmptyAvailableStations() {
        ValidatableResponse response = orders.getAllOrders();
        ArrayList<String> availableStations = response.extract().path("availableStations");

        Assert.assertNotNull("Available Stations shouldn't be empty", availableStations);
    }

    @Test
    public void getAllOrdersFromNearestStationReturnsNotEmptyBody() {
        ValidatableResponse response = orders.getOrdersFromNearestStation();
        ArrayList<String> ordersList = response.extract().path("orders");

        Assert.assertNotNull("Orders list shouldn't be empty", ordersList);
    }
}
