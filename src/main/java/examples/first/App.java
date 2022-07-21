package examples.first;

public class App {
    public Client client;
    public ConsoleEventLogger eventLogger;

    public void logEvent(String msg){
        String message = msg.replaceAll(
                String.valueOf(client.getId()), client.getFullName()
        );
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client(1, "John Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }
}
