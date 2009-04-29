package hudson.plugins.ipmsg;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class offer you to simple access to IPMessenger client .
 *
 * @author Toyokazu Ohara
 */
public class MsgClient {
    private static Messenger messenger;
    private static boolean isInit = false;
    /**
     * login.
     */
    public synchronized static void login() {
    	if (isInit) {
            LOGGER.log(Level.INFO, "Already Inited");
            logout();
    	}
        try {
        	messenger = new Messenger();
			messenger.login();
	        messenger.start();
        	isInit = true;
		} catch (IOException e) {
            LOGGER.log(Level.INFO, "Failed to login", e);
		}
    }
    public synchronized static void logout() {
    	if (!isInit) {
            LOGGER.log(Level.INFO, "Already logged out");
            return;
    	}
        try {
            messenger.logout();
        	isInit = false;
		} catch (IOException e) {
            LOGGER.log(Level.INFO, "Failed to logout", e);
		}
    }

    public synchronized static void sendAll(final String message) {
    	if (!isInit) {
            LOGGER.log(Level.INFO, "Not log-in");
            return;
    	}
        try {
        	messenger.sendMsgAll(message, true);
		} catch (IOException e) {
            LOGGER.log(Level.INFO, "Failed to send", e);
		}
    }
    private static final Logger LOGGER = Logger.getLogger(MsgClient.class.getName());
}
