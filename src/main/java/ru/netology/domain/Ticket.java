package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Comparable <Ticket> {

    private int id;
    private int price;
    private String Departure;
    private String Arrival;
    private int flightTime;

    @Override
    public int compareTo(Ticket otherTicket) {
        if (price < otherTicket.price) {
            return -1;
        }
        if (price > otherTicket.price) {
            return 1;
        }
        return 0;
    }

    public boolean matchesDeparture(String search) {
        if (this.getDeparture().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchesArrival(String search) {
        if (this.getArrival().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
