package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.entities.Cursant;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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


    private static Cursant cursant;

    public RegistrationControllerCursant() throws Exception {
    }

    public static void setCursant(Cursant cursant) { RegistrationControllerCursant.cursant = cursant; }


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleVizualizareRetete(ActionEvent actionEvent) throws IOException {
        ListaReteteController.setUserRole("Cursant");
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaRetete.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void handleSchimbaParola(ActionEvent actionEvent) throws IOException, JSONException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/SchimbaParola.fxml"));
        Scene scene = new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleVizualizareSituatieNote(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/java/sample/fxml/SituatieNoteCursant.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent fxml = FXMLLoader.load(getClass().getResource("/SituatieNoteCursant.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
