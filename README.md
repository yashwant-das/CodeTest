# Code Test

[Selenium](https://www.google.com) framework to Automate [Google Forms](https://www.google.com/forms/about/) data entry from an external data file

## Pre-requisites 

- Install [Google Chrome](https://www.google.com/chrome/)
- Use the package manager to install [maven](https://maven.apache.org/) & [chromedriver](http://chromedriver.chromium.org/)

```
brew install maven
brew cask install chromedriver
```
I prefer [Homebrew](https://brew.sh/)

## Usage
- Clone the repository

```bash
https://github.com/yash3x/CodeTest
```
- Open a terminal and go to the project root and run

```
mvn clean test
```
## Reports
Reporting is achieved with the help of [Extent Reporting Framework](extentreports.com/) 

Test Reports can be found under ~/test-report/report/

## Note
* username and password under ~/resources/application.properties are base64 encoded.
* If you are facing any issues, try to build the project in IntelliJ IDEA and re-run the test