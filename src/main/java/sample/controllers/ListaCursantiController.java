package sample.controllers;

import javafx.beans.value.ObservableStringValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Cursant;
import sample.services.StatisticaNotaService;
import sample.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class ListaCursantiController {

    @FXML
    public Text message;

    @FXML
    public TableView<Cursant> cursantTable;

    @FXML
    public TableColumn<Cursant, String> cursantNumeColumn;
    @FXML
    public TableColumn<Cursant, String> cursantPrenumeColumn;
    @FXML
    public TableColumn<Cursant, String> cursantNrNoteColumn;
    @FXML
    public TableColumn<Cursant, String> cursantMedieColumn;
    @FXML
    public TableColumn<Cursant, String> cursantValabilitateContColumn;
    @FXML
    private TextField cautareField;

    private List<Cursant> listaCursanti = UserService.getListaCursanti();

    public ListaCursantiController() throws Exception {
    }


    @FXML
    public void initialize() throws Exception {
        System.out.println("ListaCursantiController initialize()");
        setValabilitateText();
        calculMedie();
        numarNoteCursant();
        cursantNumeColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cursantPrenumeColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cursantNrNoteColumn.setCellValueFactory(new PropertyValueFactory<>("nrNote"));
        cursantMedieColumn.setCellValueFactory(new PropertyValueFactory<>("medie"));
        cursantValabilitateContColumn.setCellValueFactory(new PropertyValueFactory<>("valabilitateText"));
        FilteredList<Cursant> filteredData = new FilteredList<>(cursanti, p -> true);
        cautareField.textProperty().addListener((observable, valoareVeche, valoareNoua) -> {
            filteredData.setPredicate(cursant -> {
                // If filter text is empty, display all.
                if (valoareNoua == null || valoareNoua.isEmpty()) {
                    return true;
                }

                // Compare name with filter text.
                String lowerCaseFilter = valoareNoua.toLowerCase();

                if (cursant.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  firstname.
                }else if (cursant.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  lastname.
                }
                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Cursant> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(cursantTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        cursantTable.setItems(sortedData);
    }


    private ObservableList<Cursant> cursanti = FXCollections.observableArrayList(listaCursanti);

    public void setValabilitateText(){
        for(Cursant c: cursanti)
            if(c.getValabilitate()==1)
                c.setValabilitateText("Activ");
            else
                c.setValabilitateText("Inactiv");
    }

    public void calculMedie() throws Exception {
        for(Cursant c: cursanti)
            c.setMedie(StatisticaNotaService.getMedieCursant(c.getUsername()));
    }

    public void numarNoteCursant() throws Exception {
        for(Cursant c: cursanti){
            ArrayList<Double> note =StatisticaNotaService.getNoteCursant(c.getUsername());
            if(note != null)
                c.setNrNote(note.size());
        }

    }

    @FXML
    public void handleVizualizarePaginaCursant() throws IOException {
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


    public void handleAdaugaNotaCursant() throws IOException {
        System.out.println("ListaCursantiController - handleAdaugaNotaCursant()");
        Cursant cursantSelectat = (Cursant) cursantTable.getSelectionModel().getSelectedItem();
        AdaugaNotaController paginaC =new AdaugaNotaController();
        if( cursantSelectat!= null) {
            System.out.println("ListaCursantiController handleAdaugaNotaCursant() cursant:"+cursantSelectat.getFirstName());
            paginaC.setCursant(cursantSelectat);
            Parent fxml = FXMLLoader.load(getClass().getResource("/AdaugaNota.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati un cursant pentru a-i da o nota");
    }
    public void handleSchimbaValabilitateContCursant() throws IOException {
        System.out.println("ListaCursantiController - handleSchimbaValabilitateContCursant()");
        Cursant cursantSelectat = (Cursant) cursantTable.getSelectionModel().getSelectedItem();
        SchimbaValabilitateContController pagina =new SchimbaValabilitateContController();
        if( cursantSelectat!= null) {
            System.out.println("ListaCursantiController handleAdaugaNotaCursant() cursant:"+cursantSelectat.getFirstName());
            pagina.setCursant(cursantSelectat);
            Parent fxml = FXMLLoader.load(getClass().getResource("/SchimbaValabilitateContCursant.fxml"));
            Scene scene = new Scene(fxml);
            //scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            message.setText("Va rugam selectati un cursant pentru a-i activa/dezactiva contul");
    }
}
