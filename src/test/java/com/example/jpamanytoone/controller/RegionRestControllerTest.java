package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RegionRestControllerTest {

    @Autowired
    RegionRepository regionRepository;
    @Test
    void getRegioner() {
        Region region = new Region();
        region.setHref("test");
        region.setNavn("test");
        region.setKode("1234");

        regionRepository.findByKode("1234");

        Assertions.assertEquals(region.getKode(), "1234");


    }
}