package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.entities.Cursant;

import java.io.IOException;

public class PaginaPrincipalaCursantController {

    private static Cursant cursant;
    @FXML
    private Text numeCursant;

    public static void setCursant(Cursant cursant) { PaginaPrincipalaCursantController.cursant = cursant; }

    @FXML
    public void initialize(){
        if(cursant!=null)
            numeCursant.setText(cursant.getLastName()+" "+cursant.getFirstName());
    }
    public void handleVizualizareRetete(ActionEvent actionEvent) throws IOException {
        ListaReteteCursantiController.setUsername(cursant.getUsername());
        Parent fxml= FXMLLoader.load(getClass().getResource("/ListaReteteCursanti.fxml"));
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

    public void handleVizualizareSituatieNote(ActionEvent actionEvent) throws Exception {
        SituatieNoteController pagina = new SituatieNoteController();
        pagina.setUsername(cursant.getUsername());
        Parent fxml= FXMLLoader.load(getClass().getResource("/SituatieNoteCursant.fxml"));
        Scene scene=new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void handleVizualizareReteteFavorite(ActionEvent actionEvent) throws Exception {
        System.out.println("PaginaPrincipalaCursantController handleVizualizareReteteFavorite() username="+cursant.getUsername());
        ListaReteteFavoriteController.setUsername(cursant.getUsername());
        Parent fxml = FXMLLoader.load(getClass().getResource("/ListaReteteFavorite.fxml"));
        Scene scene = new Scene(fxml);
        //scene.setFill(Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
