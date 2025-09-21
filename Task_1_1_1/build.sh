javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.ekovalenko4

javac src/main/java/ru/nsu/ekovalenko4/sort/Sort.java -d ./build

java -cp ./build ru.nsu.ekovalenko4.sort.Sort