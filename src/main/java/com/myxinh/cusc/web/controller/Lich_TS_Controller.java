package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.domain.Category;
import com.myxinh.cusc.domain.Lich_TS;
import com.myxinh.cusc.repository.Lich_TS_Repository;
import com.myxinh.cusc.web.constants.SystemConstants;
import com.myxinh.cusc.web.errors.BadRequestAlertException;
import com.myxinh.cusc.web.errors.ErrorConstants;
import com.myxinh.cusc.web.errors.IsAlreadyException;
import com.myxinh.cusc.web.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Lich_TS_Controller {

    @Autowired
    private Lich_TS_Repository lich_ts_repository;

    @GetMapping("/lits")//get List
    public ResponseEntity<List<Lich_TS>> getAllLichTS(){
        return ResponseEntity.ok(lich_ts_repository.findAll());
    }

    @GetMapping("/lits/{lits_id}")//findOnebyId
    public ResponseEntity<Lich_TS> getOneCategoryName(@PathVariable("lits_id") int lits_id){
        Optional<Lich_TS> litsFind= lich_ts_repository.findById(lits_id);
        if(litsFind.isPresent()){
            return ResponseEntity.ok(litsFind.get());
        }else{
            throw new NotFoundException("Lich Tuyen Sinh "+ ErrorConstants.NOT_FOUND);
        }
    }
    @PostMapping("/lits")//add new Lich_TS
    public ResponseEntity<Lich_TS> saveCategory(@RequestBody Lich_TS lich_ts) throws URISyntaxException {
        if (lich_ts.getLits_id() != 0){
            throw new BadRequestAlertException(String.valueOf(lich_ts.getLits_id()));
        }else if (lich_ts_repository.findOneByCategoryName(lich_ts.getLits_ten()).isPresent()){
            throw new IsAlreadyException(lich_ts.getLits_ten());
        }
        else {
            Lich_TS lict_ts_new = lich_ts_repository.save(lich_ts);
            return ResponseEntity.created(
                    new URI(SystemConstants.BASE_URL+"/lits/"+lict_ts_new.getLits_id()))
                    .body(lict_ts_new);
        }
    }

//    @PutMapping("/categories")// update Category existing in database
//    public ResponseEntity<Lich_TS> updateCategory(@Valid @RequestBody Lich_TS lich_ts) throws URISyntaxException {
//        Optional<Lich_TS> existingLich_TS = lich_ts_repository.findOneByCategoryName(lich_ts.getLits_ten());
//        if (existingLich_TS.isPresent() && !(existingLich_TS.get().getLits_id() == lich_ts.getLits_id())){
//            throw new IsAlreadyException(lich_ts.getLits_ten());
//        }
//        Optional<Category> newCategory =categoryService.updateCategory(category);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(new URI(SystemConstants.BASE_URL+"/categories/"+category.getCategoryId()));
//        if (newCategory.isPresent()){
//            categoryRepository.save(category);
//            return ResponseEntity.ok(newCategory.get());
//        }else {
//            return ResponseEntity.noContent().headers(headers).build();
//        }
//    }
//
//    @DeleteMapping("/categories/{categoryId}")//Delete Category existing in database
//    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) throws URISyntaxException {
//        categoryService.deleteCategory(categoryId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(new URI(SystemConstants.BASE_URL+"/categories/"+categoryId));
//        return ResponseEntity.noContent().headers(headers).build();
//    }

}
