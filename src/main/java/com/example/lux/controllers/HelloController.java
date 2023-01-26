package com.example.lux.controllers;
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

    @FXML
    void btnLeftOnMouse(MouseEvent event) {
        moveSylas.setLeftChange();
        moveSylas.setLeft(true);
    }

    @FXML
    void btnPrepararOnMouse(MouseEvent event) {
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
