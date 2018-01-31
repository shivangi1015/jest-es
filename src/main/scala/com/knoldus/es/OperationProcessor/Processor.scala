package com.knoldus.es.OperationProcessor

import com.knoldus.es.esoperations.ElasticSearchOperations
import com.knoldus.es.request.ESDocument
import com.knoldus.es.request.Employee

object Processor extends ElasticSearchOperations with App {

  val emp1 = Employee("Shivangi" , "opay", "1")
  val emp2 = Employee("Divya" , "opay", "2")
  val emp3 = Employee("Akhil" , "opay", "3")
  val emp4 = Employee("Akhil" , "opay", "4")
  val eSDocument1 = ESDocument[Employee]("company", "employee", emp1.id, emp1)
  val eSDocument2 = ESDocument[Employee]("company", "employee", emp2.id, emp3)
  val eSDocument3 = ESDocument[Employee]("company", "employee", emp3.id, emp3)
  val eSDocument4 = ESDocument[Employee]("company", "employee", emp4.id, emp4)
  val result1 = insertInES(eSDocument1)
  val result2 = insertInES(eSDocument2)
  val result3 = insertInES(eSDocument3)
  val result4 = insertInES(eSDocument4)
  println(s"\n Result is: $result1\n$result2\n$result3\n$result4")

}
