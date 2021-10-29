package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.Domain.Command;

public class CancelBookingCommand {
    private String _reservationNr;

    public CancelBookingCommand(String reservationNr) {
        _reservationNr = reservationNr;
    }
}
