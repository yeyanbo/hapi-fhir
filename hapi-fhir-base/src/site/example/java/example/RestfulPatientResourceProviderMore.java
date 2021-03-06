package example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.api.Tag;
import ca.uhn.fhir.model.api.TagList;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.api.annotation.TagListParam;
import ca.uhn.fhir.model.dstu.composite.CodingDt;
import ca.uhn.fhir.model.dstu.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.resource.Conformance;
import ca.uhn.fhir.model.dstu.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.dstu.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu.resource.Organization;
import ca.uhn.fhir.model.dstu.resource.Patient;
import ca.uhn.fhir.model.dstu.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu.valueset.IssueSeverityEnum;
import ca.uhn.fhir.model.dstu.valueset.QuantityCompararatorEnum;
import ca.uhn.fhir.model.primitive.DecimalDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.annotation.AddTags;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.DeleteTags;
import ca.uhn.fhir.rest.annotation.GetTags;
import ca.uhn.fhir.rest.annotation.History;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.Metadata;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Since;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.annotation.Validate;
import ca.uhn.fhir.rest.annotation.VersionIdParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.ITestClient;
import ca.uhn.fhir.rest.client.api.IBasicClient;
import ca.uhn.fhir.rest.client.api.IRestfulClient;
import ca.uhn.fhir.rest.param.CodingListParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.QualifiedDateParam;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.ResourceVersionConflictException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

