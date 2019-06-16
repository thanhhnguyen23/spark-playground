# DAG - directed acyclic graph

## RDD operations - transformations
## RDD operations - actions
* collect
* count
* first
* take(n)
* foreach

## commands

```
/*
    Use case: Analyzing log files via DAG
    1. load into the the spark/hadoop file system
        1.1 driver/master node data is partitioned across the spark cluster

    2. processes through logs to look for specific criteria
        2.1 driver/master node then sends the code to be executed on each block
        2.2 workers are now reading HDFS blocks

    3. caches the messages
        3.1 after a series of transformations, caches results into memory

    4. then filter logs for specific strings
        4.1 worker/slave nodes then sends the data back to the driver/master node
*/
// creating the rdd
val logFile = sc.parallelize(List(1,2,3,4,5))
// transfomrations
val errors = logFile.filter(_.startsWith("ERROR"))
val messages = errors.map(_.split("\t").map(r => r(1))
// caching
messages.cache()
// actions
messages.filter(_.contains("mysql")).count()
messages.filter(_.contains("php")).count()

```
