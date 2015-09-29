package tamseo.resto.services.impl;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import tamseo.resto.dao.TableDao;
import tamseo.resto.services.RestoServiceFactory;
import tamseo.resto.services.TablesService;

public class TablesServiceImpl implements TablesService {

  // TODO: Inject tableDao
  private TableDao tableDao = new TableDao();

  @Override
  public void startTableServer(Vertx vertx, Router router) {

    router.get(RestoServiceFactory.API_URL + "/table/site/:id").handler(routingContext -> {
      tableDao.getTablesBySite(routingContext, vertx);
    });
    router.post(RestoServiceFactory.API_URL + "/table/").handler(routingContext -> {
      tableDao.addTable(routingContext, vertx);
    });
    router.get(RestoServiceFactory.API_URL + "/tables").handler(routingContext -> {
      tableDao.listTables(routingContext, vertx);
    });
    router.get(RestoServiceFactory.API_URL + "/table/:id").handler(routingContext -> {
      tableDao.getTableById(routingContext, vertx);
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }

}
