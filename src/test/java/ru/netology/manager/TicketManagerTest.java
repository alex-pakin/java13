package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

public class TicketManagerTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(11,10000,"LED","MCH",2);
    Ticket ticket2 = new Ticket(22,5000,"IKT","TRK",5);
    Ticket ticket3 = new Ticket(33,9000,"OAA","MCH",4);
    Ticket ticket4 = new Ticket(44,11000,"BAX","EIE",1);
    Ticket ticket5 = new Ticket(55,2000,"MCH","AER",3);
    Ticket ticket6 = new Ticket(66,1200,"IKT","VUS",9);
    Ticket ticket7 = new Ticket(77,12000,"IKT","TRK",19);
    Ticket ticket8 = new Ticket(88,1300,"QQT","LED",3);

    @Test
    public void shouldAddAndSortTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket [] actual = manager.findAll("","");
        Ticket [] expected = {ticket6,ticket8,ticket5,ticket2,ticket3,ticket1,ticket4,ticket7};

        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void shouldNotAddAlreadyExistingId () {
        manager.add(ticket1);
        manager.add(ticket2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(ticket2);
        });
    }

    @Test
    public void shouldFindTickets () {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket [] actual = manager.findAll("IKT","TRK");
        Ticket [] expected = {ticket2,ticket7};
        Assertions.assertArrayEquals(expected,actual);

        Ticket [] actual1 = manager.findAll("IKT","");
        Ticket [] expected1 = {ticket6,ticket2,ticket7};
        Assertions.assertArrayEquals(expected1,actual1);

        Ticket [] actual2 = manager.findAll("","MCH");
        Ticket [] expected2 = {ticket3,ticket1};
        Assertions.assertArrayEquals(expected2,actual2);

        Ticket [] actual3 = manager.findAll("LED","MHC");
        Ticket [] expected3 = {};
        Assertions.assertArrayEquals(expected3,actual3);

    }

    @Test
    public void shouldSearchTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket [] actual = manager.searchBy("LED");
        Ticket [] expected = {ticket1,ticket8};
        Assertions.assertArrayEquals(expected,actual);

        Ticket [] actual1 = manager.searchBy("I");
        Ticket [] expected1 = {ticket2,ticket4,ticket6,ticket7};
        Assertions.assertArrayEquals(expected1,actual1);

        Ticket [] actual2 = manager.searchBy("i");
        Ticket [] expected2 = {};
        Assertions.assertArrayEquals(expected2,actual2);

        Ticket [] actual3 = manager.searchBy("ZZZ");
        Ticket [] expected3 = {};
        Assertions.assertArrayEquals(expected3,actual3);
    }

    @Test
    public void shouldRemoveById() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        manager.removeById(22);
        manager.removeById(77);

        Ticket [] actual = manager.findAll("","");
        Ticket [] expected = {ticket6,ticket8,ticket5,ticket3,ticket1,ticket4};
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(99);
        });
    }
}
