package com.springboot.emailvalidation.Reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.emailvalidation.DataModels.EmailAddress;

@Repository
public interface EmailRepo extends JpaRepository<EmailAddress, Integer>{
    
    @Query(value = "select * from EmailAddress e where e.mail=:mail", nativeQuery = true)
    EmailAddress getBymail(@Param("mail") String mail);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO EmailAddress (mail, password) VALUES (:mail, :password)", nativeQuery = true)
    int insertInEmailAddress(@Param("mail") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "Update EmailAddress e set e.password=:password where e.mail=:mail", nativeQuery = true)
    int updateInEmailAddress(@Param("mail") String mail, @Param("password") String password);

}
