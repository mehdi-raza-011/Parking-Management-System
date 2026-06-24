class FeeCalculator {

    public long calculateHours(java.time.LocalDateTime entryTime) {
        java.time.Duration d =
                java.time.Duration.between(entryTime, java.time.LocalDateTime.now());

        long hours = d.toHours();
        if (d.toMinutesPart() > 0) hours++;
        if (hours == 0) hours = 1;

        return hours;
    }

    public double calculateParkingFee(long hours, double rate) {
        return hours * rate;
    }
}