@SuppressWarnings("unused")
public abstract class RestfulPatientResourceProviderMore implements IResourceProvider {

private boolean detectedVersionConflict;
private boolean conflictHappened;
private boolean couldntFindThisId;
//START SNIPPET: searchAll
@Search
public List<Organization> getAllOrganizations() {
   List<Organization> retVal=new ArrayList<Organization>(); // populate this
   return retVal;
}
//END SNIPPET: searchAll

//START SNIPPET: sort
@Search
public List<Patient> findPatients(
		@RequiredParam(name=Patient.SP_IDENTIFIER) StringParam theParameter,
		@Sort SortSpec theSort) {
   List<Patient> retVal=new ArrayList<Patient>(); // populate this

   // theSort is null unless a _sort parameter is actually provided 
   if (theSort != null) {
      
      // The name of the param to sort by
      String param = theSort.getParamName();

      // The sort order, or null
      SortOrderEnum order = theSort.getOrder();

      // This will be populated if a second _sort was specified
      SortSpec subSort = theSort.getChain();
      
      // ...apply the sort...
   }

   return retVal;
}
//END SNIPPET: sort

//START SNIPPET: underlyingReq
@Search
public List<Patient> findPatients(
		@RequiredParam(name="foo") StringParam theParameter,
		HttpServletRequest theRequest, 
		HttpServletResponse theResponse) {
 List<Patient> retVal=new ArrayList<Patient>(); // populate this
 return retVal;
}
//END SNIPPET: underlyingReq

//START SNIPPET: reference
@Search
public List<Patient> findPatients(
		@RequiredParam(name=Patient.SP_PROVIDER) ReferenceParam theProvider
		) {

// May be populated with the resource type (e.g. "Patient") if the client requested one
String type = theProvider.getResourceType(); 

// May be populated with the chain (e.g. "name") if the client requested one
String chain = theProvider.getChain();

/* The actual parameter value. This will be the resource ID if no chain was provided,
 * but refers to the value of a specific property noted by the chain if one was given.
 * For example, the following request: 
 * http://example.com/fhir/Patient?provider:Organization.name=FooOrg
 * has a type of "Organization" and a chain of "name", meaning that
 * the returned patients should have a provider which is an Organization
 * with a name matching "FooOrg".
 */
String value = theProvider.getValue();

List<Patient> retVal=new ArrayList<Patient>(); // populate this
return retVal;

}
//END SNIPPET: reference


//START SNIPPET: read
@Read()
public Patient getResourceById(@IdParam IdDt theId) {
   Patient retVal = new Patient();
   // ...populate...
   return retVal;
}
//END SNIPPET: read

//START SNIPPET: delete
@Read()
public void deletePatient(@IdParam IdDt theId) {
	// .. Delete the patient ..
	if (couldntFindThisId) {
		throw new ResourceNotFoundException("Unknown version");
	}
	if (conflictHappened) {
		throw new ResourceVersionConflictException("Couldn't delete because [foo]");
	}
	// otherwise, delete was successful
	return; // can also return MethodOutcome
}
//END SNIPPET: delete


//START SNIPPET: history
@History()
public List<Patient> getPatientHistory(@IdParam IdDt theId) {
   List<Patient> retVal = new ArrayList<Patient>();
   
   Patient patient = new Patient();
   patient.addName().addFamily("Smith");
   
   // Set the ID and version
   patient.setId(theId.withVersion("1"));
   
   // ...populate the rest...
   return retVal;
}
//END SNIPPET: history


//START SNIPPET: vread
@Read(version=true)
public Patient readOrVread(@IdParam IdDt theId) {
   Patient retVal = new Patient();
   // ...populate...
   return retVal;
}
//END SNIPPET: vread

//START SNIPPET: searchStringParam
@Search()
public List<Patient> searchByLastName(@RequiredParam(name=Patient.SP_FAMILY) StringParam theFamily) {
   List<Patient> retVal = new ArrayList<Patient>();
   
   String valueToMatch = theFamily.getValue();
   
   if (theFamily.isExact()) {
	   // Do an exact match search
   } else {
	   // Do a fuzzy search if possible
   }
   
   // ...populate...
   return retVal;
}
//END SNIPPET: searchStringParam

//START SNIPPET: searchNamedQuery
@Search(queryName="namedQuery1")
public List<Patient> searchByNamedQuery(@RequiredParam(name="someparam") StringParam theSomeParam) {
 List<Patient> retVal = new ArrayList<Patient>();
 // ...populate...
 return retVal;
}
//END SNIPPET: searchNamedQuery

//START SNIPPET: searchIdentifierParam
@Search()
public List<Patient> searchByIdentifier(@RequiredParam(name=Patient.SP_IDENTIFIER) IdentifierDt theId) {
   String identifierSystem = theId.getSystem().getValueAsString();
   String identifier = theId.getValue().getValue();
   
   List<Patient> retVal = new ArrayList<Patient>();
   // ...populate...
   return retVal;
}
//END SNIPPET: searchIdentifierParam

//START SNIPPET: searchOptionalParam
@Search()
public List<Patient> searchByNames( @RequiredParam(name=Patient.SP_FAMILY) StringParam theFamilyName,
                                    @OptionalParam(name=Patient.SP_GIVEN)  StringParam theGivenName ) {
   String familyName = theFamilyName.getValue();
   String givenName = theGivenName != null ? theGivenName.getValue() : null;
   
   List<Patient> retVal = new ArrayList<Patient>();
   // ...populate...
   return retVal;
}
//END SNIPPET: searchOptionalParam

//START SNIPPET: searchMultiple
@Search()
public List<Observation> searchByObservationNames( @RequiredParam(name=Observation.SP_NAME) CodingListParam theCodings ) {
   // This search should return any observations matching one or more
   // of the codings here.
   List<CodingDt> wantedCodings = theCodings.getCodings();
   
   List<Observation> retVal = new ArrayList<Observation>();
   // ...populate...
   return retVal;
}
//END SNIPPET: searchMultiple

//START SNIPPET: dates
@Search()
public List<Patient> searchByObservationNames( @RequiredParam(name=Patient.SP_BIRTHDATE) QualifiedDateParam theDate ) {
   QuantityCompararatorEnum comparator = theDate.getComparator(); // e.g. <=
   Date date = theDate.getValue(); // e.g. 2011-01-02
   TemporalPrecisionEnum precision = theDate.getPrecision(); // e.g. DAY
	
   List<Patient> retVal = new ArrayList<Patient>();
   // ...populate...
   return retVal;
}
//END SNIPPET: dates

public void dateClientExample() {
ITestClient client = provideTc();
//START SNIPPET: dateClient
QualifiedDateParam param = new QualifiedDateParam(QuantityCompararatorEnum.GREATERTHAN_OR_EQUALS, "2011-01-02");
List<Patient> response = client.getPatientByDob(param);
//END SNIPPET: dateClient
}

//START SNIPPET: dateRange
@Search()
public List<Observation> searchByDateRange(
    @RequiredParam(name=Observation.SP_DATE) DateRangeParam theRange ) {
  
  Date from = theRange.getLowerBoundAsInstant();
  Date to = theRange.getUpperBoundAsInstant();
	
  List<Observation> retVal = new ArrayList<Observation>();
  // ...populate...
  return retVal;
}
//END SNIPPET: dateRange


private ITestClient provideTc() {
	return null;
}
@Override
public Class<? extends IResource> getResourceType() {
   return null;
}



//START SNIPPET: pathSpec
@Search()
public List<DiagnosticReport> getDiagnosticReport( 
               @RequiredParam(name=DiagnosticReport.SP_IDENTIFIER) 
               IdentifierDt theIdentifier,
               @IncludeParam(allow= {"DiagnosticReport.subject"}) 
               Set<Include> theIncludes ) {
  List<DiagnosticReport> retVal = new ArrayList<DiagnosticReport>();
 
  // Assume this method exists and loads the report from the DB
  DiagnosticReport report = loadSomeDiagnosticReportFromDatabase(theIdentifier);

  // If the client has asked for the subject to be included:
  if (theIncludes.contains(new Include("DiagnosticReport.subject"))) {
	 
    // The resource reference should contain the ID of the patient
    IdDt subjectId = report.getSubject().getId();
	
    // So load the patient ID and return it
    Patient subject = loadSomePatientFromDatabase(subjectId);
    report.getSubject().setResource(subject);
	
  }
 
  retVal.add(report);
  return retVal;
}
//END SNIPPET: pathSpec


//START SNIPPET: pathSpecSimple
@Search()
public List<DiagnosticReport> getDiagnosticReport( 
             @RequiredParam(name=DiagnosticReport.SP_IDENTIFIER) 
             IdentifierDt theIdentifier,
             @IncludeParam(allow= {"DiagnosticReport.subject"}) 
             String theInclude ) {
  List<DiagnosticReport> retVal = new ArrayList<DiagnosticReport>();

  // Assume this method exists and loads the report from the DB
  DiagnosticReport report = loadSomeDiagnosticReportFromDatabase(theIdentifier);

  // If the client has asked for the subject to be included:
  if ("DiagnosticReport.subject".equals(theInclude)) {
	 
    // The resource reference should contain the ID of the patient
    IdDt subjectId = report.getSubject().getId();
	
    // So load the patient ID and return it
    Patient subject = loadSomePatientFromDatabase(subjectId);
    report.getSubject().setResource(subject);
	
  }

  retVal.add(report);
  return retVal;
}
//END SNIPPET: pathSpecSimple

//START SNIPPET: quantity
@Search()
public List<Observation> getObservationsByQuantity(
        @RequiredParam(name=Observation.SP_VALUE_QUANTITY) QuantityDt theQuantity) {
  
  List<Observation> retVal = new ArrayList<Observation>();
  
  QuantityCompararatorEnum comparator = theQuantity.getComparator().getValueAsEnum();
  DecimalDt value = theQuantity.getValue();
  StringDt units = theQuantity.getUnits();
  // .. Apply these parameters ..
  
  // ... populate ...
  return retVal;
}
//END SNIPPET: quantity

private DiagnosticReport loadSomeDiagnosticReportFromDatabase(IdentifierDt theIdentifier) {
	return null;
}

private Patient loadSomePatientFromDatabase(IdDt theId) {
	return null;
}


//START SNIPPET: create
@Create
public MethodOutcome createPatient(@ResourceParam Patient thePatient) {

  /* 
   * First we might want to do business validation. The UnprocessableEntityException
   * results in an HTTP 422, which is appropriate for business rule failure
   */
  if (thePatient.getIdentifierFirstRep().isEmpty()) {
    /* It is also possible to pass an OperationOutcome resource
     * to the UnprocessableEntityException if you want to return
     * a custom populated OperationOutcome. Otherwise, a simple one
     * is created using the string supplied below. 
     */
    throw new UnprocessableEntityException("No identifier supplied");
  }
	
  // Save this patient to the database...
  savePatientToDatabase(thePatient);

  // This method returns a MethodOutcome object which contains
  // the ID and Version ID for the newly saved resource
  MethodOutcome retVal = new MethodOutcome();
  retVal.setId(new IdDt("3746"));
  retVal.setVersionId(new IdDt("1"));
  
  // You can also add an OperationOutcome resource to return
  // This part is optional though:
  OperationOutcome outcome = new OperationOutcome();
  outcome.addIssue().setDetails("One minor issue detected");
  retVal.setOperationOutcome(outcome);  
  
  return retVal;
}
//END SNIPPET: create


//START SNIPPET: createClient
@Create
public abstract MethodOutcome createNewPatient(@ResourceParam Patient thePatient);
//END SNIPPET: createClient

//START SNIPPET: update
@Update
public MethodOutcome updatePatient(@IdParam IdDt theId, @ResourceParam Patient thePatient) {

  /* 
   * First we might want to do business validation. The UnprocessableEntityException
   * results in an HTTP 422, which is appropriate for business rule failure
   */
  if (thePatient.getIdentifierFirstRep().isEmpty()) {
    /* It is also possible to pass an OperationOutcome resource
     * to the UnprocessableEntityException if you want to return
     * a custom populated OperationOutcome. Otherwise, a simple one
     * is created using the string supplied below. 
     */
    throw new UnprocessableEntityException("No identifier supplied");
  }
	
  // Save this patient to the database...
  savePatientToDatabase(theId, thePatient);

  // This method returns a MethodOutcome object which contains
  // the ID and Version ID for the newly saved resource
  MethodOutcome retVal = new MethodOutcome();
  retVal.setId(theId);
  retVal.setVersionId(new IdDt("2")); // Leave this blank if the server doesn't version
  
  // You can also add an OperationOutcome resource to return
  // This part is optional though:
  OperationOutcome outcome = new OperationOutcome();
  outcome.addIssue().setDetails("One minor issue detected");
  retVal.setOperationOutcome(outcome);
  
  return retVal;
}
//END SNIPPET: update

//START SNIPPET: updateClient
@Update
public abstract MethodOutcome updateSomePatient(@IdParam IdDt theId, @ResourceParam Patient thePatient);
//END SNIPPET: updateClient

//START SNIPPET: updateVersion
@Update
public MethodOutcome updatePatient(@IdParam IdDt theId, @VersionIdParam IdDt theVersionId, @ResourceParam Patient thePatient) {
  // ..Process..
  if (detectedVersionConflict) {
	  throw new ResourceVersionConflictException("Invalid version");
  }
  MethodOutcome retVal = new MethodOutcome();
return retVal;
}
//END SNIPPET: updateVersion




//START SNIPPET: validate
@Validate
public MethodOutcome validatePatient(@ResourceParam Patient thePatient) {

  /* 
   * Actually do our validation: The UnprocessableEntityException
   * results in an HTTP 422, which is appropriate for business rule failure
   */
  if (thePatient.getIdentifierFirstRep().isEmpty()) {
    /* It is also possible to pass an OperationOutcome resource
     * to the UnprocessableEntityException if you want to return
     * a custom populated OperationOutcome. Otherwise, a simple one
     * is created using the string supplied below. 
     */
    throw new UnprocessableEntityException("No identifier supplied");
  }
	
  // This method returns a MethodOutcome object
  MethodOutcome retVal = new MethodOutcome();

  // You may also add an OperationOutcome resource to return
  // This part is optional though:
  OperationOutcome outcome = new OperationOutcome();
  outcome.addIssue().setSeverity(IssueSeverityEnum.WARNING).setDetails("One minor issue detected");
  retVal.setOperationOutcome(outcome);  

  return retVal;
}
//END SNIPPET: validate




public static void main(String[] args) throws DataFormatException, IOException {
//nothing
}


private void savePatientToDatabase(Patient thePatient) {
	// nothing
}
private void savePatientToDatabase(IdDt theId, Patient thePatient) {
	// nothing
}

//START SNIPPET: metadataProvider
public class ConformanceProvider {

