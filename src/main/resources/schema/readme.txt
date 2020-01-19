Step 1: install PostgreSQL

    sudo apt-get install postgresql

Step 2: Creating the database and corresponding user account for the project

	Login the postgresql by either one of following command:
		1.	sudo -u postgres psql -f create_db.sql
			(It will perform OS authentication. It works only if the postgresql is installed in your local machine.)

		2.	psql -h <machine_name> -U <username> postgres -f create_db.sql
			(The user account should be superuser of postgresql server. Otherwise you are unlikely to have enough privilege.)


Step 2: Setup the privilege in the database.

	Run the following command:
		psql -h <machine_name> -U jwtspringsecurity_admin -f setup_db.sql jwtspringsecurity_db


Step 3: Create the table in the database.

	Run the following command:
		psql -h <machine_name> -U jwtspringsecurity_admin -f create_table.sql jwtspringsecurity_db