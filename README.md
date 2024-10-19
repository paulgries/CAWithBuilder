# Lab: Logout

## Preamble

In the current homework, you added code to the login use case to save the currently-logged-in
user by saving the user in the Data Access Layer. You also added a unit test for this.

In this lab, you will complete a logout use case as a team. You will also begin to discuss your project
and the use cases that need to be implemented. 

We have created all the Clean Architecture classes necessary for the logout use case.

By Friday, your team will submit:
- your completed lab code [for credit]
- a draft of your project blueprint proposal. [not for credit]

# Phase 2 [for credit]
_(recall, Phase 1 was your solo task of adding the storage of the currently-logged-in user)

## Task 0: Fork this repo on GitHub
**To get started, one of you should fork this repo on GitHub and share it with the team. 
All of you should then clone it.**

TODO add note as we have in the past about preventing pushes to main + remind them to only
     commit and push the .java files they are working on; nothing in `.idea` should typically be pushed.

Open the project in IntelliJ and make sure you can successfully run `app/Main.java`.
Note: you may need to set the Project SDK in the `Project Structure...` menu, and possibly
also manually link the Maven project, as you did in Phase 1.

## Task 1: Understanding the Program

You may notice that we have refactored the code _slightly_ since Phase 1.

Open up `app.Main` and read it as a team.
- What are the Views and what are the current Use Cases?
- Which version of the DAO is `app.Main` using?

The major change since Phase 1 is that we have added the `app.AppBuilder` class which makes
it easier to understand how our CA engine is being constructed — it also makes `app.Main` nice and concise!

Try the signup and login use cases to make sure you can all run the program.

Currently, you'll notice that the "Log Out" button still doesn't work if you click it to log out after
you have logged into the application.

It's time to fix the "Log Out" button, which is part of the `LoggedInView`.
We have created all the classes for you, but some of the code is missing.
As a team, your task is to fill in the missing code so that the Log Out Use Case is implemented.

Your team will know when you are done when:

- Clicking the "Log Out" button takes the user back to the Login View when you use the program.
- The provided `LogoutInteractorTest` test passes.

The "Log Out" button is an instance variable in class `LoggedInVew`. Go find it.
Also look at the `interface_adapter.change_password.LoggedInViewModel`, which contains any
data showing on the `LoggedInVew`.

## Task 2: Dividing up the work

There are `TODO` comments left in the files
(recall that you can use the TODO tool window to conveniently pull up a complete list).

The TODOs are summarized below to help your team decide how to split them up.

TODO add the TODO notes fully here!

Once the TODOs are complete, the "Log Out" button should work!

As a team, split up the TODOs between the members of your team.

Make sure each member has at least one TODO which they will be responsible for completing.

1. Make a branch named the first part of your UofT email address, everything before the `@`.
For example, if your email address is `paul.gries@mail.utoronto.ca`, then the branch name would
be `paul.gries`.

Make sure you switch to the new branch.

In the terminal, this would look like below, but replaced with your own information:
```
git branch paul.gries
git switch paul.gries
```

2. Complete your assigned TODO and make a pull request on GitHub. In your pull request,
   briefly describe what your TODO was and how you implemented it. If you aren't sure
   about part of it, include this in your pull request so everyone knows what to look
   for when reviewing.

3. Review each other's pull requests to ensure each TODO is correctly implemented.

4. Once all TODOs are completed, your team should debug as needed to ensure the
   correctness of the code. Setting a breakpoint where the log-out use case
   interactor starts its work will likely be a great place to start when debugging.

# Project Blueprint

See Quercus for details about the project blueprint! By the end of the week,
the goal is for your team to have a fully drafted blueprint so that your team
will be ready to get started on your project after Reading Week.

TODO some basic stuff getting their repo set up for the project... now or later?
