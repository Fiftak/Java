Instrukcje:
1. Skopiuj kod źródłowy biblioteki do /md5_files
2. Skopiuj kod źródłowy aplikacji do /fconsole

Utworzone foldery są to główne foldery modułów.
Pod adresem /fconsole/module-info.java znajduje się plik opisujący dany moduł.

Kompilowanie modułu md5_files:
1. Przejdź do folderu / w cmd.
2. Wywołaj: javac -d md5_filesbin --module-source-path . --module md5_files

Analogicznie dla fconsole. Dla fconsole zostanie skompilowany również moduł zależny md5_files.

Tworzenie jara ze skompilowanym modułem md5_files.
1. Przejdź do folderu /md5_filesbin w cmd.
2. Wywołaj: jar -c --file=md5_filesj.jar -C md5_files . 

Dla fconsole.
1. Przejdź do folderu /fconsolebin w cmd.
2. Wywołaj: jar -c --file=fconsolej.jar --main-class=console.ConsoleParser -C fconsole . 

Tworzymy folder /myjava/ oraz przechodzimy do niego.
Wywołujemy:
jlink -p "../running/md5_filesj.jar" --add-modules md5_files --output md5java

Przejdźmy do folderu md5java/bin.
Sprawdźmy moduły:
java --list-modules

Uruchomienie aplikacji 
java -jar ../../../running/fconsolej.jar



