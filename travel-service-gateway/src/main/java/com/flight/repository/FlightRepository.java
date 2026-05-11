package com.flight.repository;

import com.flight.entity.FlightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FlightRepository implements PanacheRepository<FlightEntity> {

    public List<FlightEntity> findByRoute(String origin, String destination) {
        return list("origin = ?1 and destination = ?2", origin, destination);
    }
}