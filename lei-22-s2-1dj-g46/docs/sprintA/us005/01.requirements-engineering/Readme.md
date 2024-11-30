# US005 - Register a Store

## 1. Requirements Engineering


### 1.1. User Story Description

As a system administrator, I want to register a store.

### 1.2. Customer Specifications and Clarifications
**From the specifications document:**

> Each store in the network has a store manager and the set of stores is managed by a store network
manager. The main functions of a store manager are to monitor and streamline the branch with the
aim of getting to know better the business carried out and to analyse and evaluate the performance
of employees

**From the client clarifications:**

> **Question**: Are there any restrictions on the choice of an Agent?
>
> **Answer**: No.

> **Question**: In case the submission of the listing is online may the client choose any agent?
>
> **Answer**: Yes.

> **Question**: In case it is on an agency, must the agent be assigned automatically by the system?
>
> **Answer**: The agent that registers the information in the system can choose to assign any agent.

> **Question**: Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer**: No.

> **Question**: When the administrator registers a new employee, he also assigns an agency to him ? If yes, when an client adding a property chooses the agent he is also choosing the agency ( which is the one assigned to the agent selected). If this is not true please provide more info about own the process of choosing an agent/agency.
>
> **Answer**: Please check the project description available in moodle.

> **Question**: Also another question, when publishing a property, if the client leaves the listing unfinished, can it be saved or stay as as a sketch to be finished later ?
>
> **Answer**: No.

### 1.3. Acceptance Criteria

* **AC1:** The program register the store in the system.

### 1.4. Found out Dependencies

This user story has no dependencies on other user stories.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Store information
        * name
        * address
        * contact details

**Output Data:**

* (In)Success of the operation
* Unique ID code used to identify the store

### 1.6. System Sequence Diagram (SSD)

[US005-SSD](puml/us005-system-sequence-diagram.puml)

### 1.7 Other Relevant Remarks
n/a