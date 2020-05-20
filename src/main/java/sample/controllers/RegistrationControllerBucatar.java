package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

            Parent fxml= FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene scene=new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage=new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
            System.out.println("registration message: "+registrationMessage);
        }
    }
}