  @Metadata
  public Conformance getServerMetadata() {
    Conformance retVal = new Conformance();
    // ..populate..
    return retVal;
  }

}
//END SNIPPET: metadataProvider



//START SNIPPET: metadataClient
public interface MetadataClient extends IRestfulClient {
  
  @Metadata
  Conformance getServerMetadata();
  
  // ....Other methods can also be added as usual....
  
}
//END SNIPPET: metadataClient

//START SNIPPET: historyClient
public interface HistoryClient extends IBasicClient {
  /** Server level (history of ALL resources) */ 
  @History
  Bundle getHistoryServer();

  /** Type level (history of all resources of a given type) */
  @History(type=Patient.class)
  Bundle getHistoryPatientType();

  /** Instance level (history of a specific resource instance by type and ID) */
  @History(type=Patient.class)
  Bundle getHistoryPatientInstance(@IdParam IdDt theId);

  /**
   * Either (or both) of the "since" and "count" paramaters can
   * also be included in any of the methods above.
   */
  @History
  Bundle getHistoryServerWithCriteria(@Since Date theDate, @Count Integer theCount);

}
//END SNIPPET: historyClient


public void bbbbb() throws DataFormatException, IOException {
//START SNIPPET: metadataClientUsage
FhirContext ctx = new FhirContext();
MetadataClient client = ctx.newRestfulClient(MetadataClient.class, "http://spark.furore.com/fhir");
Conformance metadata = client.getServerMetadata();
System.out.println(ctx.newXmlParser().encodeResourceToString(metadata));
//END SNIPPET: metadataClientUsage
}

//START SNIPPET: readTags
@Read()
public Patient readPatient(@IdParam IdDt theId) {
  Patient retVal = new Patient();
 
  // ..populate demographics, contact, or anything else you usually would..
 
  // Create a TagList and place a complete list of the patient's tags inside
  TagList tags = new TagList();
  tags.addTag("http://animals", "Dog", "Canine Patient"); // TODO: more realistic example
  tags.addTag("http://personality", "Friendly", "Friendly"); // TODO: more realistic example
  
  // The tags are then stored in the Patient resource instance
  retVal.getResourceMetadata().put(ResourceMetadataKeyEnum.TAG_LIST, tags);
 
  return retVal;
}
//END SNIPPET: readTags

//START SNIPPET: clientReadInterface
private interface IPatientClient extends IBasicClient
{
  /** Read a patient from a server by ID */
  @Read
  Patient readPatient(@IdParam IdDt theId);

