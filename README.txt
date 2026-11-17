AUCA_ID - SCHOOL MANAGEMENT SYSTEM
Student ID: 29649

HOW TO RUN (Windows)
1. Extract the AUCA_ID folder from the ZIP file.
2. Open the extracted AUCA_ID folder.
3. Double-click run.bat.
   The script compiles the Java source and starts the console program.
4. You can also open the folder in VS Code, open Terminal in the AUCA_ID folder,
   and run:
       javac -d out -sourcepath src src\com\auca\main\Main.java
       java -cp out com.auca.main.Main

REQUIREMENTS
- Install a JDK and ensure javac and java are available in PATH.
- Uses a single Scanner and stores records in memory (records reset when app exits).
- Student ID is shown as 29649.

IMPORTANT ACADEMIC NOTE
This is a runnable learning implementation with the requested 19 menu choices and
basic registration, selection, listing, search, update, academic/payment reports,
and book borrow/return stock handling. It is not a guaranteed complete match to
every detailed requirement in the 14-page assignment. In particular, the registration
screens are simpler than the required four-step smart forms, and some advanced
cross-record validation and object-based polymorphism demonstrations still need
further work. Review and understand the source before presenting or submitting it.
