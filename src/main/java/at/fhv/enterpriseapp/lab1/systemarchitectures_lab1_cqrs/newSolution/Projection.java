package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution;

public interface Projection {

    void receiveEvent(Event event);

}
