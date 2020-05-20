package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.io.IOException;

public class RegistrationControllerCursant {
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;


    @FXML
    public void handleRegisterCursant() throws IOException {
        System.out.println("RegistrationControllerCursant->handleRegisterAction");
        try {
            UserService.addCursant(lastname.getText(),firstname.getText(), email.getText(), phone.getText(), username.getText(), password.getText(),"Cursant" );
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
