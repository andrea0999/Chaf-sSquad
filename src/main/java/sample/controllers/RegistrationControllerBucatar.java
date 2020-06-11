package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Bucatar;
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

    private static Bucatar bucatar;

    public static void setBucatar(Bucatar bucatar) { RegistrationControllerBucatar.bucatar = bucatar; }

    @FXML
    public void handleRegisterBucatar() throws IOException {
        System.out.println("RegistrationControllerBucatar->handleRegisterAction");
        try {
            Curs curs = new Curs(titlu.getText(), descriere.getText(),Double.parseDouble(cost.getText()),Integer.parseInt(nrParticipanti.getText()));
            UserService.addBucatar(prenume.getText(),nume.getText(), email.getText(), telefon.getText(), username.getText(), password.getText(),"Bucatar",curs);
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
        ListaReteteController.setUserRole("Bucatar");
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaRetete.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleAdaugaReteta(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/AdaugaReteta.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleEditeazaReteta(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/EditeazaReteta.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void handleVizualizareCursanti(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaCursanti.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void handleSchimbaParola(ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/SchimbaParola.fxml"));
        Scene scene = new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
