package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.entities.Reteta;


public class PaginaRetetaController {

    @FXML
    private Text nume;
    @FXML
    private Text ingrediente;
    @FXML
    private Text etapeCulinare;
    @FXML
    private Text timpDePreparare;

    private static Reteta reteta;

    public void setReteta(Reteta reteta) {
          this.reteta = reteta;
    }

    @FXML
    public void initialize() throws Exception {
       System.out.println("PaginaRetetaController initialize() reteta.getNume()=" +reteta.getNume());
        this.nume.setText(reteta.getNume());
        this.ingrediente.setText("Ingrediente: \n"+reteta.getIngrediente());
        this.etapeCulinare.setText("Etape culinare: \n"+reteta.getEtapeCulinare());
        this.timpDePreparare.setText("Timp de preparare: "+reteta.getTimpDePreparare());
    }

}
