package sample.entities;

import java.util.ArrayList;

public class RetetaFavorita {
    private String username;
    private ArrayList<String> reteteFavorite;

    public RetetaFavorita(String username, ArrayList<String> reteteFavorite) {
        this.username = username;
        this.reteteFavorite = reteteFavorite;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public ArrayList<String> getReteteFavorite() { return reteteFavorite; }
    public void setReteteFavorite(ArrayList<String> reteteFavorite) { this.reteteFavorite = reteteFavorite; }

}
