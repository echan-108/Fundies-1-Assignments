# Homework 11: Reddit Client

## Overview

In this project, you will add functionality and error handling to a
text-based Reddit client. You will get practice with:

- Scope functions
- Quality assurance
- Data-driven programming
- Interactive text user interfaces
- Modifying an existing code base
- Working with singletons and companion objects
- Using an external API

The project relies on [Reddit4J](https://github.com/masecla22/Reddit4J), a handwritten wrapper for the Reddit API.

Watch [Homework 11 introduction](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=ef8451a4-d390-49ee-8c57-af54004e35f6)
for a video overview.

## Getting started

### Create a Reddit account

I recommend using a fresh Reddit account unassociated with any others
that you have. If you are not familiar with Reddit, you may want to
ask a classmate to show you around or read [What is Reddit?
](https://www.digitaltrends.com/computing/what-is-reddit/) The only
Reddit terminology you need to know about are subreddits, posts, and comments.

Some content on Reddit is offensive. Some relatively safe subreddits are:

- [r/dadjokes](https://www.reddit.com/r/dadjokes/)
- [r/ProgrammerDadJokes](https://www.reddit.com/r/ProgrammerDadJokes/)
- [r/NEU](https://www.reddit.com/r/NEU/)
- [r/Oakland](https://www.reddit.com/r/oakland/)
- [r/UpliftingNews](https://www.reddit.com/r/UpliftingNews/)
- [r/goodnews](https://www.reddit.com/r/goodnews/)

You may find it useful at this stage or later ones to visit
[r/FundiesOakland](https://www.reddit.com/r/FundiesOakland/),
which I created for testing purposes.

### Create and add credentials

Follow the [text](https://github.com/masecla22/Reddit4J/wiki/Getting-Started)
or [video](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=005dc294-02f2-4a0e-85a0-af4e011b50de)
instructions to create developer credentials.

You will need to provide values for the constants `USERNAME`, `PASSWORD`,
`CLIENT_ID`, `CLIENT_SECRET` in [SecretKeys.kt](secretKeys.kt), and for
`AUTHOR` near the top of [Client.kt](Client.kt).
**Tip**: Make sure your password doesn't contain the characters `$`, `"` or `\`,
which have special meanings in Kotlin strings.

### Try out the program

Once you add your credentials, you should be able to run the program by
clicking on the green triangle to the left of `main()`.

Play around with the program. For ideas of what to do, see the video or my
[initial transcript](../../../instructionFiles/sample-transcript.md).

### Read the code

Try to understand the program. You may wish to review the code walkthrough in
the [introductory video](https://northeastern.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=ef8451a4-d390-49ee-8c57-af54004e35f6).
Recall that Panopto videos have chapters, enabling you to jump to relevant
portions.

## Changes

### Problem 1: Scope functions

The `init` block in `Connection` has unnecessarily repetitive code. Rewrite it
to use a scope function (either `apply` or `let`) so the variable name
`redditClient` appears only once. Test the program after making the changes.

This is the only change required to the `Connection` class.

### Problem 2: Posts

Currently, the user is given the options `Show first post` even if there are no
posts and `Show next post` even if there are no more posts. Modify the code so
those options are offered only when they can be satisfied.

At the same time, change the output so, instead of always having `There are
${posts.size} posts.`, pluralization is correctly handled. For an example of how
to do this, see `Option.checkForComments()`.

To test your code, you can do any of the following:

- visit a subreddit that has only images, such as
  [r/wholesomememes](https://www.reddit.com/r/wholesomememes/).
- visit a subreddit with few posts, such as [r/FundiesOakland](https://www.reddit.com/r/FundiesOakland/).
- temporarily put `.take(0)` or `.take(1)` at the end of `Connection.getPosts()`,
  so it returns only 0 or (at most) 1 posts.

Provide a transcript demonstrating:

1. `Show first post` is shown when there is a first post.
2. `Show first post` is not shown when there are no posts.
3. `Show next post` is shown when there is a next post.
4. `Show next post` is not shown when there is not a next post.
5. Pluralization is handled properly when there are no posts.
6. Pluralization is handled properly when there is a single post.
7. Pluralization is handled properly when there are multiple posts.

Before each transcript, indicate (after `//`) any temporary changes you made to
the code and which of the 7 above cases are being tested.

Don't forget to undo temporary changes.

### Problem 3: Error handling

Modify the code in `Option` so the program gracefully handles any error that
arises due to:

- bad user input (empty, non-number, number out of range, nonexistent subreddit)
- connectivity problems (if you temporarily turn off your Internet connection
  before retrieving a subreddit or comments)

You will typically detect these problems by catching exceptions thrown by Kotlin
library functions or the Reddit4J library. You can find out which exceptions are
thrown by viewing documentation or by experimenting (i.e., disconnecting
your computer from the Internet and seeing what exception is thrown). You should
never catch `Exception`, `Throwable`, or `Error`; you should always catch more
specific subtypes.

#### Unrecoverable errors

If an error cannot be recovered from (such as invalid credentials), print a
user-friendly message (no stack traces) and terminate the program by calling
[`exitProcess()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.system/exit-process.html)
with a non-zero argument.

#### User input errors

If the error is due to bad user input:

1. Print a message letting the user know what was wrong with the input and what
   they need to do differently. For example, if they entered a number that was
   out of range, tell them the correct range. If they entered non-numeric text
   when a number was expected, give them a different message.
2. Re-prompt them.
3. Read in and process new input.

#### Transient errors

If the error is possibly transient (temporary), such as a network failure, print
a message letting the user know the cause of the error, and give them the choice
of quitting, retrying, or selecting a subreddit. (The first and last options are
automatically added by `Option.offerOptions()`.)

You are not expected to recover from an `ExceptionInInitializerError`, although
you are expected to print a message explaining what went wrong (such as a
network problem or invalid credentials). **Tip**: Access the exception's `cause`
property to find the cause of the error.

If a connection problem happens when fetching a subreddit (because you've
temporarily disconnected the network), it's okay to reprompt the user for
the subreddit name if they choose to retry.

#### Transcript

Produce a transcript showing how your program handles every type of error:

1. invalid credentials
2. bad user input
   - a: empty
   - b: non-number
   - c: number out of range
   - d: nonexistent subreddit
3. connectivity problems
   - a: at start
   - b: when retrieving a subreddit

As before, you should indicate what you are testing and what temporary
modifications you make before each test.

## Problem 4: Comments

Add functionality so users can view comments. Specifically, modify
`showComment()` to:

1. Display the specified comment.
2. Give the user the options:
   - Show comment author
   - Show next comment [but only if there is a next comment]
   - Show post again
   - Show next post [but only if there is a next post]

Produce transcripts showing that each of the options works and that the `show
next` options are shown only when appropriate. Of course, you should also handle
errors and demonstrate having done so in your transcript.
