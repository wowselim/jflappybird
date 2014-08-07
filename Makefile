all: compile jar

compile:
	javac code/*.java

jar:
	cd code && jar -cvmf manifest.mf ../jflappybird.jar *.class res

clean:
	rm code/*.class
	rm jflappybird.jar
