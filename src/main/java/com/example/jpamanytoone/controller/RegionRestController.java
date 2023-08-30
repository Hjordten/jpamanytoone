package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.error.customError.BadRequest;
import com.example.jpamanytoone.error.customError.InternalServerError;
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


    //-----------------------------------------------------------------------------------------------------AUTOWIRED-------------------------------------------------------------------------------------------------//

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;
    @Autowired
    RegionRepository regionRepository;


    @GetMapping("/regioner")
    public ResponseEntity<Object> getRegioner() {
        List<Region> listRegioner = apiServiceGetRegioner.getRegioner();

        if (listRegioner.isEmpty()) {
            String errorMessage = "No information could be retrieved from the desired URL";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

        return ResponseEntity.ok(listRegioner);
    }

    //-----------------------------------------------------------------------------------------------------GET MAPPING-------------------------------------------------------------------------------------------------//
    @GetMapping("/region/kode/{kode}")
    public ResponseEntity<List<Region>> searchRegionByKode(@PathVariable String kode) {
        List<Region> regions = regionRepository.getRegionsByKode(kode);

        if (regions.isEmpty()) {
            throw new InternalServerError("No region with desired kode exists");
        }

        return ResponseEntity.ok(regions);
    }


    @GetMapping("/region/navn/{navn}")
    public ResponseEntity<List<Region>> searchRegionByNavn(@PathVariable String navn) {
        List<Region> regions = regionRepository.getRegionsByName(navn);

        if (regions.isEmpty()) {
            throw new InternalServerError("No region with desired name");
        }

        return ResponseEntity.ok(regions);
    }

    //-----------------------------------------------------------------------------------------------------DELETE MAPPING-------------------------------------------------------------------------------------------------//

    @DeleteMapping("/region/sletmedkode/{kode}")
    public ResponseEntity<String> deleteRegionByKode(@PathVariable String kode) {
        Region region = regionRepository.findByKode(kode);

        if (region == null) {
            throw new InternalServerError("No region with the desired kode or name exists");
        }
        regionRepository.delete(region);
        return ResponseEntity.ok("Region with " + kode + " deleted successfully");
    }

    @DeleteMapping("/region/sletmednavn/{navn}")
    public ResponseEntity<String> deleteRegionByNavn(@PathVariable String navn) {
        Region region = regionRepository.findByNavn(navn);

        if (region == null) {
            throw new InternalServerError("No region with the desired kode or name exists");
        }

        regionRepository.delete(region);
        return ResponseEntity.ok("Region " + navn + " deleted successfully");
    }

    //-----------------------------------------------------------------------------------------------------PUT MAPPING-------------------------------------------------------------------------------------------------//
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
    public ResponseEntity<String> updateRegionByKode(@PathVariable String kode, @RequestBody Region updatedRegion) {
        Region existingRegion = regionRepository.findByKode(kode);

        if (existingRegion == null) {
            throw new InternalServerError("No region with desired kode exists");
        }

        existingRegion.setNavn(updatedRegion.getNavn());
        existingRegion.setHref(updatedRegion.getHref());
        existingRegion.setId(kode);


        regionRepository.save(existingRegion);

        return ResponseEntity.ok("Database entry successfully updated");
    }

    //-----------------------------------------------------------------------------------------------------POST MAPPING-------------------------------------------------------------------------------------------------//
    @PostMapping("/region/indsæt")
    public ResponseEntity<String> createRegion(@RequestBody Region region) {
        if (region.getKode() == null || region.getNavn() == null || region.getHref() == null) {
            throw new BadRequest("Required fields are missing");
        }
        return ResponseEntity.ok("New entry was successfully added");
    }


}
