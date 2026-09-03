package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSessionDurationFuncTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testMinSessionDurationFunc() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        MinSessionDurationFunction minSessionDurationFunction = new MinSessionDurationFunction();
        SleepAnalysisResult result = minSessionDurationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(45, result.getValue());
        assertEquals("Минимальная продолжительность сессии (в минутах)", result.getDescription());
    }

    @Test
    void testMinSessionDurationFuncWhenInQueryNull() {
        MinSessionDurationFunction minSessionDurationFunction = new MinSessionDurationFunction();
        SleepAnalysisResult result = minSessionDurationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(0, result.getValue());
        assertEquals("Минимальная продолжительность сессии (в минутах)", result.getDescription());
    }
}
