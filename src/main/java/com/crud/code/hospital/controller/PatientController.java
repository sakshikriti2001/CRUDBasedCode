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

    @PostMapping("/create")
    public MessageResult createPatient(@RequestBody PatientDTO dto){
        return patientServ.addPatientDetails(dto);
    }

    @PutMapping("/update")
    public MessageResult updateDetails(@RequestParam Integer id ,String name , @RequestBody UpdatePatientDto dto) throws PatientException {

        return patientServ.updatePatientDetails(id , name , dto);
    }

    @GetMapping("/getAllPatientList")
    public List<PatientEntity> getAllPatientList()
    {
        return patientServ.getAllPatientList();
    }

    @PostMapping("/getById")
    public Optional<PatientEntity> getById(@RequestParam Integer id)throws PatientException
    {
        return patientServ.getById(id);
    }
    @GetMapping("/getPatientById/{id}")
    public Optional<PatientEntity> getPatientById(@PathVariable Integer id)throws PatientException
    {
        return patientServ.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatientDetail(@PathVariable("id") Integer id)
    {
        patientRepo.deleteById(id);
        return "Deleted id :- "+id+" , !Successful deletion";
    }


    @PostMapping("/deleteById")
    public String deleteById(@RequestParam Integer id)throws PatientException
    {
        return patientServ.deleteById(id);
    }



    @GetMapping("/searchByDateRange")
    public MessageResult searchByDateRange(@RequestParam LocalDate fromDate , @RequestParam LocalDate toDate)
    {
        long startTime = System.currentTimeMillis();
        List<PatientEntity> resultList = patientServ.searchByDateRangeDynamic(fromDate , toDate);
        ApiResponseSearchByRange range = ApiLogUtil.buildLogForDateRange("Search By Date Range" , "/patient/searchByDateRange","GET", fromDate,toDate,resultList, startTime,200);
        return new MessageResult(0, "SUCCESS", "List of Patient Details..", range);
    }
    @GetMapping("/findByEmail")
    public Optional<PatientEntity> findByEmail(@RequestParam String email)throws PatientException{
        return patientServ.findByEmailDynamic(email);
    }
}
