package perforce;


import java.net.URISyntaxException;
import java.util.Properties;

import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.p4java.server.IServer;
import com.perforce.p4java.server.IOptionsServer;
import com.perforce.p4java.server.ServerFactory;
import com.perforce.p4java.option.UsageOptions;

/**
* Provides simple superclass support for the p4java demo classes, including
* a standard method to get a new IServer object.<p>
* 
* The demo apps using this class rely on the system properties defined
* below for customizing runtime behavior; consult the individual field
* documentation for usage and default values.
*/

public abstract class P4JavaDemo {
	
	/**
	 * The server URI to be used for the demo run. This is retrieved
	 * from the current value of the system property com.perforce.p4javademo.serverUri;
	 * if no such system property has been set, the default used is
	 * p4java://localhost:1666.
	 */
	protected static String serverUri = System.getProperty(
											"perforce.server.uri",
											"p4java://blr-perforcerep.corp.yodlee.com:16060"); 

	/**
	 * The user name to be used for the demo run. This is retrieved
	 * from the current value of the system property com.perforce.p4javademo.userName;
	 * if no such system property has been set, the default used is
	 * "P4javaDemoUser".
	 */
	protected static String userName = System.getProperty(
											"perforce.server.user",
											"readonly");
	
	/**
	 * The Perforce client name to be used for the demo run. This is retrieved
	 * from the current value of the system property com.perforce.p4javademo.clientName;
	 * if no such system property has been set, the default used is
	 * "p4javademo".
	 */
	protected static String clientName = System.getProperty(
											"perforce.server.client",
											"readonly");
	
	/**
	 * The Perforce user password to be used for the demo run. This is retrieved
	 * from the current value of the system property com.perforce.p4javademo.password;
	 * if no such system property has been set, the default used is
	 * "none" (which is not the same as not having a password).
	 */
	protected static String password = System.getProperty(
											"perforce.server.password",
											"none");

	/**
	 * Get an IServer object from the P4Java server factory and connect to it.
	 * The URI passed to the server factory is cobbled together from the system
	 * property com.perforce.p4javademo.serverUri; if the property is not set,
	 * the URI defaults to p4java://localhost:1666.
	 * 
	 * @param props if not null, P4Java properties object to pass to the P4Java
	 * 				server factory.
	 * @return connected IServer object ready for use.
	 * @throws P4JavaException thrown if the server factory or the connection method
	 * 				detect any errors.
	 * @throws URISyntaxException thrown if the server URI passed to the server
	 * 				factory is syntactically invalid
	 */
	protected static IServer getServer(Properties props) throws P4JavaException, URISyntaxException {
		IServer server = ServerFactory.getServer(serverUri, props);
		if (server != null) {
			server.connect();
		}
		return server;
	}
	
	/**
	 * Get an IOptionsServer object from the P4Java server factory and connect to it.
	 * The URI passed to the server factory is cobbled together from the system
	 * property com.perforce.p4javademo.serverUri; if the property is not set,
	 * the URI defaults to p4java://localhost:1666.
	 * 
	 * @param props if not null, P4Java properties object to pass to the P4Java
	 * 				server factory.
	 * @param opts if not null, P4Java UsageOptions object to pass to the P4Java
	 * 				server factory.
	 * @return connected IServer object ready for use.
	 * @throws P4JavaException thrown if the server factory or the connection method
	 * 				detect any errors.
	 * @throws URISyntaxException thrown if the server URI passed to the server
	 * 				factory is syntactically invalid
	 */
	protected static IOptionsServer getOptionsServer(Properties props, UsageOptions opts) throws P4JavaException, URISyntaxException {
		IOptionsServer server = ServerFactory.getOptionsServer(serverUri, props, opts);
		if (server != null) {
			server.connect();
		}
		return server;
	}
	
	protected static String formatFileSpec(IFileSpec fileSpec) {
		return fileSpec.getDepotPathString();
	}
}
