all:
	javac -cp lib/AbsoluteLayout.jar:src src/winepackage/HomePage.java
	java -cp lib/AbsoluteLayout.jar:src winepackage.HomePage

clean:
	find src -name "*.class" -delete
