package ru.yandex.practicum.sleeptracker;

public enum Chronotype {
    OWL("Сова"),
    EARLY_BIRD("Жаворонок"),
    PIGEON("Голубь");

    private final String label;

    Chronotype(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
