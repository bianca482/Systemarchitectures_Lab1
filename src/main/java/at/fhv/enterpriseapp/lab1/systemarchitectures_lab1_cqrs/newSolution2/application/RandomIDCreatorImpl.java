package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.application;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution2.application.RandomIDCreator;

import java.util.UUID;

public class RandomIDCreatorImpl implements RandomIDCreator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
