module com.example.lux {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lux to javafx.fxml;
    exports com.example.lux;
    exports com.example.lux.controllers;
    opens com.example.lux.controllers to javafx.fxml;
}