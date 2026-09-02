package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.Sleep;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class NumbOfSesWithBadSleepQualityFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {
        int count = (int) listOfSessions
                .stream()
                .filter(session -> session.getSleepQuality() == Sleep.BAD)
                .count();
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", count);
    }
}
