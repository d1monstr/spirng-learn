package examples.first.core.loggers;

import examples.first.core.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new ArrayList<>();

    @Override
    public void logEvent(Event event) {

        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    public CacheFileEventLogger(String fileName, Integer cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;

    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

}
