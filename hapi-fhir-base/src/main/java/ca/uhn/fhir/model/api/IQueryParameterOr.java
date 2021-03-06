package ca.uhn.fhir.model.api;

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

import java.util.List;

import ca.uhn.fhir.rest.method.QualifiedParamList;

public interface IQueryParameterOr {

	/**
	 * Sets the value of this type using the <b>token</b> format. This format is used in HTTP queries as a parameter
	 * format.
	 * <p>
	 * See FHIR specification <a href="http://www.hl7.org/implement/standards/fhir/search.html#ptypes">2.2.2 Search
	 * SearchParameter Types</a> for information on the <b>token</b> format
	 * </p>
	 */
	public void setValuesAsQueryTokens(QualifiedParamList theParameters);

	// public void setValuesAsQueryTokens(List<IQueryParameterType> theParameters);

	/**
	 * Returns the value of this type using the <b>token</b> format. This format is used in HTTP queries as a parameter
	 * format.
	 * 
	 * <p>
	 * See FHIR specification <a href="http://www.hl7.org/implement/standards/fhir/search.html#ptypes">2.2.2 Search
	 * SearchParameter Types</a> for information on the <b>token</b> format
	 * </p>
	 */
	public List<IQueryParameterType> getValuesAsQueryTokens();

}
