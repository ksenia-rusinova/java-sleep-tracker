package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AvgSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {
        double avgSessionDuration = (listOfSessions == null || listOfSessions.isEmpty()) ? 0 : listOfSessions
                .stream()
                .mapToLong(session -> Duration.between(session.getDateTimeFallAsleep(), session.getDateTimeWakeUp()).toMinutes())
                .summaryStatistics()
                .getAverage();

        return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах)", (long) avgSessionDuration);
    }
}
