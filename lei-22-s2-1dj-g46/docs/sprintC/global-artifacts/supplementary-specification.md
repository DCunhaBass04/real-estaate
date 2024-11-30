# Supplementary Specification (FURPS+)

## Functionality

Real Estate USA needs an application that enables buyers who visit its stores/agencies to access the
properties available for lease or sale, as well as the company’s employees to carry out a set of
operations related to the real estate business. Among these operations are the publication of rental
and sale advertisements, the registration of a business (lease or sale) and the scheduling and
registration of visits to the property.

From time to time, property owners contact Real Estate USA with the aim of selling or renting their
properties. Owners go to one of the company's branches and meet with a real estate agent to sell or
rent one or more properties, or they can use the company's application for the same purposes. The
owner provides property characteristics and the requested price and sends the request to an agent.
Upon receiving the order, the agent sets the commission and publishes the offer in the system. The
commission can be a fixed amount or a percentage. In the case of a request for the sale of a
property, the owner must provide information on: the type of property (apartment, house or land),
the area in m2, the location, the distance from the city centre, the requested price and one or more
photographs. If the property is an apartment or a house, the owner also provides: the number of
bedrooms, the number of bathrooms, the number of parking spaces and the available equipment,
such as central heating and/or air conditioning. In case the property is a house, the existence of a
basement, an inhabitable loft, and sun exposure must be registered as well.

The real estate agent reviews advertisement requests, registers the information in the system and
publishes the offer so that it is visible to all clients who visit the agency and use the application. All
registered information, except the agency commission, can be accessed by the client who intends to
buy or rent the property; the client is, then, responsible for being able to consult the properties by
type, number of rooms, and sort by criteria such as price or the city where the property is located.

After consulting a list of properties, the client can request to schedule a visit to the real estate agent
for a specific property to verify its conditions. The agent receives the request, checks the
availability and sends the response. If the customer accepts the order, it is automatically scheduled
in the system.

After carrying out the visit, the agent records the visit and an indication of whether or not he thinks
the deal will take place.

When the client decides to buy/rent the property, he sends a request for the purchase/lease of the
property to the agent. After being appreciated by the agent, he accepts or rejects the order. If the
request is accepted, the offer will not be shown again to clients using the application.

Each store in the network has a store manager and the set of stores is managed by a store network
manager. The main functions of a store manager are to monitor and streamline the branch with the
aim of getting to know better the business carried out and to analyse and evaluate the performance
of employees.

The manager of the network intends to analyse the performance of each of the branches and the
global behaviour of the network on a daily basis.

The company's systems administrator will be responsible for registering all employees (specifying
the name, the citizen's card number, the tax number, the address, the email address, the contact
telephone number and the agency to which it is assigned) and branches of the network (specifying
the designation, the location, the email address and the contact telephone number) as well as
preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the
application. The application to be developed in this project will replace an application that was
already in operation in some agencies and will be replaced in July 2023.

## Usability 


The system must now allow the user to leave any necessary information (first and last name, email and password) blank. Furthermore, it must not allow the user to crash it by typing things in the wrong format. Therefore, everytime there is user interaction, the program will validate their input and warn them about their mistakes, before telling them to try again.


## Reliability
Fault tolerance: The system can be run after an error but does not have any built-in fault tolerance mechanisms to minimize the impact of errors.

Recoverability: If the program got any fatal error it cannot recover from it on its own and must be rerun to be functional again.

Availability: The software can be run anytime by the user with no effect on the performance .

Accuracy: The application can provide exact outputs over and over without any aftermath.

Maintainability: The software can be updated and reused because of its build up strategy following the software engineering principles.


## Performance

The software bootstrap time is between 542 and 658 ms.

To print properties it takes 17/18 ms.

Register an employee after adding the respective data takes 5 ms.




## Supportability 
Testability: The application is easy to test thanks to our IDE which has a debug feature.
 
Adaptability:The application can only be used in PC or laptop.

Maintainability: The program can always be updated and we can re-use the code as we want.

Compatibility: The application is not compatible with other applications.

Configurability: The software is easy to configure the user just needs to register and its ready to use software.

Instability: The software just have to be installed by the client on his PC or laptop

Scalability: The program has an interface that will help the user  maneuver and keep his things organized.




## +

### Design Constraints
There weren't any Design Constraints.

All the images/figures produced during the software development process should be recorded in
SVG format.

### Implementation Constraints
The application must be developed in Java language using the IntelliJ IDE or NetBeans.

All those who wish to use the
application must be authenticated with a password of seven alphanumeric characters, including
three capital letters and two digits.

During the system development, the team must: (i) adopt best practices for identifying
requirements, and for OO software analysis and design; (ii) adopt recognized coding conventions
and standards (e.g., CamelCase); (iii) use Javadoc to generate useful documentation for Java code.

The development team must implement unit tests for all methods, except for methods that
implement Input/Output operations.

The unit tests should be implemented using the JUnit 5
framework.

The JaCoCo plugin should be used to generate the coverage report.

The application should use object serialization to ensure data persistence between two runs of the
application.

### Interface Constraints
The application graphical interface is to be developed in JavaFX 11.

The application must support the English language.

### Physical Constraints
The client can´t request an agent through a phone call.

The application must run on PC or laptop.

A password cannot be sent to an employee's email, and is instead sent to a file created for that purpose.

