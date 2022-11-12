package org.example;
//класс с данными
public class BadCourierData {
//класс с константами для тестов на создание курьера без логина или пароля
    private final String LOGIN = "ohoh";
    private final String PASSWORD = "1234";

    private final String PATH = "/api/v1/courier/";


    public String getPATH() {
        return PATH;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
