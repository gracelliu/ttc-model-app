package interactor.ticket;

import stats.entry.impl.TicketSaleStat;
import stats.persistence.StatDataController;
import ticket.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketInteractor implements ITicketInteractor {
    private final StatDataController stats;
    private final TicketDataStore dataStore;

    public TicketInteractor(TicketDataStore dataStore, StatDataController stats) {
        this.dataStore = dataStore;
        this.stats = stats;
    }

    public List<BoughtTicket> buyTickets(List<TicketType> ticketTypes) {

        List<Ticket> tickets = new ArrayList<>();

        for (TicketType ticketType : ticketTypes) {
            Ticket ticket = new Ticket(ticketType);
            tickets.add(ticket);

            dataStore.saveTicket(ticket);
        }

        for (Ticket ticket : tickets) {
            TicketSaleStat saleStat = new TicketSaleStat(ticket);
            stats.record(saleStat);
        }

        List<BoughtTicket> response = new ArrayList<>();

        for (Ticket ticket : tickets) {
            BoughtTicket boughtTicket = convertTicket(ticket);
            response.add(boughtTicket);
        }

        return response;
    }

    @Override
    public Optional<BoughtTicket> getTicket(int ticketId) {
        Ticket ticket = dataStore.getTicket(ticketId).orElse(null);
        if (ticket == null) return Optional.empty();
        return Optional.of(convertTicket(ticket));
    }

    @Override
    public Optional<BoughtTicket> activateTicket(int ticketId) {
        Ticket ticket = dataStore.getTicket(ticketId).orElse(null);
        if (ticket == null) return Optional.empty();
        if (ticket.isActivated()) return Optional.of(convertTicket(ticket));
        ticket.activate();
        dataStore.saveTicket(ticket);
        return Optional.of(new BoughtTicket(ticket.getPrice(),
                ticket.getType(),
                ticket.getId(),
                ticket.isActivated(),
                ticket.getExpiry()));
    }

    @Override
    public void cleanTickets() {
        dataStore.cleanExpiredTickets();
    }

    private BoughtTicket convertTicket(Ticket ticket) {
        return new BoughtTicket(ticket.getPrice(),
                ticket.getType(),
                ticket.getId(),
                ticket.isActivated(),
                ticket.getExpiry());
    }
}
