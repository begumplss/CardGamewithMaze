# CardGamewithMaze
## Card Game with Maze
This repository contains a Java-based card game that features both card battle mechanics and maze navigation. The project was developed as part of a school assignment to demonstrate object-oriented programming concepts, game logic, and turn-based battle mechanics.

## Game Overview
The game revolves around navigating a maze, where players can encounter enemies. Upon encountering an enemy, the player enters a card battle. The cards have various effects such as dealing damage, healing, and granting immunity, allowing for strategic gameplay.

## Key Features:

Turn-based card battle system: Players use cards to attack, heal, or gain immunity.
Maze exploration: Navigate through a maze to find enemies and strategic points.
Randomized actions: The game's randomness comes from dice rolls that influence the efficiency of card actions.

## Gameplay

Players start with a set of cards (Heal, Damage, and Immunity).
When encountering an enemy in the maze, a duel begins where each player takes turns using their cards.
A dice roll determines the efficiency of each card's action.
The goal is to defeat all enemies while exploring the maze.

## Classes Overview

Card: A base class for all cards with properties like name and cost, and an action method to define card behavior.
DamageCard: A card that deals damage to the opponent.
HealCard: A card that heals the user.
ImmunityCard: A card that grants immunity for a few turns.
CardMaster: Represents the player or enemy with attributes like health, mana, and a hand of cards. Manages the card-playing logic.
GameParameters: Stores game constants such as health points, mana points, card limits, etc.
Main: The main game logic, including the maze navigation and card duels.

## How to Play

Navigate through the maze using W, A, S, D keys to move up, left, down, and right.
Upon encountering an enemy, engage in a card duel.
During the duel, use the displayed cards to attack or heal. Efficiency is determined by a dice roll.
The game ends when either the player or all enemies are defeated.
