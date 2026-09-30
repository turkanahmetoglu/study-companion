package model;

public class StreakResponse {
    private int warmth;
    private int currentStreak;

    public StreakResponse(int warmth, int currentStreak) {
        this.warmth = warmth;
        this.currentStreak = currentStreak;
    }

    public int getWarmth() {
        return warmth;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }
}