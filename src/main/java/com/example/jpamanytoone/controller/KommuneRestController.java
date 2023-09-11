package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.error.customError.BadRequest;
import com.example.jpamanytoone.error.customError.InternalServerError;
import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.service.ApiServiceGetKommuner;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Kommune> kommuner = apiServiceGetKommuner.findUsingKodeAsList(kode);

        if (kommuner.isEmpty()){
            throw new InternalServerError("No matches for kommuner with the entered value: " + kode);
        }
        return ResponseEntity.ok(kommuner);
    }

    @GetMapping("/kommune/navn/{navn}")
    public ResponseEntity<List<Kommune>> searchKommuneByPartialNavn(@PathVariable String navn) {
        List<Kommune> kommuner = apiServiceGetKommuner.findUsingNavnAsList(navn);

        if (kommuner.isEmpty()){
            throw new InternalServerError("No matches for kommuner with the entered value: " + navn);
        }
        return ResponseEntity.ok(kommuner);
    }

    //-----------------------------------------------------------------------------------------------------DELETE MAPPING-------------------------------------------------------------------------------------------------//

    @DeleteMapping("/kommune/sletmedkode/{kode}")
    public ResponseEntity<String> deleteKommuneByKode(@PathVariable String kode){
        Kommune kommune = apiServiceGetKommuner.findUsingKodeAsInstance(kode);

        if (kommune == null){
            throw new InternalServerError("No kommune with the desired kode or name exists");
        }

        apiServiceGetKommuner.delete(kommune);
        return ResponseEntity.ok("Kommune med koden: " + kode + " was deleted successfully");
    }

    @DeleteMapping("/kommune/sletmednavn/{navn}")
    public ResponseEntity<String> deleteKommuneUsingNavn(@PathVariable String navn){
        Kommune kommune = apiServiceGetKommuner.findUsingNavnAsInstance(navn);
        if (kommune == null){
            throw new InternalServerError("No kommune with the desired kode or name exists");
        }

        apiServiceGetKommuner.delete(kommune);
        return ResponseEntity.ok("Kommune was deleteed");
    }

    //-----------------------------------------------------------------------------------------------------PUT MAPPING-------------------------------------------------------------------------------------------------//

    @PutMapping("/kommune/opdatermednavn/{navn}")
    public ResponseEntity<Kommune> updateKommuneUsingNavn(@PathVariable String navn, @RequestBody Kommune updatedKommune){
        Kommune existingKommune = apiServiceGetKommuner.findUsingNavnAsInstance(navn);

        if (existingKommune == null){
            throw new InternalServerError("Kommune with desired name does not exists");
        }

        existingKommune.setNavn(navn);
        existingKommune.setHref(updatedKommune.getHref());
        existingKommune.setKode(updatedKommune.getKode());

        apiServiceGetKommuner.save(updatedKommune);

        return ResponseEntity.ok(updatedKommune);
    }

    @PutMapping("/kommune/opdatermedkode/{kode}")
    public ResponseEntity<String> updateKommuneUsingKode(@PathVariable String kode, @RequestBody Kommune updatedKommune){
        Kommune existingKommune = apiServiceGetKommuner.findUsingKodeAsInstance(kode);

        if (existingKommune == null){
            throw new InternalServerError("Kommune with desired name does not exists");
        }

        existingKommune.setNavn(updatedKommune.getNavn());
        existingKommune.setHref(updatedKommune.getHref());
        existingKommune.setKode(kode);

        apiServiceGetKommuner.save(updatedKommune);

        return ResponseEntity.ok("Database entry succesfully updated");


    }


    //-----------------------------------------------------------------------------------------------------POST MAPPING-------------------------------------------------------------------------------------------------//
    @PostMapping("/kommune/indsæt")
    public ResponseEntity<String> postKommune(@RequestBody Kommune kommune) {
        if (kommune.getHref() == null || kommune.getKode() == null || kommune.getNavn() == null){
            throw new BadRequest("Required firlds are missing");
        }

        apiServiceGetKommuner.save(kommune);
        return ResponseEntity.ok("New entry was succesfully added");
    }

}
