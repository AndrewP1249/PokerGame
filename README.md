# Poker Game Simulation

This project implements a simulation of a popular casino game, video poker. The program provides an interactive gaming experience where players can bet tokens, draw cards, and score their hands based on traditional poker rules.

## Features
- A fully functional 52-card deck simulation.
- Randomized shuffling algorithm to ensure fair gameplay.
- Player interaction to select which cards to hold or discard.
- Dynamic scoring system based on common poker hand rankings.
- Betting mechanism allowing the player to wager between 1 and 5 tokens per game, with payouts proportional to the bet.

## Poker Hands and Payouts
The program evaluates the player's final hand and assigns a payout based on the following rankings:

1. **No Pair** - Five unrelated cards.  
   **Payout**: 0

2. **One Pair** - Two cards of the same value (e.g., two queens).  
   **Payout**: 1 × Bet

3. **Two Pairs** - Two distinct pairs (e.g., two queens and two fives).  
   **Payout**: 2 × Bet

4. **Three of a Kind** - Three cards of the same value (e.g., three queens).  
   **Payout**: 3 × Bet

5. **Straight** - Five cards in consecutive values, not necessarily of the same suit (e.g., 4, 5, 6, 7, 8). Aces can be low (preceding 2) or high (following a king).  
   **Payout**: 4 × Bet

6. **Flush** - Five cards of the same suit, not necessarily in order.  
   **Payout**: 5 × Bet

7. **Full House** - A combination of three of a kind and a pair (e.g., three queens and two fives).  
   **Payout**: 6 × Bet

8. **Four of a Kind** - Four cards of the same value (e.g., four queens).  
   **Payout**: 25 × Bet

9. **Straight Flush** - A straight where all cards are of the same suit.  
   **Payout**: 50 × Bet

10. **Royal Flush** - The best hand: a 10, jack, queen, king, and ace, all of the same suit.  
    **Payout**: 250 × Bet

## Gameplay Instructions
1. At the start of each game, the deck is shuffled and the top five cards are dealt to the player.
2. The player can bet between 1 and 5 tokens per round.
3. The player may choose to keep or discard any of the dealt cards.
4. Discarded cards are replaced with new cards from the top of the deck.
5. The program evaluates the player's final hand and determines the payout based on the bet and the hand ranking.

## Implementation Details
- The game is built using a modular object-oriented approach, with the following classes:
  - **Card**: Represents an individual playing card.
  - **Deck**: Represents a 52-card deck, with methods for shuffling and dealing.
  - **Game**: Manages the gameplay, including betting, card replacement, and scoring.
  - **Player**: Represents the player and their actions during the game.
  - **PokerTest**: A test class for validating the functionality of the program.

- The program includes two versions of the `Game` constructor:
  1. A standard constructor for regular gameplay.
  2. A constructor that allows specifying a predefined hand for testing purposes via a command-line argument.
