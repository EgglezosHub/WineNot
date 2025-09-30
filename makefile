PYTHON_VENV_PATH=venv
PYTHON_SERVER_PATH=src/BackEnd/app.py
JAVA_SRC=src/winepackage/HomePage.java
MAIN_CLASS=winepackage.LoginandSignUp

JAR_CP_LINUX=lib/gson-2.10.1.jar:lib/AbsoluteLayout.jar:src
JAR_CP_WIN=lib/gson-2.10.1.jar;lib/AbsoluteLayout.jar;src

all:
	@echo "🔍 Detecting OS and starting backend..."
	@if [ "$$OS" = "Windows_NT" ]; then \
		echo "🪟 Windows detected"; \
		start cmd /k "$(PYTHON_VENV_PATH)\\Scripts\\activate.bat && pip install flask flask_cors && python $(PYTHON_SERVER_PATH)"; \
	elif [ "$$(uname)" = "Darwin" ]; then \
		echo "🍏 macOS detected"; \
		osascript -e 'tell app "Terminal" to do script "source $(PYTHON_VENV_PATH)/bin/activate && pip install flask flask_cors && python3 $(PYTHON_SERVER_PATH)"'; \
	else \
		echo "🐧 Linux detected"; \
		gnome-terminal -- bash -c "source $(PYTHON_VENV_PATH)/bin/activate && pip install -q flask flask_cors && python3 $(PYTHON_SERVER_PATH); exec bash"; \
	fi

	@echo "⚙️ Compiling Java source files..."
	@if [ "$$OS" = "Windows_NT" ]; then \
		javac -cp "$(JAR_CP_WIN)" src/winepackage/*.java; \
	else \
		javac -cp "$(JAR_CP_LINUX)" src/winepackage/*.java; \
	fi

	@echo "🚀 Launching Java frontend..."
	@if [ "$$OS" = "Windows_NT" ]; then \
		start cmd /k "java -cp $(JAR_CP_WIN) $(MAIN_CLASS)"; \
	elif [ "$$(uname)" = "Darwin" ]; then \
		osascript -e 'tell app "Terminal" to do script "java -cp $(JAR_CP_LINUX) $(MAIN_CLASS)"'; \
	else \
		gnome-terminal -- bash -c "java -cp $(JAR_CP_LINUX) $(MAIN_CLASS); exec bash"; \
	fi

clean:
	@echo "🧹 Cleaning compiled class files..."
	find src -name "*.class" -delete
