package com.rectasolutions.moving.vehicles.controllers;

import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    // ATTRIBUTES
    @GetMapping("/attributes")
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        return new ResponseEntity<>(attributeService.getAllAttributes(), HttpStatus.OK);
    }

    @GetMapping("/attributes/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(attributeService.getAttributeById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("/attributes")
    public ResponseEntity<String> saveAttribute(@RequestBody Attribute attribute) {
        attributeService.saveAttribute(attribute);
        return new ResponseEntity<>("New attribute is added", HttpStatus.OK);
    }

    @PutMapping("/attributes/{id}")
    public ResponseEntity<Object> updateAttribute(@RequestBody Attribute attributeBody, @PathVariable int id) {

        Optional<Attribute> attribute = attributeService.getAttributeById(id);

        if (!attribute.isPresent()) {
            return new ResponseEntity<>("The attribute is not found", HttpStatus.NOT_FOUND);
        }

        attributeBody.setId(id);
        attributeService.saveAttribute(attributeBody);

        return new ResponseEntity<>("The attribute has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/attributes/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable int id) {
        Optional<Attribute> attribute = attributeService.getAttributeById(id);
        if (!attribute.isPresent()) {
            return new ResponseEntity<>("The attribute is not found", HttpStatus.NOT_FOUND);
        }
        attributeService.deleteAttribute(attribute.get());
        return new ResponseEntity<>("The attribute has been deleted", HttpStatus.OK);
    }
}
