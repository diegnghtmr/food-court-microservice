package com.pragma.powerup.foodcourtmicroservice.infrastructure.input.rest;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;
import com.pragma.powerup.foodcourtmicroservice.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Create a dish", description = "Creates a new dish for a restaurant. Validates ownership and category.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dish created successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = String.class))
        )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishResponse createDish(@Valid @RequestBody DishRequest dishRequest) {
        return dishHandler.createDish(dishRequest);
    }
}
