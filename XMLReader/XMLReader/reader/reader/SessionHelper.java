package reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SessionHelper {

	/**
	 * Types and the correspondence in the XML file
	 */
	private static final String ALL = "all";
	private static final String NOTCCO = "NotCCO";
	private static final String CCO = "CCO";
	
	/**
	 * States and the correspondence in the XML file
	 */
	private static final String STOPPING = "STOPPING";
	private static final String IN_PREPARATION = "IN_PREPARATION";
	private static final String RUNNING = "RUNNING";
	private static final String ITERATING = "ITERATING";
	private static final String PAUSED = "PAUSED";
	private static final String STOPPED = "STOPPED";
	private static final String WAITING_DECISION = "WAITING_DECISION";
	private static final String NONE = "NONE";
	
	/**
	 * Is admin constants, correspondence with XML file
	 */
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	
	public static final List<String> types = Collections.unmodifiableList(Arrays.asList(ALL, NOTCCO, CCO));
	public static final List<String> states = Collections.unmodifiableList(Arrays.asList(STOPPING, IN_PREPARATION, RUNNING, ITERATING,
			PAUSED, STOPPED, WAITING_DECISION, NONE));
	public static final List<String> isAdmins = Collections.unmodifiableList(Arrays.asList(TRUE, FALSE));
	
	private String type, state, isAdmin;
	
	public SessionHelper(String type, String state, String isAdmin) {
		/* if it is CCO: all + CCO
		 * not CCO: all + notCCO
		 */
		if (!type.equals(CCO)){
			this.type = NOTCCO;
		}else{
			this.type = CCO;
		}
		this.state = state;
		this.isAdmin = isAdmin;
	}

	public String getIsAdmin() {
		return isAdmin;
	}
	
	public String getState() {
		return state;
	}
	
	public String getType() {
		return type;
	}
	

}
