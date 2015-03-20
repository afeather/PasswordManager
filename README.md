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
02. PlaintextPasswordFile = decrypt(MasterPassword, EncryptedPasswordFile)
03. for each line in PlaintextPasswordFile: get PasswordSalt, PasswordEncrypted, PasswordDescription
04. get UserAction
05. if UserAction = "add":
06.     get Description, PasswordPlaintext
07.     generate PasswordSalt
08.     PasswordEncrypted = encrypt(PasswordSalt + MasterPassword, PasswordPlaintext)
09.     write PasswordSalt, PasswordEncrypted, PasswordDescription to PlaintextPasswordFile
10.     EncryptedPasswordFile = encrypt(MasterPassword, PlaintextPasswordFile)
11.     save EncryptedPasswordFile
12. if UserAction = "get":
13.     get SearchString
14.     SearchResult = find PasswordDescription that contains SearchString
15.     print PlaintextPassword, PasswordDescription
16. if UserAction = "delete":
17. 	get SearchString
18. 	SearchResult = find PasswordDescription that contains SearchString
19. 	delete line from PlaintextPasswordFile
20. 	EncryptedPasswordFile = encrypt(MasterPassword, PlaintextPasswordFile)
21. 	save EncryptedPasswordFile
22. go to step 04
