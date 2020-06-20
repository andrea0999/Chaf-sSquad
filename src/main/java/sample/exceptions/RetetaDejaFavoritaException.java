package sample.exceptions;

public class RetetaDejaFavoritaException extends Exception{
    private String nume;

    public RetetaDejaFavoritaException(String nume) {
        super(String.format("Reteta %s este deja printre favoritele tale!", nume));
        this.nume = nume;
    }
}
