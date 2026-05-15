package com.reservation.api;

import com.reservation.entity.ReservationEntity;
import com.reservation.service.ReservationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "USER"})
public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GET
    public List<ReservationEntity> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GET
    @Path("/searchByCustomer")
    public List<ReservationEntity> search(
            @QueryParam("customerId") Long customerId)
             {

        return reservationService.searchReservations(customerId);
    }

    @GET
    @Path("/searchSpecificReservation")
    public List<ReservationEntity> searchCustomerSpecificReservation(
            @QueryParam("customerId") Long customerId,
            @QueryParam("reservationId") Long reservationId)
    {

        return reservationService.searchCustomerSpecificReservation(customerId,reservationId);
    }

    @GET
    @Path("/{id}")
    public Response getReservationById(@PathParam("id") Long id) {

        ReservationEntity reservation = reservationService.getReservationById(id);

        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Reservation not found")
                    .build();
        }

        return Response.ok(reservation).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response createReservation(ReservationEntity reservation) {

        ReservationEntity created = reservationService.createReservation(reservation);

        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response updateReservation(
            @PathParam("id") Long id,
            ReservationEntity updatedReservation) {

        ReservationEntity reservation =
                reservationService.updateReservation(id, updatedReservation);

        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Reservation not found")
                    .build();
        }

        return Response.ok(reservation).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteReservation(@PathParam("id") Long id) {

        boolean deleted = reservationService.deleteReservation(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Reservation not found")
                    .build();
        }

        return Response.noContent().build();
    }
}