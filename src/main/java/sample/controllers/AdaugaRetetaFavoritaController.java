package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.entities.Reteta;
import sample.entities.RetetaFavorita;
import sample.exceptions.RetetaAlreadyExistsException;
import sample.services.RetetaService;
import sample.services.ReteteFavoriteService;

import java.util.ArrayList;
import java.util.List;

public class AdaugaRetetaFavoritaController {

    @FXML
    private Text username;
    @FXML
    private TextField retetaFavoritaAdaugata;
    @FXML
    private Text message;

    private static String usernameLogin;
    private List<Reteta> retete = RetetaService.getListaRetete();
    private ArrayList<String> reteteFavorite = ReteteFavoriteService.getReteteFavorite(usernameLogin);

    public AdaugaRetetaFavoritaController() throws Exception {
    }

    public static void setUsername(String username) {
        usernameLogin = username;
        System.out.println("setUsernameAdaugaRetetaFavorita: "+usernameLogin);
    }
    public String getUsernameLogin(){return usernameLogin;}

    @FXML
    public void initialize() throws Exception {
        this.username.setText("Username: "+ usernameLogin);
    }


    public void handleAdaugaRetetaFavorita(ActionEvent actionEvent) throws Exception {
        try {
            Boolean gasit=false;
            for (Reteta reteta : retete) {
                if (retetaFavoritaAdaugata.getText().equals(reteta.getNume())) {
                    for(String rf : reteteFavorite){
                        if (retetaFavoritaAdaugata.getText().equals(rf))
                            throw new RetetaAlreadyExistsException(retetaFavoritaAdaugata.getText());
                    }
                    message.setText("Reteta a fost adaugata cu succes!");
                    ReteteFavoriteService.adaugaRetetaFavorita(usernameLogin, retetaFavoritaAdaugata.getText());
                    gasit=true;
                }
                if (gasit == false)
                    message.setText("Introdu o reteta existenta in lista de Retete");
            }
        }catch (RetetaAlreadyExistsException x){
            message.setText(String.valueOf(new RetetaAlreadyExistsException(usernameLogin)));
        } catch(NumberFormatException e) {
            message.setText("Te rog introdu o reteta");

        }
    }


}
