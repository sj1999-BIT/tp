---
layout: page
title: User Guide
---

WedFast is a **desktop app for organising weddings, optimized for use via a Command Line
Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type
fast, WedFast can get your contact management tasks done faster than traditional GUI apps. 

## Table of Contents
* Quickstart
* Features
  * Add a contact
  * Delete a contact
  * Edit contact
  * List contact
  * Group Contacts
  * View Instructions
  * Keep Track of important information
  * Add Price Tags to contacts
  * Filter contacts
  * Undo last change
  * Set of commands that are customizable by the user
  * Reminder via email (scheduler)
* FAQ
* Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Copy the file to the folder you want to use as the _home folder_ for your WedFast.

3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.

4. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the WedFast contact list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in WedFast's contacts.

Format: `list`

### Add Price Tags to contacts: `pr/`
Tags the contacts with a price tag specifying how much will be spent in a particular context. If
unspecified, then means no money transaction involved with the contact.

Format: `[pr/DOLLARS.CENTS]`

Examples:
* `add n/Sergio Marquina p/98777777 e/professor@moneyheist.com a/Salva Wall,
block 999, #09-99 pr/500.00`

### Editing a person : `edit`

Edits an existing person in the WedFast contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the WedFast contact list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the WedFast contact list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the WedFast contact list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Adding shortcuts : `shortcut` (Coming soon)

 Add a shortcut bound to the SHORTCUT button
 
Format: `shortcut SHORTCUT [c/COMMAND]`

* SHORTCUT is a set of values of 10 buttons [q, w, e, r, a, s, d, z, x, c]


Examples:
* `shortcut q c/find Food`
* `shortcut w c/order b/price`

### Remind via email : `remind` (Coming soon)

Sends a email reminder to the user

Format: ` remind [e/EMAIL](optional)`

* Toggles daily reminder on or off if EMAIL is empty.
* Set remind to be true if EMAIL isn't empty and set the email reminders are sent to.

Examples:
* `remind` toggle reminder
* `remind e/bob@gmail.com`



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WedFast home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Group** | `group [c/CONTACT] [g/GROUP_NAME]` <br> E.g., `group c/Alex Yoeh g/Team4`
**Track** | `track`
**Price Tag** | `[pr/DOLLARS.CENTS]` <br> E.g., `add n/Sergio Marquina p/98777777 e/professor@moneyheist.com a/Salva Wall, block 999, #09-99 pr/500.00`
**Filter** | `filter [g/GROUP_NAME] [t/TAG]...` <br> E.g., `filter g/Team4 t/male`
**Undo** | `undo`
**Shortcut** | `shortcut`<br> e.g. `shortcut q c/find Food`
**Reminder** | `remind` <br> eg. `remind e/bob@gmail.com`
