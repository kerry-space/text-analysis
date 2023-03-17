package kerry.textanalys.controller;
import kerry.textanalys.errorMessage.InvalidInputException;
import kerry.textanalys.service.AnalysisText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TextAnalzerApplicationRest {

    @Autowired
    AnalysisText analysisText;

    @PostMapping(value = "/count", consumes = "text/plain")
    public ResponseEntity<List<Map.Entry<String, Integer>>> countWords(@RequestBody String inputText) {
        // Check for empty or null input, and regular expression matches any sequence of one or more Unicode letters, include swedish ÅÖ...
        if (inputText == null || inputText.trim().equals("") || inputText.trim().isEmpty() || !inputText.matches("\\p{L}+([\\p{Zs}\\p{L}]+)*") ) {

            throw new InvalidInputException("Input text is null, empty or non alphabetic word");
        }

        List<Map.Entry<String, Integer>> mapList = analysisText.textAnalyzer(inputText);
        return ResponseEntity.ok().body(mapList);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidInputException(InvalidInputException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Required request body is missing.");
    }



}
