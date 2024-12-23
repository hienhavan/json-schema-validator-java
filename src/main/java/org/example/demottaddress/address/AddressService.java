package org.example.demottaddress.address;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final Repository repository;

    public AddressService(Repository repository) {
        this.repository = repository;
    }

    public Address createAddress(Address address) {
        repository.save(address);
        return address;
    }
    @Cacheable(value = "address", key = "#idAddress")
    public Address getAddress(Integer idAddress) {
        return repository.findById(idAddress).orElseThrow(() -> new RuntimeException("Address not found "));
    }
}
