# Developer Guide

## Acknowledgements

- Team member Jun Lim's individual project codebase - [Github](#https://github.com/jltha/ip)

##Architecture

![image](https://user-images.githubusercontent.com/69501969/160375887-d6da7278-5259-4458-83c7-f53d89fef640.png)

The above diagram provides a general overview of Sherpass and its major components. The four key areas are:

- User Interface (UI)
- Storage
- Timetable
- Study session

### UI

UI component consists the `Ui` class which manages interaction (receiving inputs and showing outputs) between the user 
and the application.

### Storage

Storage component consists `Storage` and `StorageParser` classes.
`Storage` class handles loading, writing and saving
data to and from a JSON file, such that users' data will be saved automatically.
`StorageParser` class handles the
parsing of JSON from the saved data file.

### Timetable

For components with more complicated use-cases (`Task` and `Timer`), we separate an extra Logic class to achieve better
modularity, such that each class addresses a separate concern.

Timetable component consists of `Timetable`, `Task`, `TaskList`, `TaskParser`, `TaskLogic` and various commands.

### Study session

Timetable component consists of `Timer`, `Stopwatch`, `Countdown`, `TimerParser`, `TimerLogic` and various commands.

## Design & implementation

### Study Session Implementation

The study session consists of 4 main components:

- TimerParser class
- StudyCommand class
- TimerLogic class
- Abstract Timer class, Countdown class and Stopwatch class 

The `TimerParser` component
- Parses user inputs in the main session and the study session
- Ensures the input adheres to the allowed command inputs, 
i.e. program will ask the user to key in a new input if it does not recognise the inputs given.

The `StudyCommand` component
- Accepts user inputs in the study session
- Facilitates interaction between Parser and TimerLogic

The `TimerLogic` component
- Manages the timer component when study session is launched
- Handles the logic for the timer (e.g. keeps track of state of Timer component)
- Calls made to Timer component methods are made through this method

The `Timer` component

![image](https://user-images.githubusercontent.com/69501969/160758578-39b9b3f3-d1ca-429d-8319-9f28e9c199c3.png)

- Consists of abstract `Timer` class, `Countdown` class and `Stopwatch` class as depicted in the class diagram above
- `Timer` inherits from Java's `Thread` class
- `Countdown` and `Stopwatch` inherit from `Timer`
- `Countdown` and `Stopwatch` keep track of time remaining and elapsed respectively
- `Countdown` and `Stopwatch` can be paused, resumed and stopped
- `Countdown` and `Stopwatch` prints to standard output the time remaining and elapsed respectively at regular intervals

#### Timer implementation

Sherpass’ implementation of the timer function in the study session is mainly through `Timer`, `Countdown` and 
`Stopwatch` class. Depending on the type of timer selected by the user, either `Countdown` or `Stopwatch` will be
instantiated. 

When a countdown timer is started by the user, the `Countdown` class starts a thread which keeps track of time through
a method called update(), where the thread sleeps for 1 second, then updates the time left, until the time left in the 
timer reaches zero, which then interrupts the thread. Starting the timer as a thread allows us to accept user commands 
like pause and stop for the timer through `StudyCommand` and `TimerLogic`, while the thread executes in the background 
and prints the time remaining at regular intervals. A similar process is followed in `Stopwatch`, except that the class
keeps track of time elapsed rather than time remaining.

#### Study session usage scenario

Given below is an example usage scenario when the user enters the study session, starts a countdown timer, then stops 
the timer.

Step 1. The user executes the `study` command and enters the study session through the main `Parser` component, which 
executes the `StudyCommand`. `StudyCommand` then initialises an instance of `TimerLogic`, which handles the execution
and logic of user commands during the study session, while the `StudyCommand` accepts the user’s input when the 
user is in the study session. `TimerParser` parses user inputs (commands) related to `Timer`.

Sequence diagram for `Timer` when user starts and stops a timer:

![TimerClassSD](https://user-images.githubusercontent.com/69501969/160768104-fa7e06e3-1be8-4387-b75d-ae4e79bca5b7.png)

The diagram above depicts the process when user calls start and stop (in step 2 and 3 below). All the methods
called by Timer are in parallel with other commands, since `Timer` is in a separate thread. For simplicity’s
sake, parallel frames for the remainder of methods called by `Timer` are omitted.

Step 2. The user executes `start 1` command to start a 30 minute timer. The input goes through `StudyCommand`, 
where the `TimerParser` is called to parse the command. After parsing, `TimerParser` calls the method corresponding to 
the user’s command (`start`) in `TimerLogic`. `TimerLogic` then handles the logic and initialises an instance of
`Countdown` (spawn a thread). `Countdown` then automatically updates itself while waiting for the user to issue 
commands.

Step 3. The user executes the `stop` command to stop the timer. The same process is followed by using `TimerParser` to
parse the command in the study mode, which calls on the respective `callStopTimer` method in `TimerLogic`. Within the
`callStopTimer` method is a call to a method in `Countdown` to stop the timer. Control goes back to the user for 
further commands.

#### Design considerations for Timer class
- Current implementation: Create `Timer` from scratch, using the sleep function of threads to keep 
track of time
  - Pros: Same overhead of needing to track the time left of the timer
  - Pros: No need to follow Java’s `Timer` class syntax, which can be confusing at times
  - Pros: Implementation is simple and straight-forward
  - Cons: Have to manage how we interrupt the thread after stopping the timer
- Alternative: Using Java's `Timer` class
  - Pros: The way of keeping track of the time has already been implemented
  - Pros: Using a standard library usually makes the program less prone to various errors
  - Cons: Still have to implement a way to keep track of time for our purposes of pausing a timer, since the library 
  provided by Java has no way of pausing the timer, only stopping it.


### Timetable 

The **TimeTable** component prints the daily or weekly schedule that the user wishes to see. 

The functionalities of the timetable include:
- Prints a schedule specific to the date the user inputs 
- Prints the schedule for the week the user is at
- Prints the schedule of the day whenever the user starts up Sherpass.
- The timetable schedule is represented in a table form as shown below:

![](images/timetableFormat.png)

#### Task index of a task in timetable

The task number in the timetable as shown follows the index of a task
in the overall task list, i.e. the list containing all tasks added/edited. 
This allows a more intuitive approach towards adding/editing/deleting/marking/unmarking of tasks.

#### Time and Day column in timetable

The **Time** and **Day** in the timetable follows the doOnDate attribute of a task.
Concept wise, this treats the doOnDate as the date and time when the task occurs,
or the date and time the user has set out to accomplish the task.


The **Timetable** is a class which interacts with the following components:
1. Parser
2. ShowCommand
3. TaskList

#### Parser Component

The **Parser** is a class which parses the inputs which the user enters. 
To activate the timetable, the user inputs commands that start with `show`.
This creates a **ShowCommand** object which will execute its method, thereby printing the timetable.

#### ShowCommand Component

Depending on the user input that was parsed by **Parser**, **ShowCommand** will call the 
relative methods which prints the timetable.

#### TaskList Component

As **ShowCommand** is being executed, it will retrieve a filtered list
of task by the date that is defined in the **ShowCommand** from the **TaskList** component. 
The filtered list represents the schedule that the user has on that given date. 
The list is assumed to be sorted previously when the user added/edited a task.

Below is a sequence diagram of what happens 
as the user wishes to see the schedule (timetable) for 25th May 2022:

![](images/showScheduleForADate.png)

The sequence as shown above also happens in the same fashion as the user 
requests to see the schedule for any day or the week the user is at.

The timetable for the current day is also shown to user as the user starts up
the program.

#### Design considerations for Timetable class
- Current Implementation: Printing of timetable from scratch.
  - Pros: Easy to implement as timetable is generated based on request and input.
  - Pros: Adaptive as the timetable is only generated when needed and formatting is taken care of while generating it.
  - Cons: Significant time may be taken as timetable will have to be created from scratch. The delay may be extended if user has a lot of tasks.

- Alternative Implementation: Having a few templates, before choosing the suitable template and editing it if needed.
  - Pros: Reduces computation time
  - Cons: Increases memory usage


### Loading saved files

Class diagram of Storage:

![](images/StorageClass.png)

(_Note: some methods and attributes of `TaskList`,`Ui` and `Parser` are omitted here_)

The storage component
- Can save the content of a `TaskList` to a file in JSON format
- Can load a JSON file to restore a previously saved `TaskList`
- Relies on the `Parser` class to understand the content of a JSON file
- Communicates with the user through the `Ui` class

The loading of a save file is done with the function

`Storage#load()` - Loads a saved JSON file and returns an ArrayList of task

The path of the JSON file is provided as a parameter in the constructor of `Storage` hence 
there is no need for any parameters in the `Storage#load()`. Since a save file will be created in the 
constructor of `Storage` if no such file exists, there should not be any issue with a missing save file.

The save file has the following fields:
- `identifier`: A randomly generated number given to a task. Recurring tasks share the same identifier.
- `index`: The index of the task in the array list
- `description`: Description of the task
- `status`: If the task is completed or not (`'X'` indicates completion, empty string otherwise)
- `do_date_start`: The start date and time of the task (d/M/yyyy HH:mm format)
- `do_date_end`: The end date and time of the task (d/M/yyyy HH:mm format)
- `by_date`: The due date of the task (d/M/yyyy format)
- `frequency`: How often the task repeats (`DAILY`,`WEEKLY`,`MONTHLY`,`NULL`)

The sequence diagram of `Storage#load()` is shown here:
![](images/StorageLoadSD.png)

In the event where the save file cannot be parsed by `JSONObject` (i.e. the format of the file is incorrect) 
or if there are missing fields in a task, the function `Storage#handleCorruptedSave()`
will be called. The user will get to choose to create a new save file or exit the program for manual inspection.
The file error will also be displayed to the user.

The sequence diagram of `Storage#handleCorruptedSave()` is shown here:

![](images/StorageCorruptedSD.png)

#### Design considerations for the format of the save file
- JSON (current choice)
  - Pros: Easy to modify by hand if the user wants to
  - Pros: Easy to parse data
- XML
  - Pros:Reasonably easy for users to modify
  - Cons: Difficult to parse
- Text file with custom format
  - Pros: Might use less space (negligible)
  - Cons: Unintuitive for users to modify
## Product scope
### Target user profile

Students from CEG and SOC

### Value proposition

Sherpass aims to help students to tackle their individual hectic schedules by means of a planner.
Students can manage his/her time by adding their tasks into the application and get reminders
when the tasks are due for completion. Students can also use the study timers within the study session
to block out pockets of time for studying, so that they can better focus during study sessions.

## User Stories

| Version | As a ...  | I want to ...             | So that I can ...                                           |
|---------|-----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user  | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user      | find a to-do item by name | locate a to-do without having to go through the entire list |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
