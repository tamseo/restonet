package tamseo.resto.services;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class RestoAuthService {

  public void startAuthServer(Vertx vertx, Router router) throws Exception {

    // Create a JWT Auth Provider
    JWTAuth jwt = JWTAuth.create(vertx, new JsonObject()
        .put("keyStore", new JsonObject()
            .put("type", "jceks")
            .put("path", "keystore.jceks")
            .put("password", "secret")));

    // protect the API
    router.route(RestoServiceFactory.API_URL+ "/*").handler(JWTAuthHandler.create(jwt, "/api/newToken"));

    // this route is excluded from the auth handler
    router.get("/api/newToken").handler(ctx -> {
      ctx.response().putHeader("Content-Type", "text/plain");
      ctx.response().end(jwt.generateToken(new JsonObject(), new JWTOptions().setExpiresInSeconds(6000)));
    });

  }
}

