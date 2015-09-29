package tamseo.resto.dao;

import tamseo.utils.Utilities;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class TableDao extends MongoDao {

  private static final String TABLE = "table";

  public void getTableById(RoutingContext routingContext, Vertx vertx) {

    String tableId = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();

    if (tableId == null) {
      Utilities.sendError(400, response);
    } else {
      JsonObject query = new JsonObject();
      query.put("_id", "ObjectId(\"" + tableId + "\")");
      findDocuments(TABLE, routingContext, vertx, query);
    }
  }

  public void getTablesBySite(RoutingContext routingContext, Vertx vertx) {

    String siteID = routingContext.request().getParam("id");
    HttpServerResponse response = routingContext.response();

    if (siteID == null) {
      Utilities.sendError(400, response);
    } else {
      JsonObject query = new JsonObject();
      query.put("site_id", siteID);
      findDocuments(TABLE, routingContext, vertx, query);
    }
  }

  public void addTable(RoutingContext routingContext, Vertx vertx) {
    insertDocument(TABLE, routingContext, vertx);
  }

  public void listTables(RoutingContext routingContext, Vertx vertx) {
    findDocuments(TABLE, routingContext, vertx, new JsonObject());
  }


}
