# java-learning-journey
This is my journey on learning java from scratch

Background:
I have about 3 years of c# expiriences, with a lot of LLM help along the way. I want to learn from the ground up to get the true expirience of being a developer. I’m intentionally starting with core OOP concepts and progressing step by step toward more advanced topics

Progress so far :
1. OOP basics:
   - Classes
   - Inheritance
   - Interfaces
   - Collections
   - Seperation of resposibilities
2. Basic data manipulation (CSV, lambda)
   - Writing and reading CSV
   - use of lambda
   - use of exceptions
   - events 

Structure of program
The program simulates a event-managing platform, With 2 types of events:
   - Conferences
   - Concerts

the code contains the following classes. i will start with the most simple and work my way up
1. Seat
   - int id
   - int row
   - int column
2. Location
   - int id
   - String city
   - String street
   - int number
   - List <Seat> seats
3. User (unused)
   - int Id
   - String FirstName
   - String LastnName
   - int Age
   - enum Category (bronze, silver, gold)
4. Event (Abstract)
   - int id
   - String description
   - LocalTime time
   - Location location
   - double price
5. Organization
   - Map<int, Event> events

