public class FixedFineScheme implements FineScheme {

    @Override
    public double calculateFine(long hoursParked, boolean isReservedViolation) {

    if (isReservedViolation) return 100.0;

    if (hoursParked > 24) return 50.0;

    return 0.0;
    }

}
