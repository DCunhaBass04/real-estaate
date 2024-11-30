# US001 - Display Listed Properties

## 1. Requirements Engineering

### 1.1. User Story Description

As an unregistered user, I want to display listed properties.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The user can consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.

**From the client clarifications:**

> **Question**: In the project's documentation it's mentioned that "All those who wish to use the application must be authenticated", but in the US1 it's said that an unregistered user can see a list of properties. Can users who aren't authenticated do this?
>
> **Answer**: Non-authenticated users can only list properties.


> **Question**: The properties can be in sale and lease at the same time?
>
> **Answer**: No.

> **Question**: An unregistered user can only see sale announcements, or he is able to contact the agency agents to make a purchase request ?
>
> **Answer**: From the project description: "As an unregistered user, I want to display listed properties". For now this is the only functionality of the system that the non-registered user can use.

> **Question**: Is there only one network of stores?
>
> **Answer**: Yes.

> **Question**: In the project description it is stated that "the client is, then, responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.". Is the client able to sort properties by only these 4 criteria or is he able to sort properties by any of the properties' characteristics?
>
> **Answer**: The client should be able to select the type of business (renting or buying), the type of property and the number of rooms. Then, the client should be able to sort properties by price or by parish where the property is located.
If the client does not select the type of business, the type of property and the number of rooms, the application should allow the client to sort all properties that are on sale or on renting.

> **Question**: The property size, location and type are typed or selected in order to filter the results?
>
> **Answer**: The client should be able to select (from a list) the type of business, the type of property and the number of rooms.


### 1.3. Acceptance Criteria

* **AC1:** The program lists all the properties on its system.
* **AC2:** The user doesn't need to be registered to view the properties.

### 1.4. Found out Dependencies

* In order to see all the properties, the system depends on the property sale or rent published by the agent (US002, Real Estate Agent).

### 1.5 Input and Output Data

**Input Data:**

* The option to list all properties

**Output Data:**

* List of existing properties
* For each property, list:
    * price,
    * area,
    * location.
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

[US001-SSD](puml/us001-system-sequence-diagram.puml)

### 1.7 Other Relevant Remarks

The way the properties are listed on the website depends on the user's current machine (e.g. with a phone or a computer).