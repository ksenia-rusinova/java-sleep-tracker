package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.FileSleepLogLoader;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserClassificationFunctionTest {
    private FileSleepLogLoader fileSleepLogLoader;

    @BeforeEach
    void setUp() {
        fileSleepLogLoader = new FileSleepLogLoader();
    }

    @Test
    void testUserClassificationFuncEarlyBird() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/log_for_early_bird.txt");

        UserClassificationFunction userClassificationFunction = new UserClassificationFunction();
        SleepAnalysisResult result = userClassificationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals("Жаворонок", result.getValue());
        assertEquals("Пользователь относится к хронотипу", result.getDescription());
    }

    @Test
    void testUserClassificationFuncOwl() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/log_for_owl.txt");

        UserClassificationFunction userClassificationFunction = new UserClassificationFunction();
        SleepAnalysisResult result = userClassificationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals("Сова", result.getValue());
        assertEquals("Пользователь относится к хронотипу", result.getDescription());
    }

    @Test
    void testUserClassificationFuncPigeon() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/log_for_pigeon.txt");

        UserClassificationFunction userClassificationFunction = new UserClassificationFunction();
        SleepAnalysisResult result = userClassificationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals("Голубь", result.getValue());
        assertEquals("Пользователь относится к хронотипу", result.getDescription());
    }

    @Test
    void testUserClassificationFuncQuantityMatches() throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/log_quant_of_types_matches.txt");

        UserClassificationFunction userClassificationFunction = new UserClassificationFunction();
        SleepAnalysisResult result = userClassificationFunction.apply(fileSleepLogLoader.getListOfSessions());

        assertEquals("Голубь", result.getValue());
        assertEquals("Пользователь относится к хронотипу", result.getDescription());
    }
}
