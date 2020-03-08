
docker exec -it namenode bash -c "rm -r lab1"

docker cp D:\University\big_data\lab1\scripts\lab1_files namenode:lab1
docker cp D:\University\big_data\lab1\target\lab1-1.0-SNAPSHOT-jar-with-dependencies.jar namenode:lab1/lab1.jar

docker exec -it namenode bash -c "hdfs dfs -rm -r lab1"
docker exec -it namenode bash -c "hdfs dfs -copyFromLocal lab1 lab1"

docker exec -it namenode bash -c "hadoop jar ./lab1/lab1.jar lab1/000000 lab1/result"

docker exec -it namenode bash -c "hdfs dfs -copyToLocal ./lab1/result ./lab1/result"

docker cp namenode:lab1/result/part-r-00000 result.csv

pause


