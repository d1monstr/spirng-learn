package examples.first.core.loggers;

import examples.first.core.beans.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Configuration
public class LoggersConfig {

    Collection<EventLogger> loggers = new ArrayList<>();

    @Value("src/main/resources/logs.txt")
    private String fileName;

    @Value("2")
    private Integer cacheSize;

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), dateFormat());
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    @Qualifier("eventLogger")
    public EventLogger eventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    @Qualifier("fileEventLogger")
    public EventLogger fileEventLogger() {
        return new FileEventLogger(fileName);
    }

    @Bean
    @Qualifier("cacheFileEventLogger")
    public EventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger(fileName, cacheSize);
    }

    @Bean
    @Qualifier("combinedEventLogger")
    public EventLogger combinedEventLogger() {
        loggers.add(eventLogger());
        loggers.add(fileEventLogger());
        return new CombinedEventLogger(loggers);
    }

}
