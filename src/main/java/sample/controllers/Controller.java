package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.entities.Bucatar;
import sample.services.UserService;

import java.io.IOException;


public class Controller {

    public void handleLogin(ActionEvent actionEvent) throws Exception {
        Bucatar bucatar = UserService.getBucatar();
        LoginController pagina =new LoginController();
        if(bucatar != null) {
            pagina.setBucatar(bucatar);
            Parent fxml = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void handleDetaliiCurs(ActionEvent actionEvent) throws Exception {
        Bucatar bucatar = UserService.getBucatar();
        DetaliiCursController pagina =new DetaliiCursController();
        if(bucatar != null) {
            pagina.setCurs(bucatar.getCurs());
            Parent fxml = FXMLLoader.load(getClass().getResource("/DetaliiCurs.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public void creazaInregistrareCursant(ActionEvent actionEvent) throws Exception {
        Bucatar bucatar = UserService.getBucatar();
        int catiParticipantiMomentan = UserService.getNrCursanti();
        if (bucatar != null) {
            if (bucatar.getCurs().getNrParticipanti() != catiParticipantiMomentan) {
                Parent fxml = FXMLLoader.load(getClass().getResource("/RegisterPeople.fxml"));
                Scene scene = new Scene(fxml);
                scene.setFill(Color.TRANSPARENT);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                Parent fxml = FXMLLoader.load(getClass().getResource("/NuMaiSuntLocuri.fxml"));
                Scene scene = new Scene(fxml);
                scene.setFill(Color.TRANSPARENT);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        }

    }

}
