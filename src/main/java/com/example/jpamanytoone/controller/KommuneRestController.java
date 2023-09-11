package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.error.customError.InternalServerError;
import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.service.ApiServiceGetKommuner;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KommuneRestController {

    //-----------------------------------------------------------------------------------------------------AUTOWIRED-------------------------------------------------------------------------------------------------//

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    //-----------------------------------------------------------------------------------------------------GET MAPPING-------------------------------------------------------------------------------------------------//

    @GetMapping("/kommuner")
    public ResponseEntity<Object> getKommuner() {
        List<Kommune> listKommuner = apiServiceGetKommuner.getKommuner();
        return ResponseEntity.ok(listKommuner);
    }

    @GetMapping("/kommune/kode/{kode}")
    public ResponseEntity<List<Kommune>> searchKommuneByKode(@PathVariable String kode) {
        List<Kommune> kommuner = apiServiceGetKommuner.findByKode(kode);

        if (kommuner.isEmpty()){
            throw new InternalServerError("No matches for kommuner with the entered value: " + kode);
        }
        return ResponseEntity.ok(kommuner);
    }

    @GetMapping("/kommune/navn/{navn}")
    public ResponseEntity<List<Kommune>> searchKommuneByPartialNavn(@PathVariable String navn) {
        List<Kommune> kommuner = apiServiceGetKommuner.findByNavn(navn);

        if (kommuner.isEmpty()){
            throw new InternalServerError("No matches for kommuner with the entered value: " + navn);
        }
        return ResponseEntity.ok(kommuner);
    }

    //-----------------------------------------------------------------------------------------------------DELETE MAPPING-------------------------------------------------------------------------------------------------//

    @DeleteMapping("/kommune/{kode}/slet")
    public ResponseEntity<String> deleteKommuneByKode(@PathVariable String kode){
        Kommune kommune = apiServiceGetKommuner.findByKode(kode);

        if (kommune == null){
            throw new InternalServerError("No kommune with the desired kode or name exists");
        }

        apiServiceGetKommuner.delete(kommune);
        return ResponseEntity.ok("Kommune med koden: " + kode + " was deleted successfully");
    }

    //-----------------------------------------------------------------------------------------------------POST MAPPING-------------------------------------------------------------------------------------------------//
    @PostMapping("/kommune/indsæt")
    public Kommune postKommune(@RequestBody Kommune kommune) {

        System.out.println(kommune);
        return apiServiceGetKommuner.save(kommune);
    }

}
