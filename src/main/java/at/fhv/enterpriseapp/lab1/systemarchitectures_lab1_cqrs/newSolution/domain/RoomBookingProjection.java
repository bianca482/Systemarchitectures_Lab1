package at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain;

import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.domain.model.RoomNr;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Event;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.Projection;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomBookedEvent;
import at.fhv.enterpriseapp.lab1.systemarchitectures_lab1_cqrs.newSolution.domain.events.RoomCancelledEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RoomBookingProjection implements Projection {

    HashMap<BookingNr,RoomBooking> roomBookingMap = new HashMap<BookingNr,RoomBooking>();
    HashMap<RoomNr,List<RoomBooking>> roomBookingListMap = new HashMap<RoomNr,List<RoomBooking>>();


    @Override
    public void receiveEvent(Event event) {

        if(event instanceof RoomBookedEvent){
            RoomBookedEvent roomBookedEvent = (RoomBookedEvent) event;
            if(!roomBookingMap.containsKey(roomBookedEvent.bookingNr)){
                RoomBooking roomBooking = new RoomBooking(roomBookedEvent.roomNr, roomBookedEvent.bookingNr,roomBookedEvent.StartTime, roomBookedEvent.EndTime);
                roomBookingMap.put(roomBookedEvent.bookingNr, roomBooking);


                if(!roomBookingListMap.containsKey(roomBookedEvent.roomNr)){
                    roomBookingListMap.put(roomBookedEvent.roomNr,new LinkedList<>());
                }
                List<RoomBooking> roomBookings = roomBookingListMap.get(roomBookedEvent.roomNr);
                roomBookings.add(roomBooking);
            }
        }else if(event instanceof RoomCancelledEvent){
            RoomCancelledEvent roomCancelledEvent = (RoomCancelledEvent) event;
            if(roomBookingMap.containsKey(roomCancelledEvent.bookingNr)){
                RoomBooking roomBooking=  roomBookingMap.remove(roomCancelledEvent.bookingNr);

                List<RoomBooking> roomBookings = roomBookingListMap.get(roomCancelledEvent.roomNr);
                roomBookings.remove(roomBooking);
            }
        }
    }

    public List<RoomBooking> getAllBookings() {
        return new LinkedList<>(roomBookingMap.values());
    }

    public List<RoomBooking> getAllBookings(RoomNr roomNr) {
        return roomBookingListMap.get(roomNr);
    }
}
