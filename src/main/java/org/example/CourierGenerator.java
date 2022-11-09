package org.example;

//Генератор тестовых данных для создания курьера
public class CourierGenerator {

    //Курьер с корректными данными
    public static CourierData getDefault() {

        return new CourierData("ohoh", "1234", "Julia");
    }
    //Курьер без логина
    public static CourierData getCourierWithoutLogin() {
        return new CourierData(null, "1234", "Julia");
    }
    //Курьер без пароля
    public static CourierData getCourierWithoutPassword() {
        return new CourierData("ohoh", null, "Julia");
    }



}
