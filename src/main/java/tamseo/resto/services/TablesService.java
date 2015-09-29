package tamseo.resto.services;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public interface TablesService {

  public void startTableServer(Vertx vertx, Router router);
  
}
