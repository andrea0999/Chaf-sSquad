package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import sample.entities.User;
import sample.services.UserService;

import java.util.List;
import java.util.Objects;

public class SchimbaParolaController {

    @FXML
    private Text message;
    @FXML
    private PasswordField parolaVecheField;
    @FXML
    private PasswordField parolaNouaField;
    @FXML
    private PasswordField parolaNouaConfirmareField;

    private List<User> listaUsers = UserService.getListaUsers();
    private  static String username;

    public SchimbaParolaController() throws Exception { }


    public  void setUsername(String username){
        System.out.println("schimbaParola - setUsername - username "+username);
        this.username=username;
    }

    public void handleSchimbaParolaAction(ActionEvent actionEvent) throws Exception {
        System.out.println("SchimbaParolaController - handleSchimbaParolaAction usename "+ this.username);
        //LoginController usernameUser = new LoginController();

        for(User user: listaUsers){
            if(user.getUsername().equals(username)){
                System.out.println(user.getPassword());
                if(Objects.equals(user.getPassword(), UserService.encodePassword(user.getUsername(),parolaVecheField.getText()))) {
                    System.out.println(parolaVecheField.getText());
                    if(!parolaVecheField.getText().equals(parolaNouaField.getText())) {

                        if (Objects.equals(parolaNouaField.getText(), parolaNouaConfirmareField.getText())) {
                            System.out.println(parolaNouaField.getText());
                            UserService.schimbaParola(user.getUsername(), parolaNouaField.getText());
                            message.setText("Parola schimbata cu succes!");
                            if (user.getRole().equals("Bucatar")) {
                                Thread.sleep(5000);
                                Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaPrincipalaBucatar.fxml"));
                               Scene scene = new Scene(fxml);
                                //scene.setFill(Color.TRANSPARENT);
                                Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);
                                 primaryStage.show();
                            } else {
                                Thread.sleep(5000);
                                Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaPrincipalaCursant.fxml"));
                               Scene scene = new Scene(fxml);
                                //scene.setFill(Color.TRANSPARENT);
                                Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);
                                 primaryStage.show();
                            }
                        }
                        else{
                            message.setText("Parola noua nu este aceeasi in ambele campuri; Incercati din nou");
                        }
                    }
                    else
                        message.setText("Aceasta parola este o parola veche; Introduceti alta!");
            }
                else
                    message.setText("Parola veche incorecta; Reincercati");

            }
        }

    }
}
