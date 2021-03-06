package ca.uhn.fhir.rest.server;

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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Determines the server's base using the incoming request
 */
public class IncomingRequestAddressStrategy implements IServerAddressStrategy {

	@Override
	public String determineServerBase(HttpServletRequest theRequest) {
		String requestFullPath = StringUtils.defaultString(theRequest.getRequestURI());
		String servletPath = StringUtils.defaultString(theRequest.getServletPath());
		StringBuffer requestUrl = theRequest.getRequestURL();
		String servletContextPath = "";
		if (theRequest.getServletContext() != null) {
			servletContextPath = StringUtils.defaultString(theRequest.getServletContext().getContextPath());
			// } else {
			// servletContextPath = servletPath;
		}

		String requestPath = requestFullPath.substring(servletContextPath.length() + servletPath.length());
		if (requestPath.length() > 0 && requestPath.charAt(0) == '/') {
			requestPath = requestPath.substring(1);
		}

		int contextIndex;
		if (servletPath.length() == 0) {
			if (requestPath.length() == 0) {
				contextIndex = requestUrl.length();
			} else {
				contextIndex = requestUrl.indexOf(requestPath);
			}
		} else {
			contextIndex = requestUrl.indexOf(servletPath);
		}

		String fhirServerBase;
		int length = contextIndex + servletPath.length();
		fhirServerBase = requestUrl.substring(0, length);
		return fhirServerBase;
	}

}
