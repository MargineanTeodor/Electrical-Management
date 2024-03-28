package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private int numberDevices;
    private int deviceId[]= new int[20];
    private int time[]= new int[20];
    public void readSettings() {
        String csvFile = "./configs.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            this.numberDevices=Integer.parseInt(br.readLine());
            System.out.println(numberDevices);
            int nr= this.numberDevices;
            while(nr!=0)
            {
                this.deviceId[nr]  = Integer.parseInt(br.readLine());
                this.time[nr] = Integer.parseInt((br.readLine()));
                nr--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.readSettings();
        for(int i=1;i<=main.numberDevices;i++)
        {
            System.out.println(main.time[i]);
            Function myRunnable = new Function(main.deviceId[i],main.time[i]);
            Thread t1 = new Thread(myRunnable);
            t1.start();
        }
    }
}