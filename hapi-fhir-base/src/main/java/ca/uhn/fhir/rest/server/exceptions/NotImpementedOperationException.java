package ca.uhn.fhir.rest.server.exceptions;
import ca.uhn.fhir.rest.server.Constants;

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
 * Represents an <b>HTTP 400 Bad Request</b> response.
 * This status indicates that the resource provider currently lacks the ability 
 * to fullfill the request. There is a good change that the functionality will 
 * be added in the future
 *  
 * This Represents an <b>HTTP 501 Not Implemented</b> response, which means the resource provider currently lacks the ability to fullfill the request.
 *  
 * <p>
 * Note that a complete list of RESTful exceptions is available in the
 * <a href="./package-summary.html">Package Summary</a>.
 * </p> 
 * 
 * 
 */
public class NotImpementedOperationException extends BaseServerResponseException {

public static final int STATUS_CODE = Constants.STATUS_HTTP_501_NOT_IMPLEMENTED;
    private static final long serialVersionUID = 1L;

    public NotImpementedOperationException(String theMessage) {
        super(STATUS_CODE, theMessage);
    }
}
