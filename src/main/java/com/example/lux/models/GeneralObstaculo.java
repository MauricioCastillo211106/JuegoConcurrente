package com.example.lux.models;

import java.util.Observable;
import java.util.Random;

public class GeneralObstaculo extends Observable implements Runnable{

    private Obstaculo rayo;
    private boolean status;
    private Random random;



    public void setRayo(Obstaculo rayo){
        this.rayo= rayo;
    }
    public GeneralObstaculo(){
        status= true;
    }
    int numero = (int)(Math.random()*640+1);

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void run() {
        rayo.setX(numero);

        while (status){

            rayo.setY((rayo.getY() + 4));
            setChanged();
            notifyObservers(rayo);

            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ptm");
            if (rayo.getY() >=200){
                numero = (int)(Math.random()*640+1);
                rayo.setY(0);
                rayo.setX(numero);
                System.out.println("a");
            }
            System.out.println("pasa");
        }

    }
}
