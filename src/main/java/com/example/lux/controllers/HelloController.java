package com.example.lux.controllers;
import com.example.lux.models.GeneralObstaculo;
import com.example.lux.models.personaje;
import com.example.lux.models.MoveSylas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;


public class HelloController implements Observer {

    @FXML
    private Button left;

    @FXML
    private Button preparar;

    @FXML
    private Button right;

    @FXML
    private AnchorPane rootScene;

    @FXML
    private ImageView sylas;

    private MoveSylas moveSylas;

    //Imagenes de los obstaculos
    private ImageView reyPinguino;
    private ImageView demonioPinguino;
    private ImageView rayos;

    private GeneralObstaculo moverObstaculo;
    private GeneralObstaculo [] cantidadObstaculos = new    GeneralObstaculo[2];


    @FXML
    void btnLeftOnMouse(MouseEvent event) {
        moveSylas.setLeftChange();
        moveSylas.setLeft(true);
    }

    @FXML
    void btnPrepararOnMouse(MouseEvent event) {
        //Genera los obstaculos
/*        moverObstaculo = new GeneralObstaculo();
        moverObstaculo.setRayo(new GeneralObstaculo(1, 0, 0));
        moverObstaculo.addObserver(this);
        Thread hilo1 = new Thread(moveSylas);
        hilo1.start();

        //movimiento de obstaculos
        cantidadObstaculos[0] = new GeneralObstaculo();
        cantidadObstaculos[0].setRayo(new GeneralObstaculo(2,0,0));
        cantidadObstaculos[0].addObserver(this);
        Thread hilo3 = new Thread(cantidadObstaculos[0]);
        hilo3.start();*/

        //genera a sylas
        moveSylas = new MoveSylas();
        moveSylas.setPos(new personaje(1, 319, 297));
        moveSylas.addObserver(this);
        Thread hilo1 = new Thread(moveSylas);
        hilo1.start();
    }

    @FXML
    void btnRightOnMouse(MouseEvent event) {
        moveSylas.setRightChange();
        moveSylas.setRight(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MoveSylas) {
            personaje positionPersonage = (personaje) arg;
            sylas.setLayoutX(positionPersonage.getX());
        }
    }
}
