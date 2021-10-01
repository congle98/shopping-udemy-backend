package com.example.shopbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;


    //cái kiểu này k cần thiết
//    @JsonIgnore
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<State> states;



}
