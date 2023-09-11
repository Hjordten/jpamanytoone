package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;

import java.util.List;

public interface ApiServiceGetKommuner {

    List<Kommune> getKommuner();

    List<Kommune> findUsingKodeAsList(String kode);

    List<Kommune> findUsingNavnAsList(String navn);



    Kommune findUsingKodeAsInstance(String kode);

    Kommune findUsingNavnAsInstance(String navn);

    Kommune save (Kommune kommune);

    Kommune delete(Kommune kommune);


}
