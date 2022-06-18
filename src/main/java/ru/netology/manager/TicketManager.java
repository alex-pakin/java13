package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;


public class TicketManager {

    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Ticket[] searchBy(String text) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, "", text) || matches(ticket, text, "")) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket ticket, String searchDeparture, String searchArrival) {
        if (ticket.matchesDeparture(searchDeparture) && ticket.matchesArrival(searchArrival)) {
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] findAll(String Departure, String Arrival) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, Departure, Arrival)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                int index = tmp.length - 1;
                tmp[index] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }


}
