/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package tamseo;

import tamseo.utils.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.MVELTemplateEngine;

public class TemplatePageService extends AbstractVerticle {

	public static void main(String[] args) {
		Runner.runExample(TemplatePageService.class);
	}

	@Override
	public void start() {

		Router router = Router.router(vertx);

		// Serve the dynamic pages
		router.route("/dynamic/*").handler(
				TemplateHandler.create(MVELTemplateEngine.create()));

		// Serve the static pages
		router.route().handler(StaticHandler.create());

		vertx.createHttpServer().requestHandler(router::accept).listen(8091);
	}
}
