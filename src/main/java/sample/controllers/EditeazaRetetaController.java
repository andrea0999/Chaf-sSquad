package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.entities.Reteta;
import sample.exceptions.RetetaAlreadyExistsException;
import sample.services.RetetaService;


public class EditeazaRetetaController {

        @FXML
        public Text message;
        @FXML
        public TextField nume;
        @FXML
        public TextField ingrediente;
        @FXML
        public TextField etapeCulinare;
        @FXML
        public TextField timpDePreparare;

        private static Reteta reteta;

        public  void setReteta(Reteta reteta) {
            this.reteta = reteta;
            System.out.println(this.reteta);
        }

        @FXML
        public void initialize() throws Exception {
            System.out.println("EditeazaRetetaController initialize() reteta.getNume()=" +reteta.getNume());
            this.nume.setText(reteta.getNume());
            this.ingrediente.setText("Ingrediente: \n"+reteta.getIngrediente());
            this.etapeCulinare.setText("Etape culinare: \n"+reteta.getEtapeCulinare());
            this.timpDePreparare.setText("Timp de preparare: "+reteta.getTimpDePreparare());
        }
    public void handleEditeazaReteta() throws Exception {
        System.out.println("EditeazaRetetaController-> handleEditeazaReteta");
        String numeEditat = new String(nume.getText());
        String ingredienteEditat = new String(ingrediente.getText().replace("Ingrediente: ",""));
        String etapeCulinareEditat = new String(etapeCulinare.getText().replace("Etape culinare: ",""));
        String timpDePreparareEditat = new String(timpDePreparare.getText().replace("Timp de preparare: ",""));

        if(!numeEditat.equals(reteta.getNume())){
            try {
                RetetaService.checkRetetaDoesNotAlreadyExist(numeEditat);
                RetetaService.editeazaReteta(reteta.getNume(),numeEditat,ingredienteEditat,etapeCulinareEditat,timpDePreparareEditat);
                message.setText("Reteta editata cu succes!");
            }catch (RetetaAlreadyExistsException e){
                message.setText("Aceasta reteta exista deja in lista de retete");
            }
        }
        else {
            RetetaService.editeazaReteta(reteta.getNume(), numeEditat, ingredienteEditat, etapeCulinareEditat, timpDePreparareEditat);
            message.setText("Reteta editata cu succes!");
        }

    }
}
