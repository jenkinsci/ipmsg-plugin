package hudson.plugins.ipmsg;

import hudson.Plugin;

/**
 * Entry point of a plugin.
 *
 * <p>
 * See javadoc of {@link Plugin} for more about what can be done on this class.
 *
 * @author Toyokazu Ohara
 */
public class PluginImpl extends Plugin {
    public void start() throws Exception {
		MsgClient.login();
    }

    public void stop() throws Exception {
        MsgClient.logout();
    }
}

