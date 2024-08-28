# Bind-Request-Generator-for-ldap

The `BindRequestGenerator` is a simple Java application that generates an LDAP Bind Request message. This application allows users to input their Distinguished Name (DN) and password, then constructs and prints the LDAP Bind Request in hexadecimal format.

## Features

- Prompts the user for LDAP Distinguished Name (DN) and password.
- Outputs the Bind Request in hexadecimal format.

## Prerequisites

- Java Development Kit (JDK) 8 or higher.

## How to Run

1. **Clone the Repository**: If you haven't already, clone this repository to your local machine.
   ```sh
   `git clone https://github.com/chandan-fosteringlinux/Bind-Request-Generator-for-ldap.git`

3. **Compile the Code**: Navigate to the directory containing the BindRequestGenerator.java file and compile it using the Java compiler.
   ```sh
   `javac BindRequestGenerator.java`

5. **Run the Application**: Execute the compiled class file.
     ```sh
    `java BindRequestGenerator`

6. **Provide Input**: When prompted, enter the DN and password. The application will generate and display the LDAP Bind Request in hexadecimal format.

7. **Example**
   `Enter DN (e.g., cn=Directory Manager): cn=Directory Manager
Enter password: chandan
LDAP Bind Request (hex): 30 0c 02 01 01 60 0a 02 01 03 04 1f 63 6e 3d 44 69 72 65 63 74 6f 72 79 20 4d 61 6e 61 67 65 72 80 07 63 68 61 6e 64 61 6e
` 
