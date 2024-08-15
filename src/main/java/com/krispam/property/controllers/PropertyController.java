package com.krispam.property.controllers;

import com.krispam.property.domain.entities.PropertyEntity;
import com.krispam.property.domain.entities.UserEntity;
import com.krispam.property.services.PropertyService;
import com.krispam.property.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;

    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<PropertyEntity> createProperty(@PathVariable("userId") Long userId, @RequestBody PropertyEntity property) {
        Optional<UserEntity> user = userService.findById(userId);
        if(user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        property.setOwner(user.get());
        PropertyEntity result = propertyService.create(property);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/one/{id}")
    public ResponseEntity<PropertyEntity> getPropertyById(@PathVariable Long id) {
        Optional<PropertyEntity> property = propertyService.getPropertyById(id);
        return property
                .map(propertyEntity -> new ResponseEntity<>(propertyEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<PropertyEntity> getAllProperties() {
        return propertyService.findAll();
    }

    @GetMapping("/search")
    public List<PropertyEntity> searchByTitle(@RequestParam String title, @RequestParam String address) {
        if(!title.isEmpty()) {
            return propertyService.searchByTitle(title);
        }
        else if (!address.isEmpty()) {
            return propertyService.searchByAddress(address);
        } ;

        return  propertyService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PropertyEntity> deleteById(@PathVariable Long id) {
        propertyService.deleteById(id);
        return new ResponseEntity<>(null ,HttpStatus.OK);
    }
}
