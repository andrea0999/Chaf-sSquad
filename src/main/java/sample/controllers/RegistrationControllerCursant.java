package sample.controllers;

import com.sun.javaws.IconUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.entities.Cursant;
import sample.exceptions.NuExistaReteteFavorite;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
        if (firstname.getText() == null || firstname.getText().isEmpty()) {
            registrationMessage.setText("Introduceti prenumele!");
            return;
        }
        if (lastname.getText() == null || lastname.getText().isEmpty()) {
            registrationMessage.setText("Introduceti numele!");
            return;
        }
        if (username.getText() == null || username.getText().isEmpty()) {
            registrationMessage.setText("Introduceti un nume de utilizator!");
            return;
        }
        if (password.getText() == null || password.getText().isEmpty()) {
            registrationMessage.setText("Introduceti parola!");
            return;
        }
        if (email.getText() == null || email.getText().isEmpty()) {
            registrationMessage.setText("Introduceti emailul!");
            return;
        }
        if (phone.getText() == null || phone.getText().isEmpty()) {
            registrationMessage.setText("Introduceti numarul de telefon!");
            return;
        }
        try {
            UserService.addCursant(lastname.getText(), firstname.getText(), email.getText(), phone.getText(), username.getText(), password.getText(), "Cursant");
            registrationMessage.setText("Account created successfully!");

            Parent fxml = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
            System.out.println("registration message: " + registrationMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

