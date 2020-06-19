package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Cursant;
import sample.services.UserService;

import java.io.IOException;

public class SchimbaValabilitateContController {
    @FXML
    private Text nume;
    @FXML
    private Text username;
    @FXML
    public Text message;
    @FXML
    private Text intrebare;
    @FXML
    private Button schimbaValabilitate = new Button();

    private static Cursant cursant;

    public static void setCursant(Cursant cursant) { SchimbaValabilitateContController.cursant = cursant; }

    @FXML
    public void initialize() throws Exception {
        this.nume.setText(cursant.getFirstName() +" "+ cursant.getLastName());
        this.username.setText("Username: "+cursant.getUsername());
        if(cursant.getValabilitate()==1) {
            intrebare.setText("Esti sigur ca vrei sa dezactivezi acest cont?");
            schimbaValabilitate.setText("Dezactiveaza");
        }
        else {
            intrebare.setText("Esti sigur ca vrei sa reactivezi acest cont?");
            schimbaValabilitate.setText("Reactiveaza");
        }
    }

    public void handleSchimbaValabilitatea() throws Exception {
        if(cursant.getValabilitate() == 1){
            UserService.schimbaValabilitateContCursant(cursant.getUsername(),0);
            message.setText("Contul a fost dezactivat cu succes!");
        }
        else{
            UserService.schimbaValabilitateContCursant(cursant.getUsername(),1);
            message.setText("Contul a fost reactivat cu succes!");
        }
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("SchimbaValabilitateContController-> handleCancel");
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaCursanti.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
