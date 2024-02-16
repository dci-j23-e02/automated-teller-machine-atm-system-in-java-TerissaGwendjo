---
title: ATM System Documentation
date: February 16, 2024
---
Date: February 16, 2024

# ATM System Report

## Introduction
The ATM (Automated Teller Machine) System is a Java-based application designed to simulate the functionality of a real-world ATM. The system allows users to perform various banking operations, including deposit, withdrawal, funds transfer, balance inquiry, and more.

## Objective
The primary objective of the ATM System is to provide users with a convenient and secure way to manage their bank accounts remotely. The system aims to replicate the core functionalities of a traditional ATM while incorporating additional features for enhanced user experience and security.

## Components
The ATM System consists of the following key components:

- **ATM Class:** The main class of the application responsible for user interaction, authentication, and execution of banking operations.
- **FileManager:** Manages the reading and writing of user records from and to a file.
- **Transaction and TransactionFactory:** Classes responsible for creating and executing various types of banking transactions such as deposit, withdrawal, and funds transfer.
- **TransactionHistoryManager and ScheduledTransactionManager:** Manage the transaction history and scheduled transactions, respectively.
- **AccountAlertManager:** Detects and handles unusual activity in user accounts.
- **CurrencyConverter:** Facilitates currency conversion for multi-currency support.
- **UserProfileManager:** Manages user profiles and related information.
- **SecurityManager:** Implements security enhancements such as data encryption and decryption.

## Features
The ATM System offers the following features:

- User Authentication
- Balance Inquiry
- Transaction Managements
- Transaction History
- Scheduled Transactions
- Account Alerts
- Multi-Currency Support
- User Profile Management
- Security Enhancements

## Testing
Testing is essential and was done to ensure the reliability and functionality of the ATM System. The system should undergo unit testing, integration testing, and system testing to identify and address any defects or issues. But for this Project, we underwent Unit Testing.

- ### 1. Test Cases for Individual Methods
Each test case focuses on testing a specific method or functionality within the ATM class, such as `testAuthenticateUser()`, `testDisplayBalance()`, `testDisplayAccountAlerts()`, etc.

- ### 2. Isolation of Code Under Test
The tests isolate the code under test (e.g., methods in the ATM class) from external dependencies such as user input, file I/O, or network communication. This isolation allows for targeted testing of individual units.

- ### 3. Assertion of Expected Behavior
In each test case, assertions are made to verify the expected behavior of the method being tested. For example, in `testDisplayAccountAlerts()`, the expected output is compared against the actual output captured during the test execution.

### 4. Mocking and Stubbing (Potentially)
While not explicitly shown in the provided tests, unit tests may involve mocking or stubbing external dependencies or collaborating components to simulate their behavior and isolate the unit being tested further. This helps ensure that the unit test focuses solely on the functionality of the unit under test.

## Conclusion
The ATM System is a robust and user-friendly application designed to provide users with convenient access to banking services. With its comprehensive features and security measures, the system aims to deliver a seamless banking experience while ensuring the safety and privacy of user data.

## Future Enhancements
- Implement support for additional banking operations such as bill payment, account management, and cardless transactions.
- Enhance user interface for improved usability and accessibility.
- Integrate real-time transaction alerts and notifications for users.
- Implement biometric authentication for enhanced security and user convenience.
- Introduce support for contactless transactions technology and much more.
