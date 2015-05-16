# My-Tickets
Web Tools and Development

mytickets.com is an online movie ticket reservation website.

The project contains mainly backend code with basic UI appearances and functionalities.
MyTickets is a maven project created using Spring MVC(Model, View and Controller) architecture pattern
with Hibernate as an ORM(Object Relational Mapping) tool. All the controllers are fully annotated and 
the dependencies are injected in runtime using DI(dependency injection) feature of the spring framwork.
The hibernate mappings are also implemented by annotationing the POJO's or Entities.

The project back end is designed based on layered architecture. The layers broadly consists of a controller
layer, service layer and DAO(Data Access Object) layer. Each layer is designed with a purpose of seperating
the code logic and hence increasing the modularity. The Controller layer is responsible for handling incoming
client requests and directing them to relevent service layer. The Service layer contains all the business 
logic, it is responsible for processing and performing task corresponding to a url request and sending the 
response back to controller. The third layer is DAO layer, it is responsible for performing database 
interactions only such as establishing a connection, managing a session, performing CRUD operations and 
receiving the results. MyTickets uses Hibernate at the DAO layer for database transactions.

MyTickets also uses a RESTful API namely Rotten Tomatoes for fetching movie informations.

For the client side programming HTML, CSS, Javascript and JQuery are used for making a clean and interactive
UI. Used Bootstrap for enchanced look and user experience.
