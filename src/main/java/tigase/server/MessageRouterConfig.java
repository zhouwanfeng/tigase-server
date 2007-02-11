/*  Package Jabber Server
 *  Copyright (C) 2001, 2002, 2003, 2004, 2005
 *  "Artur Hefczyc" <artur.hefczyc@tigase.org>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * $Rev$
 * Last modified by $Author$
 * $Date$
 */
package tigase.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import tigase.util.DNSResolver;

/**
 * Describe class MessageRouterConfig here.
 *
 *
 * Created: Fri Jan  6 14:54:21 2006
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev$
 */
public class MessageRouterConfig {

  private static final Logger log =
    Logger.getLogger("tigase.server.MessageRouterConfig");

  public static final String LOCAL_ADDRESSES_PROP_KEY = "hostnames";
  public static String[] LOCAL_ADDRESSES_PROP_VALUE =	{"localhost", "hostname"};

	public static final String MSG_RECEIVERS_PROP_KEY =
		"components/msg-receivers/";
	public static final String MSG_RECEIVERS_NAMES_PROP_KEY =
		MSG_RECEIVERS_PROP_KEY + "id-names";
	public static final String[] ALL_MSG_RECEIVERS_NAMES_PROP_VAL =
	{	"client_1", "server_1", "comp_1", "session_1"	};
	public static final String[] DEF_MSG_RECEIVERS_NAMES_PROP_VAL =
	{	"client_1", "server_1", "session_1"	};
	public static final String[] SM_MSG_RECEIVERS_NAMES_PROP_VAL =
	{	"comp_1", "session_1"	};
	public static final String[] CS_MSG_RECEIVERS_NAMES_PROP_VAL =
	{	"client_1", "server_1", "comp_1" };

	public static final Map<String, String> MSG_RCV_CLASSES =
		new HashMap<String, String>();

	static {
		MSG_RCV_CLASSES.put("client_1",
			"tigase.server.xmppclient.ClientConnectionManager");
		MSG_RCV_CLASSES.put("server_1",
			"tigase.server.xmppserver.ServerConnectionManager");
		MSG_RCV_CLASSES.put("comp_1",
			"tigase.server.xmppcomponent.ComponentConnectionManager");
		MSG_RCV_CLASSES.put("session_1",
			"tigase.server.xmppsession.SessionManager");
	}

// 	public static final String CLIENT_1_CLASS_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "client_1.class";
// 	public static final String CLIENT_1_CLASS_PROP_VAL =
// 		"tigase.server.xmppclient.ClientConnectionManager";
// 	public static final String CLIENT_1_ACTIVE_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "client_1.active";
// 	public static final boolean CLIENT_1_ACTIVE_PROP_VAL = true;

// 	public static final String SERVER_1_CLASS_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "server_1.class";
// 	public static final String SERVER_1_CLASS_PROP_VAL =
// 		"tigase.server.xmppserver.ServerConnectionManager";
// 	public static final String SERVER_1_ACTIVE_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "server_1.active";
// 	public static final boolean SERVER_1_ACTIVE_PROP_VAL = true;

// 	public static final String COMPONENT_1_CLASS_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "comp_1.class";
// 	public static final String COMPONENT_1_CLASS_PROP_VAL =
// 		"tigase.server.xmppcomponent.ComponentConnectionManager";
// 	public static final String COMPONENT_1_ACTIVE_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "comp_1.active";
// 	public static final boolean COMPONENT_1_ACTIVE_PROP_VAL = true;

// 	public static final String SESSION_1_CLASS_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "session_1.class";
// 	public static final String SESSION_1_CLASS_PROP_VAL =
// 		"tigase.server.xmppsession.SessionManager";
// 	public static final String SESSION_1_ACTIVE_PROP_KEY =
// 		MSG_RECEIVERS_PROP_KEY + "session_1.active";
// 	public static final boolean SESSION_1_ACTIVE_PROP_VAL = true;

	public static final String REGISTRATOR_PROP_KEY = "components/registrators/";
	public static final String REGISTRATOR_NAMES_PROP_KEY =
		REGISTRATOR_PROP_KEY + "id-names";
	public static final String[] REGISTRATOR_NAMES_PROP_VAL =
	{
		"stat_1", "service_1"
	};

	public static final String STAT_1_CLASS_PROP_KEY =
		REGISTRATOR_PROP_KEY + "stat_1.class";
	public static final String STAT_1_CLASS_PROP_VAL =
		"tigase.stats.StatisticsCollector";
	public static final String STAT_1_ACTIVE_PROP_KEY =
		REGISTRATOR_PROP_KEY + "stat_1.active";
	public static final boolean STAT_1_ACTIVE_PROP_VAL = true;

	public static final String SERVICE_1_CLASS_PROP_KEY =
		REGISTRATOR_PROP_KEY + "service_1.class";
	public static final String SERVICE_1_CLASS_PROP_VAL =
		"tigase.disco.XMPPServiceCollector";
	public static final String SERVICE_1_ACTIVE_PROP_KEY =
		REGISTRATOR_PROP_KEY + "service_1.active";
	public static final boolean SERVICE_1_ACTIVE_PROP_VAL = true;

