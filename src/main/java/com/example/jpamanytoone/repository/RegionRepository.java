package com.example.jpamanytoone.repository;

import com.example.jpamanytoone.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {

    @Query("SELECT r FROM Region r WHERE r.kode = :kode")
    List<Region> findUsingKodeAsList(@Param("kode") String kode);


    @Query("SELECT p FROM Region p WHERE p.navn LIKE CONCAT('%', :query, '%')")
    List<Region> findRegionAsList(String query);

    Region findByKode(String kode);

    @Query("SELECT r FROM Region r WHERE r.navn LIKE CONCAT('%', :navn, '%')")
    Region findByNavn(@Param("navn") String navn);
}
