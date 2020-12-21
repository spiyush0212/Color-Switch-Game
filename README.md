# Color-Switch-Game
## Details

Developers: Piyush Sharma and Ritvik Budhiraja

Re-created the classic Color Switch game 

Using Java SDK 15.0.1 and JavaFX 15.0.1 along with Scene Builder

Submitted as a final project for college course at IIIT-Delhi  

Course : CSE201: Advanced Programming 

A working demo of the game can be viewed at TBA

## Features

TBA

## Implementations

The game is developed using Java 15.0.1 and JavaFX 15.0.1 along with Scene Builder

The main menu handles operations to play game, load previous games, see stats page and quit the game

The gameplay area renders all the obstacles, color switchers, and balls.

It has 4 unique gemoterical obstacles that repeat infinitely

The game becomes harder as the player gains more points

The player begins the game with a score of 0 and with each collected star, the score increments by 1

The player loses the game if they hit any obstacle or fail to keep the ball afloat.

Pause menu can save the current state of the game, as well as return to main menu or restart game.

Load game menu can load any previous state of the game

Stats page displays total stars collected, the highest score, and other appropriate details.

Quit page lets the player quit the application or return back to main menu.


## Design

• Facade: Main game menu screen serves as a front-facing interface

• Template: All the obstacles are derived from the Item abstract class 

• Singleton: We have just 1 unique instance of the ball and player throughout the game

• Iterator & Composite: Accessing the individual obstacles and rendering them indefinitely 


## Screenshots

TBA

## How to play

TBA

## License

TBA
