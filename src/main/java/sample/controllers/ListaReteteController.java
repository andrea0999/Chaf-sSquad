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
import sample.entities.Reteta;
import sample.services.RetetaService;

import java.io.IOException;
import java.util.List;

public class ListaReteteController {

    @FXML
    private Text message;

    @FXML
    public TableView<Reteta> retetaTable;

    @FXML
    public TableColumn<Reteta, String> retetaNumeColumn;
    @FXML
    public TableColumn<Reteta, String> retetaTimpDePreparareColumn;

    private List<Reteta> listaRetete = RetetaService.getListaRetete();

    public ListaReteteController() throws Exception {
    }


    @FXML
    public void initialize()  {
        System.out.println("ListaReteteController initialize()");
        retetaNumeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        retetaTimpDePreparareColumn.setCellValueFactory(new PropertyValueFactory<>("timpDePreparare"));
        retetaTable.setItems(retete);
    }


    private ObservableList<Reteta> retete = FXCollections.observableArrayList(listaRetete);

    @FXML
    public void handleVizualizarePaginaReteta(ActionEvent actionEvent) throws IOException {
        Reteta retetaSelectata = retetaTable.getSelectionModel().getSelectedItem();
        PaginaRetetaController pagina =new PaginaRetetaController();
        if(retetaSelectata!= null) {
            System.out.println("ListaReteteController handleVizualizarePaginaReteta() reteta:"+retetaSelectata.getNume());
            pagina.setReteta(retetaSelectata);
            Parent fxml = FXMLLoader.load(getClass().getResource("/PaginaReteta.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati o reteta pentru vizualizare");
    }

    @FXML
    public void handleEditeazaReteta(ActionEvent actionEvent) throws IOException {
        Reteta retetaSelectata = retetaTable.getSelectionModel().getSelectedItem();
        EditeazaRetetaController pagina =new EditeazaRetetaController();
        if(retetaSelectata!= null) {
            System.out.println("ListaReteteController handleEditeazaReteta() reteta:"+retetaSelectata.getNume());
            pagina.setReteta(retetaSelectata);
            Parent fxml = FXMLLoader.load(getClass().getResource("/EditeazaReteta.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati o reteta pentru editare");
    }

    @FXML
    public void handleStergeReteta(ActionEvent actionEvent) throws IOException {
        Reteta retetaSelectata = retetaTable.getSelectionModel().getSelectedItem();
        StergeRetetaController pagina =new StergeRetetaController();
        if(retetaSelectata!= null) {
            System.out.println("ListaReteteController handleStergeReteta() reteta:"+retetaSelectata.getNume());
            pagina.setReteta(retetaSelectata);
            Parent fxml = FXMLLoader.load(getClass().getResource("/StergeReteta.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati o reteta pentru stergere");
    }

}
