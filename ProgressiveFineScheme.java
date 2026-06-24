public class ProgressiveFineScheme implements FineScheme {

    @Override
    public double calculateFine(long hoursParked, boolean isReservedViolation) {

    double fine = 0;

    if (isReservedViolation) fine += 100;

    if (hoursParked > 24) fine += 50;
    if (hoursParked > 48) fine += 100;
    if (hoursParked > 72) fine += 150;

    return fine;
    }
    
}
