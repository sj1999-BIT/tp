---
layout: page
title: _WedFast_ User Guide
---

<span style="font-size:2em;">_WedFast_</span> is a **desktop app for managing human resources and important contacts of 
your dream wedding, optimized for use via a Command Line Interface**(CLI) while still having the benefits of a Graphical 
User Interface (GUI). If you are as busy as a beaver and prefer typing to mouse interaction, then _WedFast_ will be your
NO.1 choice to get your contact management tasks done **faster** than traditional GUI apps!

We welcome you to have a look at this user guide to be a master on using _WedFast_!

---

## Table of Contents
<details>
  <summary>About this user guide</summary>

  * [Who is this user guide for?](#who-is-this-user-guide-for)
  * [What is this user guide for?](#what-is-this-user-guide-for)
</details>

<details>
  <summary>How to use this user guide?</summary>
  
  * [Assumption on the reader](#assumption-on-the-reader)
  * [Navigation of this user guide](#navigation-of-this-user-guide)
  * [Formatting convention](#formatting-convention)
  * [Icons used](#icons-used)
  * [Terminology](#terminology)
</details>

<details>
  <summary>Quickstart</summary>

  * [Operating system](#operating-system) 
  * [Setup requirement](#setup-requirement)
  * [Installing and launching](#installing-and-launching)
  * [Command format basics](#command-format-basics)
  * [Using Command Line Interface(CLI)](#using-command-line-interface-cli)
</details>

<details>
  <summary>Feature: Manipulating persons in your contact list</summary>

  * [Adding a person : `add`](#adding-a-person--add)
  * [Clearing all entries : `clear`](#clearing-all-entries--clear)
  * [Deleting a person : `delete`](#deleting-a-person--delete)
  * [Editing a person : `edit`](#editing-a-person--edit)
  * [Grouping persons : `group`](#grouping-persons--group)
  * [Listing all persons : `list`](#listing-all-persons--list)
  * [Locating persons : `find`](#locating-persons--find)
</details>

<details>
  <summary>Feature: Checking useful info of your wedding</summary>
  
  * [Counting down : `countdown`](#counting-down--countdown)
  * [Listing tag info : `tagInfo`](#listing-tag-info--taginfo)
  * [Retrieving report : `report`](#retrieving-report--report)
</details>

<details>
  <summary>Feature: Boosting your productivity</summary>

  * [Adding customizable shortcuts : `shortcut`](#adding-customizable-shortcuts--shortcut)
  * [Exiting the program : `exit`](#exiting-the-program--exit)
  * [Undoing last change : `undo`](#undoing-last-change--undo)
  * [Viewing help : `help`](#viewing-help--help)
  * [Redoing last change : `redo`](#redoing-last-change--redo-coming-soon) (Coming soon)
  * [Reminding via email : `remind`](#reminding-via-email--remind-coming-soon) (Coming soon)
</details>

<details>
  <summary>FAQ</summary>

  * [FAQ : About the program](#faq--about-the-program)
  * [FAQ : About the command](#faq--about-the-command)

</details>

<details>
  <summary>Command Summary</summary>

  * [Command Summary : Manipulating persons in your contact list](#command-summary--manipulating-persons-in-your-contact-list)
  * [Command Summary : Checking useful info of your wedding](#command-summary--checking-useful-info-of-your-wedding)
  * [Command Summary : Boosting your productivity](#command-summary--boosting-your-productivity)
</details>

<details>
  <summary>Helpful information</summary>

  * [Glossary](#glossary)
  * [Technical support contact](#technical-support-contact)
</details>

---

## About this User Guide
### Who is this user guide for?
* Any user who is interested in using _WedFast_ for human resources and contact management tasks of his/her wedding.
* Any novice _WedFast_ user without any prior knowledge about this app

### What is this user guide for?
* Walks you through [all the latest features](#feature-manipulating-persons-in-your-contact-list) of 
  _WedFast_. You will be able to use any feature after reading its corresponding feature section.

* Provides you a detailed and complete set of answers for [frequently asked questions](#faq). 

* Provides you with the [command cheatsheet](#command-summary) which you can refer to in case you forgot some 
[commands](/docs/UserGuide.md#Glossary) syntax format

---

## How to use this user guide?
### Assumption on the reader
* Have at least primary level of English proficiency, i.e. can read and understand texts written in English.
* Have zero knowledge on how to use _WedFast_ but know the basics on how to use a computer.

### Navigation of this user guide
* You can use your mouse to scroll up or down to read different parts of this user guide.
* Alternatively, you can press Up or Down button to achieve the same purpose. 
* You can scroll to the top to [Table of Contents](#table-of-contents) and click on any desired section (prefix by 
  right-arrow icon) to expand the list of content. Then, click on the any item from the list to jump to that content.
* Otherwise, you can press CTRL + F and type in some keywords. Then, press Enter to navigate to each keyword found in
  this user guide.

### Formatting convention
* The name of this app: _WedFast_, is written in Italic throughout the user guide.
* Text in code block can either mean the `command syntax` or `item of interest`.
* Both <span style="color:#e46c0a">main heading</span> and <span style="color:#e46c0a">subheading</span> are colored 
  in Orange.
* <span style="font-size: 2em; color:#e46c0a;">Main heading</span> is bigger than 
  <span style="font-size: 1.5em; color:#e46c0a;">subheading</span>.

### Icons used
* :arrow_forward: Click on text with this icon to expand out a list.
* :arrow_down_small: Click on text with this icon to hide the list.
* :information_source: A section with this icon contains important information that you need to take note of/tips that
                       you can apply

### Terminology
* App: Refer to this _WedFast_ application.
* Command summary: A list of command syntax you can use to run certain feature.
* Feature: _WedFast's_ function which you can use.
* FAQ: Frequently-asked questions
* Program: Refer to this _WedFast_ application.
* Quick start: A simple and quick tutorial on how to set up, install, launch, and using _WedFast_.
* Table of Contents: A by-section summary of the content in this user guide.
* _WedFast_: The name of this app.

---

## Quick start
### Operating system
Ensure your device runs on either **Windows** or **macOS** because _WedFast_ only supports these two
operating systems.

### Setup requirement
Ensure you have Java `11` or above installed in your device.

### Installing and launching
1. Download the latest `wedfast.jar` from [here](https://github.com/AY2122S1-CS2103T-W10-4/tp/releases/download/v1.2.1/WedFast.jar).

2. Copy the file to the folder you want to use as the _home folder_ for your _WedFast_.

3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   Note how the app contains some sample data.
   * `INSERT PRODUCT INITIAL STATE SCREENSHORT HERE`

### Command format basics
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Using Command Line Interface (CLI)
1. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open 
   the help window.
   Some example commands you can try:
   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`: Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

2. You can refer to the followings to learn more about _WedFast's_ features.
   * [Manipulating persons in your contact list](#feature-manipulating-persons-in-your-contact-list)
   * [Checking useful info of your wedding](#feature-checking-useful-info-of-your-wedding)
   * [Boosting your productivity](#feature-boosting-your-productivity)
---

## Feature: Manipulating persons in your contact list
### Adding a person : `add`
To add a person to the contact list:

1. Type the command in the command box with the following format:<br>
`add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [pr/PRICE] [i/IMPORTANT_INFORMATION] [r/REMINDER] [g/group] [t/TAG]…​`
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Clearing all entries : `clear`
To clear all entries in the contact list:
1. Type the command: `clear` in the command box.
2. Press Enter

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Deleting a person : `delete`
To delete a person from the contact list via index/name/tag:
1. Type the command in the command box with either of the following formats:<br>
   * `delete INDEX` or <br>
   * `delete n/NAME` or <br>
   * `delete t/TAG`
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Editing a person : `edit`
To edit an existing person in the contact list:
1. Type the command in the command box with the following format:<br>
   `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [pr/PRICE] [i/IMPORTANT_INFORMATION] [r/REMINDER] [g/group] [t/tag]…​`
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Grouping persons : `group`
To group existing persons in the contact list together to an existing named tag or create a new tag
1. Type the command in the command box with the following format:<br>
   `group t/GROUP_NAME n/NAME1, NAME2, ...`
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Listing all persons : `list`
To lists all the existing contacts.
1. Type the command: `list` in the command box.
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Locating persons : `find`
To find persons whose names contain any of the given keywords, or labelled with same tag, or labelled within the price range. 
1. Type the command in the command box with either of the following formats:<br>
   * `find KEYWORD [MORE_KEYWORDS]` or 
   * `find t/TAG [t/MORE_TAG]` or
   * `find pr/PRICE_RANGE`
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

---

## Feature: Checking useful info of your wedding
### Counting down : `countdown`
To check the countdown till your wedding day:
1. Type the command in the command box with the following format:<br>
   `countdown YYYY-MM-DD`
2. Press Enter.
3. Your wedding date has now been updated.
4. Type `countdown` in the command box and press Enter to check the countdown based on the wedding date 
   set.
5. Repeat step (1) and step (2) again if you wish to reset the wedding date.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note:**<br>
If you have not set any wedding date before, then the default wedding date will be today and typing `countdown` in the
command box will show `0 days left until your wedding on YYYY-MM-DD` where `YYYY-MM-DD` is today's date.

**:information_source: Tips:**<br>
If you had set the wedding date before, then you can follow step (4) ONLY to check the countdown.
</div>

### Listing tag info : `tagInfo`
To return a unique list of tags with the number of contacts labelled with the tag.
1. Type the command in the command box with either of the following formats:<br>
   * `tagInfo [t/TAG]...` or
   * `tagInfo list`
2. Press Enter

Example and expected outcome:<br>
* `tagInfo t/male, colleagues` tag Info: males used by 3 people, colleagues used by 5 people
* `tagInfo list` returns a list with all the tags labelled by the contacts.

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

**:information_source: Tips:**<br>
Filter through the contacts with tags TAG to obtain a list of TAGs with details of how many contacts labelled by them.
The list can be specified to only limited tags or to include all the tags used to label the contacts.

</div>

### Retrieving report : `report`
To 
1. Type the command: `report` in the command box.
2. Press Enter

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

---

## Feature: Boosting your productivity
### Adding customizable shortcuts : `shortcut`
To add a shortcut bound to the SHORTCUT button
1. Type the command in the command box with either of the following formats:<br>
    * 
2. Press Enter

* SHORTCUT is a set of values of 10 buttons [q, w, e, r, a, s, d, z, x, c]

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Exiting the program : `exit`
To exit the app.
1. Type the command: `exit` in the command box.
2. Press Enter

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Undoing last change : `undo`
To undo the last change that was made to the contact list.
1. Type the command: `undo` in the command box.
2. Press Enter.

Example and expected outcome:<br>
`PLEASE INSERT TEXT AND SCREENSHOTS HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Viewing help : `help`
To shows a message on how to access the help page.
1. Type the command: `help` in the command box.
2. Press Enter

Example and expected outcome:<br>
![help message](images/helpMessage.png)
`PLEASE INSERT TEXT HERE`

<div markdown="block" class="alert alert-info">

**:information_source: Important note/Tips(If any):**<br>
`INSERT HERE IF GOT, ELSE, KINDLY DELETE THIS PART`

</div>

### Redoing last change : `redo` (Coming soon)
_Details coming soon …_

### Reminding via email : `remind` (Coming soon)
_Details coming soon …_

---

## FAQ
### FAQ : About the program
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
       the data of your previous _WedFast_ home folder.

### FAQ : About the command

---
## Command summary
### Command Summary : Manipulating persons in your contact list
Action | Format, Examples
--------|------------------
**Add** |
**Clear** | `clear`
**Delete** | `delete INDEX` <br>e.g. `delete 3`<br><br>`delete t/TAG`<br>e.g. `delete t/food_vendor`<br><br>`delete n/NAME`<br>e.g. `delete n/Alex Yeoh`
**Edit** | 
**List** | `list`
**Help** | `help`
**Group** |


### Command Summary : Checking useful info of your wedding
Action | Format, Examples
--------|------------------
**Counting down** |`countdown`<br><br>`countdown YYYY-MM-DD`<br>e.g. `countdown 2021-10-24`
**Get tag info** |
**Get report** | `report`

### Command Summary : Boosting your productivity
Action | Format, Examples
--------|------------------
**Exit** | `exit`
**Help** | `help`
**Shortcut** |
**Redo** | (Coming soon)
**Remind** | (Coming soon)
**Undo** |

## Useful information
### Glossary
* Command - Text input to execute/run _WedFast's_ feature
* Command Line Interface - A feature that allows the user to write command to be executed

### Technical support contact
We sincerely apologized if this user guide is incapable of solving your issues/problems.
Please kindly contact with our 24/7 technical support team via the following methods:
* Email: `wedding@fastfast.com`
* Hotline: `+65 12345678`
* Website: `team4.wedfast.com`
