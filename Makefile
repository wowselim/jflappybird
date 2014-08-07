all: compile jar

check_jdk:
	@if [ -nx "which javac" ]; then
		echo "You need jdk 1.8"
		exit 1
	fi
	if [ "`javac -version`" ]

compile:
	javac code/*.java

jar:
	cd code && jar -cvmf manifest.mf ../jflappybird.jar *.class res

clean:
	rm code/*.class
	rm jflappybird.jar
