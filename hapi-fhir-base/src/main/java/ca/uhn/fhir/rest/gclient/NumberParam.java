package ca.uhn.fhir.rest.gclient;

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
 * Token parameter type for use in fluent client interfaces
 */
public class NumberParam implements IParam {

	private String myParamName;

	public NumberParam(String theParamName) {
		myParamName = theParamName;
	}

	public IMatches<ICriterion> exactly() {
		return new IMatches<ICriterion>() {
			@Override
			public ICriterion number(long theNumber) {
				return new StringCriterion(getParamName(), Long.toString(theNumber));
			}

			@Override
			public ICriterion number(String theNumber) {
				return new StringCriterion(getParamName(), (theNumber));
			}
		};
	}

	@Override
	public String getParamName() {
		return myParamName;
	}

	public IMatches<ICriterion> greaterThan() {
		return new IMatches<ICriterion>() {
			@Override
			public ICriterion number(long theNumber) {
				return new StringCriterion(getParamName(), ">" + Long.toString(theNumber));
			}

			@Override
			public ICriterion number(String theNumber) {
				return new StringCriterion(getParamName(), ">" + (theNumber));
			}
		};
	}

	public IMatches<ICriterion> greaterThanOrEqual() {
		return new IMatches<ICriterion>() {
			@Override
			public ICriterion number(long theNumber) {
				return new StringCriterion(getParamName(), ">=" + Long.toString(theNumber));
			}

			@Override
			public ICriterion number(String theNumber) {
				return new StringCriterion(getParamName(), ">=" + (theNumber));
			}
		};
	}

	public IMatches<ICriterion> lessThan() {
		return new IMatches<ICriterion>() {
			@Override
			public ICriterion number(long theNumber) {
				return new StringCriterion(getParamName(), "<" + Long.toString(theNumber));
			}

			@Override
			public ICriterion number(String theNumber) {
				return new StringCriterion(getParamName(), "<" + (theNumber));
			}
		};
	}

	public IMatches<ICriterion> lessThanOrEqual() {
		return new IMatches<ICriterion>() {
			@Override
			public ICriterion number(long theNumber) {
				return new StringCriterion(getParamName(), "<=" + Long.toString(theNumber));
			}

			@Override
			public ICriterion number(String theNumber) {
				return new StringCriterion(getParamName(), "<=" + (theNumber));
			}
		};
	}

	public interface IMatches<T> {
		/**
		 * Creates a search criterion that matches against the given number
		 * 
		 * @param theNumber
		 *            The number
		 * @return A criterion
		 */
		T number(long theNumber);

		/**
		 * Creates a search criterion that matches against the given number
		 * 
		 * @param theNumber
		 *            The number
		 * @return A criterion
		 */
		T number(String theNumber);
	}

}
