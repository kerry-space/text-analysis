package kerry.textanalys.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisTextImpl implements AnalysisText {

    @Override
    public List<Map.Entry<String, Integer>> textAnalyzer(String textMassa) {
       // String textMassa = "Banan Äpple Katt Hund Banan Hund Katt Hund";

        // Dela upp textmassan i words
        String[] words = textMassa.split(" ");

        // Skapa en hashmap för att lagra varje words och dess frekvens
        Map<String, Integer> frekvensMap = new HashMap<String, Integer>();
        for (String word : words) {
            if (frekvensMap.containsKey(word)) {
                frekvensMap.put(word, frekvensMap.get(word) + 1);
            } else {
                frekvensMap.put(word, 1);
            }
        }

        // Sortera hashmapen efter frekvens och hämta de 10 högsta frekvensorden, sedan conveter till list
        List<Map.Entry<String, Integer>> entryArrayList = new ArrayList<Map.Entry<String, Integer>>(frekvensMap.entrySet());
        Collections.sort(entryArrayList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return entryArrayList;
    }
}
