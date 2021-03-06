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

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.RuntimeResourceDefinition;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.rest.server.Constants;

public class Bundle extends BaseBundle /* implements IElement */{

	private volatile transient Map<IdDt, IResource> myIdToEntries;

	//@formatter:off
	/* ****************************************************
	 * NB: add any new fields to the isEmpty() method!!!
	 *****************************************************/
	//@formatter:on
	private List<BundleEntry> myEntries;
	private StringDt myBundleId;
	private StringDt myLinkBase;
	private StringDt myLinkFirst;
	private StringDt myLinkLast;
	private StringDt myLinkNext;
	private StringDt myLinkPrevious;
	private StringDt myLinkSelf;
	private InstantDt myPublished;
	private StringDt myTitle;
	private IntegerDt myTotalResults;
	private InstantDt myUpdated;

	/**
	 * Returns true if this bundle contains zero entries
	 */
	@Override
	public boolean isEmpty() {
		return getEntries().isEmpty();
	}

	/**
	 * Adds and returns a new bundle entry
	 */
	public BundleEntry addEntry() {
		BundleEntry retVal = new BundleEntry();
		getEntries().add(retVal);
		return retVal;
	}

	/**
	 * Retrieves a resource from a bundle given its logical ID.
	 * <p>
	 * <b>Important usage notes</b>: This method ignores base URLs (so passing in an ID of <code>http://foo/Patient/123</code> will return a resource if it has the logical ID of
	 * <code>http://bar/Patient/123</code>. Also, this method is intended to be used for bundles which have already been populated. It will cache its results for fast performance, but that means that
	 * modifications to the bundle after this method is called may not be accurately reflected.
	 * </p>
	 * 
	 * @param theId The resource ID
	 * @return Returns the resource with the given ID, or <code>null</code> if none is found
	 */
	public IResource getResourceById(IdDt theId) {
		Map<IdDt, IResource> map = myIdToEntries;
		if (map == null) {
			map = new HashMap<IdDt, IResource>();
			for (BundleEntry next : this.getEntries()) {
				if (next.getId().isEmpty() == false) {
					map.put(next.getId().toUnqualified(), next.getResource());
				}
			}
			myIdToEntries = map;
		}
		return map.get(theId.toUnqualified());
	}

	public List<BundleEntry> getEntries() {
		if (myEntries == null) {
			myEntries = new ArrayList<BundleEntry>();
		}
		return myEntries;
	}

	@Override
	public String toString() {
		ToStringBuilder b = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		b.append(getEntries().size() + " entries");
		b.append("id", getId());
		return b.toString();
	}

	public StringDt getBundleId() {
		if (myBundleId == null) {
			myBundleId = new StringDt();
		}
		return myBundleId;
	}

	public StringDt getLinkBase() {
		if (myLinkBase == null) {
			myLinkBase = new StringDt();
		}
		return myLinkBase;
	}

	public StringDt getLinkFirst() {
		if (myLinkFirst == null) {
			myLinkFirst = new StringDt();
		}
		return myLinkFirst;
	}

	public StringDt getLinkLast() {
		if (myLinkLast == null) {
			myLinkLast = new StringDt();
		}
		return myLinkLast;
	}

	public StringDt getLinkNext() {
		if (myLinkNext == null) {
			myLinkNext = new StringDt();
		}
		return myLinkNext;
	}

	public StringDt getLinkPrevious() {
		if (myLinkPrevious == null) {
			myLinkPrevious = new StringDt();
		}
		return myLinkPrevious;
	}

	public StringDt getLinkSelf() {
		if (myLinkSelf == null) {
			myLinkSelf = new StringDt();
		}
		return myLinkSelf;
	}

	public InstantDt getPublished() {
		if (myPublished == null) {
			myPublished = new InstantDt();
		}
		return myPublished;
	}

	public StringDt getTitle() {
		if (myTitle == null) {
			myTitle = new StringDt();
		}
		return myTitle;
	}

	public IntegerDt getTotalResults() {
		if (myTotalResults == null) {
			myTotalResults = new IntegerDt();
		}
		return myTotalResults;
	}

	public InstantDt getUpdated() {
		if (myUpdated == null) {
			myUpdated = new InstantDt();
		}
		return myUpdated;
	}

