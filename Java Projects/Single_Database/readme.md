# Database Implementation

## Overview
This project implements a single database system from scratch without relying on any third-party database management system. It includes functionalities for Data Manipulation Language (DML), Data Definition Language (DDL), transaction logs, and user sign-in/sign-up.

## Features
- **Data Manipulation Language (DML):** Implements SQL-like commands for data manipulation, including INSERT, UPDATE, DELETE, and SELECT operations.
- **Data Definition Language (DDL):** Supports creating, modifying, and dropping database objects such as tables, views, indexes, and triggers.
- **Transaction Logs:** Maintains transaction logs to ensure data integrity and track changes made to the database.
- **User Sign-in/Sign-up:** Provides authentication and authorization mechanisms for users to sign in and sign up to access the database.

## Implementation Details
### DML
The DML functionalities allow users to perform the following operations:
- **INSERT:** Add new records to tables.
- **UPDATE:** Modify existing records in tables.
- **DELETE:** Remove records from tables.
- **SELECT:** Retrieve data from tables based on specified criteria.

### DDL
The DDL functionalities enable users to manage database objects:
- **CREATE:** Create new tables, views, indexes, and triggers.
- **ALTER:** Modify existing database objects.
- **DROP:** Remove database objects from the system.

### Transaction Logs
The system maintains transaction logs to ensure the following:
- **Atomicity:** All operations in a transaction are completed successfully, or none of them are.
- **Consistency:** The database remains in a consistent state before and after each transaction.
- **Isolation:** Transactions are isolated from each other to prevent interference.
- **Durability:** Once a transaction is committed, its changes are permanent and recoverable.

### Sign-in/Sign-up
Users can sign in to access the database and perform authorized operations based on their roles and permissions. The sign-up process allows new users to create accounts and obtain access credentials securely.

## Usage
1. **Setup Database:**
    - Create the database schema using DDL commands.
    - Define tables, views, indexes, and triggers as needed.
2. **Perform DML Operations:**
    - Insert, update, delete, and select data using DML commands.
3. **Manage Transactions:**
    - Begin, commit, or rollback transactions to maintain data integrity.
4. **User Authentication:**
    - Implement user sign-in and sign-up mechanisms for access control.
5. **Monitor Transaction Logs:**
    - Monitor transaction logs to track changes and ensure data consistency.

