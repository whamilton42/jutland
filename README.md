# Jutland
A common programming challenge is to write a program that can play [Battleship](https://en.wikipedia.org/wiki/Battleship_(game)) - either against a human player, or another program. **Jutland** is designed to allow Battleship programs to face off against each other - regardless of which language they are written in.

Your program can play the game with whatever strategy you choose. Jutland acts as the interface between your program and the programs of others.

## How to play Battleship
See [Wikipedia](https://en.wikipedia.org/wiki/Battleship_(game)) for rules.

A few clarifications, since there are differences in rulesets:
* 10x10 grid.
* Placing ships orthogonally adjacent is allowed.
* The opponent will annouce hit or miss, but not what ship has been hit.
* The opponent will announce which ship has been sunk.

## How to write a client
Jutland facilitates inter-program communication via **websockets**.

### Creating the game
This is a manual action within Jutland (still TBD). The game will have a UUID. e.g., `fe442555-b432-49b0-aa97-2da1044c5df5`

### Submitting your grid
The first thing your program will need to do is submit its placement of ships on the grid.

You can submit your grid by posting a message to `fe442555-b432-49b0-aa97-2da1044c5df5.create_grid`. This message should be JSON formatted and contain a `grid` key pointing to a string representing your grid.

How to format your grid string:
* 100 characters long.
* Empty squares noted with an empty character.
* Ships noted using characters below.

e.g.: (replacing `_` with a space!)

```json
{
  "program": "jellicoe"
  "grid": "_D__BBBB___D_________DC_________C___P_____C___P_____C___SS____C___S_________________________________"
}
```

#### Ships
* C (Aircraft Carrier) = 5 spaces
* B (Battleship) = 4 spaces
* S (Submarine) = 3 spaces
* D (Destroyer) = 3 spaces
* P (Patrol Boat) = 2 spaces

## TODO
- [x] better environment variable handling
- [ ] creation of game
- [ ] websocket events
- [ ] announce result of shot to program
- [ ] stop passing opts around everywhere
- [ ] think about program state
- [ ] black graphics
