package tamseo.resto.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import tamseo.resto.services.RestoServiceFactory;
import tamseo.utils.Runner;

public class RestoServicesVerticle extends AbstractVerticle {

  protected final Vertx vertx = Vertx.vertx();
  protected final Router router = Router.router(vertx);

  public static void main(String[] args) {
    Runner.runExample(RestoServicesVerticle.class);
  }

  @Override
  public void start() throws Exception {
    RestoServiceFactory.getInstance().getRestoAuthService().startAuthServer(vertx, router);
    RestoServiceFactory.getInstance().getTablesService().startTableServer(vertx, router);
  }
}