  // Only one method is shown here, but many methods may be 
  // added to the same client interface!
}
//END SNIPPET: clientReadInterface

public void clientRead() {
//START SNIPPET: clientReadTags
IPatientClient client = new FhirContext().newRestfulClient(IPatientClient.class, "http://foo/fhir");
Patient patient = client.readPatient(new IdDt("1234"));
  
// Access the tag list
TagList tagList = (TagList) patient.getResourceMetadata().get(ResourceMetadataKeyEnum.TAG_LIST);
for (Tag next : tagList) {
  // ..process the tags somehow..
}
//END SNIPPET: clientReadTags

//START SNIPPET: clientCreateTags
Patient newPatient = new Patient();

// Populate the resource object
newPatient.addIdentifier().setUse(IdentifierUseEnum.OFFICIAL).setValue("123");
newPatient.addName().addFamily("Jones").addGiven("Frank");

// Populate tags
TagList tags = new TagList();
tags.addTag("http://animals", "Dog", "Canine Patient"); // TODO: more realistic example
tags.addTag("http://personality", "Friendly", "Friendly"); // TODO: more realistic example
newPatient.getResourceMetadata().put(ResourceMetadataKeyEnum.TAG_LIST, tags);

// ...invoke the create method on the client...
//END SNIPPET: clientCreateTags
}

//START SNIPPET: createTags
@Create
public MethodOutcome createPatientResource(@ResourceParam Patient thePatient) {

  // ..save the resouce..
  IdDt id = new IdDt("123"); // the new databse primary key for this resource

  // Get the tag list
  TagList tags = (TagList) thePatient.getResourceMetadata().get(ResourceMetadataKeyEnum.TAG_LIST);
  for (Tag tag : tags) {
    // process/save each tag somehow	
  }
  
  return new MethodOutcome(id);
}
//END SNIPPET: createTags

//START SNIPPET: tagMethodProvider
public class TagMethodProvider 
{
  /** Return a list of all tags that exist on the server */
  @GetTags
  public TagList getAllTagsOnServer() {
    return new TagList(); // populate this
  }

