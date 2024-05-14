# PRE Performance Tests

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Purpose

This is the Pre recorded evidence apps Performance Tests to verify
1. portal performance
2. Powerapps api connector performance

## Overview

<p align="center">
<b><a href="https://github.com/hmcts/pre-performance-tests">pre-performance-tests</a></b> • <a href="https://github.com/hmcts/pre-portal">pre-portal</a> • <a href="https://github.com/hmcts/pre-api">pre-api</a> • <a href="https://github.com/hmcts/pre-shared-infrastructure">pre-shared-infrastructure</a> •
</p>


## Getting Started

This is repository for the pre Performance Tests
- Step1: Clone the repo to your local/VM to run
- Step2: cd into the pre-performance-tests directory
- Step3: Edit precreatecasebookingSimulation and generate jwt code and add into the file
         Get clientid and add the client id into prePortalSimulation.scala
- Step4: Run the test with the command `gradle gatlingRun`

## Building and deploying the application

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:
```bash
  ./gradlew build
```

### Running the application

To run locally: - Performance test against the perftest environment: 

```bash
./gradlew gatlingRun
```

Flags: - Debug (single-user mode): -Ddebug=on e.g. ./gradlew gatlingRun -Ddebug=on - Run against AAT: Denv=aat e.g.:
```bash
./gradlew gatlingRun -Denv=<environment>
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details..

