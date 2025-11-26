package com.pragma.powerup.foodcourtmicroservice.infrastructure.input.rest;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.RestaurantRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.RestaurantResponse;
import com.pragma.powerup.foodcourtmicroservice.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Create a restaurant", description = "Creates a new restaurant using owner validation from the user microservice.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = String.class))
        )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantHandler.createRestaurant(restaurantRequest);
    }

    @Operation(summary = "List restaurants", description = "Retrieves all restaurants registered in the platform.")
    @GetMapping
    public List<RestaurantResponse> getRestaurants() {
        return restaurantHandler.getRestaurants();
    }
}
