package com.crud.code.hospital.service;

import com.crud.code.hospital.dto.PatientDTO;
import com.crud.code.hospital.dto.UpdatePatientDto;
import com.crud.code.hospital.exceptionHandler.PatientException;
import com.crud.code.hospital.model.PatientEntity;

import com.crud.code.hospital.repository.PatientRepo;
import com.crud.code.hospital.utility.ApiLogUtil;
import com.crud.code.hospital.dto.ApiResponseLogDto;
import com.crud.code.hospital.utility.MessageResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServ {
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    HttpServletRequest request;

    public MessageResult addPatientDetails(PatientDTO dto)
    {
        long startTime = System.currentTimeMillis();
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(dto.getName());
        patientEntity.setAge(dto.getAge());
        patientEntity.setGender(dto.getGender());
        patientEntity.setIllness(dto.getIllness());
        patientEntity.setEmail(dto.getEmail());
        patientEntity.setDate(LocalDate.now());
        patientRepo.save(patientEntity);
        ApiResponseLogDto logData = ApiLogUtil.buildLog("Add Patient Details", "/patient/create", "POST", dto, patientEntity, startTime, 200);
        return MessageResult.apiLogSuccess(logData);


    }

    public PatientEntity updatePatientDetails(Integer id, String name, UpdatePatientDto dto) throws PatientException {
        Optional<PatientEntity> existingPatient = patientRepo.findByIdAndName(id, name);
        if (existingPatient.isPresent()) {
            PatientEntity entity = existingPatient.get();
            entity.setAge(dto.getAge());
            entity.setIllness(dto.getIllness());
            entity.setDate(LocalDate.now());
            patientRepo.save(entity);
            return entity;
        }
        throw new PatientException("Patient not exist, you need to add");
    }

    public List<PatientEntity> getAllPatientList()
    {
        return patientRepo.findAll();
    }

    public Optional<PatientEntity> getById(Integer id) throws PatientException {
        Optional<PatientEntity> patient = patientRepo.findById(id);
        if(patient.isEmpty()){
            throw new PatientException("Patient not present");
        }
        return patient;
    }
    public String deleteById(Integer id)throws PatientException
    {
        Optional<PatientEntity> patient = patientRepo.findById(id);
        if(patient.isEmpty()){
            throw new PatientException("patient is not present with id: " +
                    id+", unsuccessful");
        }
        PatientEntity patientEntity = patient.get();
        patientRepo.deleteById(id);
        return "patient with id "+ id +" was deleted successfully";
    }
    public Optional<PatientEntity> findByEmail(String email) throws PatientException{
        Optional<PatientEntity> patient = patientRepo.findByEmail(email);
        if(patient.isEmpty()){
            throw new PatientException("Patient not found with this email");
        }
        return patient;
    }
}
