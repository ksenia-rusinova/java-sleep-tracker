package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class NumberOfSleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {

        LocalDateTime minStart = listOfSessions.getFirst().getDateTimeFallAsleep();
        LocalDateTime maxEnd = listOfSessions.getLast().getDateTimeWakeUp();

        LocalDate firstSleepDate = minStart.toLocalDate();
        LocalTime firstSleepTime = minStart.toLocalTime();
        LocalDate startNightDate = firstSleepTime.isAfter(LocalTime.NOON)
                ? firstSleepDate.plusDays(1)
                : firstSleepDate.minusDays(1);

        LocalDate endNightDate = maxEnd.toLocalDate();

        long sleeplessNights = Stream.iterate(startNightDate, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startNightDate, endNightDate) + 1)
                .filter(nightDate -> {
                    LocalDateTime nightStart = LocalDateTime.of(nightDate, LocalTime.MIDNIGHT);
                    LocalDateTime nightEnd = LocalDateTime.of(nightDate, LocalTime.of(6, 0));
                    boolean hasSleepDuringNight = listOfSessions.stream()
                            .anyMatch(s -> {
                                LocalDateTime sStart = s.getDateTimeFallAsleep();
                                LocalDateTime sEnd = s.getDateTimeWakeUp();
                                return sStart.isBefore(nightEnd) && sEnd.isAfter(nightStart);
                            });
                    return !hasSleepDuringNight;
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", (int) sleeplessNights);
    }

}
