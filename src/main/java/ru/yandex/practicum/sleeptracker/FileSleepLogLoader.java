package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSleepLogLoader {
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private final List<SleepingSession> listOfSessions = new ArrayList<>();

    public void readLogsFromFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            lines.stream()
                    .map(line -> {
                        String[] parts = splitBySemicolon(line);
                        return new SleepingSession(
                                LocalDateTime.parse(parts[0], DATE_TIME_FORMATTER),
                                LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER),
                                Sleep.valueOf(parts[2])
                        );
                    })
                    .forEach(listOfSessions::add);
        }
    }

    private String[] splitBySemicolon(String line) {
        return Arrays.stream(line.split(";"))
                .map(String::trim)
                .toArray(String[]::new);
    }

    public List<SleepingSession> getListOfSessions() {
        return listOfSessions;
    }
}
