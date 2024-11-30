# US003 - Registering a new employee

## 1. Requirements Engineering

### 1.1. User Story Description

As a system administrator, I want to register a new employee.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>The company's systems administrator will be responsible for registering all employees (specifying
the name, the citizen's card number, the tax number, the address, the email address, the contact
telephone number and the agency to which it is assigned)

**From the client clarifications:**

>**Question**: Will the administrator have to specify the category/office where a new employee will perform, when registering one?  (for example whether they are an agent, a store manager or a store network manager)
>
>**Answer**: The administrator has to specify the role of the employee.

>**Question**: If the new registered employee is a store network manager, will he be assigned a specific agency/store?
>
>**Answer**: Please read the project description carefully.

>**Question**: What would be the attributes of the System Administrator, Agency and the Roles?
>
>**Answer**: The System Administrator is an employee.

>**Question**: When registering a new employee, will the administrator set the respective employee password?
>
>**Answer**: The password should have eight characters in length and should be generated automatically. The password is sent to the employee by e-mail.
>
>**Note**: This answer has been since corrected and is no longer valid.

>**Question**: Does the System Administrator have permission to create, edit, delete, or just create new employee registrations?
>
>**Answer**: For now, the System Administrator can only do what is specified in the Project Requirements.

>**Question**: Can an employee be registered to more than one agency?
>
>**Answer**: No.

>**Question**: In your Technology Requirements, it was stated all users of the System needs a password of "seven alphanumeric characters, including three capital letters and two digits." However, it was replied to a question when a new Employee is created in the system, that a 8 digit Password should be automatically generated. How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
>
>**Answer**: Sorry, I completely forgot that all our authentication systems require passwords with seven alphanumeric characters in length , including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.

>**Question**: Does the system administrator select the agency to which the employee is assigned and his role from a list? Or does he just type that data?
>
>**Answer**: The System Administrator should select.

>**Question**: The system administrator cannot add an agent that already exists, the agent has two unique numbers that identify him (Tax number and Citizen's card number) which one should be used to identify the agent?
>
>**Answer**: The tax number.

>**Question**: Must the Tax number and Citizen's card number follow any convention? If so, which?
>
>**Answer**: You should use the tax identification number used for tax purposes in the US.

>**Question**: When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>
>**Answer**: Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which they are assigned.

>**Question**: You've stated previously that an employee can only be registered to one agency so what happens if an employee wants/has to change agencies and needs to be registered to a new one? Should the system reject such operation or should the employee's previous registration be deleted?
>
>**Answer**: For now I do not want such features to be included in the system. I will discuss your suggestion with the company shareholders and I will come back here if we decide to include such features in the system.

>**Question**: You have stated before that name, cc number, tax number, email address, phone number and the assigned agency of the employee are the mandatory requirements to register a new one, leaving out the employee's adress and role. This confused me, because it wasn't clear whether leaving out those two characteristics from the answer was intentional or not. Futhermore, the role of the employee seems like too much of an important piece of information to be left out. My request is, then, for you to state whether or not that was a conscious decision in your answer.
>
>**Answer**: The role is required.

>**Question**: I have a question related to the output data: when the system administrator is registering a new employee are we free to display what we feel is important or should a specific message be shown? I was thinking of displaying whether the operation was successful or not, is that fine or should something else be displayed as well?
>
>**Answer**: A good practice is to show the information and ask for confirmation.


### 1.3. Acceptance Criteria

* **AC1:** The program lists the employees.

* **AC2:** The program allows an admin to register a new employee.

* **AC3**: The registration of a new employee includes gathering information such as: role, name, citizen's card number, tax number, address, email address, telephone number and assigned agency, of which all are mandatory except for the role and address of the employee (of each all but the later are mandatory).

* **AC4:** The registration process should include the auto generation of a password for the employee, sent via email.

* **AC5:** The generated password is seven alphanumeric characters in length , including three capital letters and two digits.

* **AC6:** It should be noted that each employee can only be assigned one agency.

* **AC7:** The same employee cannot be registered more than once.

* **AC8:** The system should check if the new employee is already registered through the tax number.

### 1.4. Found out Dependencies

This user story has no dependencies on other user stories.

### 1.5 Input and Output Data
**Input Data:**

* Typed data:
    * role
    * name
    * citizen's card number
    * tax number
    * address
    * email address
    * telephone number
    * assigned agency

* Selected data:
    * assigned agency

**Output Data:**

* (In)Success of the operation
### 1.6. System Sequence Diagram (SSD)

[US003-SSD](puml/us003-system-sequence-diagram.puml)

### 1.7 Other Relevant Remarks
n/a
