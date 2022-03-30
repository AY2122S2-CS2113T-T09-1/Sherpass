# Sherpass User Guide

## Introduction

Sherpass is a desktop application for students to manage their academic schedules and also provide a platform for them
to focus on their academic tasks.
Optimised for use via a Command Line Interface (CLI),
users who can type fast will be able to plan out their tasks in a much quicker fashion compared to traditional GUI apps.

### Managing your time
You can add your tasks into Sherpass and get a detailed outline of your schedule. Using this schedule, you won't
have to worry ever again about forgetting tasks and missing deadlines!

Sherpass also allows you to easily add recurring tasks (e.g. classes). Simply refer to the command details for
more information.

### Study sessions
Having trouble focusing while studying? Sherpass's study sessions allow you to select study timers, inspired by the
increasingly popular [Pomodoro study sessions](https://examstudyexpert.com/pomodoro-study-method/), to help you keep track of the time whilst completing your tasks.

#### During Study sessions
Starting the study session will show you the tasks that you have planned to do for the day. You can then choose to
start one of a few default timers provided by us, or start your own custom timer. You can also choose to start a
stopwatch to keep track of how long you've been studying for, if you're unsure of how long you will need.

#### After Study sessions
After your study session, you will be prompted to mark the tasks you've completed as done. If you're still not done with
certain tasks, feel free to start another productive session!

### User Guide Target Audience
New and inexperienced users who want to use Sherpass, as well as non-users who want to know more about what Sherpass
can do.

## Table of Content
- [Quick Start](#quick-start)
- [Features](#features)
- [Command Guide](#command-guide)
  - Add tasks: [`add`](#adding-your-tasks-add)
  - Delete tasks: [`delete`](#deleting-your-tasks-delete)
  - Edit tasks: [`edit`](#editing-your-tasks-edit)
  - Mark tasks [`mark`](#marking-your-tasksmark-task_number--unmark-task_number)
  - Show tasks: [`show`](#display-daily-schedule-show-today--show-tomorrow--show-date)
  - Clear all tasks: [`clear`](#clearing-all-tasks-clear)
  - Study session
    - Enter study session: [`study`](#enter-study-session-study)
    - Start timer: [`start`](#starting-a-default-timer-start-mode_number)     
    - Pause timer: [`pause`](#pausing-a-timerstopwatch-pause)
    - Resume timer: [`resume`](#resuming-a-timerstopwatch-resume)
    - Stop timer: [`stop`](#stopping-a-timerstopwatch-stop)
    - Show tasks: [`show`](#show-your-tasks-show)
    - Mark tasks as done:[`mark`](#mark-your-tasks-as-done-mark)
    - Exit study session: [`leave`](#leave-the-study-session-leave)
  - Exit program: [`exit`]()
  - [Saving your tasks](#saving-your-tasks)
- [FAQ](#faq)
- [Glossary](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Sherpass` 
from [here](https://github.com/AY2122S2-CS2113T-T09-1/tp/releases/tag/v1.0-Release).
3. Copy the jar file to the folder you want to use as the home folder for Sherpass.
4. Open a terminal and navigate to the folder containing the jar file.
5. Start the application on the terminal using `java -jar Sherpass.jar`

## Features

## Command Guide

### Notes on command input format:
- Words in UPPER_CASE are the [parameters](#glossary) to be supplied by the user.
- Items in square brackets are optional
- [Extraneous](#glossary) parameters for commands that do not take in parameters (such as show, stop, exit) will be ignored

### Notes on task number format:
- Each task added to your schedule is assigned a task number. You can see what number a task is assigned via
the [show command](#show-your-tasks-show)
- The task number will update accordingly as you add/edit/delete a task (with the
earliest task assigned the first number, second earliest task the second number, and so on)

### Adding your tasks: `add` 
**_Note: When adding tasks, TASK_DESCRIPTION must be the first parameter provided.
Order of other parameters can be switched._**
#### Adding a single task

Format: `add TASK_DESCRIPTION /do DO_DATE /start START_TIME /end END_TIME [/by DEADLINE]`

|    Parameters    | Description                     | Accepted inputs                                   | Optional |
|:----------------:|---------------------------------|---------------------------------------------------|----------|
| TASK_DESCRIPTION | Description for the task        | Any sentence without the character '/'            | No       |
|     DO_DATE      | Date to work on the task        | d/M/yyyy format (e.g 25/3/2022 for 25 March 2022) | No       |
|    START_TIME    | Time to start working on a task | HH:mm format (e.g 18:00), must be before END_TIME | No       |
|     END_TIME     | Time to stop working on a task  | HH:mm format (e.g 19:00)                          | No       |
|     DEADLINE     | Due date for the task           | d/M/yyyy format (e.g 25/3/2022 for 25 March 2022) | Yes      |

Examples: 
- `add write final essay /do 25/3/2022 /start 20:00 /end 22:00 /by 27/3/2022`
- `add revise chapter 3 /do 25/3/2022 /start 20:00 /end 22:00`

#### Adding a recurring task
- All tasks will have the same task description.
- For a **daily** recurring task, a **month's** worth of task will be added.
- For a **weekly** recurring task, **2 months'** worth of task will be added.
- For a **monthly** recurring task, a **year's** worth of task will be added.

Format: `add TASK_DESCRIPTION /do DO_DATE /start START_TIME /end END_TIME /repeat FREQUENCY`

|    Parameters    | Description                     | Accepted inputs                              | Optional |
|:----------------:|---------------------------------|----------------------------------------------|----------|
| TASK_DESCRIPTION | Description for the task        | Any sentence without the character '/'       | No       |
|     DO_DATE      | Date to work on the task        | dd/MM/yyyy (e.g 25/3/2022 for 25 March 2022) | No       |
|    START_TIME    | Time to start working on a task | HH:mm (e.g 18:00), must be before END_TIME   | No       |
|     END_TIME     | Time to stop working on a task  | HH:mm (e.g 19:00)                            | No       |
|    FREQUENCY     | How often does the task repeat  | DAILY, WEEKLY, MONTHLY                       | No       |

Examples: 
- `add attend cs2113t lecture /do 25/3/2022 /start 16:00 /end 18:00 /repeat WEEKLY`
- `add browse internship offers /do 27/3/2022 /start 21:00 /end 21:30 /repeat DAILY`

### Deleting your tasks: `delete`

**_Note: Be careful when deleting a recurring task. Entering the TASK_NUMBER
of a recurring task will all instances of that task to be removed permanently._**

Format: `delete TASK_NUMBER`

| Parameters  | Description                                         | Accepted inputs       | Optional |
|:-----------:|-----------------------------------------------------|-----------------------|----------|
| TASK_NUMBER | The index of the task as seen in the `show` command | Any valid task number | No       |

Example:
- `delete 1`


### Editing your tasks: `edit`

#### Editing a single task

Format: `edit TASK_NUMBER [TASK_DESCRIPTION] [/do DO_DATE /start START_TIME /end END_TIME] [/by DEADLINE]`

|    Parameters    | Description                                         | Accepted inputs                                   | Optional               |
|:----------------:|-----------------------------------------------------|---------------------------------------------------|------------------------|
|   TASK_NUMBER    | The index of the task as seen in the `show` command | Any valid task number                             | No                     |
| TASK_DESCRIPTION | Description for the task                            | Any sentence without the character '/'	           | Yes                    |
|     DO_DATE      | Date to work on the task                            | dd/MM/yyyy (e.g 25/3/2022 for 25 March 2022)      | Yes                    |
|    START_TIME    | Time to start working on a task                     | HH:mm (e.g 18:00), must be before END_TIME        | No if DO_DATE is given |
|     END_TIME     | Time to start working on a task                     | HH:mm (e.g 19:00)                                 | No if DO_DATE is given |
|     DEADLINE     | Due date for the task                               | d/M/yyyy format (e.g 25/3/2022 for 25 March 2022) | Yes                    |

Examples:
- `edit 2 write draft essay`
- `edit 3 submit draft essay /do 26/3/2022 /start 10:00 /end 13:00`

#### Editing a recurring task
- The specified task and all its future occurrence will be edited.
- Occurrences earlier than the specified task will not be affected.
- To change frequency, delete and add the recurring task with the new frequency using the `delete` and `add` command respectively.

Format: `edit TASK_NUMBER [TASK_DESCRIPTION] [/do DO_DATE /start START_TIME /end END_TIME]`

|    Parameters    | Description                                         | Accepted inputs                                   | Optional               |
|:----------------:|-----------------------------------------------------|---------------------------------------------------|------------------------|
|   TASK_NUMBER    | The index of the task as seen in the `show` command | Any valid task number                             | No                     |
| TASK_DESCRIPTION | Description for the task                            | Any sentence without the character '/'	           | Yes                    |
|     DO_DATE      | Date to work on the task                            | dd/MM/yyyy (e.g 25/3/2022 for 25 March 2022)      | Yes                    |
|    START_TIME    | Time to start working on a task                     | HH:mm (e.g 18:00), must be before END_TIME        | No if DO_DATE is given |
|     END_TIME     | Time to start working on a task                     | HH:mm (e.g 19:00)                                 | No if DO_DATE is given |


Before edit command:

![beforeedit](images/beforeedit.png)

After command: `edit 1 /do 25/3/2022 /start 18:00 /end 20:00`

![afteredit](images/afteredit.png)


### Marking your tasks`mark TASK_NUMBER` / `unmark TASK_NUMBER`

Mark the tasks that you've done in the session with `mark`.
You can also mark a task as undone with `unmark`.

Format: `mark TASK_NUMBER` or `unmark TASK_NUMBER`

| Parameters  | Description                                       | Accepted inputs                       | Optional |
|:-----------:|---------------------------------------------------|---------------------------------------|----------|
| TASK_NUMBER | The task number as seen in the `show all` command | Any valid task number in the schedule | No       |

Example:

![](images/markTasks.png)

### Display Daily Schedule: `show today` / `show tomorrow` / `show DATE`

Displays your schedule for today, tomorrow or any specific date.

Upon startup, Sherpass also shows your schedule for the day.

Format:
- To see the schedule for the day: `show today`
- To see the schedule for tomorrow: `show today` or `show tmr`
- To see the schedule for a specific day: `show DATE`, where the date is in the format `d/M/YYYY`.
  
   E.g. `show 25/3/2022` shows the schedule for 25th March 2022

Below is an example of the schedule that is being displayed from the given date
25/3/2022:

![ShowDateTimetableFormat](images/showDateTimetableFormat.PNG)

### Display Weekly Schedule: `show week` / `show next week`

Displays your schedule for the week or the week after.

Format:
- To see the schedule for the week you are in: `show week`
- To see the schedule for the week after: "show next week" or `show nextweek`

Below is an example of the weekly schedule displayed:

![weeklyTimetablePart1](images/weeklyTimetablePart1.PNG)
![weeklyTimetablePart2](images/weeklyTimetablePart2.PNG)

### Display Monthly Schedule: `show month` / `show MONTH`

Displays your schedule for the current month or any specific month

Format:
- To see the schedule for the current month: `show month`
- To see the schedule for any specific month: `show MONTH`, where 
  MONTH can be the abbreviation of the specific month. 


  E.g. `show sep` or `show september` is the same command 

  E.g. `show september` to display the schedule for **September**

Below is an example of the monthly schedule displayed:

![monthlySchedule](images/monthlySchedule.PNG)

### Getting a detailed list: `show all` / `show todo`

Other than getting a timetable, Sherpass also allows you
to view all the tasks that have been added/edited/deleted.

Format:
- To view all the tasks that exist in your list: `show all`
- To view all the tasks that are yet to be completed: `show todo` 

Below are some sample outputs using `show all` and `show todo` respectively

Show all tasks:

![](images/showAllTasks.png)

Show pending tasks:

![](images/showPendingTasks.png)


### Clearing all tasks: `clear`
To save the trouble of deleting tasks one by one if you wish to delete all of them,
Sherpass allows you to do so using `clear`

**_Note: Be sure to use `clear` only when you want to 
remove all the tasks in your list._**

### Saving your tasks

All changes to your tasks are saved automatically into a JSON file at `[JAR FILE LOCATION]/data/Sherpass.json`.

There is no need to manually save your tasks.

_Note: Advanced users are welcome to manually edit the save file._

### Enter study session: `study`
Enter the study session where you can start timers to help finish your tasks.

Format: `study`

Example:

![image](https://user-images.githubusercontent.com/69501969/160783109-3187d5df-06f1-4fa4-a407-18a87a67c770.png)

### Starting a timer: `start`

**_Note:_**
- Once you have entered the study session and have selected a timer,
a separate pop-up timer window will be shown to you. 
- It will show you the amount of time left or the elapsed time. Buttons are provided on the window for you
to pause/resume/stop the timer quickly. 
- You may also choose to enter the commands via the 
command line interface instead.

#### Starting a default timer: `start MODE_NUMBER`

Jump right into a study session by starting a study timer from our list of default timers.

**_Note:_**

- Only one timer can be running at any given point of time.
- Timer keeps track of time remaining and prints time remaining at regular intervals.
- All our timers can be paused, resumed and stopped while the timer is running.


Format: `start MODE_NUMBER`

|      Parameters      | Description                                             | Accepted inputs               | Optional  |
|:--------------------:|---------------------------------------------------------|-------------------------------|-----------|
|     MODE_NUMBER      | The default mode number of the study timer chosen (0-4) | Any valid default mode number | No        |

- You can choose from 3 default timers.
- `MODE_NUMBER` 0 starts a 30 second(s) timer for testing purposes.
- `MODE_NUMBER` 1 starts a 30 minute(s) timer
- `MODE_NUMBER` 2 starts a 1 hour(s) timer
- `MODE_NUMBER` 3 starts a 1.5 hour(s) timer

Examples:

- `start 1`

In the terminal:

![](images/terminalStart1.png)

In the pop-up window:

![](images/startDefaultOne.png)

### Starting a custom timer: `start`
Need something else rather than what is provided by our default timers? You can start your own custom timer to suit 
your own needs.

- Start a custom timer with `DURATION` set by you

Format: `start /custom DURATION`

|    Parameters    | Description                                               | Accepted inputs   | Optional |
|:----------------:|-----------------------------------------------------------|-------------------|----------|
|     DURATION     | Duration of the timer (in seconds) that you wish to start | Any valid integer | No       |

- Timer `DURATION` is in seconds
- `/custom` flag is mandatory to start a custom timer

Example:

- `start /custom 60`

In the terminal:

![](images/terminalCustom60sec.png)

In the pop-up window:

![](images/customTimer60sec.JPG)

### Starting a stopwatch: `start stopwatch`
Not sure how long you'll need to complete your academic tasks? You can start a stopwatch to keep track of how long 
you've been studying.
- Stopwatch tracks and prints time elapsed.

Format: `start stopwatch`

Example:

In the terminal:

![image](https://user-images.githubusercontent.com/69501969/160330116-886c03a2-b3c8-4e9c-a879-8fba990c2668.png)

In the pop-up window:

![](images/stopWatch.png)

### Pausing a timer/stopwatch: `pause`
Pauses a study timer/stopwatch that is currently running. Useful for when you want a quick toilet break, double check
your tasks for the day, or mark a task as done.

Format: `pause` or clicking the pause button in the pop-up window.

Example:

In the terminal:

![](images/pauseTimer.png)

![image](https://user-images.githubusercontent.com/69501969/160329512-3a74d513-d95c-4aa2-9d88-a29ca3f93459.png)

In the pop-up window:

Time left in the window will not change until the user enters `resume`
in the terminal or clicks on the resume button.

### Resuming a timer/stopwatch: `resume`
You can resume a paused timer/stopwatch when you're ready to work on your tasks again.

- Resume a timer/stopwatch that has been paused.

Format: `resume` or clicking the resume button in the pop-up window.

Example:

In the terminal:

![image](https://user-images.githubusercontent.com/69501969/160329950-48c20933-84aa-46ef-9f7d-e58154c3270a.png)

In the pop-up window:

![](images/resumeTimer.png)

Time left/elapsed time will continue to run.

### Stopping a timer/stopwatch: `stop`
Finished your task earlier than expected, or something more urgent came up? You can stop a timer/stopwatch at any time.

- Stop a timer/stopwatch that has been started.

Format: 
- Entering `stop` in the terminal
- Clicking the stop button in the pop-up window
- Closing the pop-up window (Clicking the X button in the top right corner)

Example:

In the terminal:

![image](https://user-images.githubusercontent.com/69501969/160329869-2696b661-585b-4fae-9ebd-149b79316dd2.png)

In the pop-up window:

![](images/stopTimer.png)

The window will disappear immediately after stopping the timer.

### Show your tasks: `show`
The show feature is also supported in the study session. 
Simply follow the format from the [show feature](#show-your-tasks-show)
to show your tasks.

**_Note:_**
- You can only show tasks while the timer is paused or stopped.

Example:

![image](https://user-images.githubusercontent.com/69501969/160788279-c38a7611-e1e9-4aef-87b7-6a06b6d96c9e.png)

### Mark your tasks as done: `mark`

The mark feature is also supported in the study session. 
Simply follow the format from the [mark feature](#marking-your-tasksmark-task_number--unmark-task_number)
to mark your task.

**_Note:_**
- You can only mark tasks while the timer is paused or stopped.

Example:

![image](https://user-images.githubusercontent.com/69501969/160330280-f0cae4ae-0561-4c09-bc1f-8f89c6136aa6.png)

### Leave the study session: `leave`
Leave the study session to go back to the main session.

Format: `leave`

Example:

![image](https://user-images.githubusercontent.com/69501969/160330328-f3981868-6bcc-4e29-989c-90356cf15c10.png)

## FAQ

**Q**: My save file is corrupted, how do I resolve this?

**A**: Sherpass will print an error message 
containing details of the corruption (e.g., missing fields, brackets). You can either modify the save file accordingly to
rectify the problem or allow Sherpass to create a new save file 
**(Caution: You will lose all previously saved tasks!)**

**Q**: How do I transfer my data to another computer? 

**A**: Transfer a copy of your save file to your other device and place it in `[JAR FILE LOCATION]/data/Sherpass.json`

## Glossary

* *Parameter* - A parameter is the means by which a value is passed to the command processing program.
* *Extraneous* - Something that is unnecessary or irrelevant.
## Command Summary

| Action                        | Format, Examples                                                                                                                                                             |
|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Generate timetable            | `show today` / `show DATE` / `show week` / `show MONTH`                                                                                                                      |
| List all tasks                | `show all`                                                                                                                                                                   |
| List all pending tasks        | `show todo`                                                                                                                                                                  |
| Clear all tasks               | `clear`                                                                                                                                                                      |
| Add single task               | `add TASK_DESCRIPTION /do DO_DATE /start START_TIME /end END_TIME [/by DEADLINE]`<br/>e.g. `add write final essay /do 25/3/2022 /start 20:00 /end 22:00 /by 27/3/2022`       |
| Delete task(s)                | `delete TASK_NUMBER`                                                                                                                                                         |
| Edit single task              | `edit TASK_NUMBER [TASK_DESCRIPTION] [/do DO_DATE /start START_TIME /end END_TIME] [/by DEADLINE]`<br/>e.g.`edit 3 submit draft essay /do 26/3/2022 /start 10:00 /end 13:00` |
| Add recurring task            | `add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat FREQUENCY`<br/>e.g. `add attend cs2113t lecture /do 25/3/2022 /start 16:00 /end 18:00 /repeat WEEKLY` |
| Edit recurring task           | `edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start TIME /end TIME] /repeat`<br/>e.g.`edit 1 /do 25/3/2022 /start 18:00 /end 20:00 /repeat`                                |
| Mark a task as done           | `mark TASK_NUMBER`                                                                                                                                                           |
| Mark a task as undone         | `unmark TASK_NUMBER`                                                                                                                                                         |
| Enter study session           | `study`                                                                                                                                                                      |
| Start default timer           | `start MODE_NUMBER`                                                                                                                                                          |
| Start custom timer            | `start /custom DURATION`                                                                                                                                                     |
| Start stopwatch               | `start stopwatch`                                                                                                                                                            |
| Pause timer/stopwatch         | `pause`                                                                                                                                                                      |
| Resume timer/stopwatch        | `resume`                                                                                                                                                                     |
| Stop timer/stopwatch          | `stop`                                                                                                                                                                       |
| Show tasks (in study session) | `show all`                                                                                                                                                                   |
| Leave study session           | `leave`                                                                                                                                                                      |

