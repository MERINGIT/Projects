# Extended ACL

This Java application, named "Extended ACL" reads ACL (Access Control List) statements from a specified file, checks them against input addresses, and determines whether to permit or deny access based on predefined rules.

## How to Use

1. **ACL File Configuration:**
    - Ensure the ACL rules file ("acl_rules.txt") is correctly formatted.
    - Specify deny and permit rules in the file in extended ACL format
    - Each rule should be on a new line.

2. **Input Addresses Configuration:**
    - Update the input addresses file ("input_addresses.txt") with the IP addresses you want to check (source IP,destination IP and Port Number).
    - Each address should be on a new line.

3. **Running the Application:**
    - Execute the `ExtendedACL` class, and the application will process ACL rules and check input addresses accordingly.
    - The results will be displayed in the console.

## Dependencies

- Java 8 or higher


