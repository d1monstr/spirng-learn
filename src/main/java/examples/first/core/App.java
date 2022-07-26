package examples.first.core;

import examples.first.core.beans.Client;
import examples.first.core.beans.Event;
import examples.first.core.enums.EventType;
import examples.first.core.loggers.CacheFileEventLogger;
import examples.first.core.loggers.ConsoleEventLogger;
import examples.first.core.loggers.EventLogger;
import examples.first.core.loggers.FileEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static examples.first.core.enums.EventType.*;

public class App {
    public Client client;
    public ConsoleEventLogger eventLogger;
    public EventLogger defaultLogger;
    public Map<EventType, EventLogger> loggers;

    public void logEvent(EventType type, Event event){
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public App(Client client, ConsoleEventLogger eventLogger, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        ConsoleEventLogger consoleEventLogger = (ConsoleEventLogger) ctx.getBean("eventLogger");
        FileEventLogger fileEventLogger = (FileEventLogger) ctx.getBean("fileEventLogger");
        CacheFileEventLogger cacheFileEventLogger = (CacheFileEventLogger) ctx.getBean("cacheFileEventLogger");



        event.setMsg("Some event for 1");
        app.logEvent(INFO, event);

        event.setMsg("Some event for 2");
        app.logEvent(ERROR, event);

        event.setMsg("Some event for 3");
        app.logEvent(null, event);

        ctx.close();
    }
}
