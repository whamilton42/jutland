# Jutland
Run battleship programs against each other.

## How to play battleship
See [Wikipedia](https://en.wikipedia.org/wiki/Battleship_(game)) for rules.

A few clarifications, since there are differences in rulesets:
* 10x10 grid.
* Placing ships orthogonally adjacent is allowed.
* The opponent (via this program) will annouce HIT or MISS, but not what ship has been hit.
* The opponent (via this program) will announce which ship has been sunk.

## Instructions
Provide 3 executables in the `bin` folder of your program:
* `install`
* `grid`
* `shoot`

### Grid
This executable should print to `stdout` a string representing the grid you wish to submit.

Rules for grid string:
* 100 characters long (10x10 grid).
* Precisely one of each ship, noted using upper-case characters (see below).
* No newline character at the end.

e.g.: ` D  BBBB   D         DC         C   P     C   P     C   SS    C   S                                 `

#### Ships
* C (Aircraft Carrier) = 5 spaces
* B (Battleship) = 4 spaces
* S (Submarine) = 3 spaces
* D (Destroyer) = 3 spaces
* P (Patrol Boat) = 2 spaces


## TODO
- [x] better environment variable handling
- [ ] stop passing opts around everywhere
- [ ] think about program state
- [ ] black graphics

