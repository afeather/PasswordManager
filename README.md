This is a password manager that is used to organize and encrypt passwords.

usage: 	java PasswordManager -ADD USERNAME PASSWORD
		java PasswordManager -GET SEARCHSTRING
		
algorithm:
if master password does not exist
	create master password

get action (add password, get password)
if action == add password
	get description of what the password is for
	get the password
	encrypt the password
	save it text and password to file

if action == get password
	get the text to search for
	serach password file for string
	get encrypted password
	print unencrypted password
	
password file
DESCRIPTION OF ACCTION -- PASSWORD HASH
	