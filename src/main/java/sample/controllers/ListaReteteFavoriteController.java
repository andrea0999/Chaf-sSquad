package sample.controllers;

import javafx.beans.value.ObservableValue;
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
import sample.entities.RetetaFavorita;
import sample.exceptions.NuExistaReteteFavorite;
import sample.services.RetetaService;
import sample.services.ReteteFavoriteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaReteteFavoriteController {

    @FXML
    private Text message;

    @FXML
    public TableView<Reteta> retetaFavoritaTable;
    @FXML
    public TableColumn<Reteta, String> retetaFavoritaNumeColumn;
    @FXML
    public TableColumn<Reteta, String> retetaFavoritaTimpDePreparareColumn;
    @FXML
    private TextField cautareField;

    AdaugaRetetaFavoritaController userFavorit = new AdaugaRetetaFavoritaController();
    private ArrayList<String> listaReteteFavorite = ReteteFavoriteService.getReteteFavorite(userFavorit.getUsernameLogin());
    private List<Reteta> listaReteteInitiale = RetetaService.getListaRetete();

    private ObservableList<Reteta> retete = FXCollections.observableArrayList();

    public ListaReteteFavoriteController() throws Exception {
    }


    //public ArrayList getListaReteteFavorite(){return listaReteteFavorite;}

    private ArrayList<Reteta> reteteFavorite(){
        ArrayList<Reteta> listaRetete = new ArrayList<>();
         for(Reteta r :listaReteteInitiale){
             for (String rf: listaReteteFavorite){
                 if(r.getNume().equals(rf)) {
                     listaRetete.add(r);
                 }
             }
         }

         return listaRetete;
    }

    @FXML
    public void initialize() throws NuExistaReteteFavorite, IOException {
        System.out.println("ListaReteteFavoriteController initialize()");
        retetaFavoritaNumeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        retetaFavoritaTimpDePreparareColumn.setCellValueFactory(new PropertyValueFactory<>("timpDePreparare"));

        if (listaReteteFavorite != null) {
            retete = FXCollections.observableArrayList(reteteFavorite());
        }
            else
                message.setText("Nu exista Retete Preferate");


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
            sortedData.comparatorProperty().bind(retetaFavoritaTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            retetaFavoritaTable.setItems(sortedData);

        }





    @FXML
    public void handleVizualizarePaginaRetetaFavorita(ActionEvent actionEvent) throws IOException {
        Reteta retetaSelectata = retetaFavoritaTable.getSelectionModel().getSelectedItem();
        PaginaRetetaController pagina =new PaginaRetetaController();
        if(retetaSelectata!= null) {
            System.out.println("ListaReteteFavoriteController handleVizualizarePaginaReteta() reteta:"+retetaSelectata.getNume());
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


}
