package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiServiceGetRegioner {

    List<Region> getRegioner();

    List<String> findKommuner (String id);

    List<Region> searchRegionByKode(String kode);

    List<Region> getRegionsByName(String navn);

    Region save(Region region);

    Region findByKode(String kode);

    Region delete(Region region);

    Region findByNavn(String navn);
}
