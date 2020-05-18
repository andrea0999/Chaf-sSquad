package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.entities.Curs;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.io.IOException;

public class RegistrationControllerBucatar {
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField nume;
    @FXML
    private TextField prenume;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField telefon;
    @FXML
    private TextField titlu;
    @FXML
    private TextField descriere;
    @FXML
    private TextField nrParticipanti;
    @FXML
    private TextField cost;


    @FXML
    public void handleRegisterBucatar() throws IOException {
        System.out.println("RegistrationControllerBucatar->handleRegisterAction");
        try {
            Curs curs = new Curs(titlu.getText(), descriere.getText(),Double.parseDouble(cost.getText()),Integer.parseInt(nrParticipanti.getText()));
            UserService.addBucatar(nume.getText(),prenume.getText(), email.getText(), telefon.getText(), username.getText(), password.getText(),"Bucatar",curs);
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
            System.out.println("registration message: "+registrationMessage);
        }
    }
}
