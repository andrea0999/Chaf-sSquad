package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.entities.StatisticaNote;
import sample.services.StatisticaNotaService;

import java.util.ArrayList;
import java.util.List;

public class SituatieNoteController {

    @FXML
    private Text listaNote;
    @FXML
    private Text medieNote;

    private static String username;
    private List<StatisticaNote> statistica = StatisticaNotaService.getListaStatistica();

    public SituatieNoteController() throws Exception {
    }


    public void setUsername(String username) {
        System.out.println("statisticaNote - setUsername - username " + username);
        this.username = username;
    }


    @FXML
    public void initialize() throws Exception {
        System.out.println("SituatieNoteController initialize() ");
        Boolean ok=false;
        for (StatisticaNote st : statistica) {
            if (st.getUsername().equals(username) &&  StatisticaNotaService.getNoteCursant(username) != null) {
                    this.listaNote.setText("Note: " + StatisticaNotaService.getNoteCursant(username).toString().replace("[", "").replace("]", "").replace(",", ", "));
                     this.medieNote.setText("Medie: " + StatisticaNotaService.getMedieCursant(username));
                    ok = true;
            }
            if(ok==false){
                this.medieNote.setText("Medie: " + StatisticaNotaService.getMedieCursant(username));
                this.listaNote.setText("Note: -");
            }

        }
    }


}
