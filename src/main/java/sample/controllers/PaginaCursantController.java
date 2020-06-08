package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sample.entities.Cursant;
import sample.entities.Reteta;

import java.io.IOException;


public class PaginaCursantController {

    @FXML
    private Text nume;
    @FXML
    private Text prenume;
    @FXML
    private Text email;
    @FXML
    private Text telefon;
    @FXML
    private Text username;
    @FXML
    private Text rol;

    private static Cursant cursant;

    public void setCursant(Cursant cursant) {
        this.cursant = cursant;
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println("PaginaCursantController initialize() cursant.getFirstName()=" +cursant.getFirstName());
        this.nume.setText(cursant.getLastName());
        this.prenume.setText(cursant.getFirstName());
        this.email.setText("Adresa Email: "+cursant.getEmail());
        this.telefon.setText("Numar de Telefon: "+cursant.getPhone());
        this.username.setText("Username: "+cursant.getUsername());
        this.rol.setText("Rol: "+cursant.getRole());
    }
}
