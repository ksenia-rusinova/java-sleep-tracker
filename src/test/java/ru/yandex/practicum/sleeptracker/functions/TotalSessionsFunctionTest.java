package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalSessionsFunctionTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testTotalSessionsFunction() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        TotalSessionsFunction totalSessionsFunction = new TotalSessionsFunction();
        SleepAnalysisResult result = totalSessionsFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(13, result.getValue());
        assertEquals("Количество сессий сна за представленный период", result.getDescription());
    }
}
