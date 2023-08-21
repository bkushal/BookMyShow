package com.lld.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.bookmyshow.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
