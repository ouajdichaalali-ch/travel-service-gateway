package com.reservation.repository;

import com.reservation.entity.ReservationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<ReservationEntity> {

    public List<ReservationEntity> findByReservationId(String reservationId) {

        return list(
                "reservationId = ?1",
                reservationId
        );
    }

    public List<ReservationEntity> findCustomerReservations(
            Long customerId) {

        return list(
                "customerId = ?1",
                customerId
        );
    }

    public List<ReservationEntity> findCustomerSpecificReservation(
            Long customerId, Long reservationId) {

        return list(
                "customerId = ?1 and reservationId= ?2",
                customerId,
                reservationId
        );
    }
}