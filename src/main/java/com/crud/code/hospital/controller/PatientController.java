package com.crud.code.hospital.controller;

import com.crud.code.hospital.dto.ApiResponseSearchByRange;
import com.crud.code.hospital.dto.PatientDTO;
import com.crud.code.hospital.dto.UpdatePatientDto;
import com.crud.code.hospital.exceptionHandler.PatientException;
import com.crud.code.hospital.model.PatientEntity;

import com.crud.code.hospital.repository.PatientRepo;
import com.crud.code.hospital.service.PatientServ;
import com.crud.code.hospital.utility.ApiLogUtil;
import com.crud.code.hospital.utility.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientServ patientServ;
    @Autowired
    PatientRepo patientRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public MessageResult createPatient(@RequestBody PatientDTO dto){
        return patientServ.addPatientDetails(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public MessageResult updateDetails(@RequestParam Integer id ,String name , @RequestBody UpdatePatientDto dto) throws PatientException {

        return patientServ.updatePatientDetails(id , name , dto);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/getAllPatientList")
    public List<PatientEntity> getAllPatientList()
    {
        return patientServ.getAllPatientList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/getById")
    public Optional<PatientEntity> getById(@RequestParam Integer id)throws PatientException
    {
        return patientServ.getById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/getPatientById/{id}")
    public Optional<PatientEntity> getPatientById(@PathVariable Integer id)throws PatientException
    {
        return patientServ.getById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deletePatientDetail(@PathVariable("id") Integer id)
    {
        patientRepo.deleteById(id);
        return "Deleted id :- "+id+" , !Successful deletion";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteById")
    public String deleteById(@RequestParam Integer id)throws PatientException
    {
        return patientServ.deleteById(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/searchByDateRange")
    public MessageResult searchByDateRange(@RequestParam LocalDate fromDate , @RequestParam LocalDate toDate)
    {
        long startTime = System.currentTimeMillis();
        List<PatientEntity> resultList = patientServ.searchByDateRangeDynamic(fromDate , toDate);
        ApiResponseSearchByRange range = ApiLogUtil.buildLogForDateRange("Search By Date Range" , "/patient/searchByDateRange","GET", fromDate,toDate,resultList, startTime,200);
        return new MessageResult(0, "SUCCESS", "List of Patient Details..", range);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findByEmail")
    public Optional<PatientEntity> findByEmail(@RequestParam String email)throws PatientException{
        return patientServ.findByEmailDynamic(email);
    }
}
