# JSON Verifier

This Java project provides a method to verify input JSON data, specifically in the format of an AWS IAM Role Policy definition. The method checks whether the Resource field of each statement in the policy contains a single asterisk (*) and returns false if it does, and true otherwise.

## Usage

1. Clone the repository:

    ```bash
    git clone https://github.com/jakubkopta/JSONVerifier.git
    ```
2. Navigate to the project directory:

    ```bash
    cd JSONVerifier
    ```

3. Compile the Java code:

    ```bash
    javac JSONVerifier.java
    ```

4. Run the program:

    ```bash
    java JSONVerifier
    ```

The program will print the verification result based on the example JSON file provided in the `src/main/resources` directory.

## Input

The input JSON file should follow the AWS::IAM::Role Policy format. You can provide your own JSON file or modify the example file located at `src/main/resources/exampleFile.json`.

## Output

The program returns a list of boolean values indicating whether each statement's Resource field contains a single asterisk (*) or not.

## Dependencies

- Jackson Databind library is used for parsing JSON files. You can include it in your project using Maven or Gradle:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>{version}</version>
</dependency>
