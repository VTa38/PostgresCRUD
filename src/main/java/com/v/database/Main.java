package com.v.database;

import com.v.database.model.Streamer;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Проверка работоспособности
        System.out.println("Check select:");
        System.out.println(DBUtils.getStreamers());
        System.out.println(DBUtils.getSalary());

        System.out.println("\nCheck create streamer:");
        DBUtils.createStreamer(2,"New_Streamer", 54);
        System.out.println(DBUtils.getStreamerById(2).orElse(new Streamer()));

        System.out.println("\nCheck create salary");
        DBUtils.createSalary(2,228L,1);
        System.out.println(DBUtils.getSalaryById(2));

        System.out.println("\nCheck update salary:");
        DBUtils.updateSalary(1,383838L,999999999);
        System.out.println(DBUtils.getSalaryById(1));

        System.out.println("\nCheck update streamer:");
        DBUtils.updateStreamer(2,"ded",-1);
        System.out.println(DBUtils.getStreamerById(2));

        System.out.println("\nCheck delete:");
        DBUtils.deleteStreamer(2);
        System.out.println(DBUtils.getStreamers());
        System.out.println(DBUtils.getSalary());
    }
}
