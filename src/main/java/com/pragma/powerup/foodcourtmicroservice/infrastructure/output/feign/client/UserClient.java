package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.client;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.configuration.FeignConfiguration;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "userClient",
    url = "${clients.user-service.url}",
    configuration = FeignConfiguration.class
)
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);

    @GetMapping("/users/{id}/roles/owner")
    Boolean hasOwnerRole(@PathVariable("id") Long id);
}
