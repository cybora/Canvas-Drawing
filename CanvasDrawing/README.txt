*Build and run instructions

1. Go to the main folder ( /CanvasDrawing )
2. Run the command " mvn clean "
2. Run the command " mvn package "
3. Go to the target folder
4. Run " java -jar CanvasDrawing-0.0.1-SNAPSHOT.jar "
5. The applications will provide the usage when started.

*Design decisions

1. Made a research on similar projects
2. Searched for a bucket fill algorithm and its implementation.

*Used libraries

jUnit 5 dependencies for benefiting the latest jUnit features.

*Errors and edge cases

In the code, I tried to be careful about the cases below ;

1. The existence of Canvas for drawing operations.
2. The validity of the coordinates of points of lines, rectangles and bucket fill in terms of the negative values and outside the canvas.

