package ca.uhn.fhir.rest.server.exceptions;

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

/**
 * Exception for use when a response is received or being sent that
 * does not correspond to any other exception type. An HTTP status code
 * must be provided, and will be provided to the caller in the case of a 
 * server implementation.
 */
public class UnclassifiedServerFailureException extends BaseServerResponseException {

	public UnclassifiedServerFailureException(int theStatusCode, String theMessage) {
		super(theStatusCode, theMessage);
	}

	private static final long serialVersionUID = 1L;

}
