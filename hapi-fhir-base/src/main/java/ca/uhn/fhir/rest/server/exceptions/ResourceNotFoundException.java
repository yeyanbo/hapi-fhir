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

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu.composite.IdentifierDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.server.Constants;

/**
 * Represents an <b>HTTP 404 Resource Not Found</b> response, which means that 
 * the request is pointing to a resource that does not exist. 
 */
public class ResourceNotFoundException extends BaseServerResponseException {

	public static final int STATUS_CODE = Constants.STATUS_HTTP_404_NOT_FOUND;

	public ResourceNotFoundException(IdDt theId) {
		super(STATUS_CODE, "Resource " + (theId != null ? theId.getValue() : "") + " is not known");
	}

	public ResourceNotFoundException(Class<? extends IResource> theClass, IdentifierDt thePatientId) {
		super(STATUS_CODE, "Resource of type " + theClass.getSimpleName() + " with ID " + thePatientId + " is not known");
	}

	public ResourceNotFoundException(Class<? extends IResource> theClass, IdDt thePatientId) {
		super(STATUS_CODE, "Resource of type " + theClass.getSimpleName() + " with ID " + thePatientId + " is not known");
	}

	public ResourceNotFoundException(String theMessage) {
		super(STATUS_CODE, theMessage);
	}

	private static final long serialVersionUID = 1L;

}
