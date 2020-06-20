package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import sample.entities.Reteta;
import sample.entities.User;
import sample.exceptions.RetetaAlreadyExistsException;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.RetetaService;

import java.util.List;
import java.util.Objects;

public class AddController {
    @FXML
    public Text message;
    @FXML
    public TextField titluField;
    @FXML
    public TextField ingredienteField;
    @FXML
    public TextField etapeCulinareField;
    @FXML
    public TextField timpPrepareField;


    public void handleAdaugaReteteButtonAction() throws Exception {
        System.out.println("AddController-> handleAdaugaReteteButtonAction");
        int errors=0;
        if(titluField.getText().isEmpty() || titluField.getText() == null){
            errors++;
            message.setText("Introduceti numele retetei!");
        }
        else if(ingredienteField.getText().isEmpty() || ingredienteField.getText() == null){
            errors++;
            message.setText("Introduceti ingredientele retetei!");
        }
        else if(etapeCulinareField.getText().isEmpty() || etapeCulinareField.getText() == null){
            errors++;
            message.setText("Introduceti etapele culinare ale retetei!");
        }
        else if(timpPrepareField.getText().isEmpty() || timpPrepareField.getText() == null){
            errors++;
            message.setText("Introduceti timpul de preparare ale retetei!");
        }
        if(errors == 0) {
            try {
                RetetaService.addReteta(titluField.getText(), ingredienteField.getText(), etapeCulinareField.getText(), timpPrepareField.getText());
                message.setText("Reteta adaugata cu succes!");
            } catch (RetetaAlreadyExistsException e) {
                message.setText(e.getMessage());
                System.out.println("registration message: " + message);
            }
        }

    }
}
