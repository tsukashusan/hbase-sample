package example

import org.apache.hadoop.hbase.{TableName,HBaseConfiguration}
import org.apache.hadoop.hbase.client.ConnectionFactory

object Hello extends Greeting with App {
  println(greeting)
  //hbaseTest
  def hbaseTest(){
      println("Job Start!")
      val tableName = TableName.valueOf("stock-prices");
      val conf = HBaseConfiguration.create
      conf.clear
      conf.set("hbase.zookeeper.property.clientPort", "2181")
      conf.set("hbase.zookeeper.quorum", "zk2-hbase.2risndr15ibellqxn2wi3yjyha.lx.internal.cloudapp.net,zk3-hbase.2risndr15ibellqxn2wi3yjyha.lx.internal.cloudapp.net,zk4-hbase.2risndr15ibellqxn2wi3yjyha.lx.internal.cloudapp.net")
      conf.set("zookeeper.znode.parent", "/hbase-unsecure")
      conf.set("hbase.cluster.distributed", "true")
      val conn = ConnectionFactory.createConnection(conf)
      println(conf)
      val admin = conn.getAdmin()
      if (!admin.tableExists(tableName)) {
         println(conf)
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
