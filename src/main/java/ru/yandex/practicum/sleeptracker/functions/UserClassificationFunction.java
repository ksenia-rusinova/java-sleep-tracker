package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserClassificationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> listOfSessions) {
        List<SleepingSession> validNights = listOfSessions.stream()
                .filter(s -> {
                    LocalTime fallTime = s.getDateTimeFallAsleep().toLocalTime();
                    LocalDate fallDate = s.getDateTimeFallAsleep().toLocalDate();
                    LocalDate wakeDate = s.getDateTimeWakeUp().toLocalDate();

                    boolean fallInNightWindow = (fallTime.compareTo(LocalTime.of(19, 0)) >= 0)
                            || (fallTime.compareTo(LocalTime.of(11, 0)) <= 0);

                    if (!fallInNightWindow) return false;

                    boolean crossesMidnight = wakeDate.isAfter(fallDate);
                    if (crossesMidnight) {
                        LocalTime wakeTime = s.getDateTimeWakeUp().toLocalTime();
                        if (wakeTime.isAfter(LocalTime.of(11, 0))) {
                            return false;
                        }
                    }

                    return true;
                })
                .collect(Collectors.toList());

        Map<Chronotype, Long> counts = validNights.stream()
                .map(s -> {
                    LocalTime fallTime = s.getDateTimeFallAsleep().toLocalTime();
                    LocalTime wakeTime = s.getDateTimeWakeUp().toLocalTime();

                    boolean isOWL = (fallTime.compareTo(LocalTime.of(23, 0)) >= 0)
                            && (wakeTime.compareTo(LocalTime.of(9, 0)) >= 0);

                    boolean isEarlyBird = (fallTime.compareTo(LocalTime.of(22, 0)) <= 0)
                            && (wakeTime.compareTo(LocalTime.of(7, 0)) <= 0);

                    return isOWL ? Chronotype.OWL
                            : (isEarlyBird ? Chronotype.EARLY_BIRD : Chronotype.PIGEON);
                })
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        long maxCount = counts.values().stream().mapToLong(Long::longValue).max().orElse(0);
        long maxTypes = counts.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .count();

        String type;
        if (maxTypes != 1) {
            type = Chronotype.PIGEON.getLabel();
        } else {
            type = counts.entrySet().stream()
                    .filter(e -> e.getValue() == maxCount)
                    .findFirst()
                    .get()
                    .getKey()
                    .getLabel();
        }

        return new SleepAnalysisResult("Пользователь относится к хронотипу", type);
    }
}
