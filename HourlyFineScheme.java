public class HourlyFineScheme implements FineScheme {

    @Override
    public double calculateFine(long hoursParked, boolean isReservedViolation) {

    double fine = 0;

    if (isReservedViolation) fine += 100;

    if (hoursParked > 24) {
        fine += (hoursParked - 24) * 20.0;
    }

    return fine;
    }

}
