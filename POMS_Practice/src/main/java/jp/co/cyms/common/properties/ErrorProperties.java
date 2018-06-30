package jp.co.cyms.common.properties;

import java.io.IOException;
import java.util.Properties;

import com.opensymphony.xwork2.util.logging.LoggerFactory;

import jp.co.cyms.common.SystemCommon;

public class ErrorProperties {
	protected static com.opensymphony.xwork2.util.logging.Logger LOG = LoggerFactory.getLogger(ErrorProperties.class);

	public static enum ExceptionKey {
		/**
		 * example: code java ErrorProperties.get(ExceptionKey.EA_0001) content in file
		 * error.properties: EA-0001=Invalid value
		 */
		// Login - logout
		CA_0001,
		EA_0009,
		EA_0010,
		EA_0011,
		EA_0012,
		// Item master
		EB_0001,
		EB_0002,
		EB_0003,
		EB_0004,
		EB_0005,
		EB_0006,
		EB_0007,
		CA_0002,
		
		// Item master 2
		IM2_0001,
		IM2_0002,
		IM2_0003,
		IM2_0004,
		IM2_0005,
		IM2_0006,
		IM2_0007,
		IM2_0008,
		IM2_0009,
		IM2_0010,
		IM2_0011,
		IM2_0012,
		
		// Item master
		IM_0001,
		IM_0002,
		
		// Stock Management
		CM_0000,
		CM_0001,
		CM_0002,
		CM_0003,
		CM_0004,
		CM_0005,
		CM_0006,
		CM_0007,
		CM_0008,
		CM_0009,
		CM_0010,
		CM_0011,
		CM_0012,
		CM_0013,
		CM_0014,
		CM_0015,
		CM_0016,
		CM_0017,
		
		// PC Order Entry
		PCOD_0001,
		PCOD_0002,
		PCOD_0003,
		PCOD_0004,
		PCOD_0005,
		PCOD_0006,
		PCOD_0007,
		PCOD_0008,
		PCOD_0009,
		PCOD_0010,
		PCOD_0011,
		PCOD_0012,
		PCOD_0013,
		PCOD_0014,
		PCOD_0015,
		PCOD_0016,
		PCOD_0017,
		PCOD_0018,
		PCOD_0019,
		
		// Active item master
		IM3_0001,
		
		// PC order progress
		OP_0001,
		OP_0002,
		OP_0003,
		OP_0004,
		OP_0005,
		OP_0006,
		OP_0007,
		OP_0008,
		OP_0009,
		OP_0010,
		OP_0011,
		
		// Order Log
		OL_0001,
		
		// Delivery Order Entry/Update
		DO_0001,
		DO_0002,
		DO_0003,
		DO_0004,
		DO_0005,
		DO_0006,
		
		// KDDI Lease Information Entry
		LIE_0001,
		LIE_0002,
		LIE_0003,
		
		//Cuong 
		CUONG_0001,
		;

		public static ExceptionKey get(String key) {
			ExceptionKey ret = null;
			try {
				ret = valueOf(key);
			} catch (IllegalArgumentException e) {
				LOG.error("Error key:", e);
			}
			return ret;
		}
	}

	public static String get(ExceptionKey key) throws IOException {
		SystemCommon system = new SystemCommon();
		Properties messageProp = system.getProperties("/properties/error.properties");
		return messageProp.getProperty(key.toString().replace("_", "-"));
	}
}
