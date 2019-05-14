package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.Attribute;
import com.rectasolutions.moving.vehicles.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    public Optional<Attribute> getAttributeById(int id){
        return attributeRepository.findById(id);
    }

    public List<Attribute> getAllAttributes(){
        return attributeRepository.findAll();
    }

    public Attribute saveAttribute(Attribute attribute){
        return attributeRepository.save(attribute);
    }

    public void deleteAttribute(Attribute attribute) {
        attributeRepository.delete(attribute);
    }
}
