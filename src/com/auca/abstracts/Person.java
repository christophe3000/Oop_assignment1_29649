package com.auca.abstracts;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public abstract class Person implements Identifiable, Registrable {
    protected String personId, firstName, lastName, email, phoneNumber;
    protected LocalDate dateOfBirth;

    public Person(String personId, String firstName, String lastName, String email,
                  String phoneNumber, LocalDate dateOfBirth) {
        this.personId=personId; this.firstName=firstName; this.lastName=lastName;
        this.email=email; this.phoneNumber=phoneNumber; this.dateOfBirth=dateOfBirth;
    }
    public String getPersonId(){ return personId; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getEmail(){ return email; }
    public String getPhoneNumber(){ return phoneNumber; }
    public LocalDate getDateOfBirth(){ return dateOfBirth; }
    public void setFirstName(String v){ firstName=v; }
    public void setLastName(String v){ lastName=v; }
    public void setEmail(String v){ email=v; }
    public void setPhoneNumber(String v){ phoneNumber=v; }
    public void setDateOfBirth(LocalDate v){ dateOfBirth=v; }
    public abstract String getRoleDescription();
    public String getId(){ return personId; }
    public boolean isActive(){ return true; }
    public void validate() throws ValidationException {
        if(personId==null || personId.isBlank()) throw new ValidationException("Person ID is required.");
        if(firstName==null || firstName.trim().length()<2) throw new ValidationException("First name must have at least 2 characters.");
        if(lastName==null || lastName.trim().length()<2) throw new ValidationException("Last name must have at least 2 characters.");
        if(email==null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) throw new ValidationException("Invalid email.");
        if(phoneNumber==null || !phoneNumber.matches("^\\+?\\d{10,15}$")) throw new ValidationException("Phone must contain 10-15 digits.");
        if(dateOfBirth==null || !dateOfBirth.isBefore(LocalDate.now())) throw new ValidationException("Date of birth must be in the past.");
    }
    public void printDetails(){ System.out.println(this); }
    public String getRegistrationSummary(){ return personId+" - "+firstName+" "+lastName; }
    public String toString(){ return getRoleDescription()+": "+getRegistrationSummary(); }
}
