compile:
	javac -d build src/*.java src/model/*.java src/vue/*.java src/control/*.java
execute:
	java -cp "build" main
	
	

both:
	javac -d build src/*.java src/model/*.java src/vue/*.java src/control/*.java
	java -cp "build" main

