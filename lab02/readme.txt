Proszę przejść do tego folderu gdzie znajduje się ten README.

javac -p "nasalibs;javafx-sdk-11.0.2\lib" --add-modules "javafx.base,javafx.controls,javafx.graphics,javafx.media,org.json,javafx.fxml" -d outbin --module-source-path . --module nasaapp

Proszę przekopiować folder nasaapp/resources do outbin/nasaapp/ (powinien powstać folder outbin/nasaapp/resources).
jar -c --file=compiled.jar --main-class=gui.GUIMain -C outbin/nasaapp .

java -p "nasalibs;javafx-sdk-11.0.2\lib" --add-modules="javafx.swing,javafx.media,javafx.base,javafx.fxml,javafx.graphics,javafx.controls,org.json" -jar compiled.jar
