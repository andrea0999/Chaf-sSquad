package sample.Entities;

public class Reteta {

    private String nume;
    private String ingrediente;
    private String etapeCulinare;
    private double timpDePreparare;

    public Reteta(String nume, String ingrediente, String etapeCulinare, double timpDePreparare) {
        this.nume = nume;
        this.ingrediente = ingrediente;
        this.etapeCulinare = etapeCulinare;
        this.timpDePreparare = timpDePreparare;
    }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getIngrediente() { return ingrediente; }
    public void setIngrediente(String ingrediente) { this.ingrediente = ingrediente; }

    public String getEtapeCulinare() { return etapeCulinare; }
    public void setEtapeCulinare(String etapeCulinare) { this.etapeCulinare = etapeCulinare; }

    public double getTimpDePreparare() { return timpDePreparare; }
    public void setTimpDePreparare(double timpDePreparare) { this.timpDePreparare = timpDePreparare; }
}
