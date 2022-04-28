package com.v.database.model;

public class Salary {
    // Модель таблицы "Salary"
    private int id_streamer;
    private Long cardNumber;
    private int salary;

    public Salary(){

    }

    public Salary(int id_streamer, Long cardNumber, int salary){
        this.id_streamer = id_streamer;
        this.cardNumber = cardNumber;
        this.salary = salary;
    }

    public void setId_streamer(int id_streamer) {this.id_streamer = id_streamer;}

    public void setCardNumber(Long cardNumber) {this.cardNumber = cardNumber;}

    public void setSalary(int salary) {this.salary = salary;}

    public int getId_streamer() {return id_streamer;}

    public Long getCardNumber() {return cardNumber;}

    public int getSalary() {return salary;}


    @Override
    public String toString() {
        return "Salary{" +
                "id_streamer=" + id_streamer +
                ", cardNumber=" + cardNumber +
                ", salary=" + salary +
                '}';
    }
}