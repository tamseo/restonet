package tamseo;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import tamseo.utils.Runner;

public class RealtimeService extends AbstractVerticle {

  private static final String MARKET_PRICE = "market-price";
  double rPrice = 100;

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(RealtimeService.class);
  }

  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);

    // Allow outbound traffic to the news-feed address

    BridgeOptions options =
        new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress(MARKET_PRICE));

    router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

    // Serve the static resources
    router.route().handler(StaticHandler.create());

    vertx.createHttpServer().requestHandler(router::accept).listen(8081);

    vertx.setPeriodic(1000, t -> {
      rPrice = 100 + Math.random() * 5;
      vertx.eventBus().publish(MARKET_PRICE, rPrice + " JPY");
    });
  }
}
