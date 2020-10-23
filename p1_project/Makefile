.PHONY =  junit5 

junit5: DataStructureADTTest.java
	javac -cp .:./classes/:junit-platform-console-standalone-1.5.2.jar *.java

my: junit5
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestDS_My 

deb: junit5
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestDS_Deb

all: junit5
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -p ""


