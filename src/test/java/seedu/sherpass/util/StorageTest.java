package seedu.sherpass.util;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    private static final DateTimeFormatter parseWithTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    @Test
    public void writeSaveData_oneTask_expectFileCreated() {
        Ui ui = new Ui();
        File testFile = new File("data/test.json");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            Storage storage = new Storage("data/test.json");
            TaskList tasks = new TaskList();
            Task newTask = new Task(-1,"task_one",
                    LocalDateTime.parse("12/12/2022 12:00", parseWithTimeFormat),null,
                    null, null);
            tasks.addTask(newTask);
            storage.writeSaveData(tasks);
        } catch (IOException | TimeClashException exception) {
            exception.printStackTrace();
        }
        assertTrue(testFile.exists());
    }

    @Test
    public void load_oneTask_expectTaskList() {
        writeSaveData_oneTask_expectFileCreated();
        try {
            Storage storage = new Storage("data/test.json");
            TaskList actualList = new TaskList(storage.load());
            Task task = actualList.getTasks().get(0);
            assertEquals(task.getDescription(), "task_one");
            assertEquals(task.getByDate(), LocalDateTime.parse("12/12/2022 12:00", parseWithTimeFormat));
            assertEquals(task.getDoOnStartDateTime(), null);
            assertEquals(task.getDoOnEndDateTime(), null);
            assertEquals(task.getStatusIcon(), " ");
        } catch (InvalidInputException | IOException | JSONException exception) {
            exception.printStackTrace();
        }
    }
}
