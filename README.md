# com.kata.mower.application.MowerApp
Auto-mower Application for square surfaces.

## Description

### Goal

Build a program that implement the following mower’s specification.

### The tasks
The company X wants to develop an auto-mower for square surfaces.

The mower can be programmed to go throughout the whole surface. com.kata.mower.domain.Mower's position is
represented by coordinates (X,Y) and a characters indicate the orientation according to cardinal notations
(N,E,W,S).

The lawn is divided in grid to simplify navigation.
For example, the position can be 0,0,N, meaning the mower is in the lower left of the lawn, and oriented to
the north.

To control the mower, we send a simple sequence of characters. Possible characters are
L,R,F. L and R turn the mower at 90° on the left or right without moving the mower. F means the mower
move forward from one space in the direction in which it faces and without changing the orientation.

If the position after moving is outside the lawn, mower keep it's position. Retains its orientation and go to
the next command.

We assume the position directly to the north of (X,Y) is (X,Y+1).

To program the mower, we can provide an input file constructed as follows:
The first line corresponds to the coordinate of the upper right corner of the lawn. the bottom left corner is
assumed as (0,0).

The rest of the file can control multiple mowers deployed on the lawn. Each mower has 2 next lines:

The first line gives mower's starting position and orientation as "X Y O". X and Y being the
position and O the orientation.

The second line gives instructions to the mower to go throughout the lawn. Instructions are
characters without spaces.

Each mower moves sequentially, meaning that the second mower moves only when the first has fully
performed its series of instructions.

When a mower has finished, it gives the final position and orientation.

# Run application with Maven
 `mvn exec:java -Dexec.mainClass="com.kata.mower.application.MowerApp"   -Dexec.args="<mySampleFile>"`  

Examples

 - simple sample file
 
 `mvn exec:java -Dexec.mainClass="com.kata.mower.application.MowerApp" -Dexec.args="src/test/resources/sample.txt"`  
 
- sample file with collisions between mowers

 `mvn exec:java -Dexec.mainClass="com.kata.mower.application.MowerApp" -Dexec.args="src/test/resources/sampleWithCollisions.txt"`  

 
## Execute tests
`mvn clean test`


## Notes

 - We assume two mowers (or more) cannot be one the same grown position at the same time
 - One mower must start inside the grown
 
