package com.reservation.service;

import com.reservation.entity.ReservationEntity;
import com.reservation.repository.ReservationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ReservationService {

    @Inject
    ReservationRepository reservationRepository;

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.listAll();
    }

    public List<ReservationEntity> searchReservations(Long customerId ) {

        return reservationRepository.findCustomerReservations(customerId);
    }

    public List<ReservationEntity> searchCustomerSpecificReservation(Long customerId, Long reservationId ) {

        return reservationRepository.findCustomerSpecificReservation(customerId, reservationId);
    }

    public ReservationEntity getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public ReservationEntity createReservation(ReservationEntity reservation) {

        reservationRepository.persist(reservation);

        return reservation;
    }

    @Transactional
    public ReservationEntity updateReservation(Long id, ReservationEntity updatedReservation) {

        ReservationEntity reservation = reservationRepository.findById(id);

        if (reservation == null) {
            return null;
        }

        reservation.reservationId = updatedReservation.reservationId;
        reservation.customerId = updatedReservation.customerId;
        reservation.flightId = updatedReservation.flightId;
        reservation.hotelId = updatedReservation.hotelId;
        reservation.customerName = updatedReservation.customerName;
        reservation.customerEmail = updatedReservation.customerEmail;

        return reservation;
    }

    @Transactional
    public boolean deleteReservation(Long id) {
        return reservationRepository.deleteById(id);
    }
}