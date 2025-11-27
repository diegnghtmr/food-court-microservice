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

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishUpdateRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishActiveRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

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

    @Operation(summary = "Update a dish", description = "Updates the price and description of an existing dish. Validates ownership.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dish updated successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Dish not found",
            content = @Content(schema = @Schema(implementation = String.class))
        )
    })
    @PutMapping("/{id}")
    public DishResponse updateDish(@PathVariable Long id, @Valid @RequestBody DishUpdateRequest dishUpdateRequest) {
        return dishHandler.updateDish(id, dishUpdateRequest);
    }

    @Operation(summary = "Enable/Disable dish", description = "Updates the active state of a dish. Requires Owner role.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dish active state updated"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User not owner"),
        @ApiResponse(responseCode = "404", description = "Dish not found")
    })
    @PutMapping("/{id}/active")
    public DishResponse updateDishActiveState(@PathVariable Long id, @Valid @RequestBody DishActiveRequest dishActiveRequest) {
        return dishHandler.updateDishActiveState(id, dishActiveRequest.getActive());
    }

    @Operation(summary = "List dishes by restaurant", description = "Retrieves active dishes for a specific restaurant with pagination.")
    @GetMapping("/restaurant/{idRestaurant}")
    public List<DishResponse> getDishesByRestaurant(
            @PathVariable Long idRestaurant,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return dishHandler.getDishesByRestaurant(idRestaurant, page, size);
    }
}
