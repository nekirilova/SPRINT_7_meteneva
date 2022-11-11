package org.example;

import java.util.List;

public class OrderGenerator {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getColorBlack() {
        return colorBlack;
    }

    public void setColorBlack(List<String> colorBlack) {
        this.colorBlack = colorBlack;
    }

    public List<String> getColorGrey() {
        return colorGrey;
    }

    public void setColorGrey(List<String> colorGrey) {
        this.colorGrey = colorGrey;
    }

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
}
