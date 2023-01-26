package com.example.lux.controllers;
import com.example.lux.models.GeneralObstaculo;
import com.example.lux.models.Obstaculo;
import com.example.lux.models.personaje;
import com.example.lux.models.MoveSylas;
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

    private ImageView obstaculo1 ;

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
        //Creacion de la imagen de la comida
        obstaculo1 = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/Rayo.gif")));
        obstaculo1.setFitHeight(200);
        obstaculo1.setFitWidth(40);
        obstaculo1.setLayoutX(0);
        obstaculo1.setLayoutY(0);
        rootScene.getChildren().addAll(obstaculo1);
        //Genera los obstaculos
        cantidadObstaculos[0] = new GeneralObstaculo();
        cantidadObstaculos[0].setRayo(new Obstaculo(1, 0, 0));
        cantidadObstaculos[0].addObserver(this);
        Thread hilo2 = new Thread(cantidadObstaculos[0]);
        hilo2.start();
        System.out.println(Thread.currentThread().getName());

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
        else if (o instanceof GeneralObstaculo){
            Obstaculo obstaculoPos = (Obstaculo) arg;
            switch (obstaculoPos.getId()){
                case 1:
                    obstaculo1.setLayoutY(obstaculoPos.getY());
                    obstaculo1.setLayoutX((obstaculoPos.getX()));
                break;
            }
        }
    }
}
