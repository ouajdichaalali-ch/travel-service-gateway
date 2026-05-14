package com.flight.repository;

import com.flight.entity.FlightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class FlightRepository implements PanacheRepository<FlightEntity> {

    public List<FlightEntity> findByRoute(String origin, String destination) {
        return list("origin = ?1 and destination = ?2", origin, destination);
    }


    public List<FlightEntity> findByRouteAndDate(
            String origin,
            String destination,
            LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return list(
                "origin = ?1 and destination = ?2 and departureTime >= ?3 and departureTime < ?4",
                origin,
                destination,
                start,
                end
        );
    }
}