package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.Attribute;
import com.rectasolutions.moving.vehicles.repositories.AttributeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

class AttributeServiceTest {

    List<Attribute> attributeList = new ArrayList<>();

    @Mock
    private AttributeRepository attributeRepository;

    @Mock
    private AttributeService attributeService;

    @BeforeEach
    void setUp() {
        attributeService = new AttributeService(attributeRepository);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void listAttributes(){
        Attribute attribute1 = new Attribute();
        attribute1.setId(1);
        attribute1.setAttributeName("length");
        Attribute attribute2 = new Attribute();
        attribute2.setId(2);
        attribute2.setAttributeName("weight");
        attributeList.add(attribute1);
        attributeList.add(attribute2);
    }

    @Test
    void getAttributeById() {
        Optional<Attribute> exampleAttribute = Optional.of(attributeList.get(0));
        when(attributeService.getAttributeById(1)).thenReturn(exampleAttribute);
        assertNotNull(attributeService.getAttributeById(1));
        assertNotNull(attributeService.getAttributeById(1).get());
        assertEquals(1, attributeService.getAttributeById(1).get().getId());
        assertEquals("length", attributeService.getAttributeById(1).get().getAttributeName());
    }

    @Test
    void getAllAttributes() {
        when(attributeService.getAllAttributes()).thenReturn(attributeList);
        assertNotNull(attributeService.getAllAttributes());
        assertNotNull(attributeService.getAllAttributes().get(0));
        assertNotNull(attributeService.getAllAttributes().get(1));
        assertEquals(1,attributeService.getAllAttributes().get(0).getId());
        assertEquals(2,attributeService.getAllAttributes().get(1).getId());
        assertEquals("length",attributeService.getAllAttributes().get(0).getAttributeName());
        assertEquals("weight",attributeService.getAllAttributes().get(1).getAttributeName());
    }

    @Test
    void saveAttribute() {
        Attribute newAttribute = new Attribute();
        newAttribute.setId(3);
        newAttribute.setAttributeName("height");
        when(attributeService.saveAttribute(any(Attribute.class))).thenReturn(newAttribute);
        assertNotNull(attributeService.saveAttribute(new Attribute()));
        assertEquals(3, attributeService.saveAttribute(new Attribute()).getId());
        assertEquals("height", attributeService.saveAttribute(new Attribute()).getAttributeName());
    }

    @Test
    void deleteAttribute() {
        doNothing().when(attributeService).deleteAttribute(any(Attribute.class));
        attributeService.deleteAttribute(new Attribute());
        verify(attributeService).deleteAttribute(any(Attribute.class));
    }
}