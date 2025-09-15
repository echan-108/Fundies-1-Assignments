# SUMMARY

Answer all the questions. Please put your answers _after_ the
italicized instructions, as shown in the
[video](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=d327c168-e0e8-4f70-9f3f-b12f0048baac).

## Logistics

### Did you successfully implement everything that was requested, to the best of your knowledge?
_Yes_ [1 point]

### How long did the assignment take?

_This Assgnment took about three hours to complete._
[1 point]

### Who did you work with and how?

_I worked on my own for this assignment._ [1 points]

### What resources did you use?

_I used the class resources for this assignment exclusivly._ [2 points]

## Testing and debugging

_I thought that writing tests was kind of tedius, you have to write so many to cover your bases, I understand that they are necessary, but man, I am not a fan. I thought of a a few edge cases while I was implementing the methods, and implemented them in my code before I ran any tests. As a result, my tests didn't really catch any bugs, or cause me to to change my implementations. I am fairly confident in the correctness of my code._

* What did you think of writing tests?
* Did you think of edge cases when writing tests that caused you to go back
  and change your implementation?
* Did your tests uncover bugs?
* What was your debugging process like?
* How confident are you in the correctness of your code?

_Your answer should be at least one full paragraph._ [5 points]

## Reflections

_Give **one or more paragraphs** reflecting on your experience with the
assignment, including answers to all of these questions:_

* What was the most difficult part of the assignment?
* What was the most rewarding part of the assignment?
* What did you learn doing the assignment?

_The most difficult part of this assignment was getting the winner of the battle function. I'm a little foggy when it comes to while loop semantics. The most rewarding part of this assignment was completing it. During this assignment, I brushed up on the semantics of a while loops, and learned how to create enums within a class and access those enums in kotlin!_
[5 points]

