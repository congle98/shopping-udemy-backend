package com.example.shopbackend.dao;

import com.example.shopbackend.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "states",path = "states")
public interface StateRepository extends JpaRepository<State,Integer> {

    //@Param @RequestParam @PathVariable đều dc nhé :v ảo ma
    List<State> findByCountryCode(@Param("code") String code);
}
