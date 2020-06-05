package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.entities.Cursant;
import sample.entities.Reteta;


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
        this.nume.setText(cursant.getFirstName());
        this.prenume.setText("Prenume: "+cursant.getLastName());
        this.email.setText("Adresa Email: "+cursant.getEmail());
        this.telefon.setText("Numar de Telefon: "+cursant.getPhone());
        this.username.setText("Username: "+cursant.getUsername());
        this.rol.setText("Rol: "+cursant.getRole());

    }

}
