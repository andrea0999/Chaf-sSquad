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
import sample.services.StatisticaNotaService;

import java.io.IOException;


public class PaginaCursantController {

    @FXML
    private Text nume;
    @FXML
    private Text username;
    @FXML
    private Text medie;
    @FXML
    private Text note;
    @FXML
    private Text email;
    @FXML
    private Text telefon;

    private static Cursant cursant;

    public void setCursant(Cursant cursant) {
        this.cursant = cursant;
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println("PaginaCursantController initialize() cursant.getFirstName()=" +cursant.getFirstName());
        this.nume.setText(cursant.getLastName()+" "+cursant.getFirstName());
        this.username.setText("Username: "+cursant.getUsername());
        if(StatisticaNotaService.getNoteCursant(cursant.getUsername()) != null){
            this.note.setText("Note: " + StatisticaNotaService.getNoteCursant(cursant.getUsername()).toString().replace("[", "").replace("]", "").replace(",", ", "));
        }
        else {
            this.note.setText("Note: -");
        }
        this.medie.setText("Medie: "+ StatisticaNotaService.getMedieCursant(cursant.getUsername()));
        this.email.setText("Email: "+cursant.getEmail());
        this.telefon.setText("Telefon: "+cursant.getPhone());
    }
}
