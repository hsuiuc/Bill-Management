# Bill-Management

## Application Description

This is a bill management application. You can view your expense situation
for this month. How much you have spent. How much you still have. If you are
 already over-balanced, the main panel will become red to remind you. Add expenses in different categories (tranportation, living...).
List expenses in each category. Generate a statistic expense chart for the month.
Save expenses to a local file as a backup and use that file to recover.

## Tech Stack
Use Java Swing to generate GUI. Use Mysql as database. Use JDBC to retrieve
data from database.

## Code Structure
Use Swing as GUI to display and get data from user. Add event listeners to
buttons. The event listener will call service controller to deal with 
service logic. Service controller will call DAO to retrieve data from database.
DAO will pass data to Swing components to present to users.

## Run Application
run Bill-Management/src/startup/Bootstrap

## Application Images