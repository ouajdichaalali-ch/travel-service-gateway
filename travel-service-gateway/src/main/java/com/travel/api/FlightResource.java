package com.travel.api;

import com.travel.dto.FlightDto;
import com.travel.service.FlightService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {

    @Inject
    FlightService flightService;

    @GET
    @Path("/search")
    public List<FlightDto> searchFlights(
            @QueryParam("origin") String origin,
            @QueryParam("destination") String destination) {

        return flightService.searchFlights(origin, destination);
    }
}