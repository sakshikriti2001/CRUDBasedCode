package com.crud.code.hospital.service;

import com.crud.code.hospital.config.SqlQueryLoader;
import com.crud.code.hospital.dto.ApiResponseUpdate;
import com.crud.code.hospital.dto.PatientDTO;
import com.crud.code.hospital.dto.UpdatePatientDto;
import com.crud.code.hospital.exceptionHandler.PatientException;
import com.crud.code.hospital.model.PatientEntity;

import com.crud.code.hospital.repository.PatientRepo;
import com.crud.code.hospital.utility.ApiLogUtil;
import com.crud.code.hospital.dto.ApiResponseCreate;
import com.crud.code.hospital.utility.MessageResult;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServ {
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private EntityManager entityManager;

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

        patientEntity.setApiName("Register User");
        patientEntity.setEndpointURL("/auth/registerUser");
        patientEntity.setHttpMethod("POST");
        patientEntity.setRequestBody(dto.toString());
        patientEntity.setResponseBody("User registered successfully!");
        patientEntity.setHttpStatusCode(200);
        patientEntity.setTotalDuration(System.currentTimeMillis() - startTime);
        patientEntity.setCreatedBy(patientEntity.getName());
        patientEntity.setCreatedDate(LocalDateTime.now());
        patientRepo.save(patientEntity);
        ApiResponseCreate logData = ApiLogUtil.buildLog("Add Patient Details", "/patient/create", "POST", dto, patientEntity, startTime, 200);
        return MessageResult.apiLogSuccessCreate(logData);
    }

    public Optional<PatientEntity> findByIdAndNameDynamic(Integer id ,String name){
        String sql = SqlQueryLoader.get("patient.findByIdAndName");
        return entityManager.createQuery(sql , PatientEntity.class)
                .setParameter("id" , id)
                .setParameter("name" , name)
                .getResultList()
                .stream()
                .findFirst();
    }
    public List<PatientEntity> searchByDateRangeDynamic(LocalDate fromDate,
                                         LocalDate toDate){
        String sql = SqlQueryLoader.get("patient.searchByDateRange");
        return entityManager.createQuery(sql , PatientEntity.class)
                .setParameter("fromDate" , fromDate)
                .setParameter("toDate" , toDate)
                .getResultList();
    }

    public Optional<PatientEntity> findByEmailDynamic(String email){
        String sql = SqlQueryLoader.get("patient.findByEmail");
        return entityManager.createQuery(sql , PatientEntity.class)
                .setParameter("email" , email)
                .getResultList()
                .stream().findFirst();
    }

    public MessageResult updatePatientDetails(Integer id, String name, UpdatePatientDto dto) throws PatientException {
        long startTime = System.currentTimeMillis();
        Optional<PatientEntity> existingPatient = findByIdAndNameDynamic(id, name);
        if (existingPatient.isPresent()) {
            PatientEntity entity = existingPatient.get();
            entity.setAge(dto.getAge());
            entity.setIllness(dto.getIllness());
            entity.setDate(LocalDate.now());
            patientRepo.save(entity);
            ApiResponseUpdate update = ApiLogUtil.buildLogForUpdate("Update Patient Details" , "/patient/update" , "POST" ,dto , entity ,startTime,201);
            return MessageResult.apiLogSuccessUpdate(update);
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
        Optional<PatientEntity> patient = findByEmailDynamic(email);
        if(patient.isEmpty()){
            throw new PatientException("Patient not found with this email");
        }
        return patient;
    }
}
