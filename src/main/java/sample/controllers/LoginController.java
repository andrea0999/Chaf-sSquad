package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Bucatar;
import sample.exceptions.ContCursantInactivException;
import sample.services.UserService;

import java.io.IOException;

public class LoginController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Cursant", "Bucatar");

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox roleField;

    private static Bucatar bucatar;

    public static void setBucatar(Bucatar bucatar) { LoginController.bucatar = bucatar; }

    @FXML
    public void initialize(){
        roleField.setValue("Cursant");
        roleField.setItems(roleList);
    }

    public void creazaInregistrareBucatar(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/RegisterChef.fxml"));
        Scene scene=new Scene(fxml);
        scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void creazaInregistrareCursant(ActionEvent actionEvent) throws Exception {
        int catiParticipantiMomentan=UserService.getNrCursanti();
        if(this.bucatar.getCurs().getNrParticipanti() != catiParticipantiMomentan) {
            Parent fxml = FXMLLoader.load(getClass().getResource("/RegisterPeople.fxml"));
            Scene scene = new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else{
            Parent fxml = FXMLLoader.load(getClass().getResource("/NuMaiSuntLocuri.fxml"));
            Scene scene = new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }


    @FXML
    public void handleLoginButtonAction(ActionEvent actionEvent) throws Exception {
        String username = usernameField.getText();
        SchimbaParolaController userSchimbaParola = new SchimbaParolaController();
        userSchimbaParola.setUsername(username);

        SituatieNoteController userStatisticaNote = new SituatieNoteController();
        userStatisticaNote.setUsername(username);

        String password = passwordField.getText();
        String role = roleField.getValue().toString();
        System.out.println("LoginController handleLoginButtonAction() role="+role);

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

        if(role.equals("Bucatar") ) {
            if (UserService.checkCredentiale(username, password, role)) {
                loginMessage.setText("Autentificare cu succes");
                try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaPrincipalaBucatar.fxml"));
                    Scene scene = new Scene(fxml);
                    scene.setFill(Color.TRANSPARENT);
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                loginMessage.setText("Incorrect login!");
        }
        else {
            try{
                if(UserService.checkCredentiale(username,password,role) && role.equals("Cursant")){
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
            }catch (ContCursantInactivException e1) {
                loginMessage.setText(e1.getMessage());
            }
        }

    }


}

