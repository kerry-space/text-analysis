package kerry.textanalys.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AnalysisTextImplTest {

    @Autowired
    private AnalysisText analysisText;

    @Test
    void testTextAnalyzer() {
        String textMassa = "Banan Äpple Katt Hund Banan Hund Katt Hund";
        List<Map.Entry<String, Integer>> result = analysisText.textAnalyzer(textMassa);

        assertEquals(4, result.size());

        assertEquals("Hund", result.get(0).getKey());
        assertEquals(3, result.get(0).getValue().intValue());

        assertEquals("Banan", result.get(1).getKey());
        assertEquals(2, result.get(1).getValue().intValue());

        assertEquals("Katt", result.get(2).getKey());
        assertEquals(2, result.get(2).getValue().intValue());
    }
}