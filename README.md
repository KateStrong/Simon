# Final Project - Simon

### Course Info

Class: CSCI 205  
Instructor: Brian King  
Semester: Fall 2022

## Project Information
This project is a GUI version of the game Simon. Simon is a short term
memory skill game in which players are given a sequence of 4 colored flashing lights
and must then repeat said sequence correctly in order to advance. If the sequence is entered 
correctly then it will play again with an additional light added to the end of the sequence.  

For this rendition of the game, when the application is run, the user will be brought to a main 
menu screen where they have the option to either enter the game or read the instructions. 
Upon entering the game, users will see the game which contains four colored buttons surrounding 
a grey Start button. Once the start button is pressed, a random colored button will light up. 
The user will then have to click the same button. Upon being correctly clicked, the button will 
light up again, the score will increase by 1, and a new color will be added to the sequence.
If the wrong sequence is clicked the game will stop and the user will be greeted with a popup 
informing them they lost and giving them their score. The system is currently set to last 10 rounds.

## Project Structure
This Project is organized using the Model-View-Controller (MVC) design pattern.

## 3rd Party Libraries Used

junit-jupiter: 5.8.2 - https://junit.org/junit5/  
javafx: 19 - https://openjfx.io/javadoc/19/