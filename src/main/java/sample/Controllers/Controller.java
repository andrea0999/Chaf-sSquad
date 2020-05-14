package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
<<<<<<< HEAD:src/main/java/sample/Controllers/Controller.java
=======



>>>>>>> a9919cb79364093825b40170939ae9da0c98f84c:src/main/java/sample/Controller.java
    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
<<<<<<< HEAD:src/main/java/sample/Controllers/Controller.java

=======
>>>>>>> a9919cb79364093825b40170939ae9da0c98f84c:src/main/java/sample/Controller.java
    public void creazaInregistrareBucatar(ActionEvent actionEvent) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("/RegisterChef.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void creazaInregistrareCursant(ActionEvent actionEvent) throws IOException {

        Parent fxml=FXMLLoader.load(getClass().getResource("/RegisterPeople.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
