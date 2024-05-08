# ACL Packet Checker

This Java application, named "StandardACL" reads ACL (Access Control List) statements from a specified file, checks them against input addresses, and determines whether to permit or deny access based on predefined rules.

## How to Use

1. **ACL File Configuration:**
    - Ensure the ACL rules file ("acl_rules.txt") is correctly formatted.
    - Specify deny and permit rules in the file in standard ACL format
    - Each rule should be on a new line.

2. **Input Addresses Configuration:**
    - Update the input addresses file ("input_addresses.txt").
    - Each address should be on a new line.

3. **Running the Application:**
    - Execute the `StandardACL` class, and the application will process ACL rules and check input addresses accordingly.
    - The results will be displayed in the console.

## Dependencies

- Java 8 or higher


