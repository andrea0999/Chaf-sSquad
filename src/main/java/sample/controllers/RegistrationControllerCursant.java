package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
        } catch (UsernameAlreadyExistsException e) {
           registrationMessage.setText(e.getMessage());
            System.out.println("registration message: "+registrationMessage);
        }
    }
}
