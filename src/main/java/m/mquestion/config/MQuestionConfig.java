package m.mquestion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "m.mquestion")
@PropertySource(value = {"/WEB-INF/environment.properties"})
public class MQuestionConfig {
    
    @Autowired
    private Environment env;

}
