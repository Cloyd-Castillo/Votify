# 📚 **Votify - Voting System**

---

## I. **Project Overview**

### **Project Description**  
**Votify** is a Java-based voting system designed to facilitate the creation, management, and participation in polls. It supports both administrators and users, offering functionalities such as creating polls, voting, and viewing results. Administrators can create timed or untimed polls, end polls, and manage active polls dynamically. Users can participate in polls and view results in real time. The system emphasizes user-friendly interaction, security, and scalability, aligning with Object-Oriented Programming (OOP) principles.

---

## II. **Application of OOP Principles**

### **🔒 Encapsulation**
- Encapsulation is applied through the use of private fields and public getter and setter methods in classes like Poll and TimedPoll. For instance, poll details such as title, options, and votes are encapsulated to prevent direct access from external code, ensuring data integrity and controlled interaction.

### **🔄 Polymorphism**
- Polymorphism is evident in the ability to handle both Poll and TimedPoll objects through a common Poll reference. For example, methods like addOption and displayResults behave differently based on the actual type of the poll instance, demonstrating runtime polymorphism.

### **🔍 Abstraction**
- Abstraction is implemented by defining clear interfaces and methods for essential operations such as addOption, vote, and endPoll. Users and administrators interact with high-level functionality without needing to understand the underlying implementation details.

### **🌳 Inheritance**
- The system uses inheritance to promote code reuse and hierarchy. The TimedPoll class extends the Poll class, inheriting its attributes and methods while introducing additional functionality specific to timed polls, such as duration management.

---

## III. **Chosen Sustainable Development Goal (SDG)**

### **Goal 16: Peace, Justice, and Strong Institution**

**Target:**  
Ensure responsive, inclusive, participatory, and representative decision-making at all levels.

- **🗳️ Promoting Democratic Participation:** The voting system enables inclusive decision-making by providing a platform where individuals can participate in fair polls.
- **🌐 Accessibility and Transparency:** The system ensures that results are accessible to all participants and that voting processes are transparent.
- **📈 Empowering Institutions:** By offering tools for creating and managing polls, Votify supports institutions in fostering fair and representative decision-making processes.
- **💡 Future Growth:** The platform can be expanded to support larger-scale elections or opinion polls, aligning with SDG 16 by encouraging peaceful and inclusive societies.
---

## VI. **How to Run the Program**

### **Prerequisites**
- **Code Editor or IDE** (e.g., Visual Studio Code)
- **Java Development Kit (JDK)**

### **Steps**
1. **Compile the Votify.java main file**
  
2. **Follow the on-screen prompts:**
   - Choose from the main menu options (Register, Login, Admin Login, Exit).
   - Navigate admin or user menus based on login role.

3. **Admin Operations:** 
   - Create polls (timed or untimed).
   - View poll results.
   - End polls with real-time status updates.

4. **User Operations:**
   - Register a new account and log in.
   - Vote in available polls.
   - View results of ended polls.

5. **Exit the program by selecting the appropriate menu option.**
