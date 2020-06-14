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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Reteta;
import sample.entities.RetetaFavorita;
import sample.exceptions.RetetaAlreadyExistsException;
import sample.exceptions.RetetaDejaFavoritaException;
import sample.services.RetetaService;
import sample.services.ReteteFavoriteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaReteteCursantiController {

    @FXML
    private Text message;

    @FXML
    public TableView<Reteta> retetaTable;
    @FXML
    public TableColumn<Reteta, String> retetaNumeColumn;
    @FXML
    public TableColumn<Reteta, String> retetaTimpDePreparareColumn;
    @FXML
    private TextField cautareField;

    private List<Reteta> listaRetete = RetetaService.getListaRetete();
    private ObservableList<Reteta> retete = FXCollections.observableArrayList(listaRetete);

    private static String usernameLogin;
    private ArrayList<String> reteteFavorite = ReteteFavoriteService.getReteteFavorite(usernameLogin);

    public ListaReteteCursantiController() throws Exception {
    }

    public static void setUsername(String username) {
        usernameLogin = username;
        System.out.println("setUsernameAdaugaRetetaFavorita: "+usernameLogin);
    }

    @FXML
    public void initialize() throws Exception {
        retetaNumeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        retetaTimpDePreparareColumn.setCellValueFactory(new PropertyValueFactory<>("timpDePreparare"));
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
    public void handleAdaugaRetetaFavorita(ActionEvent actionEvent) throws Exception {
        Reteta retetaSelectata = retetaTable.getSelectionModel().getSelectedItem();
        if(retetaSelectata!= null) {
            try {
                System.out.println("ListaReteteController handleStergeReteta() reteta:" + retetaSelectata.getNume());
                ReteteFavoriteService.adaugaRetetaFavorita(usernameLogin, retetaSelectata.getNume());
                message.setText("Reteta adaugata cu succes!");
            }catch (RetetaDejaFavoritaException e){
                message.setText(e.getMessage());
            }
        }
        else
            message.setText("Va rugam selectati o reteta pentru a o adauga la favorite!");
    }


}
