package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.service;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.eventside.domain.model.Room;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.readside.queries.FreeRoomsQuery;

import java.util.List;

public interface RoomReadService {
    List<Room> handleQuery(FreeRoomsQuery freeRoomsQuery);
}
