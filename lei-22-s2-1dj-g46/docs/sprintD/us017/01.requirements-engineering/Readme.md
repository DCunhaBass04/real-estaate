# US 017 - Listing All Deals

## 1. Requirements Engineering


### 1.1. User Story Description


As a network manager, I want to list all deals made.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

> The manager of the network intends to analyse the performance of each of the branches and the global behaviour of the network on a daily basis.

**From the client clarifications:**

>**Question**: Can you confirm that we are analyzing the deals made in all the branches all together?

>**Answer**: Yes, we are analyzing the deals made in all the branches all together.

>**Question**: In this User Story it is requested that "All deals made" are listed. Are these deals just accepted purchase requests, or are declined purchase requests also included?

>**Answer**: A deal takes place when the proposed purchase/renting is accepted.

>**Question**: What should be the default order of the deals when displaying them to the network manager?

>**Answer**: The default is to sort deals from the most recent ones to the oldest ones.

### 1.3. Acceptance Criteria


* **AC1:** The actor should be able to sort all properties by property area (square feet) in descending/ascending order.
* **AC2:** Two sorting algorithms should be implemented (to be chosen manually by the network manager).
* **AC3:** Worst-case time complexity of each algorithm should be documented in the application user manual that must be delivered with the application (in the annexes, where algorithms should be written in pseudocode).


### 1.4. Found out Dependencies


* There is a dependency to "US011 Accept Offers" and "US012 Import a Legacy System" since those are the US's responsible for adding sold properties to the system.


### 1.5 Input and Output Data


**Input Data:**

* Selected data:
    * picking ascending or descending order of property's area
    * picking the algorithm the user wants to use


**Output Data:**

* sorted list of all deals registered in the system

### 1.6. System Sequence Diagram (SSD)

![US017-SSD](svg/us017-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* The details about each algorithm's worst-case time complexity (mentioned in **AC3**) is not registered here, only on the user manual's annexes.