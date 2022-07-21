package examples.first.core;

import examples.first.core.beans.Client;
import examples.first.core.loggers.ConsoleEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public Client client;
    public ConsoleEventLogger eventLogger;

    public void logEvent(String msg){
        String message = msg.replaceAll(
                String.valueOf(client.getId()), client.getFullName()
        );
        eventLogger.logEvent(message);
    }

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }
}
