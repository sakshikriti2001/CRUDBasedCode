package com.crud.code.hospital.repository;

import com.crud.code.hospital.config.SqlQueryLoader;
import com.crud.code.hospital.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, Integer> {
//   String FIND_BY_ID_AND_NAME = SqlQueryLoader.get("patient.findByIdAndName");
//   String SEARCH_BY_DATE_RANGE = SqlQueryLoader.get("patient.searchByDateRange");
//   String FIND_BY_EMAIL = SqlQueryLoader.get("patient.findByEmail");
//
//
//    @Query(FIND_BY_ID_AND_NAME)
//    Optional<PatientEntity> findByIdAndName(@Param("id")Integer id , @Param("name")String name);
//@Query(SEARCH_BY_DATE_RANGE)
//List<PatientEntity> searchByDateRange(@Param("fromDate") LocalDate fromDate,
//                                      @Param("toDate") LocalDate toDate);
//@Query(FIND_BY_EMAIL)
//Optional<PatientEntity> findByEmail(@Param("email") String email);
}