	public static void getDefaults(Map<String, Object> defs,
		Map<String, Object> params) {

		String config_type = (String)params.get("config-type");
		String[] rcv_names = DEF_MSG_RECEIVERS_NAMES_PROP_VAL;
		if (config_type.equals("--gen-config-all")) {
			rcv_names = ALL_MSG_RECEIVERS_NAMES_PROP_VAL;
		}
		if (config_type.equals("--gen-config-sm")) {
			rcv_names = SM_MSG_RECEIVERS_NAMES_PROP_VAL;
		}
		if (config_type.equals("--gen-config-cs")) {
			rcv_names = CS_MSG_RECEIVERS_NAMES_PROP_VAL;
		}

		defs.put(MSG_RECEIVERS_NAMES_PROP_KEY, rcv_names);
		for (String name: rcv_names) {
			defs.put(MSG_RECEIVERS_PROP_KEY + name + ".class",
				MSG_RCV_CLASSES.get(name));
			defs.put(MSG_RECEIVERS_PROP_KEY + name + ".active", true);
		}
// 		defs.put(CLIENT_1_CLASS_PROP_KEY, CLIENT_1_CLASS_PROP_VAL);
// 		defs.put(CLIENT_1_ACTIVE_PROP_KEY, CLIENT_1_ACTIVE_PROP_VAL);
// 		defs.put(SERVER_1_CLASS_PROP_KEY, SERVER_1_CLASS_PROP_VAL);
// 		defs.put(SERVER_1_ACTIVE_PROP_KEY, SERVER_1_ACTIVE_PROP_VAL);
// 		defs.put(COMPONENT_1_CLASS_PROP_KEY, COMPONENT_1_CLASS_PROP_VAL);
// 		defs.put(COMPONENT_1_ACTIVE_PROP_KEY, COMPONENT_1_ACTIVE_PROP_VAL);
// 		defs.put(SESSION_1_CLASS_PROP_KEY, SESSION_1_CLASS_PROP_VAL);
// 		defs.put(SESSION_1_ACTIVE_PROP_KEY, SESSION_1_ACTIVE_PROP_VAL);

		defs.put(REGISTRATOR_NAMES_PROP_KEY, REGISTRATOR_NAMES_PROP_VAL);
		defs.put(STAT_1_CLASS_PROP_KEY, STAT_1_CLASS_PROP_VAL);
		defs.put(STAT_1_ACTIVE_PROP_KEY, STAT_1_ACTIVE_PROP_VAL);
		defs.put(SERVICE_1_CLASS_PROP_KEY, SERVICE_1_CLASS_PROP_VAL);
		defs.put(SERVICE_1_ACTIVE_PROP_KEY, SERVICE_1_ACTIVE_PROP_VAL);
		if (params.get("--virt-hosts") != null) {
			LOCAL_ADDRESSES_PROP_VALUE =
				 ((String)params.get("--virt-hosts")).split(",");
		} else {
			LOCAL_ADDRESSES_PROP_VALUE = DNSResolver.getDefHostNames();
		}
    defs.put(LOCAL_ADDRESSES_PROP_KEY, LOCAL_ADDRESSES_PROP_VALUE);
	}

	private Map<String, Object> props = null;

	public MessageRouterConfig(Map<String, Object> props) {
		this.props = props;
	}

	public String[] getRegistrNames() {
		String[] names = (String[])props.get(REGISTRATOR_NAMES_PROP_KEY);
		log.config(Arrays.toString(names));
		ArrayList<String> al = new ArrayList<String>();
		for (String name: names) {
			if ((Boolean)props.get(REGISTRATOR_PROP_KEY + name + ".active")) {
				al.add(name);
			} // end of if ((Boolean)props.get())
		} // end of for (String name: names)
		return al.toArray(new String[al.size()]);
	}

	public String[] getMsgRcvNames() {
		String[] names = (String[])props.get(MSG_RECEIVERS_NAMES_PROP_KEY);
		log.config(Arrays.toString(names));
		ArrayList<String> al = new ArrayList<String>();
		for (String name: names) {
			if ((Boolean)props.get(MSG_RECEIVERS_PROP_KEY + name + ".active")) {
				al.add(name);
			} // end of if ((Boolean)props.get())
		} // end of for (String name: names)
		return al.toArray(new String[al.size()]);
	}

	public ComponentRegistrator getRegistrInstance(String name) throws
		ClassNotFoundException, InstantiationException, IllegalAccessException {

		String cls_name = (String)props.get(REGISTRATOR_PROP_KEY + name + ".class");
		// I changed location for the XMPPServiceCollector class
		// to avoid problems with old configuration files let's detect it here
		// and silently convert it to new package name:
		if (cls_name.equals("tigase.server.XMPPServiceCollector")) {
			log.warning("Old package name for XMPPServiceCollector class, please correct it to new, correct location: tigase.disco.XMPPServiceCollector");
			cls_name = "tigase.disco.XMPPServiceCollector";
		}
		return (ComponentRegistrator)Class.forName(cls_name).newInstance();
	}

	public MessageReceiver getMsgRcvInstance(String name) throws
		ClassNotFoundException, InstantiationException, IllegalAccessException {

		String cls_name = (String)props.get(MSG_RECEIVERS_PROP_KEY + name + ".class");
		return (MessageReceiver)Class.forName(cls_name).newInstance();
	}

} // MessageRouterConfig
