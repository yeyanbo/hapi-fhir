
package ca.uhn.fhir.model.dstu.valueset;

/*
 * #%L
 * HAPI FHIR Library
 * %%
 * Copyright (C) 2014 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;

import ca.uhn.fhir.model.api.IValueSetEnumBinder;

public enum SecurityEventObjectTypeEnum {

	/**
	 * Code Value: <b>1</b>
	 *
	 * Person.
	 */
	_1("1", "http://hl7.org/fhir/object-type"),
	
	/**
	 * Code Value: <b>2</b>
	 *
	 * System Object.
	 */
	_2("2", "http://hl7.org/fhir/object-type"),
	
	/**
	 * Code Value: <b>3</b>
	 *
	 * Organization.
	 */
	_3("3", "http://hl7.org/fhir/object-type"),
	
	/**
	 * Code Value: <b>4</b>
	 *
	 * Other.
	 */
	_4("4", "http://hl7.org/fhir/object-type"),
	
	;
	
	/**
	 * Identifier for this Value Set:
	 * http://hl7.org/fhir/vs/object-type
	 */
	public static final String VALUESET_IDENTIFIER = "http://hl7.org/fhir/vs/object-type";

	/**
	 * Name for this Value Set:
	 * SecurityEventObjectType
	 */
	public static final String VALUESET_NAME = "SecurityEventObjectType";

	private static Map<String, SecurityEventObjectTypeEnum> CODE_TO_ENUM = new HashMap<String, SecurityEventObjectTypeEnum>();
	private static Map<String, Map<String, SecurityEventObjectTypeEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, SecurityEventObjectTypeEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (SecurityEventObjectTypeEnum next : SecurityEventObjectTypeEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, SecurityEventObjectTypeEnum>());
			}
			SYSTEM_TO_CODE_TO_ENUM.get(next.getSystem()).put(next.getCode(), next);			
		}
	}
	
	/**
	 * Returns the code associated with this enumerated value
	 */
	public String getCode() {
		return myCode;
	}
	
	/**
	 * Returns the code system associated with this enumerated value
	 */
	public String getSystem() {
		return mySystem;
	}
	
	/**
	 * Returns the enumerated value associated with this code
	 */
	public SecurityEventObjectTypeEnum forCode(String theCode) {
		SecurityEventObjectTypeEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<SecurityEventObjectTypeEnum> VALUESET_BINDER = new IValueSetEnumBinder<SecurityEventObjectTypeEnum>() {
		@Override
		public String toCodeString(SecurityEventObjectTypeEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(SecurityEventObjectTypeEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public SecurityEventObjectTypeEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public SecurityEventObjectTypeEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, SecurityEventObjectTypeEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	SecurityEventObjectTypeEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
