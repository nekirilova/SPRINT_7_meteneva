package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)

public class CreateOrderTest {
    private Orders orders;
    private final NewOrder newOrder;
    private TrackOrder trackOrder;
    private static OrderGenerator orderGenerator;
    private int trackNumber;
    public CreateOrderTest(NewOrder newOrder) {
        this.newOrder = newOrder;
    }

    //создаем многомерный объект с данными для тестов
    @Parameterized.Parameters
    public static Object[][] fillOrderFields() {
        orderGenerator = new OrderGenerator();

        return new Object[][] {
                {orderGenerator.getDefault()},
                {orderGenerator.getDefaultColorBlack()},
                {orderGenerator.getDefaultColorGrey()},
                {orderGenerator.getDefaultWithoutColor()},
        };
    }

//    @After
//    public void cleanUp() {
//        orders.cancel(trackOrder);
//    }

    //при успешном создании заказа возвращается статус код 201
    @Test
    public void createOrderWithCorrectFieldsReturnsStatusCode201() {
        orders = new Orders();
        ValidatableResponse responseCreateOrder = orders.create(this.newOrder);
       // String trackNumber = "track:" + Integer.toString(responseCreateOrder.extract().path("track"));
        trackNumber = responseCreateOrder.extract().path("track");
        //System.out.println(trackNumber);
        int expectedStatusCode = 201;
        int actualStatusCode = responseCreateOrder.extract().statusCode();

        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }

    //При создании заказа возращается трек номер
    @Test
    public void createOrderWithCorrectFieldsReturnsTrackNumber() {
        orders = new Orders();
        ValidatableResponse responseCreateOrder = orders.create(this.newOrder);
        trackNumber = responseCreateOrder.extract().path("track");
        Assert.assertNotNull("No track number was found", trackNumber);
    }
}
