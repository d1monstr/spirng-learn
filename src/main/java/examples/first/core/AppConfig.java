package examples.first.core;

import examples.first.core.beans.Client;
import examples.first.core.beans.Event;
import examples.first.core.enums.EventType;
import examples.first.core.loggers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Configuration
@Import(LoggersConfig.class)
public class AppConfig {

    HashMap<EventType, EventLogger> loggers = new HashMap<>();

    @Value("${id}")
    private String id;

    @Value("${name}")
    private String name;

    @Value("${greetings}")
    private String greetings;

    @Value("#{T(examples.first.core.beans.Event).isDay() ? fileEventLogger : eventLogger}")
    private EventLogger defaultLogger;

    @Autowired
    @Qualifier("eventLogger")
    private EventLogger eventLogger;

    @Autowired
    @Qualifier("fileEventLogger")
    private EventLogger fileEventLogger;

    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger cacheFileEventLogger;

    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Bean
    public Client client() {
        return new Client(id, name);
    }

    @Bean
    public App app() {
        loggers.put(EventType.INFO, eventLogger);
        loggers.put(EventType.ERROR, combinedEventLogger);
        return new App(client(), eventLogger, defaultLogger, loggers);
    }
}
