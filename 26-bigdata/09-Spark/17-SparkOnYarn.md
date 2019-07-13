


-- spar on yarn 集群提交
./spark-submit --master yarn --deploy-mode cluster --class com.suyh.MainDriver01 Driver01.jar

-- spark on yarn 本地提交 三种命令模式
./spark-submit --master yarn --deploy-mode client  --class com.suyh.MainDriver01 Driver01.jar
./spark-submit --master yarn-client --class com.suyh.MainDriver01 Driver01.jar
./spark-submit --master yarn --class com.suyh.MainDriver01 Driver01.jar
