# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Owner, Agent

---

**Transaction Line Items**

* Property

---

**Product/Service related to a Transaction or Transaction Line Item**

* Property

---


**Transaction Records**

* System, Network Branch

---  


**Roles of People or Organizations**

* Client, System Administrator, Organization


---


**Places**

* Store, Property

---

**Noteworthy Events**

* System

---


**Physical Objects**

* Store, Property

---


**Descriptions of Things**

* System


---


**Catalogs**

* System  

---


**Containers**

* System

---


**Elements of Containers**

*  User, Network Branch, Store

---


**Organizations**

* System Administrator

---

**Other External/Collaborating Systems**

* System


---


**Records of finance, work, contracts, legal matters**

* System

---


**Financial Instruments**

* Property, System Administrator, System

---


**Documents mentioned/used to perform some work/**

* System, System Administrator
---



### **Rationale to identify associations between conceptual classes**

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_A_** is physically or logically part of **_B_**
+ **_A_** is physically or logically contained in/on **_B_**
+ **_A_** is a description for **_B_**
+ **_A_** known/logged/recorded/reported/captured in **_B_**
+ **_A_** uses or manages or owns **_B_**
+ **_A_** is related with a transaction (item) of **_B_**
+ etc.



| Concept (A) 		          |       Association   	       |    Concept (B) |
|-------------------------|:---------------------------:|---------------:|
| User  	                 |      logged in    		 	      |         System |
| Owner  	                |       chooses    		 	       |          Agent |
| Agent                   |      publishes    		 	      |       Property |
| Agent                   |  publishes sale on    		 	  |         System |
| Client  	               | schedules a visit with 		 	 |          Agent |
| System Administrator 		 |         manages   	         |         System |
| System Administrator 		 |        registers   	        | Network Branch |
| System Administrator 		 |        registers   	        |          Store |
| Organization 		         |           has   	           |      Employees |
| Organization  	         |        owns    		 	         |           Task |
| Employee  	             |       creates    		 	       |           Task |
| Platform  	             |       defines    		 	       |       Category |
| Platform                |   has registered    		 	    |   Organization |
| Task                    |    cataloged as    		 	     |       Category |



## Domain Model

[Domain Model](puml/project-domain-model.puml)



