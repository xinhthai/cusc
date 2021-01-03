package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.domain.Lits;
import com.myxinh.cusc.repository.LitsRepository;
import com.myxinh.cusc.service.LitsService;
import com.myxinh.cusc.web.constants.SystemConstants;
import com.myxinh.cusc.web.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LitsController {

    @Autowired
    private LitsRepository litsRepository;

    @Autowired
    private LitsService litsService;

    @GetMapping("/lits")//get List
    public ResponseEntity<List<Lits>> getAllLichTS(){
        return ResponseEntity.ok(litsRepository.findAll());
    }

    @GetMapping("/lits/{litsId}")//findOnebyId
    public ResponseEntity<Lits> getOneCategoryName(@PathVariable("litsId") int litsId){
        Optional<Lits> litsFind= litsService.findById(litsId);
        if(litsFind.isPresent()){
            return ResponseEntity.ok(litsFind.get());
        }else{
            throw new NotFoundException("Lits "+ ErrorConstants.NOT_FOUND);
        }
    }

    @PostMapping("/lits")//add new Lich_TS
    public ResponseEntity<Lits> saveCategory(@RequestBody Lits lits) throws URISyntaxException, ParseException {
        if (lits.getLits_id() != 0){
            throw new BadRequestAlertException(String.valueOf(lits.getLits_id()));
        }else if (litsRepository.findOneByName(lits.getLits_ten()).isPresent()){
            throw new IsAlreadyException(lits.getLits_ten());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date lits_ktdk = simpleDateFormat.parse(lits.getLits_ktdk());
        Date lits_bddk = simpleDateFormat.parse(lits.getLits_bddk());
        if (lits_ktdk.before(lits_bddk) || lits_bddk.after(lits_ktdk)){
            throw new DateLogicException(ErrorConstants.DATE_LOGIC+lits.getLits_bddk()+" and"+lits_ktdk);
        }
        else {
            Lits litsNew = litsService.addLits(lits);
            return ResponseEntity.created(
                    new URI(SystemConstants.BASE_URL+"/lits/"+litsNew.getLits_id()))
                    .body(litsNew);
        }
    }

    @PutMapping("/lits")// update Lits existing in database
    public ResponseEntity<Lits> updateCategory(@Valid @RequestBody Lits lits) throws URISyntaxException, ParseException {
        Optional<Lits> existingLich_TS = litsRepository.findOneByName(lits.getLits_ten());
        if (existingLich_TS.isPresent() && !(existingLich_TS.get().getLits_id() == lits.getLits_id())){
            throw new IsAlreadyException(lits.getLits_ten());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date lits_ktdk = simpleDateFormat.parse(lits.getLits_ktdk());
        Date lits_bddk = simpleDateFormat.parse(lits.getLits_bddk());
        if (lits_ktdk.before(lits_bddk) || lits_bddk.after(lits_ktdk)){
            throw new DateLogicException(ErrorConstants.DATE_LOGIC+lits.getLits_bddk()+" and"+lits_ktdk);
        }
        Optional<Lits> newLits =litsService.updateLits(lits);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(SystemConstants.BASE_URL+"/categories/"+lits.getLits_id()));
        if (newLits.isPresent()){
            litsService.addLits(lits);
            return ResponseEntity.ok(newLits.get());
        }else {
            return ResponseEntity.noContent().headers(headers).build();
        }
    }
//
    @DeleteMapping("/lits/{litsId}")//Delete Lits existing in database
    public ResponseEntity<Void> deleteCategory(@PathVariable int litsId) throws URISyntaxException {
        litsService.deleteLits(litsId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(SystemConstants.BASE_URL+"/lits/"+litsId));
        return ResponseEntity.noContent().headers(headers).build();
    }

}
