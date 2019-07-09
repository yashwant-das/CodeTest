# Code Test

Selenium framework to Automate Google Forms entry from an external *.xlsx file

## Installation

* Clone the repository

```bash
https://github.com/yash3x/CodeTest
```

* Use the package manager to install maven, chromedriver

```bash
brew install maven
brew cask install chromedriver
```

## Usage
username and password under ~/resources/application.properties are base64 encoded.

Open a terminal and go to the project root and run

```
mvn clean test
```

## Note
If you face any issue, try to build the project in IntelliJ IDEA and re-run the test