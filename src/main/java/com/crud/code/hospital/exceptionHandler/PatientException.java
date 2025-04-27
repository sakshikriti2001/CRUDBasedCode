package com.crud.code.hospital.exceptionHandler;

public class PatientException extends Exception{
public PatientException(String message)  //here i call constructor and using super keyword Calls the constructor of the parent class (Exception) and passes the message to it.
{
    super(message);
}
}
