package com.myxinh.cusc.service;

import com.myxinh.cusc.domain.Lits;
import com.myxinh.cusc.repository.LitsRepository;
import com.myxinh.cusc.service.mapper.LitsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LitsService {

    @Autowired
    private LitsRepository litsRepository;

    public Lits addLits(Lits lits){
        return litsRepository.save(LitsConverter.convertToDomain(lits));
    }

    public Optional<Lits> updateLits(Lits lits){
        return Optional.of(litsRepository
                .findById(lits.getLits_id()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(LitsConverter::convertToDomain);
    }

    public void deleteLits(int litsId){
        litsRepository.findById(litsId).ifPresent(
                lits -> {litsRepository.delete(lits);}
        );
    }
    public Optional<Lits> findById(int litsId){
        return litsRepository.findById(litsId);
    }

}
