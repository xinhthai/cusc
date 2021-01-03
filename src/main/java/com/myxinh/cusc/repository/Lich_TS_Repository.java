package com.myxinh.cusc.repository;

import com.myxinh.cusc.domain.Category;
import com.myxinh.cusc.domain.Lich_TS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Lich_TS_Repository extends JpaRepository<Lich_TS, Integer> {

    @Query("SELECT lits FROM Lich_TS lits WHERE lits.lits_ten = :lits_name")
    Optional<Lich_TS> findOneByCategoryName(@Param("lits_name") String lits_name);
}
