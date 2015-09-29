package tamseo;

import tamseo.utils.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class HelloWorldService extends AbstractVerticle {

	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		Runner.runClusteredExample(HelloWorldService.class);
	}

	@Override
	public void start() throws Exception {

		// Create mongo service
		JsonObject config = new JsonObject();
		config.put("host", "127.0.0.1");
		config.put("port", 27017);
		config.put("username", "admin");
		config.put("password", "admin");
		config.put("authSource", "admin");
		config.put("db_name", "restonet");

		DeploymentOptions options = new DeploymentOptions().setConfig(config);

		vertx.deployVerticle("service:io.vertx:vertx-mongo-service", options,
				res -> {

					if (res.succeeded()) {
						// Deployed ok
						System.out.println("OK");
			} else {
				// Failed to deploy
			}

		});

	}
}
