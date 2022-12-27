# OOP Course - Assignment

A project for an OOP course taken through a B.Sc in Computer Science.

The project covered the Observer design pattern, we extended the abilities of the UndoableStringBuilder class (a wrapper around StringBuilder).
We've used 2 interfaces, Member - the observer interface, and, Sender - the subject interface.
The Tests have been written using JUnit 5 and using a JvmUtilities file for testing the memory usage and efficiency. 

### GroupAdmin class
The GroupAdmin class implements the Sender interface which is the subject. each instance posses an ArrayList of ConcreteMember objects, and an UndoableStringBuilder object which acts as our char sequence. Each time we register a member to the GroupAdmin, it is being added to the ArrayList of ConcreteMembers, thus it will be notified on any changed made to the UndoableStringBuilder object that the group admin holds by changing the member's UndoableStringBuilder field.
Methods implemented: register, unregister, insert, append, delete, undo, notify, ToString.

### ConcreteMember class
The ConcreteMember class implements the Member interface which is the observer. each instance posses a name field, and an UndoableStringBuilder object which acts as our char sequence. Each time we register a ConcreteMember to the GroupAdmin, it will be notified on any changed made to the UndoableStringBuilder object that the group admin holds by changing the member's UndoableStringBuilder field.
Methods implemented: update, ToString.

## Setup
**prerequisites:**
1. JDK version 19 and onwards
2. An IDE of choice which is capable of handling java projects with Maven

**dependencies:**
1. log4j-core
2. junit-jupiter-engine
3. jol-core
4. junit-jupiter-api

**Setup and run**
1. ```git clone https://github.com/zachibs/OOPMatala2.git ```
2. Build the project
3. Run Tests.java / Use the classes in your code
## Folder Structure

The workspace contains two folders:

- `src`: the folder to maintain source code
    - `main`: holds all interfaces and implementations
    - `test`: holds the JvmUtilities.java file and the Tests.java test file.
- `target`: the folder to hold the compiled output files
