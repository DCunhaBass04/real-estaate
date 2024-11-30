# US004 - Request for Listing

## 1. Requirements Engineering

### 1.1. User Story Description

As an client, I intend to submit a request for listing a property sale or rent,
choosing the responsible agent.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Owners go to one of the company's branches and meet with a real estate agent to sell or rent one or more properties, or they can use the company's application for the same purposes. The client provides property characteristics and the requested price and sends the request to an agent.

**From the client clarifications:**

> **Question**: In the Project description, there are only specifications for a Sale. What are the required characteristics for a rental?
>
> **Answer**: The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration.

> **Question**: Are there any restrictions on the choice of an Agent?
>
> **Answer**: No.

> **Question**: Does that imply that a seller can choose the agency/branch/store independently of the location of the property?
>
> **Answer**: Yes.

> **Question**: Also, must the agent who reviews and publishes an advertisement request be the same agent who accepts or rejects the purchase/lease request for that listing?
>
> **Answer**: The agent that receives the request is the one that posts the announcement.

> **Question**: In case the submission of the listing is online may the client choose any agent?
>
> **Answer**: Yes.

> **Question**: In case it is on an agency, must the agent be assigned automatically by the system?
>
> **Answer**: The agent that registers the information in the system can choose to assign any agent.

> **Question**: Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer**: No.

> **Question**: When publishing a property, if the client leaves the listing unfinished, can it be saved or stay as as a sketch to be finished later ?
>
> **Answer**: No.

> **Question**: Is there a designated currency for this business, or should we use USD?
>
> **Answer**: Please use USD.

> **Question**: When publishing a property, if the client leaves the listing unfinished, can it be saved or stay as a sketch to be finished later ?
>
> **Answer**: No.

> **Question**: Should we consider that, until the request is reviewed and posted, the request stays in a "not published" state?
>
> **Answer**: This is an implementation detail. For me, as a client, I want the feature implemented as I already described in the project description.

> **Question**: Does an client need to be registered in the system to submit a request for a property listing?
>
> **Answer**: No. When making the request to list a property, the client should introduce his own data. The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the telephone number.

> **Question**: When assigning an agent to a property listing, are the available agents shown by the system for the client to pick? Or does the client need to provide the agent's information (name, agency,etc)?
>
> **Answer**: The client should select one agent from a list of agents that work in the selected agency. The client should select the agency before selecting the agent.

### 1.3. Acceptance Criteria

* **AC1:** The system lists all available agents
* **AC2:** The request is sent to the system so the chosen agent can see it.

### 1.4. Found out Dependencies

* The agent that the client will choose must be listed as an employee registered in the system (US003, Registering a new employee).

### 1.5 Input and Output Data

**Input Data:**

* Data about the property:
  * Typed data:
    * price,
    * area,
    * location,
    * distance from the city centre,
    * photographs.
  * Selected data:
     * Type of property,
     * type of commission,
     * type of business.

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

[US004-SSD](puml/us004-system-sequence-diagram.puml)

### 1.7 Other Relevant Remarks

When listing possible responsible agents, the system must only show the available agents (Not all employees nor users are agents).
