package sample.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.entities.Reteta;
import sample.entities.StatisticaNote;
import sample.exceptions.NumarMaximDeNoteAtinsException;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatisticaNotaService {
    private static List<StatisticaNote> statistica = new ArrayList<>();
    private static  boolean dejaParcurs = false;


    public static Object readJsonNote(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();

        return jsonParser.parse(reader);

    }

    public static void parcurgereStatisticaNote() throws Exception {
        if(!dejaParcurs){
            JSONArray statisticaListaNote = (JSONArray) readJsonNote("StatisticaNote.json");
            System.out.println("StatisticaNotaService - parcurgereStatisticaNote");
            dejaParcurs=true;
            Iterator<JSONObject> iterator = statisticaListaNote.iterator();
            while (iterator.hasNext()) {
                JSONObject statisticaNoteJson = iterator.next();

                ArrayList<Double> note = (ArrayList<Double>) statisticaNoteJson.get("ListaNote");
                StatisticaNote st = new StatisticaNote(statisticaNoteJson.get("username").toString(),note );
                statistica.add(st);
            }
        }
    }

    public static void writeJsonStatisticaNote(String filename) throws Exception {
        JSONArray statisticaNote = new JSONArray();
        System.out.println("StatisticaNotaService - writeJsonStatisticaNote");
        for(StatisticaNote st : statistica){
            JSONObject sampleObject = new JSONObject();

            sampleObject.put("username", st.getUsername());
            JSONArray listaNoteJson = new JSONArray();

            for(Double nota : st.getNote())
                listaNoteJson.add(nota);

            sampleObject.put("ListaNote",listaNoteJson);
            statisticaNote.add(sampleObject);

        }

        Files.write(Paths.get(filename), statisticaNote.toJSONString().getBytes());
    }

    public static void adaugaNota(String username, double nota) throws Exception {
        parcurgereStatisticaNote();
        System.out.println("StatisticaNotaService - adaugaNota" + statistica.size());
        if (statistica.size() == 0) {
            StatisticaNote st = new StatisticaNote(username, new ArrayList(6));
            st.getNote().add(nota);
            statistica.add(st);
            System.out.println("adaugaNota -init - listaNote " + st.getNote());
            writeJsonStatisticaNote("StatisticaNote.json");
        } else {
            Boolean gasit = false;
            for (StatisticaNote x : statistica) {
                System.out.println("StatisticaNoteService-adaugaNota-for "+ x.getUsername());
                if (x.getUsername().equals(username)) {
                    if (x.getNote().size() > 5)
                        throw new NumarMaximDeNoteAtinsException(username);
                    else {
                        x.getNote().add(nota);
                        System.out.println("adaugaNota -else1 - listaNote " + x.getNote());
                        writeJsonStatisticaNote("StatisticaNote.json");
                        gasit=true;
                    }

                }
            }
            if(gasit== false){
                    StatisticaNote st = new StatisticaNote(username, new ArrayList(6));
                    st.getNote().add(nota);
                    statistica.add(st);
                    System.out.println("adaugaNota -else2 - listaNote " + st.getNote());
                    writeJsonStatisticaNote("StatisticaNote.json");
            }
        }
    }

}
