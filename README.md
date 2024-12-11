# NOMAD

![nomad](https://github.com/user-attachments/assets/2d3ae4ee-daa3-4299-8dce-22d699eb9e8e)

NOMAD is a sophisticated travel planner designed to simplify and enhance your travel experience. With its intuitive user interface, NOMAD helps you plan your entire journey—from creating detailed itineraries to managing your budget and tracking your carbon footprint. Whether you're a solo traveler or planning a group trip, NOMAD ensures that your adventures are well-organized, financially manageable, and environmentally conscious.

---

## Key Features

1. User Log-In System:
NOMAD includes a secure log-in system that allows users to create personalized profiles. By registering an account, users can save their travel information and access it anytime, whether on the same device or across multiple platforms. This functionality ensures that users can manage itineraries and past trips in a secure environment, protecting their data while offering seamless access.

2. Itinerary Planning:
With NOMAD’s itinerary planner, users can easily build their trips day by day. From selecting destinations and accommodations to organizing transportation and activities, the program provides a step-by-step process to help you visualize your entire trip. You can even modify and update your itinerary as you plan, making the process flexible and dynamic.

3. Budget Planning:
Budgeting is made simple with NOMAD’s integrated budget tool. Users can input estimated costs for flights, lodging, meals, and activities, and the program automatically calculates the total trip expenses. Additionally, NOMAD tracks real-time costs, allowing users to adjust their plans as needed to stay within their financial limits.

4. Carbon Footprint Tracking:
NOMAD is committed to sustainable travel. The program includes a carbon footprint tracking feature that estimates the environmental impact of your travel choices. This includes transportation methods (flights, trains, cars) and the accommodations you select. Users are provided with recommendations to reduce their carbon footprint, promoting more eco-friendly travel decisions.

5. Saving Itineraries:
NOMAD allows users to save and revisit past itineraries. This feature helps you easily reference your previous trips for future planning, offering quick access to details like destinations, costs, and activities. Users can also copy and modify old itineraries to create new ones, streamlining the trip-planning process.

---

## OOP Principles

NOMAD is designed using the four key principles of Object-Oriented Programming (OOP), ensuring that the application is efficient, scalable, and easy to maintain:

1. Encapsulation:
>NOMAD encapsulates its data within different classes, each representing a distinct part of the program. For example, classes for Users, Itineraries, Budgets, and Carbon Footprint Tracking contain private fields that are only accessible through public methods (getters and setters). This ensures that each part of the system is well-protected from unintended changes, reducing the likelihood of bugs and maintaining data integrity.

2. Inheritance:
>The program uses inheritance to create a flexible and extensible structure. For instance, different types of trips inherit common functionality from a base Trip class, while adding specific attributes or methods unique to each type. This makes it easier to add features in the future without rewriting code.

3. Polymorphism:
>NOMAD employs polymorphism to allow different classes to share the same method names but perform different actions depending on the context. For example, when calculating the carbon footprint, the method could behave differently depending on whether the user is traveling by flight, train, or car. This reduces redundancy and increases code efficiency.

4. Abstraction:
>The program abstracts complex processes behind simple interfaces. For example, when planning an itinerary, users interact with the interface, unaware of the intricate back-end processes that validate and store the data. Similarly, users can see their budget and carbon footprint information without needing to understand the underlying calculations. This improves the user experience and streamlines development.

---

# Sustainable Development Goal (SDG) Requirements

NOMAD plays an important role in advancing several key Sustainable Development Goals (SDGs), particularly those focused on responsible consumption, environmental protection, and fostering inclusive economic growth.

1. SDG 12: Responsible Consumption and Production
>NOMAD contributes to SDG 12 by helping users make more informed and sustainable travel choices. The carbon footprint tracking feature encourages travelers to consider the environmental impact of their journeys, promoting the use of eco-friendly transportation options and accommodations. Additionally, the budgeting tool helps users assess costs and identify ways to minimize wasteful spending, fostering more responsible travel planning.

2. SDG 13: Climate Action
>Through its carbon footprint tracking and sustainable travel recommendations, NOMAD directly supports SDG 13. By providing users with tools to reduce their carbon emissions, the program empowers individuals to make conscious decisions that contribute to climate action. Whether through promoting the use of public transportation or encouraging low-carbon accommodation choices, NOMAD helps users lower their environmental impact while traveling.

3. SDG 8: Decent Work and Economic Growth
>NOMAD also aligns with SDG 8 by fostering inclusive economic growth. Through its itinerary and budget planning features, users can explore affordable travel options, which in turn can support local economies and businesses. By facilitating more efficient and budget-friendly travel planning, NOMAD helps make travel more accessible to a broader range of people, including those with limited financial resources, thereby promoting decent work and inclusive economic opportunities.

4. SDG 11: Sustainable Cities and Communities
>NOMAD’s emphasis on eco-friendly travel choices ties into SDG 11, which focuses on making cities and human settlements inclusive, safe, resilient, and sustainable. By encouraging sustainable transportation methods and promoting environmentally conscious accommodations, NOMAD helps travelers contribute to the development of greener and more sustainable urban areas.


---

## Setup Instructions

To get NOMAD up and running, follow the steps below:

Initialize the Database:
First, you need to set up the program’s database. Inside the /db folder, you will find the init.sql file. This file contains the SQL schema needed to create the necessary tables for users, itineraries, budgets, and carbon footprint data.

Open init.sql and execute it on your SQL database to set up the tables.
Make sure the database is properly configured before continuing to the next step.
Configure Database Connection:
NOMAD uses the DatabaseConnection.java class to connect to the database. To configure it:

Open DatabaseConnection.java located in the /src folder.
Modify the following parameters in the class:
URL: Update the database URL to reflect the location of your database.
Username: Set the username to access the database.
Password: Provide the correct password for the database.

Example:

>String url = "jdbc:mysql://localhost:3306/nomad";  
>String username = "your-username";  
>String password = "your-password";
Make sure that your database is accessible from your development environment and the login credentials are accurate.

Run the Program:
Once you’ve completed the database setup and connection configuration, you can run the program. Launch NOMAD via your preferred IDE or from the command line to start planning your trips!

NOMAD provides a comprehensive solution for travel planning, focusing on ease of use, financial management, and sustainability. With OOP principles deeply embedded in its design, the program is built to be efficient, scalable, and easy to maintain, ensuring a seamless user experience. Follow the setup instructions to get started and explore the world with NOMAD!






