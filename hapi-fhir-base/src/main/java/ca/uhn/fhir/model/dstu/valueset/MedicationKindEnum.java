
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

public enum MedicationKindEnum {

	/**
	 * Code Value: <b>product</b>
	 *
	 * The medication is a product.
	 */
	PRODUCT("product", "http://hl7.org/fhir/medication-kind"),
	
	/**
	 * Code Value: <b>package</b>
	 *
	 * The medication is a package - a contained group of one of more products.
	 */
	PACKAGE("package", "http://hl7.org/fhir/medication-kind"),
	
	;
	
	/**
	 * Identifier for this Value Set:
	 * http://hl7.org/fhir/vs/medication-kind
	 */
	public static final String VALUESET_IDENTIFIER = "http://hl7.org/fhir/vs/medication-kind";

	/**
	 * Name for this Value Set:
	 * MedicationKind
	 */
	public static final String VALUESET_NAME = "MedicationKind";

	private static Map<String, MedicationKindEnum> CODE_TO_ENUM = new HashMap<String, MedicationKindEnum>();
	private static Map<String, Map<String, MedicationKindEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, MedicationKindEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (MedicationKindEnum next : MedicationKindEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, MedicationKindEnum>());
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
	public MedicationKindEnum forCode(String theCode) {
		MedicationKindEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<MedicationKindEnum> VALUESET_BINDER = new IValueSetEnumBinder<MedicationKindEnum>() {
		@Override
		public String toCodeString(MedicationKindEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(MedicationKindEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public MedicationKindEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public MedicationKindEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, MedicationKindEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	MedicationKindEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
