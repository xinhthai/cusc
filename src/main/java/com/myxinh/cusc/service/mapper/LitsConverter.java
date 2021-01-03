package com.myxinh.cusc.service.mapper;

import com.myxinh.cusc.domain.Lits;
import com.myxinh.cusc.service.utils.SystemUtils;

public class LitsConverter {
    public static Lits convertToDomain(Lits lits){
        Lits newLits = new Lits();
        newLits.setLits_id(lits.getLits_id());
        newLits.setLits_ten(lits.getLits_ten());
        newLits.setLits_thidauvao(lits.isLits_thidauvao());
        newLits.setLits_ngaythi(SystemUtils.getDate(lits.getLits_ngaythi()));
        newLits.setLits_ngaykg(SystemUtils.getDate(lits.getLits_ngaykg()));
        newLits.setLits_bddk(SystemUtils.getDate(lits.getLits_bddk()));
        newLits.setLits_ktdk(SystemUtils.getDate(lits.getLits_ktdk()));
        newLits.setLits_tghoc(lits.getLits_tghoc());
        newLits.setLits_ttkhac(lits.getLits_ttkhac());
        return newLits;
    }
}
