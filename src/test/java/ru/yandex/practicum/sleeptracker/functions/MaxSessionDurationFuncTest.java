package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSessionDurationFuncTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testMaxSessionDurationFunc() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        MaxSessionDurationFunction maxSessionDurationFunction = new MaxSessionDurationFunction();
        SleepAnalysisResult result = maxSessionDurationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(500, result.getValue());
        assertEquals("Максимальная продолжительность сессии (в минутах)", result.getDescription());
    }

    @Test
    void testMaxSessionDurationFuncWhenInQueryNull() {
        MaxSessionDurationFunction maxSessionDurationFunction = new MaxSessionDurationFunction();
        SleepAnalysisResult result = maxSessionDurationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(0, result.getValue());
        assertEquals("Максимальная продолжительность сессии (в минутах)", result.getDescription());
    }
}
