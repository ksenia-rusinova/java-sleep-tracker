package ru.yandex.practicum.sleeptracker;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    private static final FileSleepLogLoader fileSleepLogLoader = new FileSleepLogLoader();
    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> listOfFunctions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        fileSleepLogLoader.readLogsFromFile("src/main/resources/sleep_log.txt");

        SleepTrackerApp app = new SleepTrackerApp();
        app.registerFunction(new TotalSessionsFunction());
        app.registerFunction(new NumbOfSesWithBadSleepQualityFunction());

        List<SleepingSession> sessions = fileSleepLogLoader.getListOfSessions();
        listOfFunctions.stream()
                .map(func -> func.apply(sessions))
                .forEach(result -> System.out.printf("%s: %s%n", result.getDescription(), result.getValue()));
    }

    private void registerFunction(Function<List<SleepingSession>, SleepAnalysisResult> function) {
        listOfFunctions.add(function);
    }
}