package ca.uhn.fhir.context;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ca.uhn.fhir.model.api.ICompositeDatatype;
import ca.uhn.fhir.model.api.IElement;
import ca.uhn.fhir.model.api.annotation.DatatypeDef;

public class RuntimeExtensionDtDefinition extends RuntimeCompositeDatatypeDefinition {

	private List<BaseRuntimeChildDefinition> myChildren;

	public RuntimeExtensionDtDefinition(DatatypeDef theDef, Class<? extends ICompositeDatatype> theImplementingClass) {
		super(theDef, theImplementingClass);
	}

	@Override
	public List<BaseRuntimeChildDefinition> getChildren() {
		return myChildren;
	}

	@Override
	public void sealAndInitialize(Map<Class<? extends IElement>, BaseRuntimeElementDefinition<?>> theClassToElementDefinitions) {
		super.sealAndInitialize(theClassToElementDefinitions);
		
		/*
		 * The "url" child is a weird child because it is not parsed and encoded in the normal way,
		 * so we exclude it here 
		 */
		
		List<BaseRuntimeChildDefinition> superChildren = super.getChildren();
		ArrayList<BaseRuntimeChildDefinition> children = new ArrayList<BaseRuntimeChildDefinition>();
		for (BaseRuntimeChildDefinition baseRuntimeChildDefinition : superChildren) {
			if (baseRuntimeChildDefinition.getValidChildNames().contains("url")) {
				continue;
			}
			children.add(baseRuntimeChildDefinition);
		}
		
		myChildren = Collections.unmodifiableList(children);
	}

}
