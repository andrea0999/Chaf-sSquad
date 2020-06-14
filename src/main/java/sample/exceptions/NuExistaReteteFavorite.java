package sample.exceptions;

public class NuExistaReteteFavorite extends Exception {
    public NuExistaReteteFavorite() {
        super(String.format("Nu exista retete in lista cu preferinte!"));
    }
}
