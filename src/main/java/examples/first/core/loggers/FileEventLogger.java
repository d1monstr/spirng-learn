package examples.first.core.loggers;

import examples.first.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    String fileName;

    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);
        file.canWrite();
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, "\n" + event.toString(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
