# 📚 **Votify - Voting System**

---

## I. **Project Overview**

### **Project Description**  
**Votify** is a Java-based voting system designed to facilitate the creation, management, and participation in polls. It supports both administrators and users, offering functionalities such as creating polls, voting, and viewing results. Administrators can create timed or untimed polls, end polls, and manage active polls dynamically. Users can participate in polls and view results in real time. The system emphasizes user-friendly interaction, security, and scalability, aligning with Object-Oriented Programming (OOP) principles.

---

## II. **Application of OOP Principles**

### **🔒 Encapsulation**
- Encapsulation is demonstrated by restricting direct access to critical fields using private access modifiers. For example, in the User class, the username and password fields are private, and access is provided through controlled getter methods. This ensures data security and maintains the integrity of the User objects while allowing flexibility for future enhancements.
### **🌳 Inheritance**
- Inheritance is implemented through the Poll and TimedPoll classes. TimedPoll extends Poll to inherit its properties and methods, while adding specific functionality such as time-bound voting periods. This allows reusability and extension of the base poll features without duplicating code.

### **🔄 Polymorphism**
- Polymorphism is applied in the handling of polls where objects of both Poll and TimedPoll are treated uniformly through their shared interface. For example, the voting and results display functionalities use polymorphic behavior to interact seamlessly with both regular and timed polls, enhancing flexibility and scalability.

### **🔍 Abstraction**
- Abstraction is utilized by dividing the system into high-level components, such as services (AdminService and UserService) and utility classes like FileManager, to hide implementation details. This separation makes the code easier to manage and extend, as users of these services interact with clean, intuitive methods without concerning themselves with the underlying logic.

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

## IV. **How to Run the Program**

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