  /** Return a list of all tags that exist on at least one instance
   *  of the given resource type */
  @GetTags(type=Patient.class)
  public TagList getTagsForAllResourcesOfResourceType() {
    return new TagList(); // populate this
  }

  /** Return a list of all tags that exist on a specific instance
   *  of the given resource type */
  @GetTags(type=Patient.class)
  public TagList getTagsForResources(@IdParam IdDt theId) {
    return new TagList(); // populate this
  }

  /** Return a list of all tags that exist on a specific version
   *  of the given resource type */
  @GetTags(type=Patient.class)
  public TagList getTagsForResourceVersion(@IdParam IdDt theId, 
                                           @VersionIdParam IdDt theVersion) {
    return new TagList(); // populate this
  }

  /** Add tags to a resource */
  @AddTags(type=Patient.class)
  public void getTagsForResourceVersion(@IdParam IdDt theId, 
                                        @TagListParam TagList theTagList) {
    // add tags
  }

  /** Add tags to a resource version */
  @AddTags(type=Patient.class)
  public void addTagsToResourceVersion(@IdParam IdDt theId,
                                       @VersionIdParam IdDt theVersion,
                                       @TagListParam TagList theTagList) {
    // add tags
  }

  /** Remove tags from a resource */
  @DeleteTags(type=Patient.class)
  public void deleteTagsFromResourceVersion(@IdParam IdDt theId,
                                            @TagListParam TagList theTagList) {
    // add tags
  }

