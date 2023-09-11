package com.example.jpamanytoone.repository;

import com.example.jpamanytoone.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    @Query("SELECT k FROM Kommune k WHERE k.kode = :kode")
    List<Kommune> findUsingKodeAsList(@Param("kode") String kode);


    @Query("SELECT k FROM Kommune k WHERE k.navn = :navn")
    List<Kommune> findUsingNavnAsList(@Param("navn") String navn);


    @Query("SELECT k FROM Kommune k WHERE k.kode = :kode")
    Kommune findUsingKodeAsInstance(@Param("kode") String kode);


    @Query("SELECT k FROM Kommune k WHERE k.navn = :navn")
    Kommune findUsingNavnAsInstance(@Param("navn") String navn);

}
