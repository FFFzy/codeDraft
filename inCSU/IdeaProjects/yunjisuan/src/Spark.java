public class Spark {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("pagerank").setMaster("local")
        val sc = new SparkContext(conf)
        val links = sc.parallelize(Array(
                ("A",List("E","B","C")),
                ("B",List("A","D","C")),
                ("C",List("F","B","A")),
                ("D",List("E","C"))
                ))
        var rank = links.mapValues(v=>1.0)

        for(i<- 0 to 10){
            val contribs = links.join(rank).flatMap{
                case(url,(links,rank))=>
                links.map(dest=>(dest,rank/links.size))
            }
            rank = contribs.reduceByKey(_+_)
                            .mapValues(v=>0.15+0.85*v)
        }
        rank.saveAsTextFile("output.txt")
    }
}