  /** Remove tags from a resource version */
  @DeleteTags(type=Patient.class)
  public void deleteTagsFromResourceVersion(@IdParam IdDt theId,
                                            @VersionIdParam IdDt theVersion,
                                            @TagListParam TagList theTagList) {
    // add tags
  }

}
//END SNIPPET: tagMethodProvider

//START SNIPPET: transaction
@Transaction
public List<IResource> transaction(@TransactionParam List<IResource> theResources) {
   // theResources will contain a complete bundle of all resources to persist
   // in a single transaction
   for (IResource next : theResources) {
      InstantDt deleted = (InstantDt) next.getResourceMetadata().get(ResourceMetadataKeyEnum.DELETED_AT);
      if (deleted != null && deleted.isEmpty() == false) {
         // delete this resource
      } else {
         // create or update this resource
      }
   }

   /*
    * According to the specification, a bundle must be returned. This bundle will contain
    * all of the created/updated/deleted resources, including their new/updated identities.
    * 
    * The returned list must be the exact same size as the list of resources
    * passed in, and it is acceptable to return the same list instance that was
    * passed in. 
    */
   List<IResource> retVal = theResources;
   for (IResource next : theResources) {
      /*
       * Populate each returned resource with the new ID for that resource,
       * including the new version if the server supports versioning.
       */
      IdDt newId = new IdDt("Patient", "1", "2"); 
      next.setId(newId);
   }
   
   return retVal;
}
//END SNIPPET: transaction


}


