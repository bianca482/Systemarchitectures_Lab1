package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.*;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.services.queries.FreeRoomsQuery;

import java.util.LinkedList;
import java.util.List;

public class RoomReadService {

    private RoomBookingProjection roomBookingProjection;
    private RoomInfoProjection roomInfoProjection;

    public RoomReadService(RoomBookingProjection roomBookingProjection, RoomInfoProjection roomInfoProjection) {
        this.roomBookingProjection = roomBookingProjection;
        this.roomInfoProjection = roomInfoProjection;
    }

    public  List<RoomInfo> query(FreeRoomsQuery freeRoomsQuery){
        List<RoomInfo> roomInfoList = roomInfoProjection.getAllRooms();

        List<RoomInfo> freeRooms = new LinkedList<>();
        for(RoomInfo roomInfo: roomInfoList){
            if(roomInfo.getMaxCapacity()>=freeRoomsQuery.getNumberOfPeoples()){
                List<RoomBooking> allBookings = roomBookingProjection.getAllBookings(roomInfo.getRoomNr());
                for (RoomBooking roomBooking : allBookings) {
                    if((roomBooking.getStartTime().isAfter(freeRoomsQuery.getStartTime()) && roomBooking.getStartTime().isBefore(freeRoomsQuery.getEndTime()))  ||
                            (roomBooking.getEndTime().isAfter(freeRoomsQuery.getStartTime()) && roomBooking.getEndTime().isBefore(freeRoomsQuery.getEndTime()))){
                        // do nothing
                    }else{
                        freeRooms.add(roomInfo);
                    }
                }
            }
        }
        return freeRooms;
    }
}
