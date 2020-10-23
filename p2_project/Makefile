.PHONY =  junit5 junit4

rbt: junit5
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestBST
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestRBT

junit5: TestBST.class TestRBT.class
	javac -Xlint:deprecation -cp .:BST.jar:./classes/:junit-platform-console-standalone-1.5.2.jar *.java

STADT.class: STADT.java 
	javac -Xlint:deprecation BST.java STADT.java 

RBT.class: STADT.class RBT.java 
	javac -Xlint:deprecation BST.java RBT.java 

BST.class: STADT.class BST.java 
	javac -Xlint:deprecation BST.java 

TestBST.class: BST.class
	javac -Xlint:deprecation -cp .:BST.jar:./classes/:junit-platform-console-standalone-1.5.2.jar TestBST.java 

TestRBT.class: RBT.class BST.class
	javac -Xlint:deprecation -cp .:BST.jar:./classes/:junit-platform-console-standalone-1.5.2.jar TestRBT.java 

TestBST: TestBST.class
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestBST

TestRBT: TestRBT.class
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -c TestBST

all: junit5
	java -jar junit-platform-console-standalone-1.5.2.jar --class-path .:./classes/ -p ""

clean:
	rm -f *.class
