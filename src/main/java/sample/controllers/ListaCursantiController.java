package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Cursant;
import sample.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ListaCursantiController {

    @FXML
    private Text message;

    @FXML
    public TableView<Cursant> cursantTable;

    @FXML
    public TableColumn<Cursant, String> cursantNumeColumn;
    @FXML
    public TableColumn<Cursant, String> cursantPrenumeColumn;
    @FXML
    public TableColumn<Cursant, String> cursantRolColumn;

    private List<Cursant> listaCursanti = UserService.getListaCursanti();

    public ListaCursantiController() throws Exception {
    }


    @FXML
    public void initialize()  {
        System.out.println("ListaCursantiController initialize()");

        cursantNumeColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cursantPrenumeColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cursantRolColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        cursantTable.setItems(cursanti);
    }


    private ObservableList<Cursant> cursanti = FXCollections.observableArrayList(listaCursanti);

    @FXML
    public void handleVizualizarePaginaCursant(ActionEvent actionEvent) throws IOException {
        Cursant cursantSelectat = (Cursant) cursantTable.getSelectionModel().getSelectedItem();
        PaginaCursantController paginaC =new PaginaCursantController();
        if( cursantSelectat!= null) {
            System.out.println("ListaCursantiController handleVizualizarePaginaCursant() cursant:"+cursantSelectat.getFirstName());
            paginaC.setCursant(cursantSelectat);
            Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaCursant.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati un cursant pentru vizualizare");
    }


    public void handleAdaugaNotaCursant(ActionEvent actionEvent) throws IOException {
        System.out.println("ListaCursantiController - handleAdaugaNotaCursant");
        Cursant cursantSelectat = (Cursant) cursantTable.getSelectionModel().getSelectedItem();
        AdaugaNotaController paginaC =new AdaugaNotaController();
        if( cursantSelectat!= null) {
            System.out.println("ListaCursantiController handleAdaugaNotaCursant() cursant:"+cursantSelectat.getFirstName());
            paginaC.setCursant(cursantSelectat);
            URL url = new File("src/main/java/sample/fxml/AdaugaNota.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            //Parent fxml = FXMLLoader.load(getClass().getResource("/fxml/AdaugaNota.fxml"));
            Scene scene = new Scene(root);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati un cursant pentru vizualizare");
    }
}
