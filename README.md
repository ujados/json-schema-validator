# JSON Validator

A Java-based application for validating JSON files against JSON schemas. This project uses **Java 21** and the *
*`com.networknt` JSON Schema Validator** library to ensure JSON data conforms to specified schema definitions.

## Features

- Validate JSON files against JSON schemas.
- Option to ignore date-related validation errors.
- Simple graphical user interface (GUI) for ease of use.
- Command-line validation support (if extended).

## Requirements

- **Java 21** (Ensure it is installed and set up in your environment).
- No additional frameworks or dependencies beyond the `com.networknt` JSON Schema Validator library.

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd json-validator
   ```
2. Build the project using Maven:
   ```bash
   mvn clean package
   ```
3. Run the application:
   ```bash
   java -jar target/json-schema-validator-1.0.jar
   ```
4. Packaging:

   This application has been packaged using jpackage, a tool provided by the JDK for creating native installers and
   application bundles. The packaging process includes the runtime image to ensure the application runs with Java 21
   without requiring a separate JDK installation.

   Packaging Command:
   ```bash
   jpackage --input target --main-jar json-schema-validator-1.0.jar --main-class org.json.json_schema_validator.JsonValidatorApplication --name JsonValidator --type app-image  
   ```

5. Executables:

   This repository includes a Windows executable packaged with jpackage for ease of use. The executable allows
   running the application without requiring a separate Java installation, as it includes the necessary runtime.

   Notes:

   The Windows executable is located in the json-schema-validator\JsonValidator\JsonValidator.zip. JsonValidator.exe.
   For other platforms, or if you prefer, you can still run the application directly.

## Usage
#### GUI Mode
Launch the application.
Provide the paths to the JSON schema and the JSON file to validate.
(Optional) Check the "Ignore Date Errors" box to skip date-related validation errors.
Click the "Validate" button to see the results.  
