# Demo 1
## Magic function for markdown
```
%md
```

## list of 10000 integers
## range() is more memory efficient 
```
data = range(1,10001)
```


## lets see our list
```
len(data)
```

## ds - dataset
## sc - SparkContext
## parallelize() to create a dataset and spread it across the cluster paritions (8)
``` ds = sc.parallelize(data, 8)

```
## more info on parallelize here
## help(sc.parallelize)

## show us what we havein ds using the collect()
```
ds.collect()
```

## working with datasets
### actions
* show
* count
* collect
* save

### transformations
* select
* distinct
* groupBy
* sum
* orderBy
* filter
* limit


# Demo 2
Working with Text Files
## 1. Find sample data
## 2. Read in text file
## 3. Read in directory
## 4. Create DataFrame


## browsing file system
```
%fs ls
```

## checkout some some sample data
```
%fs ls /databricks-datasets/bikeSharing/README.md
```
## next read the file and count the lines in a document
```
path = "/databricks-datasets/bikeSharing/README.md"
data = sc.textFile(path) # use the sc context to read in a text file
data.count()
```

## take a quick look at the first line
```
data.first()
```

## take a quick look at the first 20 lines
```
data.first(20)
```

## read in file from above
```
logFile = path
```

## cache the data
```
logData = sc.textFile(logFile).cache()
```

## get number of times "bike" shows up
### uses lambda function and lower() to convert the line to lowercase
### use count to figure out how many times this is true

```
numBikes = logData.filter(lambda s: 'bike' in s.lower()).count()
```

## show results
```
print("Lines with 'bike': %i" % (numBikes))
```


## read in directory of files with wholeTextFiles()
### read in directory looking for anything ending in .csv
```
path = "/databricks-datasets/Rdatasets/data-001/csv/datasets/*.csv"
```

### use wholeTextFiles to get each file listed separately with {filename, content}
```
files = sc.wholeTextFiles(path)
```

### count how many files there are
```
files.count()
```

## convert list of files to datafame
### use toDF to convert object to data frame with column names
```
filenames = files.toDF(['name', 'data'])
```

### show entire dataframe
```
display(filenames)
```


## show only the names using select()
```
display(filenames.select('name'))
```

## DataFrame
### similar to a table in SQL, Pandas in Python, and a DataFrame in R
### allows for more expressive operations on datasets


# Demo 3
Loading CSV Data in DataFrames
## 1. Find CSV Data
## 2. Sample CSV file
## 3. Create a DataFrame with CSV


### find a directory with csv
```
%fs ls /databricks-datasets/online_retail/data-001/
```

#### specify path
```
path = "/databricks-datasets/online_retail/data-001/data.csv"
```

#### load as text
```
data = spark.read.csv(path)
```

#### show sample
```
data.take(20)
```

### read in data to dataframe with column headers
#### read in file using csv format
```
df = spark.read.load(path,
                    format='com.databricks.spark.csv',
                    headers='true',
                    inferSchema='true')
```

#### show 20 rows
```
display(df)
```


### show countries
```
display( # shows results in grid
    df
        .select("Country") # chooses just one column
        .distinct() # removes duplicates
        .orderBy("Country") # sorts results in ascending order
```

# Demo 4
Exploring Data in DataFrames

## 1. read in data
## 2. inspect data
## 3. aggregate data
## 4. filter data

### find a directory with CSVs
%fs ls /databricks-datasets/online_retail/data-001/
### read in data in dataframe with column headers
#### specify path
path = "/databricks-datasets/online_retail/data-001/data.csv"
#### read in file using csv format
df = spark.read.load(path,
                    format='com.databricks.sparks.csv',
                    header='true',
                    inferSchema='true')
#### show 20 rows
display(df)

### show dataframe schema
#### takea  look at our schema
df.printSchema()

### select just 1 column
#### show just the countries
df.select("Country").show()

