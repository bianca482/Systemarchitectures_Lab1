package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.impl;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.service.RandomIDCreator;

import java.util.UUID;

public class RandomIDCreatorImpl implements RandomIDCreator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
