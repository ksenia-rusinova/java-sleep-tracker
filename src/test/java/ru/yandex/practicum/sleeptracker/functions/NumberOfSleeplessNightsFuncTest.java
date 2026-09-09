package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfSleeplessNightsFuncTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testNumbOfSleeplessNightsFunc() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        NumberOfSleeplessNightsFunction numberOfSleeplessNightsFunction = new NumberOfSleeplessNightsFunction();
        SleepAnalysisResult result = numberOfSleeplessNightsFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(20, result.getValue());
        assertEquals("Количество бессонных ночей", result.getDescription());
    }
}
