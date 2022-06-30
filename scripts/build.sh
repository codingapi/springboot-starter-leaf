cd ../
mvn clean package
cp leaf-server/target/*.jar ./scripts/leaf-server.jar

cd ./scripts

docker build -t codingapi4lorne/leaf-server:v0.0.1 .
