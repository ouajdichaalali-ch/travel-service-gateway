package com.hotel.service;

import com.hotel.entity.HotelEntity;
import com.hotel.repository.HotelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class HotelService {

    @Inject
    HotelRepository hotelRepository;

    public List<HotelEntity> getAllHotels() {
        return hotelRepository.listAll();
    }

    public List<HotelEntity> searchHotels(
            String city,
            LocalDate checkIn,
            LocalDate checkOut) {

        return hotelRepository.findAvailableHotels(
                city,
                checkIn,
                checkOut
        );
    }

    public HotelEntity getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    @Transactional
    public HotelEntity createHotel(HotelEntity hotel) {

        hotelRepository.persist(hotel);

        return hotel;
    }

    @Transactional
    public HotelEntity updateHotel(Long id, HotelEntity updatedHotel) {

        HotelEntity hotel = hotelRepository.findById(id);

        if (hotel == null) {
            return null;
        }

        hotel.hotelId = updatedHotel.hotelId;
        hotel.hotelName = updatedHotel.hotelName;
        hotel.city = updatedHotel.city;
        hotel.country = updatedHotel.country;
        hotel.availableRooms = updatedHotel.availableRooms;
        hotel.pricePerNight = updatedHotel.pricePerNight;
        hotel.rating = updatedHotel.rating;
        hotel.availableFrom = updatedHotel.availableFrom;
        hotel.availableTo = updatedHotel.availableTo;

        return hotel;
    }

    @Transactional
    public boolean deleteHotel(Long id) {
        return hotelRepository.deleteById(id);
    }
}