package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.entities.Reteta;

public class StergeRetetaController {

    @FXML
    private Text nume;

    private static Reteta reteta;

    public void setReteta(Reteta reteta) {
        this.reteta = reteta;
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println("StergeRetetaController initialize() reteta.getNume()=" +reteta.getNume());
        this.nume.setText(reteta.getNume());
    }


}
