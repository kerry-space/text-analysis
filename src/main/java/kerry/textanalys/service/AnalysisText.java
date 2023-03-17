package kerry.textanalys.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AnalysisText {
   List<Map.Entry<String, Integer>> textAnalyzer(String text);
}
