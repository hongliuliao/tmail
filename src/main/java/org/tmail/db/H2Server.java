/**
 * 
 */
package org.tmail.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.h2.tools.Server;

/**
 * If want see data from browser, start it!
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午3:17:46
 */
public class H2Server {
	
	private static Log log = LogFactory.getLog(H2Server.class);
	
	private static int WEB_PORT = 8082;
	
	public void start() {
		try {
			Server server = Server.createWebServer(new String[] {"-webPort", WEB_PORT + "", "-webAllowOthers"});
			server.start();
			log.info("H2 server starting...");
		} catch (Exception e) {
			log.error("Start server error!", e);
		}
	}
	
	public static void main(String[] args) {
		H2Server h2Server = new H2Server();
		h2Server.start();
	}
	
}
