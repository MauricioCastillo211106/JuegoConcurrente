package com.example.lux.models;

import java.util.Observable;
import java.util.Random;

public class GeneralPinguino extends Observable implements Runnable {
    private Pinguino pinguin;
    private boolean status;
    private Random random;

    public void setPinguin(Pinguino pinguin) {
        this.pinguin = pinguin;
    }

    public GeneralPinguino() {
        status = true;
    }

    int numero = (int) (Math.random() * 640 + 1);

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public void run() {
        pinguin.setX(numero);
        while (status) {
            pinguin.setY(pinguin.getY() + 4);
            setChanged();
            notifyObservers(pinguin);
            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (pinguin.getY() >= 300) {
                numero = (int) (Math.random() * 640 + 1);
                pinguin.setY(0);
                pinguin.setX(numero);
            }
        }
    }
}
