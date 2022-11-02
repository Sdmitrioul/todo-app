# To-Do App

App was developed for Software Design course in ITMO (CT) university.

### Purpose: 
Gain practical experience in using the MVC (Model-View-Controller) pattern.
### The application allows:

1. _View to-do lists and tasks in them_
2. _Add/remove to-do lists_
3. _Add tasks_
4. _Mark tasks as done_

#### Used technologies:

* Spring MVC
* Thymeleaf
* Spring for DI
* SQLite

#### How to run:

* Clone project using ```git clone <reepo_url>```
* Add `.env` file in the root directory of the project. Add env variables, you find example [here](.env.template). `DB` variable is preferable to add in any case, other variables is optional.
* You can launch app from root directory by run command: ```gradle bootRun```