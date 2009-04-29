package hudson.plugins.ipmsg;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import hudson.plugins.ipmsg.tk.ipmsg.IPMessenger;

/**
 * Implementation class of IPMessenger.
 *
 * @author Toyokazu Ohara
 */
public class Messenger extends IPMessenger {
    private List<String> hostNames = new ArrayList<String>();
    public Messenger() throws IOException {
        this.userName    = IPMsgPublisher.descriptor().getUserName();
        this.nickName    = IPMsgPublisher.descriptor().getNickName();
        this.group       = IPMsgPublisher.descriptor().getGroup();
        this.hostName    = InetAddress.getLocalHost().getHostName();
        this.absenceMode = false;
        this.absenceMsg  = "";
        this.socket      = new DatagramSocket(IPMsgPublisher.descriptor().getPort());
        this.in_port     = IPMsgPublisher.descriptor().getPort();
        this.debug       = false;
    }
    
    public void sendMsgAll(final String msg, final boolean secret) throws IOException {
        for (String host : hostNames) {
            sendMsg(host, msg, secret);
        }
    }

    @Override
    public void addMember(String host, String nickName, String group,
            String addr, int absence) {
        hostNames.add(host);
    }

    @Override
    public void openMsg(String host, String user) {
    }

    @Override
    public void receiveMsg(String host, String user, String msg, boolean lock) {
    }

    @Override
    public void removeMember(String host) {
        hostNames.remove(host);
    }
    
    @Override
    public void logout() throws IOException {
    	super.logout();
    }
    private static final Logger LOGGER = Logger.getLogger(Messenger.class.getName());

}
