# Password Manager
This is a password manager that is used to organize and encrypt passwords.

# Usage
java PasswordManager -ADD DESCRIPTION PASSWORD
java PasswordManager -GET SEARCHSTRING
java PasswordManager -UPDATE ENTRYID NEWPASSWORD
		
# Password File
The password file is contains the description, salt and encrypted password for each entry.

# Algorithm
01. get MasterPassword
02. decrypt PasswordFile with MasterPassword
03. read Passwords from PasswordFile
04. get UserAction
05. if UserAction = "add"
06. 	get Description, Password
07. 	create Password object
08. 	save Password object
09. if UserAction = "get"
10. 	get SearchString
11. 	find Password with SearchString in Description
12. 	print Password
13. go to step 04
