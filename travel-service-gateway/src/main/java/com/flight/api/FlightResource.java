package com.flight.api;

import com.flight.dto.ApiResponse;
import com.flight.dto.FlightDto;
import com.flight.service.FlightService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import com.flight.entity.FlightEntity;
import java.math.BigDecimal;
import java.util.List;

@Path("/api/flights")
@Produces(MediaType.APPLICATION_JSON)
@Valid
public class FlightResource {

    @Inject
    FlightService flightService;

    @GET
    @Path("/search")
    public ApiResponse<List<FlightEntity>> searchFlights(
            @QueryParam("origin")
            @NotBlank(message = "Origin is required")
            String origin,

            @QueryParam("destination")
            @NotBlank(message = "Destination is required")
            String destination,

            @QueryParam("maxPrice")
            BigDecimal maxPrice)
            {

        return new ApiResponse<>(
                true,
                flightService.searchFlights(origin, destination, maxPrice)
        );
    }
}