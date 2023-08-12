package persistence.impl;

import entity.ticket.Ticket;
import persistence.boundary.TicketDataStore;

import java.util.*;

/**
 * This class facilitates the storage and retrieval of ticket data in memory.
 */
public class MemoryTicketDataStore implements TicketDataStore {

    /**
     * Stores the tickets in memory in a map.
     */
    private final Map<Integer, Ticket> tickets = new HashMap<>();

    @Override
    public void save(Ticket entity) {
        tickets.put(entity.getId(), entity);
    }

    @Override
    public Optional<Ticket> find(int id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    @Override
    public boolean existsById(int id) {
        return tickets.containsKey(id);
    }

    @Override
    public void delete(int id) {
        tickets.remove(id);
    }

    @Override
    public void deleteAll() {
        tickets.clear();
    }

    @Override
    public void cleanExpiredTickets() {
        tickets.values().removeIf(Ticket::isExpired);
    }
}
