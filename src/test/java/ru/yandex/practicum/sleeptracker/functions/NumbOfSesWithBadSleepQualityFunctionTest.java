package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumbOfSesWithBadSleepQualityFunctionTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testNumbOfSesWithBadSleepQuality() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        NumbOfSesWithBadSleepQualityFunction numbOfSesWithBadSleepQualityFunction = new NumbOfSesWithBadSleepQualityFunction();
        SleepAnalysisResult result = numbOfSesWithBadSleepQualityFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(2, result.getValue());
        assertEquals("Количество сессий с плохим качеством сна", result.getDescription());
    }

    @Test
    void testNumbOfSesWithBadSleepQualityWhenInQueryNull() throws IOException {
        NumbOfSesWithBadSleepQualityFunction numbOfSesWithBadSleepQualityFunction = new NumbOfSesWithBadSleepQualityFunction();
        SleepAnalysisResult result = numbOfSesWithBadSleepQualityFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals(0, result.getValue());
        assertEquals("Количество сессий с плохим качеством сна", result.getDescription());
    }
}
