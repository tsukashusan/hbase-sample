package example

import org.apache.hadoop.hbase.{TableName,HBaseConfiguration,HTableDescriptor,HColumnDescriptor}
import org.apache.hadoop.hbase.client.ConnectionFactory

object Hello extends Greeting with App {
  hbaseTest(args)
  def hbaseTest(parameter:Array[String]){
    val quorum = parameter.mkString(",")
    println("quorum:%s".format(quorum))
    println("Job Start!")
    val tableName = TableName.valueOf("stock-prices");
    val conf = HBaseConfiguration.create
    conf.set("hbase.zookeeper.quorum", quorum)
    conf.set("zookeeper.znode.parent", "/hbase-unsecure")
    val conn = ConnectionFactory.createConnection(conf)
    println(conf)
    val admin = conn.getAdmin
    val tableNames = admin.listTableNames
    tableNames foreach { (t) =>{ 
        if(t.compareTo(tableName) == 0){
          admin.disableTable(t)
          admin.deleteTable(t)
          println("table %s deleted".format(t.getNameAsString))
        }
      }
    }
    if (!admin.tableExists(tableName)) {
       val table = new HTableDescriptor(tableName)
       table.addFamily(new HColumnDescriptor("id"))
       admin.createTable(table)
       println("table %s created!".format(tableName.getNameAsString))
       admin.disableTable(tableName)
       println("table %s disabled!".format(tableName.getNameAsString))
       admin.deleteTable(tableName)
       println("table %s deleted!".format(tableName.getNameAsString))
    }
    println("Job Finish!")
  }
  def runMain(params:List[String]): Unit = {
      params foreach println
  }
}
trait Greeting {
  lazy val greeting: String = "hello"
}
