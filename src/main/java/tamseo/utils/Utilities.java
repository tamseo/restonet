package tamseo.utils;

import io.vertx.core.http.HttpServerResponse;

public class Utilities {

  public static void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }
  
}
