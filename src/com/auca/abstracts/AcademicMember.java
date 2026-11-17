package com.auca.abstracts;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public abstract class AcademicMember extends Person {
    protected String memberNumber;
    protected Department department;
    protected LocalDate joinDate;
    protected String academicStatus, institutionalEmail, advisorName;

    public AcademicMember(String personId,String firstName,String lastName,String email,
      String phoneNumber,LocalDate dateOfBirth,String memberNumber,Department department,
      LocalDate joinDate,String academicStatus,String institutionalEmail,String advisorName) {
        super(personId,firstName,lastName,email,phoneNumber,dateOfBirth);
        this.memberNumber=memberNumber; this.department=department; this.joinDate=joinDate;
        this.academicStatus=academicStatus; this.institutionalEmail=institutionalEmail; this.advisorName=advisorName;
    }
    public String getMemberNumber(){ return memberNumber; }
    public Department getDepartment(){ return department; }
    public LocalDate getJoinDate(){ return joinDate; }
    public String getAcademicStatus(){ return academicStatus; }
    public String getInstitutionalEmail(){ return institutionalEmail; }
    public String getAdvisorName(){ return advisorName; }
    public abstract String getAcademicCategory();
    public void validate() throws ValidationException {
        super.validate();
        if(memberNumber==null || memberNumber.isBlank()) throw new ValidationException("Member number is required.");
        if(department==null) throw new ValidationException("Department is required.");
        if(joinDate==null) throw new ValidationException("Join date is required.");
        if(academicStatus==null || academicStatus.isBlank()) throw new ValidationException("Academic status is required.");
        if(institutionalEmail==null || !institutionalEmail.contains("@")) throw new ValidationException("Institutional email is invalid.");
        if(advisorName==null || advisorName.isBlank()) throw new ValidationException("Advisor name is required.");
    }
}
