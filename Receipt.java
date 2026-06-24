class Receipt {
    String ticketId;
    double parkingFee;
    double fine;
    double previousFine;
    double total;

    public Receipt(String ticketId, double parkingFee,
                   double fine, double previousFine, double total) {
        this.ticketId = ticketId;
        this.parkingFee = parkingFee;
        this.fine = fine;
        this.previousFine = previousFine;
        this.total = total;
    }

    @Override
    public String toString() {
        return "=== RECEIPT ===\n" +
               "Ticket: " + ticketId + "\n" +
               "Parking Fee: RM " + parkingFee + "\n" +
               "Current Fine: RM " + fine + "\n" +
               "Previous Unpaid Fine: RM " + previousFine + "\n" +
               "TOTAL: RM " + total;
    }
}
