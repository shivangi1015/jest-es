package com.knoldus.es.esoperations

import com.knoldus.es.client.ESClient
import com.knoldus.es.request.ESDocument
import io.searchbox.client.JestClient
import play.api.libs.json.Json
import io.searchbox.core.{DocumentResult, Index, Search, SearchResult}


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




}
