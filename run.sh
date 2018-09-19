mkdir -p classes

javac -d "classes" "main/BufferedImageLoader.java"
javac -d "classes" "main/SpriteSheet.java"

javac -d "classes" "main/Handler.java"
javac -d "classes" "main/Window.java"

javac -d "classes" "main/RacecarMain.java"
javac -d "classes" "main/TitlePage.java"
javac -d "classes" "main/Map.java"

javac -d "classes" "main/KeyInput.java"
javac -d "classes" "main/ID.java"
javac -d "classes" "main/Player.java"
javac -d "classes" "main/GameObject.java"

java -classpath "classes" main.RacecarMain