package sample.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class LoginController  {

    ObservableList<String> roleList = FXCollections.observableArrayList("Cursant", "Bucatar");

    @FXML
    public  Text loginMessage;
    @FXML
    public  Text loginMessage1;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public ChoiceBox roleField;

    private static Bucatar bucatar;

    private static int countB=0, countC=0;
    private static  boolean dejaBlocat = false;

    public static void setBucatar(Bucatar bucatar) { LoginController.bucatar = bucatar; }

    @FXML
    public void initialize(){
        roleField.setValue("Cursant");
        roleField.setItems(roleList);
    }

    @FXML
    private  Button loginButton = new Button();

    public void blocareButon(){
        new Thread(() -> {
            Platform.runLater(new Runnable() {
                public void run() {
                    loginButton.setDisable(true);
                }
            });
            try {
                Thread.sleep(30000); //5 seconds, obviously replace with your chosen time
            }
            catch(InterruptedException ex) {
            }
            Platform.runLater(new Runnable() {
                public void run() {
                    loginButton.setDisable(false);
                }
            });
        }).start();
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
    public void handleLoginButtonAction() throws Exception {
        String username = usernameField.getText();

        SchimbaParolaController userSchimbaParola = new SchimbaParolaController();
        userSchimbaParola.setUsername(username);

        SituatieNoteController userStatisticaNote = new SituatieNoteController();
        userStatisticaNote.setUsername(username);

        ListaReteteCursantiController userAdaugaRetetaFavorita = new ListaReteteCursantiController();
        userAdaugaRetetaFavorita.setUsername(username);

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
                /*try {
                    RegistrationControllerBucatar pagina =new RegistrationControllerBucatar();
                    pagina.setBucatar(UserService.getBucatar());
                    Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaPrincipalaBucatar.fxml"));
                    Scene scene = new Scene(fxml);
                    scene.setFill(Color.TRANSPARENT);
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            } else {
                if(countB==2 && !dejaBlocat) {
                    loginMessage1.setText("Ati ajuns la numarul maxim de incercari; Va rugam asteptati 30 de minute si apoi reincercati");
                    blocareButon();
                    countB=0;
                }
                else {
                    countB++;
                    loginMessage.setText("Incorrect login!");
                     }
                }
            }
        else {
            try{
                if(UserService.checkCredentiale(username,password,role) && role.equals("Cursant")){
                    PaginaPrincipalaCursantController.setCursant(UserService.getCursantByUsername(username));
                    loginMessage.setText("Autentificare cu succes");
                   /* try {
                        Parent fxml= FXMLLoader.load(getClass().getResource("/PaginaPrincipalaCursant.fxml"));
                        Scene scene=new Scene(fxml);
                        scene.setFill(Color.TRANSPARENT);
                        Stage primaryStage=new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }else {
                    if(countC==2){
                        loginMessage1.setText("Ati ajuns la numarul maxim de incercari; Va rugam asteptati 30 de minute si apoi reincercati");
                        blocareButon();
                        countC=0;
                    }
                else{
                        countC++;
                        loginMessage.setText("Incorrect login!");
                    }
                }
            }catch (ContCursantInactivException e1) {
                loginMessage.setText(e1.getMessage());
            }
        }

    }


}

