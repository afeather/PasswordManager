# Password Manager
This is a password manager that is used to organize and encrypt passwords.

usage: 	java PasswordManager -ADD DESCRIPTION PASSWORD
		java PasswordManager -GET SEARCHSTRING
		java PasswordManager -UPDATE ENTRYID NEWPASSWORD
		
# Password File
The password file is contains the description, salt and encrypted password for each entry.

# Algorithm
1. Open Password File
2. Get user input
3. Do Action
4. Go to 2
