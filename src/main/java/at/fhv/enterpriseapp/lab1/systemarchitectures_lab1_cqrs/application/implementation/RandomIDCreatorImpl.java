package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.implementation;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.application.RandomIDCreator;

import java.math.BigInteger;
import java.util.UUID;

public class RandomIDCreatorImpl implements RandomIDCreator {

    @Override
    public String generateId() {
        return new BigInteger(UUID.randomUUID().toString(), 10).toString();
    }
}
