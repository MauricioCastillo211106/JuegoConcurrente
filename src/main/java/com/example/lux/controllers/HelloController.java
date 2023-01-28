package com.example.lux.controllers;

import com.example.lux.models.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

    private ImageView obstaculo1, obstaculo2, obstaculo3;


    //Imagenes de los obstaculos
    private ImageView reyPinguino;
    private ImageView demonioPinguino;
    private ImageView rayos;

    private GeneralObstaculo moverObstaculo;
    private GeneralObstaculo[] cantidadObstaculos = new GeneralObstaculo[1];
    private GeneralPinguino[] cantidadPinguinos = new GeneralPinguino[2];


    @FXML
    void btnLeftOnMouse(MouseEvent event) {
        moveSylas.setLeftChange();
        moveSylas.setLeft(true);
    }

    @FXML
    void btnPrepararOnMouse(MouseEvent event) {
        //Creacion de la imagen de la comida
        rayos = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/ObstaculoRayo.gif")));
        rayos.setFitHeight(200);
        rayos.setFitWidth(20);
        rayos.setLayoutX(0);
        rayos.setLayoutY(0);
        rootScene.getChildren().addAll(rayos);

        //Creacion de la imagen de la Pinguino
        demonioPinguino = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/ObstaculoPinguino.gif")));
        demonioPinguino.setFitHeight(100);
        demonioPinguino.setFitWidth(100);
        demonioPinguino.setLayoutX(0);
        demonioPinguino.setLayoutY(0);
        rootScene.getChildren().addAll(demonioPinguino);

        reyPinguino = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/ObstaculoReyPinguino.gif")));
        reyPinguino.setFitHeight(100);
        reyPinguino.setFitWidth(100);
        reyPinguino.setLayoutX(0);
        reyPinguino.setLayoutY(0);
        rootScene.getChildren().addAll(reyPinguino);

        //Genera los obstaculos
        cantidadObstaculos[0] = new GeneralObstaculo();
        cantidadObstaculos[0].setRayo(new Obstacle(1, 0, 0));
        cantidadObstaculos[0].addObserver(this);
        Thread hilo2 = new Thread(cantidadObstaculos[0]);
        hilo2.start();
        System.out.println(Thread.currentThread().getName());

        cantidadPinguinos[0] = new GeneralPinguino();
        cantidadPinguinos[0].setPinguin(new Pinguino(1, 0, 0));
        cantidadPinguinos[0].addObserver(this);
        Thread hilo3 = new Thread(cantidadPinguinos[0]);
        hilo3.start();
        cantidadPinguinos[1] = new GeneralPinguino();
        cantidadPinguinos[1].setPinguin(new Pinguino(2, 0, 0));
        cantidadPinguinos[1].addObserver(this);
        Thread hilo4 = new Thread(cantidadPinguinos[1]);
        hilo4.start();

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
            Platform.runLater(() -> sylas.setLayoutX(positionPersonage.getX()));
        } else if (o instanceof GeneralObstaculo) {
            Obstacle obstaculoPos = (Obstacle) arg;
            Platform.runLater(() -> rayos.setLayoutY(obstaculoPos.getY()));
            Platform.runLater(() -> rayos.setLayoutX((obstaculoPos.getX())));
        }

        if (o instanceof GeneralPinguino) {
            Pinguino obstaculoPinguino = (Pinguino) arg;

            switch (obstaculoPinguino.getId()) {

                case 1:
                    Platform.runLater(() -> demonioPinguino.setLayoutY(obstaculoPinguino.getY()));
                    Platform.runLater(() -> demonioPinguino.setLayoutX((obstaculoPinguino.getX())));
                    break;
                case 2:
                    Platform.runLater(() -> reyPinguino.setLayoutY(obstaculoPinguino.getY()));
                    Platform.runLater(() -> reyPinguino.setLayoutX((obstaculoPinguino.getX())));
                    break;
            }
        }
        if(rayos.getBoundsInParent().intersects(sylas.getBoundsInParent())){
            moveSylas.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadPinguinos[0].setStatus(false);
            cantidadPinguinos[1].setStatus(false);
        }
        if(reyPinguino.getBoundsInParent().intersects(sylas.getBoundsInParent())){
            moveSylas.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadPinguinos[0].setStatus(false);
            cantidadPinguinos[1].setStatus(false);
        }
        if(demonioPinguino.getBoundsInParent().intersects(sylas.getBoundsInParent())){
            moveSylas.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadPinguinos[0].setStatus(false);
            cantidadPinguinos[1].setStatus(false);
        }
    }
}
