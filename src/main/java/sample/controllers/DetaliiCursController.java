package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.entities.Curs;
import sample.services.UserService;

public class DetaliiCursController {
    @FXML
    private Text titlu;
    @FXML
    private Text descriere;
    @FXML
    private Text cost;
    @FXML
    private Text nrParticipanti;

    private static Curs curs;

    public void setCurs(Curs curs) {
        this.curs = curs;
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println("DetaliiCursController initialize() curs.getTitlu()=" +curs.getTitlu());
        this.titlu.setText(curs.getTitlu());
        this.descriere.setText(curs.getDescriere());
        this.cost.setText("Costul este de "+curs.getCost()+"RON/persoana");
        this.nrParticipanti.setText("Grabeste-te! Numarul maxim de participanti este "+curs.getNrParticipanti());
    }
    public void creazaInregistrareCursant(ActionEvent actionEvent) throws Exception {
        int catiParticipantiMomentan = UserService.getNrCursanti();
        if (curs.getNrParticipanti() != catiParticipantiMomentan) {
            Parent fxml = FXMLLoader.load(getClass().getResource("/RegisterPeople.fxml"));
            Scene scene = new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            Parent fxml = FXMLLoader.load(getClass().getResource("/NuMaiSuntLocuri.fxml"));
            Scene scene = new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

}
