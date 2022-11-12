package org.example;

import java.util.List;
//Класс для генерации тестовых данных
public class OrderGenerator {
    private String firstName = "Masha";
    private String lastName = "Kuznetsova";
    private String address = "Lenina, 25-10";
    private int metroStation = 34;
    private String phone = "89008002030";
    private int rentTime = 1;
    private String deliveryDate = "2022-11-15";
    private String comment = "no comment";
    private List<String> color = List.of("BLACK", "GREY");
    private List<String> colorBlack = List.of("BLACK");
    private List<String> colorGrey = List.of("GREY");
    private List<String> withoutColor = List.of();

    //заказ с корректными полями и двумя выбранными цветами самоката
    public NewOrder getDefault() {
        return new NewOrder(this.firstName, this.lastName, this.address, this.metroStation,
                this.phone, this.rentTime, this.deliveryDate, this.comment, this.color);
    }

    //заказ с корректными полями и черным цветом самоката
    public NewOrder getDefaultColorBlack() {
        return new NewOrder(this.firstName, this.lastName, this.address, this.metroStation,
                this.phone, this.rentTime, this.deliveryDate, this.comment, this.colorBlack);
    }

    //заказ с корректными полями и серым цветом самоката
    public NewOrder getDefaultColorGrey() {
        return new NewOrder(this.firstName, this.lastName, this.address, this.metroStation,
                this.phone, this.rentTime, this.deliveryDate, this.comment, this.colorGrey);
    }
//заказ с корректными полями с невыбранным цветом самоката
    public NewOrder getDefaultWithoutColor() {
        return new NewOrder(this.firstName, this.lastName, this.address, this.metroStation,
                this.phone, this.rentTime, this.deliveryDate, this.comment, this.withoutColor);
    }

}
