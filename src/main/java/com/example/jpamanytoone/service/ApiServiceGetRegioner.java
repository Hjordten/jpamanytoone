package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;

import java.util.List;

public interface ApiServiceGetRegioner {

    //---------------------------------LIST-------------------------//
    List<Region> getRegioner();

    List<Region> findUsingKodeAsList(String kode);

    List<Region> findUsingNavnAsList(String navn);

    List<String> findKommuner (String id);

    //---------------------------------INSTANCE-------------------------//

    Region findUsingNavnAsInstance(String navn);

    Region findUsingkodeAsInstance(String kode);

    Region save(Region region);

    Region delete(Region region);
}
