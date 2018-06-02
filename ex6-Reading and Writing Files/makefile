# 311124283 312490675
# chen eliyahou   meytal birka

compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt

run:
	java -jar ass6game.jar

jar:
	jar cfm ass6game.jar Manifest.mf  -C bin . -C resources/ .

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java src/*/*.java

bin:
	mkdir bin

all: compile jar run

