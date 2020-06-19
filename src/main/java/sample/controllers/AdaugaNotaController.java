package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.entities.Cursant;
import sample.entities.StatisticaNote;
import sample.exceptions.NumarMaximDeNoteAtinsException;
import sample.services.StatisticaNotaService;


import java.awt.*;

public class AdaugaNotaController {
    @FXML
    private Text numeComplet;
    @FXML
    public Text username;
    @FXML
    public TextField notaAdaugata;
    @FXML
    public Text message;

    private static Cursant cursant;

    public static void setCursant(Cursant cursant) {
        AdaugaNotaController.cursant = cursant;
        System.out.println("setCursant: "+cursant.getFirstName());
    }

    @FXML
    public void initialize() throws Exception {
        this.numeComplet.setText(cursant.getFirstName() +" "+ cursant.getLastName());
        this.username.setText("Username: "+cursant.getUsername());
    }


    public void handleAdaugaNota() throws Exception {
       try{
           if(Double.parseDouble(notaAdaugata.getText())>0 && Double.parseDouble(notaAdaugata.getText()) <=10){
               message.setText("Nota a fost adaugata cu succes!");
               StatisticaNotaService.adaugaNota(cursant.getUsername(),Double.parseDouble(notaAdaugata.getText()));
           }
           else
               message.setText("Va rugam introduceti o nota in intervalul 1-10!");
       } catch(NumberFormatException e){
           message.setText("Va rugam introduceti o nota!");
       }catch (NumarMaximDeNoteAtinsException x){
           message.setText(x.getMessage());
       }
    }
}
