# Lab: Logout

## Preamble

In the current homework, you added code to the login use case to save the currently-logged-in
in user persistent by saving it in the Data Access Layer. You also added a unit test for this.

In this lab, you will complete a logout use case as a team. You will also discuss your project
and the use cases that need to be implemented. 

We have created all the Clean Architecture classes necessary for the logout use case.

By Friday, your team will submit a draft of your project proposal.

# Phase 1 [for credit]

## Task 0: Fork this repo on GitHub
**To get started, one of you should fork this repo on GitHub and share it with the team. 
All of you should clone it.**

Open the project in IntelliJ and make sure you can successfully run `app/Main.java`.
Note: you may need to set the Project SDK in the `Project Structure...` menu, and possibly
also manually link the Maven project.

## Task 1: Understanding the Program

Open up `app.Main` and read it as a team. What are the Views and what are the current Use Cases?

Try the signup and login use cases to make sure you can all run the program.

It's time to fix the "Log Out" button, which is part of the `LoggedInView`.
We have created all the classes for you, but some of the code is missing.

The "Log Out" button is an instance variable in class `LoggedInVew`. Go find it.
Also look at the `interface_adapter.change_password.LoggedInViewModel`, which contains any
data showing on the `LoggedInVew`.



1. ake a branch named the first part of your UofT email address, everything before the `@`.
For example, if your email address is `paul.gries@mail.utoronto.ca`, then the branch name would
be `paul.gries`.

Make sure you switch to the new branch.

In the terminal, this would look like below, but replaced with your own information:
```
git branch paul.gries
git switch paul.gries
```

