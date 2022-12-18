# java_gui_app
## managment of students informations
- simple managment system gui, that adds, deletes, and looks for your informations in a database.
Set environment variable to match your database server (hostname, password, port, user) for connection.
Launch from your terminal to go through the same process and get those variables.
- Launch mysql, phpmyadmin containers : go to /mysql folder and run (if user not added to docker group(best practice ;) i recommand not to add your user to it) use sudo) docker-compose build then docker-compose up -d (to run it in a detached mode)

### Linux:
  In the terminal, 'export MY_ENV_VARIABLE="env_content"' or add that line to your .bash_rc then source it using 'source .bash_rc' in the terminal.
