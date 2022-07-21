package examples.first.core.loggers;

import examples.first.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    String fileName;

    private File file;

    public void init() throws IOException {
        this.file = new File(fileName);
        file.canWrite();
    }

    @Override
    public void logEvent(Event event) {
//        FileUtils.writeStringToFile();
    }
}
