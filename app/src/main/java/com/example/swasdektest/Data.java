package com.example.swasdektest;
import java.util.*;

public class Data{

    private int heartRate;
    List<Observer> lstObservers = new ArrayList<>();
    Data(){
        this.heartRate = 0;

    }

    public void addObserver(Observer observer){
        this.lstObservers.add(observer)
;    }

    public void doSomething(){
        while(true){
            try {
                Random random = new Random();
                Thread.sleep(10000);
                System.out.println("here at rndom");
                System.out.println();
                setHeartRate(random.nextInt(100));
                System.out.println(lstObservers.size());
                for(int i=0;i<lstObservers.size();i++){

                    System.out.println(lstObservers.get(i).getClass().toString());

                    System.out.println(lstObservers.get(i).getClass().getDeclaredMethods().toString());
                    lstObservers.get(i).update();
                }
            }
            catch(Exception e){
                System.out.println(e.getCause());
                System.out.println("Error ar Observable class Data : Thread");
            }
        }
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }


}
