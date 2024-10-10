package com.springboot.emailvalidation.DataModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "FlagPics") 
public class FlagPics {

    @Id
    private String country;

    @Lob
    private byte[] flag;
}
