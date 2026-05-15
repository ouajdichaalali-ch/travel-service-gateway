package com.hotel.api;

import com.hotel.entity.HotelEntity;
import com.hotel.service.HotelService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/hotels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "USER"})
public class HotelResource {

    private final HotelService hotelService;

    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GET
    public List<HotelEntity> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GET
    @Path("/search")
    public List<HotelEntity> search(
            @QueryParam("city") String city,
            @QueryParam("checkIn") String checkIn,
            @QueryParam("checkOut") String checkOut) {

        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);

        return hotelService.searchHotels(
                city,
                checkInDate,
                checkOutDate
        );
    }

    @GET
    @Path("/{id}")
    public Response getHotelById(@PathParam("id") Long id) {

        HotelEntity hotel = hotelService.getHotelById(id);

        if (hotel == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();
        }

        return Response.ok(hotel).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response createHotel(HotelEntity hotel) {

        HotelEntity created = hotelService.createHotel(hotel);

        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response updateHotel(
            @PathParam("id") Long id,
            HotelEntity updatedHotel) {

        HotelEntity hotel =
                hotelService.updateHotel(id, updatedHotel);

        if (hotel == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();
        }

        return Response.ok(hotel).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteHotel(@PathParam("id") Long id) {

        boolean deleted = hotelService.deleteHotel(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Hotel not found")
                    .build();
        }

        return Response.noContent().build();
    }
}