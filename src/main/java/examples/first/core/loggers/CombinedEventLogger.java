package examples.first.core.loggers;

import examples.first.core.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
