package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {
        long maxSessionDuration = (listOfSessions == null) ? 0 : listOfSessions
                .stream()
                .mapToLong(session -> Duration.between(session.getDateTimeFallAsleep(), session.getDateTimeWakeUp()).toMinutes())
                .summaryStatistics()
                .getMax();

        return new SleepAnalysisResult("Максимальная продолжительность сессии (в минутах)", maxSessionDuration);
    }
}
