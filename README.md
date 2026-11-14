# 📚 Library Management System

<div align="center">

### J.K.Y.S Tech

**A Java-Based Console Library Management System**

A simple and organized library management application designed to streamline book management, borrower records, and library transactions using Java.

![Java](https://img.shields.io/badge/Java-Programming-orange?style=for-the-badge&logo=openjdk)
![VS Code](https://img.shields.io/badge/VS_Code-IDE-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Academic_Project-green?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Console_Application-lightgrey?style=for-the-badge)

</div>

---

## 📖 About the Project

The **Library Management System** is a Java console-based application developed by **J.K.Y.S Tech** as an academic programming project.

The system aims to simplify library operations by providing an organized way to manage books, borrowers, and borrowing transactions.

It features role-based menus for administrators, students, and guests, allowing users to access functions appropriate to their roles.

The project demonstrates fundamental Java programming concepts, including object-oriented programming, conditional statements, loops, methods, collections, and input handling.

## ✨ Key Features

### 📚 Book and Asset Management

- Add new books to the library catalog.
- Search for existing books.
- Delete book records.
- Display the complete book catalog.
- View available library materials.
- Manage book borrowing and returning.

### 👥 Borrower Management

- Manage borrower information and records.
- Access borrower management through the administrator menu.
- Track borrowing activities.
- View individual borrower history.

### 🔄 Borrowing and Returning

- Allow students to borrow books.
- Process book returns.
- Track borrowing transactions.
- Display borrowing history.
- Provide administrators with access to library transaction records.

### 🔐 Role-Based Access

The system provides three user roles with different access privileges.

| User Role | Access and Permissions |
|---|---|
| 👨‍💼 Administrator | Manage borrowers, books, transactions, and library records |
| 🎓 Student | Borrow and return books, browse the catalog, and view borrowing history |
| 👤 Guest | Browse the library's book catalog |

### 🛡️ Input Handling

- Menu-driven navigation.
- Input validation for menu selections.
- Error messages for invalid choices.
- Repeated menu access until the user exits.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Main programming language |
| Visual Studio Code | Development environment |
| Java Development Kit (JDK) | Compilation and execution |
| Git | Version control |
| GitHub | Source code hosting and collaboration |

The application uses a console-based interface and does not require a graphical user interface.

---

## 📂 Project Structure

```text
LibrarySystem/
│
├── .vscode/
│   └── VS Code configuration
│
├── bin/
│   └── librarysystem/
│       └── Compiled Java classes
│
├── src/
│   └── librarysystem/
│       ├── Main.java
│       ├── LibrarySystem.java
│       ├── BorrowerManagement.java
│       └── Other supporting Java classes
│
└── README.md
```

**Main Components**

- `Main.java` — Entry point and user menu navigation.
- `LibrarySystem.java` — Core library operations, including book management and transactions.
- `BorrowerManagement.java` — Borrower-related management functions.
- Supporting classes — Handle user information, account selection, and other application functionality.

---

## 🚀 Getting Started

### Prerequisites

Before running the application, ensure that you have:

- Java Development Kit (JDK) installed.
- Visual Studio Code or another Java-compatible IDE.
- Git (optional, for cloning the repository).

### 1. Clone the Repository

Open a terminal and execute:

```bash
git clone https://github.com/zeth39/LibrarySystem.git
```

### 2. Navigate to the Project Directory

```bash
cd LibrarySystem
```

### 3. Open the Project

To open the project in Visual Studio Code:

```bash
code .
```

Make sure the Java extensions are installed and the JDK is properly configured.

### 4. Compile the Application

From the project root directory, run:

```bash
javac -d bin src/librarysystem/*.java
```

### 5. Run the Application

```bash
java -cp bin librarysystem.Main
```

The application will launch in the terminal and display the library system's introductory screen and user selection interface.

---

## 💻 System Interface

The application uses an interactive, text-based interface.

### Administrator Menu

```text
=== ADMIN MENU ===

1. Borrowers management
2. Asset management
3. Book history
4. Book details
5. Show book catalog
6. Show all library materials
0. Exit
```

### Student Menu

```text
=== STUDENT MENU ===

1. Borrow book
2. Return book
3. Show book catalog
4. Borrower history
5. Show all library materials
0. Exit
```

### Guest Menu

```text
=== GUEST MENU ===

1. Show book catalog
0. Exit
```

The available operations depend on the user's assigned role.

---

## 🧠 Programming Concepts Applied

This project demonstrates several important Java programming concepts.

**Object-Oriented Programming (OOP)**

The system organizes its functionality into Java classes, separating responsibilities such as menu navigation, borrower management, and library operations.

**Conditional Statements**

Conditional logic determines which menus and operations are available based on user selections and access privileges.

**Loops**

Repeated menu execution allows users to perform multiple operations without restarting the application.

**Methods**

Reusable methods organize operations such as adding, searching, borrowing, and returning books.

**Collections and Data Management**

Library records and transaction information are managed through the application's Java data structures.

**Input Validation**

User inputs are checked to help prevent invalid menu selections and improve program reliability.

---

## 🎯 Project Objectives

The Library Management System was developed to:

1. Simplify the management of library materials and borrower records.
2. Provide an organized borrowing and returning process.
3. Implement different access privileges for administrators, students, and guests.
4. Demonstrate practical applications of Java programming and OOP principles.
5. Improve the accuracy and organization of basic library transactions.
6. Develop problem-solving, debugging, and collaborative programming skills.

---

## 🔮 Future Improvements

Potential enhancements include:

- [ ] Graphical user interface (GUI).
- [ ] Database integration for persistent record storage.
- [ ] Advanced book searching and filtering.
- [ ] Automated report generation.
- [ ] Improved account authentication.
- [ ] Exportable borrowing and transaction reports.
- [ ] Web-based library management interface.

These features are potential future developments and are not necessarily included in the current implementation.

---

## 👨‍💻 Development Team

### J.K.Y.S Tech

Developed as a collaborative academic project to demonstrate Java programming, software development, and library management concepts.

**Repository:** [github.com/zeth39/LibrarySystem](https://github.com/zeth39/LibrarySystem)

**GitHub:** [@zeth39](https://github.com/zeth39)

---

## 📜 Project Disclaimer

This application was developed for **educational purposes**. It is intended to demonstrate fundamental programming concepts and basic library management operations rather than serve as a production-ready library system.

---

<div align="center">

### 📚 J.K.Y.S Tech — Library Management System

*Organizing knowledge, one book at a time.*

**Made with ☕ Java**

</div>