.PHONY = compile run handin list delete_all_files clean

# TODO: MUST replace deppeler with your CS login name (lower case)
CSLOGIN:=ishaan

# TODO: may edit path to your javac,java,jar,javafx installations
JC:= javac  #/usr/bin/javac # replace with path to javac or javac.exe
JAVA:= java # /usr/bin/java # replace with path to java or javaw.exe
CP:= -classpath "."

# DO NOT EDIT THESE VARIABLES 
BESTLINUX:=$(strip $(CSLOGIN))@best-linux.cs.wisc.edu
HANDINDIR:=/p/course/cs400-deppeler/handin/202001/e2/$(CSLOGIN)
APP:=Main      # your main class must be named Main and in Main.java

help:
	@echo HANDINDIR = $(HANDINDIR)
	@echo 1. If /e2/deppeler is showing your handin directory 
	@echo Edit Makefile and set the CSLOGIN variable to your CS user name
	@echo 2. Type the handin action you wish to execute
	@echo ========== MAKE ACTIONS ===========
	@echo make submit - to submit your work to your handin directory
	@echo make list   - to list contents of your handin directory
	@echo make build  - to compile from the command line
	@echo make run    - to run Main from the command line
	@echo make help   - to see this message

build:
	$(JC) $(CP) *.java

run:    
	$(JAVA) $(MP) $(CP) Main data.csv

submit:
	scp *.* $(BESTLINUX):$(HANDINDIR)

list:
	ssh $(BESTLINUX) ls -l $(HANDINDIR)

deleteall:
	ssh $(BESTLINUX) rm $(HANDINDIR)/*.*

clean:
	\rm *.class

