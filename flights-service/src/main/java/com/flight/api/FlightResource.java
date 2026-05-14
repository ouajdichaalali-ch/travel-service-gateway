package com.flight.api;

import com.flight.entity.FlightEntity;
import com.flight.service.FlightService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.List;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.RolesAllowed;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "USER"})
public class FlightResource {

    private final FlightService flightService;

    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GET
    public List<FlightEntity> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GET
    @Path("/search")
    public List<FlightEntity> search(
            @QueryParam("origin") String origin,
            @QueryParam("destination") String destination,
            @QueryParam("date") String date) {

        LocalDate departureDate = LocalDate.parse(date);

        return flightService.searchFlights(
                origin,
                destination,
                departureDate
        );
    }

    @GET
    @Path("/{id}")
    public Response getFlightById(@PathParam("id") Long id) {

        FlightEntity flight = flightService.getFlightById(id);

        if (flight == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Flight not found")
                    .build();
        }

        return Response.ok(flight).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response createFlight(FlightEntity flight) {

        FlightEntity created = flightService.createFlight(flight);

        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    public Response updateFlight(@PathParam("id") Long id,
                                 FlightEntity updatedFlight) {

        FlightEntity flight =
                flightService.updateFlight(id, updatedFlight);

        if (flight == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Flight not found")
                    .build();
        }

        return Response.ok(flight).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFlight(@PathParam("id") Long id) {

        boolean deleted = flightService.deleteFlight(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Flight not found")
                    .build();
        }

        return Response.noContent().build();
    }

}