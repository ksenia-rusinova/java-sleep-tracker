package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSessionDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {
        long minSessionDuration = (listOfSessions == null) ? 0 : listOfSessions
                .stream()
                .mapToLong(session -> Duration.between(session.getDateTimeFallAsleep(), session.getDateTimeWakeUp()).toMinutes())
                .summaryStatistics()
                .getMin();

        return new SleepAnalysisResult("Минимальная продолжительность сессии (в минутах)", minSessionDuration);
    }
}