### remove duplicates from column and sort
display( # show results in grid
    df
        .select("Country") # selects 1 column
        .distinct() # removes duplicates
        .orderBy("Country") # sorts results in ascending
### calculate order totals
display(
    df
        .select(df["InvoiceNo"], df["UnitPrice"] * df["Quantity"])
        .groupBy("InvoiceNo")
        .sum()
)
### inspect results with filter
df.filter(df["InvoiceNo"] == 536596).show()
### show top 10 products in the UK

display(
    df
        .select(df["Country"[, df["Description"], (df["UnitPrice"] * df["Quantity"].alias("Total"))
        .groupBy("Country", "Description")
        .sum()
        .filter(df["Country"] == "United Kingdom")
        .sort("sum(Total)", ascending=False)
        .limit(10)
)



# spark.apache.org Quick Start

## basics
```
// make a new dataset from the text file README.md
val textFile = spark.read.textFile("README.md")

// get values from the dataset directly by calling some actions
// or transform the dataset to get a new one
textFile.count() // number of items in this dataset

textFile.first() // first item in this dataset

// transform this dataset into a new one
// call filter to return a new dataset with a subset of the items in the file
val linesWithSpark = textFile.filter(line => line.contains("Spark"))

// we can now chain together transformations and actions
textFile.filter(line => line.contains("Spark")).count() // how many lines contains spark

```
## dataset operations
```
// lets say you want to find the line with the most words
// maps line to an integer value; creating new dataset
// reduce is called on this dataset to find the largest word count
textFile.map(line => line.split(" ").size).reduce((a,b) => if (a > b) a else b)


import java.langMath
textFile.map(line => line.split(" ").size).reduce((a, b) => Math.max(a,b))


// spark can also use mapreduce flows easily
// flatMap is used to transform a dataset of lines to a dataset of words, and then combine GroupByKey and count to compute the per-word counts in the file as a dataset
val wordCounts = textFile.flatMap(line => line.split(" ")).groupBy(identity).count()

wordCounts.collect()
```

## caching




# Scala
* preferred programming language over python
* runs on top of the JVM
    - can access Java classes
* functional programming




# RDD
* resilient distributed dataset
## spark context
* responsible for making RDDs resilient and distributed
* creates RDD
* spark shell creates a "sc" object for you

## example
```
val nums = parallelize(List(1,2,3,4,5))
sc.textFile("/some/path/to/some/text/file.txt")

```

## transforming RDDs
* map
* flatmap
* filter
* distinct
* sample
* union, intersection, subtract, cartesian



## map example
```
val rdd = sc.parallelize(List(1,2,3,4)
val squares = rdd.map(x => x * x)

```

### functional programming examle
```
// example 1
rdd.map(x => x * x)

// example 2
def squareIt(x: Int): Int = {
    return x * x
}
rdd.map(squareIt)
```

## RDD actions
* collect
* count
* countByValue
* take - peek the first 10 lines of a dataset
* top
* reduce

## spark internals
* textFile()
* mape()
* countByValue() (RDD action)

## key/value RDDs


## weather example
```
def parseLine(line: String) = {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f/5.0f) + 32.0f
    (stationID, entryType, temperature)
}
val lines = sc.textFiles("../1800.csv")
val parsedLines = lines.marp(parseLine)

val minTemps = parsedLines.filter(x => x._2 == "TMIN")
val stationTemps = minTemps.map(x => (x._1, x._3.toFloat))
val minTempsByStation = stationTemps.reduceByKey( (x, y) => min(x,y))


val results = minTempsByStation.collect()

for(result <- results.sorted){
    val station = result._1
    val temp = result._2
    val formattedTemp = f"$temp$.2f F"
    println(s"$station minimum temperature: $formattedTemp")
}
```



# SparkSession vs SparkContext
* prior to Spark 2.0.0., the main connection objects were SparkContxt, SqlContxt, and HiveContext
* SparkSession object encapsulates the SparkContext object
    - for operations such as reading/creating Datasets, use the SparkSession object




## map vs flatmap

