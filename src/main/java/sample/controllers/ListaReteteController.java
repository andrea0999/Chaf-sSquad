package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Reteta;
import sample.services.RetetaService;

import java.io.IOException;
import java.util.List;

public class ListaReteteController {

    @FXML
    public Text message;

    @FXML
    public TableView<Reteta> retetaTable;
    @FXML
    public TableColumn<Reteta, String> retetaNumeColumn;
    @FXML
    public TableColumn<Reteta, String> retetaTimpDePreparareColumn;
    @FXML
    private TextField cautareField;

    private List<Reteta> listaRetete = RetetaService.getListaRetete();
    public ListaReteteController() throws Exception {}
    private ObservableList<Reteta> retete = FXCollections.observableArrayList(listaRetete);

    private static String userRole;

    public static void setUserRole(String userRole) { ListaReteteController.userRole = userRole;}

    @FXML
    public void initialize()  {
        System.out.println("ListaReteteController initialize()");
        retetaNumeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        retetaTimpDePreparareColumn.setCellValueFactory(new PropertyValueFactory<>("timpDePreparare"));
        //ascundeButoaneCursanti();
        FilteredList<Reteta> filteredData = new FilteredList<>(retete, p -> true);
                cautareField.textProperty().addListener((observable, valoareVeche, valoareNoua) -> {
            filteredData.setPredicate(reteta -> {
                // If filter text is empty, display all.
                if (valoareNoua == null || valoareNoua.isEmpty()) {
                    return true;
                }

                // Compare name with filter text.
                String lowerCaseFilter = valoareNoua.toLowerCase();

                if (reteta.getNume().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                }
                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Reteta> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(retetaTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        retetaTable.setItems(sortedData);
    }


    @FXML
    private Button editeazaReteta=new Button();
    @FXML
    private Button stergeReteta=new Button();

   /* public void ascundeButoaneCursanti(){
        if(userRole.equals("Cursant")){
            editeazaReteta.setVisible(false);
            stergeReteta.setVisible(false);
        }
    }*/

    @FXML
    public void handleVizualizarePaginaReteta() throws IOException {
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
    public void handleEditeazaReteta() throws IOException {
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
    public void handleStergeReteta() throws IOException {
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
