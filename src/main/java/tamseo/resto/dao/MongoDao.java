package tamseo.resto.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import tamseo.utils.Utilities;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;

public class MongoDao {

  private static Logger log = Logger.getLogger("MongoDao");
  private final MongoClient mongoClient = getMongoClient();
  
  private MongoClient getMongoClient( ) {
    
    URI uri;
    String configString="";
    try {
      uri = getClass().getResource("/db_config.json").toURI();
      configString = new String(Files.readAllBytes(Paths.get(uri)));
    } catch (URISyntaxException|IOException e) {
      log.log(Level.SEVERE,e.getMessage());
    }

    JsonObject config = new JsonObject(configString);

    return MongoClient.createShared(Vertx.vertx(), config);
  }

  protected void insertDocument(String schema, RoutingContext routingContext,
      Vertx vertx) {
    HttpServerResponse response = routingContext.response();
    JsonObject table = routingContext.getBodyAsJson();
    if (table == null) {
      Utilities.sendError(400, response);
    } else {
      mongoClient.insert(schema, table, res -> {
        if (res.succeeded()) {
          response.setStatusCode(201);
        } else {
          res.cause().printStackTrace();
          log.log(Level.INFO,res.cause().getMessage());
        }
      });
      response.end();
    }
  }

  protected void findDocuments(String schema, RoutingContext routingContext,
      Vertx vertx, JsonObject query) {

    mongoClient.find(schema, query, res -> {
      if (res.succeeded()) {
        JsonArray tables = new JsonArray();
        res.result().forEach(tables::add);
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json").end(tables.encodePrettily());
      } else {
        res.cause().printStackTrace();
        log.log(Level.INFO,res.cause().getMessage());
      }
    });
  }


}
