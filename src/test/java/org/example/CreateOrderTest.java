package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)

public class CreateOrderTest {
    private Orders orders;
    private NewOrder newOrder;
    private TrackOrder trackOrder;
    private static OrderGenerator orderGenerator;

    //определяем поля конструктора тестовых данных
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
//создаем конструктор тестовых данных

    public CreateOrderTest(String firstName, String lastName,
                           String address, int metroStation, String phone, int rentTime,
                           String deliveryDate, String comment, List<String> color) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    //создаем многомерный объект с данными для тестов

    @Parameterized.Parameters
    public static Object[][] fillOrderFields() {
        orderGenerator = new OrderGenerator();
        return new Object[][] {
                {orderGenerator.getFirstName(), orderGenerator.getLastName(), orderGenerator.getAddress(),
                orderGenerator.getMetroStation(), orderGenerator.getPhone(), orderGenerator.getRentTime(),
                orderGenerator.getDeliveryDate(),orderGenerator.getComment(), orderGenerator.getColor()},
                {orderGenerator.getFirstName(), orderGenerator.getLastName(), orderGenerator.getAddress(),
                        orderGenerator.getMetroStation(), orderGenerator.getPhone(), orderGenerator.getRentTime(),
                        orderGenerator.getDeliveryDate(),orderGenerator.getComment(), orderGenerator.getColorBlack()},
                {orderGenerator.getFirstName(), orderGenerator.getLastName(), orderGenerator.getAddress(),
                        orderGenerator.getMetroStation(), orderGenerator.getPhone(), orderGenerator.getRentTime(),
                        orderGenerator.getDeliveryDate(),orderGenerator.getComment(), orderGenerator.getColorGrey()},
        };
    }

    @Before
    public void setUp() {
        newOrder = new NewOrder();
       // orderGenerator = new OrderGenerator();

    }

//    @After
//    public void cleanUp() {
//        orders.cancel(trackOrder);
//    }

    @Test
    public void createOrderWithCorrectFieldsReturnsStatusCode201() {
        orders = new Orders();
        ValidatableResponse responseCreateOrder = orders.create(newOrder);
       // String trackNumber = "track:" + Integer.toString(responseCreateOrder.extract().path("track"));
System.out.println(Integer.toString(responseCreateOrder.extract().path("track")));

        int expectedStatusCode = 201;
        int actualStatusCode = responseCreateOrder.extract().statusCode();

        Assert.assertEquals("Incorrect status code", expectedStatusCode, actualStatusCode);
    }
}
