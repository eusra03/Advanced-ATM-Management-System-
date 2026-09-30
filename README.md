# ATM Management System

A Java-based **ATM Management System** designed to simulate essential banking operations such as customer authentication, balance inquiry, deposits, withdrawals, fund transfers, account registration, and administrator management.

The project was developed as part of the **CSE 215L** course at **North South University** by **Group 7**.

---

##  Project Overview

Automated Teller Machines (ATMs) provide customers with convenient self-service banking facilities without requiring direct assistance from a bank teller.

This project implements an ATM Management System using **Java** with a modular, object-oriented design. The system extends basic ATM functionality by including features such as:

* Customer account registration
* Admin approval of new accounts
* Cash deposits
* Cash withdrawals
* Fund transfers
* Balance inquiry
* Customer account management
* ATM balance management
* File-based data storage
* Authentication and access control

The system is primarily implemented as a **console-based Java application**.

---

##  Main Goal

The main goal of the project is to provide more functionality than a basic ATM system.

In addition to withdrawing money, the system allows users to:

* Deposit money
* Transfer money between accounts
* Register new accounts
* View account information
* Manage their PIN
* Perform various banking operations

The system also provides administrators with tools to oversee customer accounts and ATM operations.

---

##  Features

###  Customer Features

* Customer login and authentication
* Account balance inquiry
* Cash withdrawal
* Cash deposit
* Fund transfer
* Account information viewing
* Transaction/mini-statement generation
* PIN management
* Persistent customer information using files

###  Administrator Features

* Administrator login
* View customer accounts
* Check ATM balance
* Manage customer registration requests
* Approve new customer registrations
* Remove approved registration requests
* Add registration requests from stored files
* Monitor customer account information

###  Registration

New customers can submit registration requests by providing:

* Account ID / username
* PIN / password
* Password confirmation
* Initial deposit

The administrator can then review and approve registration requests.

###  File Management

The system uses file-based storage to maintain customer and registration information.

Customer information can be:

* Read from files
* Written to files
* Updated after transactions

The project also uses CSV-based data storage for customer information.

---

##  Project Structure

The system is organized around several Java classes, each responsible for a specific part of the application.

```text
ATM Management System
│
├── ATM
│   └── Main application and program flow
│
├── LogIn
│   └── Authentication and login functionality
│
├── Customer
│   └── Customer account operations
│
├── Admin
│   └── Administrator operations
│
├── TransferMoney
│   └── Money transfer functionality
│
├── RegistrationRequest
│   └── New customer registration
│
└── CustomerReadWrite
    └── Reading and writing customer data
```

---

##  Main Classes

### `ATM`

The main class responsible for running the application and coordinating the different classes and methods.

After starting the application, users are presented with the main menu.

The main menu provides options for:

1. Admin Login
2. Customer Login
3. New Customer Registration
4. Exit

---

### `LogIn`

Responsible for handling login-related functionality.

#### Attributes

```java
accountNo : String
pin : String
```

#### Main Methods

```java
Login(String accountNo, String pin)
getAccountNo()
getPin()
setAccountNo(String accountNo)
setPin(String pin)
authenticate(String accountNo, String pin)
```

The `authenticate()` method validates the supplied account credentials and returns a boolean result.

---

### `Customer`

Represents a customer account and extends the login functionality.

#### Attribute

```java
balance : double
```

#### Main Methods

```java
getBalance()
setBalance(double balance)
deposit(double amount)
withdraw(double amount)
toString()
```

The `deposit()` and `withdraw()` methods update the customer's balance as well as the ATM balance where applicable.

---

### `Admin`

Handles administrator-level operations.

#### Main Attributes

```java
atmBalance : double
RegistrationRequests : ArrayList
```

#### Main Methods

```java
getAtmBalance()
setAtmBalance(double atmBalance)
listRegistrationRequests()
checkAtmBalance()
checkCustomerAccount()
approveRegistration()
addRegistrationRequest()
removeRegistrationRequest()
addRegistrationRequestsFromFile()
getRegistrationRequestByUsername()
```

The administrator can monitor ATM funds, view customer accounts, and approve new customer registration requests.

---

### `TransferMoney`

Responsible for transferring money between customer accounts.

```java
transfer(
    String fromAccount,
    String toAccount,
    double amount,
    ArrayList<Customer> customers
)
```

The method transfers the specified amount between accounts, updates the customer balances, writes the updated information to the file, and returns a mini statement.

---

### `RegistrationRequest`

Handles new customer registration.

#### Attributes

```java
username : String
password : String
confirmPass : String
initialDeposit : double
```

#### Main Methods

```java
getUsername()
getPassword()
getConfirmPass()
getInitialDeposit()
storeNewUser()
adminApproval()
```

The class stores registration information and supports the administrator approval process.

---

### `CustomerReadWrite`

Responsible for reading and writing customer information using files.

```java
readCustomers()
writeCustomers(customers)
```

Customer information is stored using CSV-based file handling.

---

##  Application Flow

