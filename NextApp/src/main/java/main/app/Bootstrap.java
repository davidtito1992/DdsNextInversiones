package main.app;

import redis.clients.jedis.Jedis;

/*** Ejecutar antes de levantar el servidor por primera vez ****/

public class Bootstrap {

	public static void main(String[] args) throws Exception {

		new Bootstrap().run();
	}

	public void run() throws Exception {
		
		@SuppressWarnings("resource")
		Jedis jedisCache = new Jedis();
		AppData appData = new AppData();

		appData.cargarUsuarios();
		appData.cargarEmpresas();
		appData.cargarIndicadores();
		appData.cargarMetodologias();
		jedisCache.flushAll();
		appData.precalcularTodosLosIndicadores();
		
		System.out.println("TERMINE!!!!");
	}

}
