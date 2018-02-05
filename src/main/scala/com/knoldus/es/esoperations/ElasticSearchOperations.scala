package com.knoldus.es.esoperations

import java.lang

import com.knoldus.es.client.ESClient
import com.knoldus.es.request.ESDocument
import io.searchbox.client.JestClient
import io.searchbox.core._
import org.elasticsearch.index.query.QueryBuilders.matchAllQuery
import org.elasticsearch.index.query.{BoolQueryBuilder, MatchAllQueryBuilder, QueryBuilders}
import org.elasticsearch.search.builder.SearchSourceBuilder
import play.api.libs.json.Json


trait ElasticSearchOperations {

  val client: JestClient = ESClient.jestClient

  def insertInES[T](eSDocument: ESDocument[T])(implicit tjs: play.api.libs.json.Writes[T]): DocumentResult = {
    val source = Json.stringify(Json.toJson(eSDocument.obj))

    //creating index in ElasticSearch
    val index: Index = new Index.Builder(source)
      .index(eSDocument.index)
      .`type`(eSDocument.docType)
      .id(eSDocument.docId)
      .build()
    //executing client
    client.execute(index)
  }

  def search(index: String, docType: String): lang.Long = {
    //build query with QueryBuilder
    val searchSourceBuilder: SearchSourceBuilder = new SearchSourceBuilder()
    val query = searchSourceBuilder.query(QueryBuilders.matchAllQuery())
    println("Query:: " + query.toString)
    val search = new Search.Builder(query.toString).addIndex(index).addType(docType).build()
    client.execute(search).getTotal
  }

  def update[T](eSDocument: ESDocument[T], index: String, docType: String)(implicit tjs: play.api.libs.json.Writes[T]) = {
    val source = Json.stringify(Json.toJson(eSDocument.obj))
    val updateQuery = new Update.Builder(source).index(index).`type`(docType).build()
    client.execute(updateQuery)
  }

  def delete(index: String): DocumentResult = {
    val deleteRequest = new Delete.Builder("").index(index).build()
    client.execute(deleteRequest)
  }
}
