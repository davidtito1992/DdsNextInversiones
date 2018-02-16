package main.app;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {

	JedisPool pool;
	Jedis jedis;

	public Jedis getConexionDirecta() {
		jedis = new Jedis("localhost");
		return jedis;
	}

	public void cerrarConexionDirecta() {
		if (jedis != null) {
			jedis.close();
		}
	}

	public Jedis getConexion() {
		pool = new JedisPool(new JedisPoolConfig(), "localhost");
		jedis = pool.getResource();
		return jedis;
	}

	public void destruirPool() {

		if (jedis != null) {
			jedis.close();
		}

		if (pool != null) {
			pool.destroy();
		}
	}
}
