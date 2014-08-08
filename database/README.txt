Instructions:

You need to get hsqldb.jar in the sample project.
Add hsqldb.jar in lib folder.
Go to Project, Properties, Java Build Path, Libraries
Click Add JARS, go to the same lib folder and select hsqldb.jar
Click OK. 

A few notes:

If you want to switch between using the stub and the real db go
to createDataAccess in Services.java and do it there.

DB.script is the db we are using.
OriginalDB.script is a backup of the original state of DB.script
It is used to restore DB.script to it's original form.

The DB.script in the database folder WILL get modified after 
you run the program and add a new user. For example, if you added
a user "Average American" and then closed the program, then
you will see the modification reflected onto DB.script.

If you keep running the program repeatedly and do not restore
DB.script to it's original form, then when you add the
first couple of users they will NOT be added because they have
the same primary key as the users you have added previously. There
is nothing wrong with the program, and this is not a bug,
it is just that you have to delete any changes to DB.script if you 
don't want to run into this.

You will want to restore the DB.script with OriginalDB.script. 
You can do this manually yourself by just removing any changes
to DB.script, or if you are lazy and you can run RestoreDB.bat 
if you have a windows system or RestoreDB.sh if you have linux.