	public List<IResource> toListOfResources() {
		ArrayList<IResource> retVal = new ArrayList<IResource>();
		for (BundleEntry next : getEntries()) {
			if (next.getResource() != null) {
				retVal.add(next.getResource());
			}
		}
		return retVal;
	}

	public static Bundle withSingleResource(IResource theResource) {
		Bundle retVal = new Bundle();
		retVal.addEntry().setResource(theResource);
		return retVal;
	}

	/**
	 * Returns the number of entries in this bundle
	 */
	public int size() {
		return getEntries().size();
	}

	/**
	 * Returns a list containing all resources of the given type from this bundle
	 */
	public <T extends IResource> List<T> getResources(Class<T> theClass) {
		ArrayList<T> retVal = new ArrayList<T>();
		for (BundleEntry next : getEntries()) {
			if (next.getResource() != null && theClass.isAssignableFrom(next.getResource().getClass())) {
				@SuppressWarnings("unchecked")
				T resource = (T) next.getResource();
				retVal.add(resource);
			}
		}
		return retVal;
	}

	/**
	 * Creates a new entry using the given resource and populates it accordingly
	 * 
	 * @param theResource
	 *            The resource to add
	 */
	public void addResource(IResource theResource, FhirContext theContext, String theServerBase) {
		BundleEntry entry = addEntry();
		entry.setResource(theResource);

		entry.setResource(theResource);

		RuntimeResourceDefinition def = theContext.getResourceDefinition(theResource);

		if (theResource.getId() != null && StringUtils.isNotBlank(theResource.getId().getValue())) {
			String title = ResourceMetadataKeyEnum.TITLE.get(theResource);
			if (title != null) {
				entry.getTitle().setValue(title);
			} else {
				entry.getTitle().setValue(def.getName() + " " + theResource.getId().getValue());
			}

			StringBuilder b = new StringBuilder();
			b.append(theServerBase);
			if (b.length() > 0 && b.charAt(b.length() - 1) != '/') {
				b.append('/');
			}
			b.append(def.getName());
			b.append('/');
			String resId = theResource.getId().getIdPart();
			b.append(resId);

			entry.getId().setValue(b.toString());

			if (isNotBlank(theResource.getId().getVersionIdPart())) {
				b.append('/');
				b.append(Constants.PARAM_HISTORY);
				b.append('/');
				b.append(theResource.getId().getVersionIdPart());
			} else {
				IdDt versionId = (IdDt) ResourceMetadataKeyEnum.VERSION_ID.get(theResource);
				if (versionId != null) {
					b.append('/');
					b.append(Constants.PARAM_HISTORY);
					b.append('/');
					b.append(versionId.getValue());
				}
			}

			entry.getLinkSelf().setValue(b.toString());
		}

		InstantDt published = ResourceMetadataKeyEnum.PUBLISHED.get(theResource);
		if (published == null) {
			entry.getPublished().setToCurrentTimeInLocalTimeZone();
		} else {
			entry.setPublished(published);
		}

		InstantDt updated = ResourceMetadataKeyEnum.UPDATED.get(theResource);
		if (updated != null) {
			entry.setUpdated(updated);
		}

		InstantDt deleted = ResourceMetadataKeyEnum.DELETED_AT.get(theResource);
		if (deleted != null) {
			entry.setDeleted(deleted);
		}

		IdDt previous = ResourceMetadataKeyEnum.PREVIOUS_ID.get(theResource);
		if (previous != null) {
			entry.getLinkAlternate().setValue(previous.withServerBase(theServerBase, def.getName()));
		}

		TagList tagList = ResourceMetadataKeyEnum.TAG_LIST.get(theResource);
		if (tagList != null) {
			for (Tag nextTag : tagList) {
				entry.addCategory(nextTag);
			}
		}

	}

	public static Bundle withResources(ArrayList<IResource> theUploadBundle, FhirContext theContext, String theServerBase) {
		Bundle retVal = new Bundle();
		for (IResource next : theUploadBundle) {
			retVal.addResource(next, theContext, theServerBase);
		}
		return retVal;
	}

	public void setPublished(InstantDt thePublished) {
		myPublished = thePublished;
	}

}
