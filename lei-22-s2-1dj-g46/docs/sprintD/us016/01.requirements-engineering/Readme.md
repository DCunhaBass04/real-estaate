# US 016 - Sending an Email Response

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, when viewing a booking request, I want to respond to the user that scheduled the visit.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

> After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions. **The agent receives the request, checks the availability and sends the response.**


**From the client clarifications:**

> **Question:** In AC2, what is DEI's email service? Are you referring to Outlook?
> 
> **Answer:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.: gmail, DEI's email service, etc.). DEI email service is an email service like gmail or Outllook. These are only examples and you should prepare your system to support any email service.

> **Question:** When the agent is responding to the user that created the request, what should the answer be? Because accepting or declining the request is already done in US011.
> 
> **Answer:** In US11 the agent wants to accept or decline a purchase order for a property. In US16 the agent wants to answer visit requests. Please discuss the requirements with your team and professors before making a question.

> **Question:** When the agent requests the booking requests list to contact the client, that list should ONLY contain the requests related to that agent?
> 
> **Answer:** Yes. Listing is a feature described in US15. Important: In US15 the Agent gets a list of booking requests (made to him). Then, the agent, may want to respond to the user (as defined in US16). US15 and US16 are executed sequentially. Even so, the agent should be able to see a list of all booking requests made to him (US15) without answer any booking request.

> **Question:** Our team is having trouble understanding US016's AC2. Until now, the email has been sent in the form of a text file, however, with this AC, a configuration file that allows the use of different platforms has been introduced. How should the sending of emails be carried out then?
> 
> **Answer:** The configuration file defines the email service to be used. The URI of the email service should be defined in the configuration file. The URI can be the path of a file. Please discuss this question with your ESOFT teachers.

> **Question:** Is the response saved in the system or only sent through email?
> 
> **Answer:** The system should record typed text messages. Regarding other messages, that only use information that is already registered in the system, the system should only record that the message was sent.

### 1.3. Acceptance Criteria

* **AC1:** The response is sent by email.
* **AC2:** Different email services can send the message. These services must be configured using a configuration file to enable using different platforms (e.g.:gmail, DEI's email service, etc.) 
* **AC3:** The response should include the name and phone number of the responsible Agent.
* **AC4:** The response should include the property identification and location.
* **AC5:** When an Agent responds to a booking request the list of booking requests should be updated to not show this request.


### 1.4. Found out Dependencies


* There is a dependency to "US015 Listing all Bookings" since the agents needs to have all of their requested bookings to respond to them.


### 1.5 Input and Output Data


**Input Data:**

* Selected data:
  * the number of the booking we want to answer to

* Typed data:
    * a full and detailed response


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![US016-SSD](svg/us016-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* The system will also register the response inside itself, in order to aid with "US020 Read Response"'s development.