```text
                  ┌─────────────────┐
                  │   Start ATM     │
                  └────────┬────────┘
                           │
                           ▼
                  ┌─────────────────┐
                  │    Main Menu    │
                  └────────┬────────┘
                           │
             ┌─────────────┼─────────────┐
             ▼             ▼             ▼
       Admin Login    Customer Login   Registration
             │             │             │
             ▼             ▼             ▼
       Admin Panel    Customer Panel   Submit Request
             │             │             │
             │       ┌─────┼─────┐       ▼
             │       │     │     │    Admin Approval
             │       ▼     ▼     ▼       │
             │    Deposit Withdraw       ▼
             │       │     │      Transfer
             │       │     │         │
             └───────┴─────┴─────────┘
                           │
                           ▼
                     File Storage
```

---

##  Technologies Used

* **Java**
* **Object-Oriented Programming (OOP)**
* **ArrayList**
* **File Handling**
* **CSV**
* **Java Swing** *(explored for GUI implementation)*

---

##  Java Libraries Used

The project makes use of several Java classes for collections, user input, and file handling.

| Library / Class  | Purpose                                       |
| ---------------- | --------------------------------------------- |
| `ArrayList`      | Stores and manipulates collections of objects |
| `Scanner`        | Reads user input                              |
| `BufferedReader` | Reads text from files efficiently             |
| `File`           | Handles file and directory paths              |
| `FileReader`     | Reads character data from files               |
| `BufferedWriter` | Writes text efficiently using buffering       |
| `FileWriter`     | Writes character data to files                |

These libraries support the application's collection management, user interaction, and persistent file storage.

---

##  Authentication

The system provides separate authentication flows for administrators and customers.

### Admin

The administrator logs in using an administrator account and can access administrative functions such as:

* Viewing customer information
* Checking ATM balance
* Managing registration requests

### Customer

Customers must provide their existing account ID and PIN to access their account portal.

After successful authentication, customers can perform available banking operations such as:

* Deposit
* Withdrawal
* Transfer
* Account information viewing

---

##  Banking Operations

### Deposit

A customer can deposit money into their account.

The operation updates:

```text
Customer Balance
       +
ATM Balance
```

---

### Withdrawal

A customer can withdraw money from their account.

The operation updates:

```text
Customer Balance
       -
ATM Balance
```

---

### Transfer

Customers can transfer money from one customer account to another.

```text
Sender Account
      │
      │  Transfer Amount
      ▼
TransferMoney
      │
      ▼
Receiver Account
```

The updated account information is also persisted to the file.

---

##  Registration Process

New customers follow this process:

```text
New Customer
     │
     ▼
Enter Account ID
     │
     ▼
Enter PIN
     │
     ▼
Confirm PIN
     │
     ▼
Enter Initial Deposit
     │
     ▼
Registration Request
     │
     ▼
Administrator Review
     │
     ├── Approved ──► Customer Account Created
     │
     └── Pending ───► Request Remains Stored
```

---

##  How to Run

### Prerequisites

Make sure Java is installed on your computer.

Check your Java installation:

```bash
java -version
```

and:

```bash
javac -version
```

### Clone the Repository

```bash
git clone https://github.com/your-username/your-repository-name.git
```

Navigate into the project:

```bash
cd your-repository-name
```

### Compile

Compile the Java source files using:

```bash
javac *.java
```

### Run

Run the main ATM class:

```bash
java ATM
```

NOTE: Check the report pdf for detailed images of workflow.
---

##  Sample Credentials

The project report provides the following credentials for trial purposes:

### Admin

```text
Account ID: admin123
Password/PIN: password
```

### Customer

```text
Account ID: Amreen
PIN: 5001
```

These credentials are intended for testing the project as documented in the project report.

> **Security Note:** Do not use real banking credentials or sensitive personal information when testing or deploying this educational project.

---

##  Object-Oriented Programming Concepts

The project demonstrates several OOP concepts.

### Inheritance

`Customer` and `Admin` inherit login-related functionality from `LogIn`.

### Methods

Methods are implemented throughout the different classes to provide specific system functionality.

### Overriding

Method overriding is demonstrated in the `Customer` class.

### Encapsulation

Class attributes are managed through constructors, getters, and setters where applicable.

### `final` Variables

The `RegistrationRequest` class uses final variables for registration information.

### ArrayList

`ArrayList` is used to manage customer objects and registration requests.

### Error Handling

Error handling is implemented in classes including:

* `Admin`
* `RegistrationRequest`
* `CustomerReadWrite`

The project report identifies these concepts as important lessons learned during development.

---

##  Data Storage

The application uses files to store persistent information.

File handling is used for:

* Customer information
* Registration requests
* Updating account information
* Reading stored customer data

CSV files are used for customer information to provide a more readable representation of stored data.

---

##  Challenges Faced

Several challenges were encountered during development:

* Binary file reading
* Implementing registration approval
* Implementing the GUI properly
* Managing customer lists
* Understanding and applying `ArrayList`
* Experimenting with interfaces and abstract classes

The GUI was explored using Java Swing, but a complete GUI implementation was not achieved due to limited experience and development time.


##  Future Improvements

Possible future improvements include:

* Complete GUI implementation using **JavaFX or Java Swing**
* Biometric login
* Comprehensive transaction logging
* Improved administrator functionality
* Customer ID sorting and organization
* Additional security restrictions
* Improved error handling
* Bug fixes

These improvements are identified in the original project report as future development possibilities.


##  Acknowledgement

This project was developed as an academic project to practice Java programming, object-oriented programming, file handling, collections, authentication, and basic banking-system logic.

---


