# Physics Pong

This is a pong game that I have added advanced features to.
What are the advanced features? I don't know.

![screenshot](https://user-images.githubusercontent.com/34696211/175762948-50a48e1f-0cb6-4c0f-8f53-091afd9e1dbf.png)

But ~~maybe not~~ expect physics, explosions. *(Feature may not be implemented due to lack of demand)*

### How to run

Pre-built jar file is available in releases. 

1. Main class is `lordxerus.pong2022.GameApp`. I personally compile it to jar.
2. Also make sure to extract all the JAR dependencies in the lib folder into the jar file. I have this set up as artifacts in IntelliJ Idea, and they should be contained in the XML files IJ Idea generated.
    - The only thing you don't want to extract blindly is `core.jar` or `core3.jar` in `processing`.
    - If you're compiling with Java 10 or older, use `core3.jar` for Processing 3 core.
    - Otherwise use `core.jar`, which is the Processing 4 core.
    - **Do not extract both core jars into the final jar. I believe they have conflicting names and do not know what would happen if you do**.
3. \[Optional\] Create a folder called `data` in the same directory as the JAR file of this project. Place inside of `data` a file called `wall.mp3`. This file will be played when the ball bounces against the wall. **Try to keep the audio short as the entire sound is kept in memory.**
4. Run the JAR File


### How to play

As of v0.2.0 a lot of features are not done yet. They probably won't ever get done unless there is a demand.

Try to keep the ball in screen. If you really can't use `space` to reset.

##### Left Paddle control
`W` to go Up
`S` to go Down
`A` to remove dampening (try out in game! paddle may clip through wall! `space` to reset!)

##### Right Paddle control
`up arrow` to go Up
`down arrow` to go Down
`right arrow` to remove dampening

##### General Controls
`space` to reset paddle position, ball position, and ball velocity.

### Finished Features

Walls. Paddles. Ball. Physics?

### Unfinished Features

I need a fricking particle system. I also have no idea to implement it. Help.

Score Keeping.

Scene Switching. Except my code is messy and I have no idea how scenes are supposed to work. Help.

## Technical Information

Work not in progress. Message [Underscore#5169](discord.com/users/307615687483064320) on Discord.

