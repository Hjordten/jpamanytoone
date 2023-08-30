package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/region/indsæt")
    public Region createRegion(@RequestBody Region region) {

        if (region.getKode() == null || region.getNavn() == null || region.getHref() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        return regionRepository.save(region);
    }

    @DeleteMapping("/region/sletmedkode/{kode}")
    public ResponseEntity<String> deleteRegionByKode(@PathVariable String kode) {
        Region region = regionRepository.findByKode(kode);

        if (region != null) {
            regionRepository.delete(region);
            return ResponseEntity.ok("Region with kode " + kode + " deleted successfully");
        } else {
            throw new EntityNotFoundException("No entry with desired kode exists");
        }
    }

    @DeleteMapping("/region/sletmednavn/{navn}")
    public ResponseEntity<String> deleteRegionByNavn(@PathVariable String navn){
        Region region = regionRepository.findByNavn(navn);

        if (region != null){
            regionRepository.delete(region);
            return ResponseEntity.ok("Region with navn " + navn + " deleted succesfully");
        } else {
            throw new EntityNotFoundException("No entry with desired navn exists");
        }
    }



    @PutMapping("/region/opdatermednavn/{navn}")
    public ResponseEntity<Region> updateRegion(@PathVariable String navn, @RequestBody Region updatedRegion) {
        Region existingRegion = regionRepository.findByNavn(navn);

        if (existingRegion == null) {
            throw new EntityNotFoundException("Region with navn " + navn + " not found");
        }

        existingRegion.setNavn(updatedRegion.getNavn());

        Region savedRegion = regionRepository.save(existingRegion);

        return ResponseEntity.ok(savedRegion);
    }

    @PutMapping("/region/opdatermedkode/{kode}")
    public ResponseEntity<Region> updateRegionByKode(@PathVariable String kode, @RequestBody Region updatedRegion) {
        Region existingRegion = regionRepository.findByKode(kode);

        if (existingRegion == null) {
            throw new EntityNotFoundException("Region with kode " + kode + " not found");
        }

        existingRegion.setNavn(updatedRegion.getNavn());
        existingRegion.setHref(updatedRegion.getHref());


        Region savedRegion = regionRepository.save(existingRegion);

        return ResponseEntity.ok(savedRegion);
    }




}
