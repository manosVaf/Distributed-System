# Distributed System

The idea of this project is to find the top-10 places which have the most check-ins withing a specified area in New York
based on real data from Foursquare.Project is based on the Map-Reduce model and consists of a client, which is an android
application, and a server that consists of three Mapppers and a Reducer. Each Mapper process a specified part of data and 
sends a top-10 of that data to the reducer which sorts the three top-10s into one and sends it to client. 
For server and client was used **Java 8** with emphasis on lambda expressions and **MySql** for storing data.