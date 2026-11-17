package com.auca.course;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.department.Department;
import com.auca.teacher.Teacher;

public class Course implements Identifiable, Registrable {
    private String courseId;
    private String courseCode;
    private String courseTitle;
    private int creditHours;
    private Department department;
    private Teacher assignedTeacher;

    public Course(String courseId, String courseCode, String courseTitle, int creditHours, Department department, Teacher assignedTeacher) {
        this.courseId=courseId;
        this.courseCode=courseCode;
        this.courseTitle=courseTitle;
        this.creditHours=creditHours;
        this.department=department;
        this.assignedTeacher=assignedTeacher;
    }

    public String getCourseId(){ return courseId; }
    public void setCourseId(String value){ this.courseId=value; }
    public String getCourseCode(){ return courseCode; }
    public void setCourseCode(String value){ this.courseCode=value; }
    public String getCourseTitle(){ return courseTitle; }
    public void setCourseTitle(String value){ this.courseTitle=value; }
    public int getCreditHours(){ return creditHours; }
    public void setCreditHours(int value){ this.creditHours=value; }
    public Department getDepartment(){ return department; }
    public void setDepartment(Department value){ this.department=value; }
    public Teacher getAssignedTeacher(){ return assignedTeacher; }
    public void setAssignedTeacher(Teacher value){ this.assignedTeacher=value; }

    @Override public String getId(){ return courseId; }
    @Override public String getDisplayName(){ return courseTitle; }
    @Override public boolean isActive(){ return true; }
    @Override public void validate() throws ValidationException {
        if(courseId==null || courseId.isBlank()) throw new ValidationException("courseId is required.");
        if(courseId==null || courseId.isBlank()) throw new ValidationException("courseId is required.");
        if(courseCode==null || courseCode.isBlank()) throw new ValidationException("courseCode is required.");
        if(courseTitle==null || courseTitle.isBlank()) throw new ValidationException("courseTitle is required.");
        if(creditHours<0) throw new ValidationException("creditHours cannot be negative.");
    }
    @Override public void printDetails(){ System.out.println(this); }
    @Override public String getRegistrationSummary(){ return getId()+" - "+getDisplayName(); }
    @Override public String toString(){ return "Course[" + getRegistrationSummary() + "]"; }
}
