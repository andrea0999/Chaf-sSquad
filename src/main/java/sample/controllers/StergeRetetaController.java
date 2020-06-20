package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Reteta;
import sample.services.RetetaService;

public class StergeRetetaController {

    @FXML
    private Text nume;
    @FXML
    public Text message;

    private static Reteta reteta;

    public void setReteta(Reteta reteta) {
        this.reteta = reteta;
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println("StergeRetetaController initialize() reteta.getNume()=" +reteta.getNume());
        this.nume.setText(reteta.getNume());
    }

    public void handleStergeReteta() throws Exception {
        System.out.println("StergeRetetaController-> handleStergeReteta");
        RetetaService.stergeReteta(reteta.getNume());
        message.setText("Reteta stearsa cu succes!");
    }

    public void handleCancel(ActionEvent actionEvent) throws Exception {
        System.out.println("StergeRetetaController-> handleCancel");
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaRetete.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
