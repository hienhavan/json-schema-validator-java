package org.example.demottaddress.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.demottaddress.exception.UnauthorizedException;
import org.example.demottaddress.exception.UserNotFoundException;
import org.example.demottaddress.utils.JsonSchemaValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {
    String checkAuth = "123456a";
    private final AddressService addressService;
    private final JsonSchemaValidator jsonSchemaValidator;
    ObjectMapper objectMapper = new ObjectMapper();


    public Controller(AddressService addressService, JsonSchemaValidator jsonSchemaValidator) {
        this.addressService = addressService;
        this.jsonSchemaValidator = jsonSchemaValidator;
    }

    @PostMapping("/api/v1/address")
    @SneakyThrows
    public Address addUser(@RequestBody String userJson) {
        if (!jsonSchemaValidator.validateJson(userJson)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format or schema");
        }
        Address address = objectMapper.readValue(userJson, Address.class);
        return addressService.createAddress(address);
    }

    @GetMapping("/api/v1/get-address/{idAddress}")
    public Address getAddress(@PathVariable Integer idAddress, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.equals(checkAuth)) {
            throw new UnauthorizedException("Invalid or missing authorization");
        }
        try {
            return addressService.getAddress(idAddress);
        } catch (RuntimeException ex) {
            throw new UserNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new UserNotFoundException("An error occurred");
        }
    }
}
