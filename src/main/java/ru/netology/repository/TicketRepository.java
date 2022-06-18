package ru.netology.repository;

import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public Ticket [] findAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }


    public void save(Ticket ticket) {
        if(findById(ticket.getId()) != null) {
            throw new AlreadyExistsException("Элемент с таким id: " + ticket + " уже существует в базе");
        }
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(tickets,0,tmp,0,tickets.length);
        int newTicket = tmp.length - 1;
        tmp[newTicket] = ticket;
        tickets = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Не найден элемент с id: " + id);
        }
        int length = tickets.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
        }
        tickets = tmp;
    }


}
