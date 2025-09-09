# JSON_Parser

A simple JSON parser project in Java that validates JSON files according to the language rules. The parser identifies objects, arrays, strings, numbers, booleans (`true`/`false`), and `null`.

## Challenge

This project was developed for the following challenge:  
[https://codingchallenges.fyi/challenges/challenge-json-parser](https://codingchallenges.fyi/challenges/challenge-json-parser)

## Project Structure

```
JSON_Parser.iml
JSONParser.class
JSONParser.java
Classes/
  Lexer.java
  Parser.java
  Token.java
  TokenType.java
tests/
  step1/
  step2/
  step3/
  step4/
.idea/
```

- **JSONParser.java**: Main entry point of the program.
- **Classes/**: Implementation of parser components.
- **tests/**: Test files for different validation steps and cases.

## How to Compile

Compile all Java files:

```sh
javac JSONParser.java
```

## How to Run

Run the program, passing the path to the JSON file to validate:

```sh
java JSONParser tests/step2/valid.json
```

If the file is valid, the message will be displayed:

```
Valid JSON: {file content}
```

If there is a syntax error, an error message will be shown.

## Tests

Test files are located in `tests/step1`, `tests/step2`, `tests/step3`, and `tests/step4`, containing valid and invalid examples for each validation step.

## Author

Developed by Eduardo.