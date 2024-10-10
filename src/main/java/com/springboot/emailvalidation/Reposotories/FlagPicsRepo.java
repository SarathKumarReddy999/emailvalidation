package com.springboot.emailvalidation.Reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.emailvalidation.DataModels.FlagPics;

@Repository
public interface FlagPicsRepo extends JpaRepository<FlagPics, String> {

    @Query(value = "select flag from FlagPics f where f.country=:country", nativeQuery=true)
    byte[] getFlagByName(@Param("country") String country);
}
