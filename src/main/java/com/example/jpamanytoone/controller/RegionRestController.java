package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;
    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/regioner")
    public List<Region> getRegioner() {
        List<Region> listRegioger = apiServiceGetRegioner.getRegioner();
        return listRegioger;
    }

    @GetMapping("/region/kode/{kode}")
    public List<Region> searchRegionByKode(@PathVariable String kode) {
        List<Region> regions = regionRepository.getRegionsByKode(kode);
        return regions;
    }

    @GetMapping("/region/navn/{navn}")
    public List<Region> searchRegionByPartialNavn(@PathVariable String navn) {
        List<Region> regions = regionRepository.getRegionsByName(navn);
        return regions;
    }

}
