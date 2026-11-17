package com.auca.main;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * AUCA_ID School Management System - Student ID 29649.
 * Console application using one Scanner and in-memory records.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, LinkedHashMap<String, LinkedHashMap<String,String>>> db = new LinkedHashMap<>();
    private static final Map<String,Integer> stock = new HashMap<>();
    private static final Map<String,String> loans = new LinkedHashMap<>();

    private static final String[] TYPES = {"Department","Student","Teacher","Administrator","Course","Classroom",
        "Enrollment","Assessment","FeePayment","LibraryBook","BookLoan"};

    public static void main(String[] args) {
        for (String t:TYPES) db.put(t,new LinkedHashMap<>());
        System.out.println("AUCA_ID SCHOOL MANAGEMENT SYSTEM");
        System.out.println("Student ID: 29649");
        boolean run=true;
        while(run) {
            menu();
            String c=read("Select option: ");
            try {
                switch(c) {
                    case "1": register("Department"); break;
                    case "2": register("Student"); break;
                    case "3": register("Teacher"); break;
                    case "4": register("Administrator"); break;
                    case "5": register("Course"); break;
                    case "6": register("Classroom"); break;
                    case "7": register("Enrollment"); break;
                    case "8": register("Assessment"); break;
                    case "9": register("FeePayment"); break;
                    case "10": register("LibraryBook"); break;
                    case "11": borrow(); break;
                    case "12": returnBook(); break;
                    case "13": viewAll(); break;
                    case "14": search(); break;
                    case "15": update(); break;
                    case "16": academicSummary(); break;
                    case "17": paymentHistory(); break;
                    case "18": availableBooks(); break;
                    case "19": polymorphism(); break;
                    case "0": run=false; break;
                    default: System.out.println("Invalid option. Choose 0-19.");
                }
            } catch(Exception e) { System.out.println("Operation could not be completed: "+e.getMessage()); }
        }
        System.out.println("Thank you. Goodbye!");
    }

    private static void menu() {
        System.out.println("\n================ AUCA_ID / SCHOOL MANAGEMENT ================");
        System.out.println(" 1 Register Department       11 Borrow Library Book");
        System.out.println(" 2 Register Student          12 Return Library Book");
        System.out.println(" 3 Register Teacher          13 View All Records");
        System.out.println(" 4 Register Administrator    14 Search Record by ID");
        System.out.println(" 5 Register Course           15 Update Existing Record");
        System.out.println(" 6 Register Classroom        16 Student Academic Summary");
        System.out.println(" 7 Enroll Student            17 Student Payment History");
        System.out.println(" 8 Record Assessment         18 View Available Books");
        System.out.println(" 9 Record Fee Payment        19 Demonstrate Polymorphism");
        System.out.println("10 Register Library Book      0 Exit");
    }

    private static String read(String p) { System.out.print(p); return sc.nextLine().trim(); }
    private static String required(String label) {
        while(true) { String v=read(label+": "); if(!v.isEmpty()) return v; System.out.println("This field is required."); }
    }
    private static String date(String label) {
        while(true) { String v=required(label+" (YYYY-MM-DD)"); try { LocalDate.parse(v); return v; }
            catch(Exception e){System.out.println("Invalid date. Example: 2026-11-10");}}
    }
    private static String number(String label, boolean positive) {
        while(true) { String v=required(label); try { BigDecimal n=new BigDecimal(v);
            if(positive && n.compareTo(BigDecimal.ZERO)<=0) throw new Exception();
            if(!positive && n.compareTo(BigDecimal.ZERO)<0) throw new Exception(); return v;
        } catch(Exception e){System.out.println(positive?"Enter a number greater than zero.":"Enter a non-negative number.");}}
    }
    private static String select(String type) {
        while(true) {
            LinkedHashMap<String,LinkedHashMap<String,String>> list=db.get(type);
            if(list.isEmpty()) {
                System.out.println("No "+type+" records exist. Register "+type+" first.");
                if(read("Register now? (Y/N): ").equalsIgnoreCase("Y")) register(type);
                else return null;
                continue;
            }
            List<String> ids=new ArrayList<>(list.keySet());
            System.out.println("Select "+type+":");
            for(int i=0;i<ids.size();i++) System.out.println((i+1)+". "+ids.get(i)+" - "+display(list.get(ids.get(i))));
            System.out.println("0. Cancel");
            String s=read("Selection: ");
            if(s.equals("0")) return null;
            try { int n=Integer.parseInt(s); if(n>=1&&n<=ids.size()) return ids.get(n-1); }
            catch(Exception ignored) {}
            System.out.println("Invalid selection.");
        }
    }
    private static String display(Map<String,String> m) {
        return m.getOrDefault("name",m.getOrDefault("title",m.getOrDefault("firstName","Record")));
    }
    private static void register(String type) {
        System.out.println("\n----------- REGISTER "+type.toUpperCase()+" -----------");
        LinkedHashMap<String,String> r=new LinkedHashMap<>();
        String id=required(type+" ID (unique)");
        if(db.get(type).containsKey(id)) {System.out.println("Duplicate ID. Nothing saved.");return;}
        r.put("id",id);
        switch(type) {
            case "Department":
                r.put("name",required("Department name")); r.put("code",required("Department code"));
                r.put("office",required("Office location")); r.put("email",required("Contact email"));
                String ht=read("Head teacher ID (optional, press Enter to skip): "); r.put("headTeacher",ht); break;
            case "Student":
            case "Teacher":
                if(db.get("Department").isEmpty()){System.out.println("A Department is required."); if(read("Register Department now? Y/N: ").equalsIgnoreCase("Y"))register("Department");}
                String dep=select("Department"); if(dep==null)return; r.put("department",dep);
                r.put("firstName",required("First name")); r.put("lastName",required("Last name"));
                r.put("email",required("Email")); r.put("phone",required("Phone number")); r.put("dob",date("Date of birth"));
                r.put("memberNumber",required("Member number")); r.put("joinDate",date("Join date"));
                r.put("academicStatus",required("Academic status (ACTIVE/INACTIVE)"));
                r.put("institutionalEmail",required("Institutional email")); r.put("advisor",required("Advisor name"));
                if(type.equals("Student")) {
                    r.put("studentNumber",required("Student number")); r.put("program",required("Program name"));
                    r.put("admissionDate",date("Admission date")); r.put("studyYear",number("Study year (1-8)",true));
                    r.put("guardianPhone",required("Guardian phone")); r.put("scholarship",required("Scholarship (YES/NO)"));
                } else {
                    r.put("employeeNumber",required("Employee number")); r.put("specialization",required("Specialization"));
                    r.put("hireDate",date("Hire date")); r.put("salary",number("Salary",true));
                    r.put("officeNumber",required("Office number")); r.put("employmentType",required("Employment type"));
                } break;
            case "Administrator":
                r.put("firstName",required("First name")); r.put("lastName",required("Last name"));
                r.put("email",required("Email")); r.put("phone",required("Phone number")); r.put("dob",date("Date of birth"));
                r.put("staffNumber",required("Staff number")); r.put("jobTitle",required("Job title"));
                r.put("office",required("Assigned office")); r.put("employmentDate",date("Employment date"));
                r.put("accessLevel",number("Access level",false)); r.put("active",required("Active (YES/NO)")); break;
            case "Course":
                String d=select("Department"); if(d==null)return; String t=select("Teacher"); if(t==null)return;
                r.put("department",d);r.put("teacher",t);r.put("code",required("Course code"));
                r.put("title",required("Course title"));r.put("creditHours",number("Credit hours (1-6)",true)); break;
            case "Classroom":
                r.put("building",required("Building name"));r.put("room",required("Room number"));
                r.put("capacity",number("Capacity",true));r.put("floor",number("Floor number",false));
                r.put("available",required("Available (YES/NO)")); break;
            case "Enrollment":
                String st=select("Student"); if(st==null)return; String co=select("Course");if(co==null)return;
                r.put("student",st);r.put("course",co);r.put("date",date("Enrollment date"));
                r.put("semester",required("Semester"));r.put("status",required("Status (ACTIVE/INACTIVE)"));break;
            case "Assessment":
                String en=select("Enrollment");if(en==null)return;r.put("enrollment",en);
                r.put("type",required("Assessment type"));r.put("maximum",number("Maximum score",true));
                r.put("obtained",number("Obtained score",false));r.put("date",date("Assessment date"));break;
            case "FeePayment":
                String stu=select("Student");if(stu==null)return;r.put("student",stu);
                r.put("amount",number("Amount",true));r.put("date",date("Payment date"));
                r.put("method",required("Payment method"));r.put("receipt",required("Receipt number"));break;
            case "LibraryBook":
                r.put("isbn",required("ISBN"));r.put("title",required("Title"));r.put("author",required("Author"));
                r.put("total",number("Total copies",false));r.put("available",number("Available copies",false));
                try {if(Integer.parseInt(r.get("available"))>Integer.parseInt(r.get("total")))throw new Exception();}
                catch(Exception e){System.out.println("Available copies cannot exceed total copies.");return;}
                stock.put(id,Integer.parseInt(r.get("available")));break;
            case "BookLoan":
                System.out.println("Use option 11 (Borrow Library Book) to create a loan.");return;
        }
        System.out.println("\nREVIEW "+type+":");
        for(Map.Entry<String,String> e:r.entrySet())System.out.println(e.getKey()+" : "+e.getValue());
        String ok=read("1 Save  |  0 Cancel: ");
        if(!ok.equals("1")){if(type.equals("LibraryBook"))stock.remove(id);System.out.println("Cancelled. No record saved.");return;}
        db.get(type).put(id,r);System.out.println(type+" saved successfully. ID: "+id);
    }
    private static void borrow() {
        String s=select("Student");if(s==null)return;String b=select("LibraryBook");if(b==null)return;
        int n=stock.getOrDefault(b,0);if(n<=0){System.out.println("BookUnavailableException: no copies available.");return;}
        String loan=required("Loan ID");if(loans.containsKey(loan)){System.out.println("Duplicate loan ID.");return;}
        String borrowed=date("Borrowed date"),due=date("Due date");
        if(!LocalDate.parse(due).isAfter(LocalDate.parse(borrowed))){System.out.println("Due date must be after borrowed date.");return;}
        LinkedHashMap<String,String> r=new LinkedHashMap<>();r.put("id",loan);r.put("student",s);r.put("book",b);r.put("borrowedDate",borrowed);r.put("dueDate",due);r.put("returnedDate","Not returned");
        if(read("Confirm borrowing? (Y/N): ").equalsIgnoreCase("Y")){db.get("BookLoan").put(loan,r);loans.put(loan,b);stock.put(b,n-1);System.out.println("Book borrowed. Available copies: "+(n-1));}
        else System.out.println("Cancelled; stock unchanged.");
    }
    private static void returnBook() {
        String id=select("BookLoan");if(id==null)return;Map<String,String> r=db.get("BookLoan").get(id);
        if(!r.get("returnedDate").equals("Not returned")){System.out.println("This book was already returned.");return;}
        String d=date("Return date");if(LocalDate.parse(d).isBefore(LocalDate.parse(r.get("borrowedDate")))){System.out.println("Return date cannot be before borrowed date.");return;}
        if(read("Confirm return? (Y/N): ").equalsIgnoreCase("Y")){r.put("returnedDate",d);String b=r.get("book");stock.put(b,stock.getOrDefault(b,0)+1);System.out.println("Book returned. Stock updated.");}
    }
    private static void viewAll() {
        for(String t:TYPES){System.out.println("\n"+t.toUpperCase());if(db.get(t).isEmpty())System.out.println("No records.");
            for(Map<String,String> r:db.get(t).values())System.out.println(r);}
    }
    private static void search() {
        String id=required("Enter record ID");boolean found=false;
        for(String t:TYPES)if(db.get(t).containsKey(id)){System.out.println(t+": "+db.get(t).get(id));found=true;}
        if(!found)System.out.println("Record not found.");
    }
    private static void update() {
        String id=required("Record ID to update");String type=null;Map<String,String> r=null;
        for(String t:TYPES)if(db.get(t).containsKey(id)){type=t;r=db.get(t).get(id);break;}
        if(r==null){System.out.println("Record not found.");return;}
        List<String> keys=new ArrayList<>(r.keySet());for(int i=0;i<keys.size();i++)System.out.println((i+1)+". "+keys.get(i)+" = "+r.get(keys.get(i)));
        try{int n=Integer.parseInt(read("Field number to edit (0 cancel): "));if(n==0)return;if(n<1||n>keys.size())throw new Exception();
            String k=keys.get(n-1);if(k.equals("id")){System.out.println("ID cannot be changed.");return;}
            String v=required("New value for "+k);String old=r.get(k);r.put(k,v);
            if(read("Save update? Y/N: ").equalsIgnoreCase("Y"))System.out.println("Updated successfully.");else{r.put(k,old);System.out.println("Update cancelled.");}
        }catch(Exception e){System.out.println("Invalid field selection.");}
    }
    private static void academicSummary() {
        String s=select("Student");if(s==null)return;System.out.println("Academic summary for "+s);
        for(Map<String,String> e:db.get("Enrollment").values())if(s.equals(e.get("student"))){System.out.println("Enrollment: "+e);
            for(Map<String,String> a:db.get("Assessment").values())if(e.get("id").equals(a.get("enrollment")))System.out.println("Assessment: "+a);}
    }
    private static void paymentHistory() {
        String s=select("Student");if(s==null)return;boolean any=false;
        for(Map<String,String> p:db.get("FeePayment").values())if(s.equals(p.get("student"))){System.out.println(p);any=true;}
        if(!any)System.out.println("No payment history for this student.");
    }
    private static void availableBooks() {
        boolean any=false;for(Map<String,String> b:db.get("LibraryBook").values()){int n=stock.getOrDefault(b.get("id"),0);
            if(n>0){System.out.println(b.get("id")+" | "+b.get("title")+" | available: "+n);any=true;}}
        if(!any)System.out.println("No books currently available.");
    }
    private static void polymorphism() {
        System.out.println("OOP polymorphism demonstration:");
        System.out.println("A common menu workflow handles Department, Student, Teacher, Administrator and other record types.");
        System.out.println("In Java, a superclass/interface reference can refer to different subclass objects and invoke overridden methods.");
        System.out.println("The required hierarchy is Person -> AcademicMember -> Student/Teacher and Person -> Administrator.");
        System.out.println("The project model classes are in their prescribed packages; inspect Person, AcademicMember, Student and Teacher.");
    }
}
