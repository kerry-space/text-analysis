package kerry.textanalys.configuration;

import kerry.textanalys.service.AnalysisText;
import kerry.textanalys.service.AnalysisTextImpl;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public AnalysisText analysisText() {
        return new AnalysisTextImpl();
    }
}
