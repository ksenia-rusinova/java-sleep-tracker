package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime dateTimeFallAsleep;
    private final LocalDateTime dateTimeWakeUp;
    private final Sleep sleepQuality;

    public SleepingSession(LocalDateTime dateTimeFallAsleep, LocalDateTime dateTimeWakeUp, Sleep sleepQuality) {
        this.dateTimeFallAsleep = dateTimeFallAsleep;
        this.dateTimeWakeUp = dateTimeWakeUp;
        this.sleepQuality = sleepQuality;
    }

    public LocalDateTime getDateTimeFallAsleep() {
        return dateTimeFallAsleep;
    }

    public LocalDateTime getDateTimeWakeUp() {
        return dateTimeWakeUp;
    }

    public Sleep getSleepQuality() {
        return sleepQuality;
    }
}
