App Development for Smart Devices

CS 441/541

Assignment #1: TO-DO LIST

Published: Tuesday, Jan 28, 2020

Deadline: 11:59 PM, Tuesday, Feb 11, 2020

All projects are required to be submitted with up to a one-page report that specifies what was
implemented, and to what degree it was implemented. You need to also justify your design
decisions. Graduate students may go beyond one page, with additional information regarding
implementation of the graduate student requirements.

1. 40 points maximum - DONE

We’re going to write a “To-do list” app. The goal of this app is to be able to add things that you
need to do to a list on the screen. The requirements for the layout are:

• There should be a general title: To Do List (5 points)
• For each task, there should be a task title and a short task description. (5 points)
• There should be two lines of EditText boxes to input the “task title” and the “task
description” (5 points)
o A hint should be displayed what each form field means.
o The title and the subtitle description should be separate TextView objects. You
cannot use one TextView object.
• There should be an “Add” button that adds the entered “task title” and “task
description” as an item to a list, showing task 1, task 2, etc. (10 points).
o Help might be found:
https://developer.android.com/guide/topics/ui/layout/recyclerview
• The alignment of the all the titles and descriptions should be centralized. (5 points)
• The list should occupy the full range under the form fields all the way to the Add button.
(5 points)
• The Add button should NOT be touching the borders of the screen or the list in any way.
It must be oriented in the bottom right. (5 points)

2. 20 points maximum - DONE

Add a function on each task item in the listView: When the user holds an item for a long
time, that item is deleted from the list. Note: short click should do nothing.

3. 20 points maximum - DONE

Add a function to the “Add” button. By pressing the button, save the current contents of
the listView to a text file on the phone.

4. 20 points maximum

When the user TERMINATES AND REOPENS, NOT “CLOSES” the app, the existing list should
be loaded and shown in the ListView/RecyclerView.

Graduate students’ additional requirements [20 points maximum]

Add a checkbox to each individual list element. The checkbox should be located at the right
end of every list item. When user checks it, the corresponding task item will be deleted directly.
