package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.services.UserService;
import sun.plugin.dom.html.HTMLBodyElement;

import java.io.IOException;

public class LoginController {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField roleField;

    public void creazaInregistrareBucatar(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/RegisterChef.fxml"));
        Scene scene=new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void creazaInregistrareCursant(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/RegisterPeople.fxml"));
        Scene scene=new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    public void handleLoginButtonAction(ActionEvent actionEvent)  throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Introduceti username-ul");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Introduceti parola");
            return;
        }
        if (role == null || role.isEmpty()) {
            loginMessage.setText("Introduceti rolul");
            return;
        }

        if(UserService.checkCredentiale(username,password) && role.equals("Bucatar")){
            loginMessage.setText("Autentificare cu succes");
            try {
                Parent fxml= FXMLLoader.load(getClass().getResource("/PaginaPrincipalaBucatar.fxml"));
                Scene scene=new Scene(fxml);
                scene.setFill(Color.TRANSPARENT);
                Stage primaryStage=new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            if(UserService.checkCredentiale(username,password) && role.equals("Cursant")){
                loginMessage.setText("Autentificare cu succes");
                try {
                    Parent fxml= FXMLLoader.load(getClass().getResource("/PaginaPrincipalaCursant.fxml"));
                    Scene scene=new Scene(fxml);
                    scene.setFill(Color.TRANSPARENT);
                    Stage primaryStage=new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                loginMessage.setText("Incorrect login!");
        }

        /*if (username.equals("teacher") && password.equals("teacher")) {
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                Parent viewStudentsRoot = FXMLLoader.load(getClass().getResource("../fxml/view-students.fxml"));
                Scene scene = new Scene(viewStudentsRoot, 600, 400);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }*/



    }
}

