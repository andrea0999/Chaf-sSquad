package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import sample.exceptions.RetetaAlreadyExistsException;
import sample.services.RetetaService;

public class AddController {
    @FXML
    private Text message;
    @FXML
    private TextField titluField;
    @FXML
    private TextField ingredienteField;
    @FXML
    private TextField etapeCulinareField;
    @FXML
    private TextField timpPrepareField;


    public void handleAdaugaReteteButtonAction(ActionEvent actionEvent) throws Exception {
        System.out.println("AddController-> handleAdaugaReteteButtonAction");
        try {
            RetetaService.addReteta(titluField.getText(),ingredienteField.getText(),etapeCulinareField.getText(),timpPrepareField.getText());
            message.setText("Reteta adaugata cu succes!");


        } catch (RetetaAlreadyExistsException e) {
            message.setText(e.getMessage());
            System.out.println("registration message: "+message);
        }

    }
}
