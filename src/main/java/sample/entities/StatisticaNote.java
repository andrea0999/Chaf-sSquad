package sample.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatisticaNote {

    private String username;
    private ArrayList<Double> note;
    private  double medie=0.0;

    public StatisticaNote(String username, ArrayList<Double> note) {
        this.username = username;
        this.note = note;
    }

    public double calculMedie(){
        DecimalFormat df = new DecimalFormat("#.##");
        double sum=0;
        for(Double nota : note)
            sum+=nota;
        medie = sum/note.size();
        return Double.parseDouble(df.format(medie));
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Double> getNote() {
        return note;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNote(ArrayList<Double> note) {
        this.note = note;
    }

    public String toString(){
        String s= "";
        for(Double nota: note)
            s+=nota+" ";
        return s;
    }

}