## Transcript 
```
BATTLE BETWEEN TWO MOBS: 
The Creeper took 4 hearts of damage.
It is now Injured.
The Skeleton took 11 hearts of damage.
It is now Injured.
The Creeper took 5 hearts of damage.
It is now Injured.
The Skeleton took 9 hearts of damage.
It is now Dead.
Creeper has won!

MOB MADNESS: 
The Ender Dragon took 2 hearts of damage.
It is now Injured.
The Zombie took 19 hearts of damage.
It is now Injured.
The Ender Dragon took 3 hearts of damage.
It is now Injured.
The Zombie took 1 heart of damage.
It is now Dead.

The Wither took 6 hearts of damage.
It is now Injured.
The Skeleton took 11 hearts of damage.
It is now Injured.
The Wither took 5 hearts of damage.
It is now Injured.
The Skeleton took 9 hearts of damage.
It is now Dead.

The Wither took 12 hearts of damage.
It is now Injured.
The Ender Dragon took 15 hearts of damage.
It is now Injured.
The Wither took 17 hearts of damage.
It is now Injured.
The Ender Dragon took 19 hearts of damage.
It is now Injured.
The Wither took 16 hearts of damage.
It is now Injured.
The Ender Dragon took 17 hearts of damage.
It is now Injured.
The Wither took 14 hearts of damage.
It is now Injured.
The Ender Dragon took 11 hearts of damage.
It is now Injured.
The Wither took 14 hearts of damage.
It is now Injured.
The Ender Dragon took 20 hearts of damage.
It is now Injured.
The Wither took 17 hearts of damage.
It is now Injured.
The Ender Dragon took 14 hearts of damage.
It is now Injured.
The Wither took 14 hearts of damage.
It is now Injured.
The Ender Dragon took 16 hearts of damage.
It is now Injured.
The Wither took 14 hearts of damage.
It is now Injured.
The Ender Dragon took 10 hearts of damage.
It is now Injured.
The Wither took 19 hearts of damage.
It is now Injured.
The Ender Dragon took 13 hearts of damage.
It is now Injured.
The Wither took 11 hearts of damage.
It is now Injured.
The Ender Dragon took 13 hearts of damage.
It is now Injured.
The Wither took 17 hearts of damage.
It is now Injured.
The Ender Dragon took 19 hearts of damage.
It is now Injured.
The Wither took 18 hearts of damage.
It is now Injured.
The Ender Dragon took 12 hearts of damage.
It is now Injured.
The Wither took 18 hearts of damage.
It is now Injured.
The Ender Dragon took 16 hearts of damage.
It is now Dead.

The Creeper took 6 hearts of damage.
It is now Injured.
The Warden took 10 hearts of damage.
It is now Injured.
The Creeper took 10 hearts of damage.
It is now Injured.
The Warden took 10 hearts of damage.
It is now Injured.
The Creeper took 4 hearts of damage.
It is now Dead.

The Spider took 5 hearts of damage.
It is now Injured.
The Elder Guardian took 2 hearts of damage.
It is now Injured.
The Spider took 6 hearts of damage.
It is now Injured.
The Elder Guardian took 2 hearts of damage.
It is now Injured.
The Spider took 5 hearts of damage.
It is now Dead.

The Elder Guardian took 9 hearts of damage.
It is now Injured.
The Warden took 5 hearts of damage.
It is now Injured.
The Elder Guardian took 7 hearts of damage.
It is now Injured.
The Warden took 7 hearts of damage.
It is now Injured.
The Elder Guardian took 7 hearts of damage.
It is now Injured.
The Warden took 6 hearts of damage.
It is now Injured.
The Elder Guardian took 6 hearts of damage.
It is now Injured.
The Warden took 8 hearts of damage.
It is now Injured.
The Elder Guardian took 7 hearts of damage.
It is now Injured.
The Warden took 7 hearts of damage.
It is now Injured.
The Elder Guardian took 10 hearts of damage.
It is now Injured.
The Warden took 4 hearts of damage.
It is now Injured.
The Elder Guardian took 7 hearts of damage.
It is now Injured.
The Warden took 6 hearts of damage.
It is now Injured.
The Elder Guardian took 6 hearts of damage.
It is now Injured.
The Warden took 4 hearts of damage.
It is now Injured.
The Elder Guardian took 6 hearts of damage.
It is now Injured.
The Warden took 6 hearts of damage.
It is now Injured.
The Elder Guardian took 8 hearts of damage.
It is now Injured.
The Warden took 7 hearts of damage.
It is now Injured.
The Elder Guardian took 3 hearts of damage.
It is now Dead.

The Warden took 11 hearts of damage.
It is now Injured.
The Wither took 9 hearts of damage.
It is now Injured.
The Warden took 16 hearts of damage.
It is now Injured.
The Wither took 10 hearts of damage.
It is now Injured.
The Warden took 12 hearts of damage.
It is now Injured.
The Wither took 10 hearts of damage.
It is now Injured.
The Warden took 18 hearts of damage.
It is now Injured.
The Wither took 9 hearts of damage.
It is now Injured.
The Warden took 14 hearts of damage.
It is now Injured.
The Wither took 7 hearts of damage.
It is now Injured.
The Warden took 13 hearts of damage.
It is now Injured.
The Wither took 10 hearts of damage.
It is now Injured.
The Warden took 10 hearts of damage.
It is now Injured.
The Wither took 6 hearts of damage.
It is now Injured.
The Warden took 12 hearts of damage.
It is now Injured.
The Wither took 9 hearts of damage.
It is now Injured.
The Warden took 13 hearts of damage.
It is now Injured.
The Wither took 7 hearts of damage.
It is now Injured.
The Warden took 14 hearts of damage.
It is now Injured.
The Wither took 10 hearts of damage.
It is now Injured.
The Warden took 12 hearts of damage.
It is now Injured.
The Wither took 1 heart of damage.
It is now Dead.

The winner of Mob Madness is: Warden

Process finished with exit code 0
```

