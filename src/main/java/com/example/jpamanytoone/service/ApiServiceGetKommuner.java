package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;

import java.util.List;

public interface ApiServiceGetKommuner {

    List<Kommune> getKommuner();

    List<Kommune> findByKode(String kode);

    List<Kommune> findByNavn(String navn);


}
