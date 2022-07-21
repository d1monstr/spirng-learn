package examples.first.core.loggers;

import examples.first.core.beans.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println(event);
    }
}
