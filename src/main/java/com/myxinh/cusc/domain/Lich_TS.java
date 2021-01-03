package com.myxinh.cusc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lich_ts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lich_TS implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lits_id")
    private int lits_id;

    @Column(name = "lits_ten")
    private String lits_ten;

    @Column(name = "lits_thidauvao")
    private boolean lits_thidauvao;

    @Column(name = "lits_ngaythi")
    private String lits_ngaythi;

    @Column(name = "lits_ngaykg")
    private String lits_ngaykg;

    @Column(name = "lits_bddk")
    private String lits_bddk;

    @Column(name = "lits_ktdk")
    private String lits_ktdk;

    @Column(name = "lits_tghoc")
    private String lits_tghoc;

    @Column(name = "lits_ttkhac")
    private String lits_ttkhac;

    @Column(name = "lits_ngayghidanh")
    private String lits_ngayghidanh;

}
