package seedu.sherpass.util;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final DateTimeFormatter parseWithTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    @Test
    public void writeSaveData_oneTask_expectFileCreated() {
        File testFile = new File("data/test.json");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            Storage storage = new Storage("data/test.json");
            TaskList tasks = new TaskList();
            Task newTask = new Task(69, "task_one",
                    null,
                    LocalDateTime.parse("12/12/2022 12:00", parseWithTimeFormat),
                    LocalDateTime.parse("12/12/2022 12:10", parseWithTimeFormat));
            tasks.addTask(newTask, Frequency.SINGLE);
            storage.writeSaveData(tasks);
        } catch (IOException | TimeClashException | InvalidInputException exception) {
            exception.printStackTrace();
        }
        assertTrue(testFile.exists());
    }

    @Test
    public void load_oneTask_expectTaskList() {
        writeSaveData_oneTask_expectFileCreated();
        try {
            Storage storage = new Storage("data/test.json");
            TaskList actualList = new TaskList();
            try {
                storage.load(actualList);
            } catch (IOException | TimeClashException e) {
                e.printStackTrace();
            }
            Task task = actualList.getTask(0);
            assertEquals(task.getDescription(), "task_one");

            assertNull(task.getByDateTime());
            assertEquals(task.getDoOnStartDateTime(), LocalDateTime.parse("12/12/2022 12:00", parseWithTimeFormat));

            assertEquals(task.getDoOnEndDateTime(), LocalDateTime.parse("12/12/2022 12:10", parseWithTimeFormat));
            assertEquals(task.getStatusIcon(), " ");
        } catch (InvalidInputException | IOException | JSONException exception) {
            exception.printStackTrace();
        }
    }
}
