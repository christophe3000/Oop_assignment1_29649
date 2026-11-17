package com.auca.student;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.abstracts.AcademicMember;
import java.time.LocalDate;
import com.auca.department.Department;

public class Student extends AcademicMember {
    private String studentNumber;
    private String programName;
    private LocalDate admissionDate;
    private int studyYear;
    private String guardianPhone;
    private boolean scholarshipStatus;

    public Student(String personId, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth, String memberNumber, Department department, LocalDate joinDate, String academicStatus, String institutionalEmail, String advisorName, String studentNumber, String programName, LocalDate admissionDate, int studyYear, String guardianPhone, boolean scholarshipStatus) {
        super(personId,firstName,lastName,email,phoneNumber,dateOfBirth,memberNumber,department,joinDate,academicStatus,institutionalEmail,advisorName);
        this.studentNumber=studentNumber;
        this.programName=programName;
        this.admissionDate=admissionDate;
        this.studyYear=studyYear;
        this.guardianPhone=guardianPhone;
        this.scholarshipStatus=scholarshipStatus;
    }

    public String getStudentNumber(){ return studentNumber; }
    public void setStudentNumber(String value){ this.studentNumber=value; }
    public String getProgramName(){ return programName; }
    public void setProgramName(String value){ this.programName=value; }
    public LocalDate getAdmissionDate(){ return admissionDate; }
    public void setAdmissionDate(LocalDate value){ this.admissionDate=value; }
    public int getStudyYear(){ return studyYear; }
    public void setStudyYear(int value){ this.studyYear=value; }
    public String getGuardianPhone(){ return guardianPhone; }
    public void setGuardianPhone(String value){ this.guardianPhone=value; }
    public boolean getScholarshipStatus(){ return scholarshipStatus; }
    public void setScholarshipStatus(boolean value){ this.scholarshipStatus=value; }

    @Override public String getDisplayName(){ return firstName + " " + lastName; }
    @Override public String getAcademicCategory(){ return "Student"; }
    @Override public String getRoleDescription(){ return "Student"; }
    @Override public void validate() throws ValidationException {
        if(studentNumber==null || studentNumber.isBlank()) throw new ValidationException("studentNumber is required.");
        if(studentNumber==null || studentNumber.isBlank()) throw new ValidationException("studentNumber is required.");
        if(programName==null || programName.isBlank()) throw new ValidationException("programName is required.");
        if(studyYear<0) throw new ValidationException("studyYear cannot be negative.");
        if(guardianPhone==null || guardianPhone.isBlank()) throw new ValidationException("guardianPhone is required.");
    }
    @Override public void printDetails(){ System.out.println(this); }
    @Override public String getRegistrationSummary(){ return getId()+" - "+getDisplayName(); }
    @Override public String toString(){ return "Student[" + getRegistrationSummary() + "]"; }
}
