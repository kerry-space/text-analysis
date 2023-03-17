package kerry.textanalys.controller;

import kerry.textanalys.errorMessage.InvalidInputException;
import kerry.textanalys.service.AnalysisText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TextAnalzerApplicationRestTest {

    @Mock
    AnalysisText analysisText;

    @InjectMocks
    TextAnalzerApplicationRest textAnalzerApplicationRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void countWords_validInput_shouldReturnMapList() {
        // Given
        String inputText = "Banan Äpple Katt Hund Banan Hund Katt Hund";
        Map.Entry<String, Integer>[] expected = new Map.Entry[]{
                new AbstractMap.SimpleEntry<>("Hund", 3),
                new AbstractMap.SimpleEntry<>("Katt", 2),
                new AbstractMap.SimpleEntry<>("Banan", 2),
                new AbstractMap.SimpleEntry<>("Äpple", 1)
        };
        List<Map.Entry<String, Integer>> expectedResult = Arrays.asList(expected);
        when(analysisText.textAnalyzer(anyString())).thenReturn(expectedResult);

        // When
        ResponseEntity<List<Map.Entry<String, Integer>>> actualResult = textAnalzerApplicationRest.countWords(inputText);

        // Then
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertEquals(expectedResult, actualResult.getBody());
    }

    @Test
    public void countWords_invalidInput_shouldThrowInvalidInputException() {
        // Given
        String inputText = "1234";

        // When & Then
        InvalidInputException exception = org.junit.jupiter.api.Assertions.assertThrows(
                InvalidInputException.class,
                () -> textAnalzerApplicationRest.countWords(inputText)
        );
        assertEquals("Input text is null, empty or non alphabetic word", exception.getMessage());
    }

}