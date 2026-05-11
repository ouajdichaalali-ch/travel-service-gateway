package com.flight.api;

import com.flight.entity.FlightEntity;
import com.flight.service.FlightService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
            @QueryParam("destination") String destination) {

        return flightService.searchFlights(origin, destination);
    }
}