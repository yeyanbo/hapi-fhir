package ca.uhn.fhir.narrative;

import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.util.Date;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.dstu.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu.composite.PeriodDt;
import ca.uhn.fhir.model.dstu.composite.QuantityDt;
import ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu.resource.Conformance;
import ca.uhn.fhir.model.dstu.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu.resource.Encounter;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.dstu.resource.Organization;
import ca.uhn.fhir.model.dstu.resource.Patient;
import ca.uhn.fhir.model.dstu.valueset.DiagnosticReportStatusEnum;
import ca.uhn.fhir.model.dstu.valueset.EncounterClassEnum;
import ca.uhn.fhir.model.dstu.valueset.EncounterTypeEnum;
import ca.uhn.fhir.model.dstu.valueset.ObservationStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.parser.DataFormatException;

public class DefaultThymeleafNarrativeGeneratorTest {
	private FhirContext myCtx;
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(DefaultThymeleafNarrativeGeneratorTest.class);
	private DefaultThymeleafNarrativeGenerator gen;

	@Before
	public void before() {
		gen = new DefaultThymeleafNarrativeGenerator();
		gen.setUseHapiServerConformanceNarrative(true);
		gen.setIgnoreFailures(false);
		gen.setIgnoreMissingTemplates(false);
		
		myCtx=new FhirContext();
		myCtx.setNarrativeGenerator(gen);
	}

	
	
	
	@Test
	public void testGeneratePatient() throws DataFormatException {
		Patient value = new Patient();

		value.addIdentifier().setSystem("urn:names").setValue("123456");
		value.addName().addFamily("blow").addGiven("joe").addGiven(null).addGiven("john");
		value.getAddressFirstRep().addLine("123 Fake Street").addLine("Unit 1");
		value.getAddressFirstRep().setCity("Toronto").setState("ON").setCountry("Canada");

		value.setBirthDate(new Date(), TemporalPrecisionEnum.DAY);

		String output = gen.generateNarrative(value).getDiv().getValueAsString();
		assertThat(output, StringContains.containsString("<div class=\"hapiHeaderText\"> joe john <b>BLOW </b></div>"));
		
		String title = gen.generateTitle(value);
		assertEquals("joe john BLOW (123456)", title);
		ourLog.info(title);

		value.getIdentifierFirstRep().setLabel("FOO MRN 123");
		title = gen.generateTitle(value);
		assertEquals("joe john BLOW (FOO MRN 123)", title);
		ourLog.info(title);

	}


	@Test
	public void testGenerateEncounter() throws DataFormatException {
		Encounter enc = new Encounter();

		enc.addIdentifier("urn:visits", "1234567");
		enc.setClassElement(EncounterClassEnum.AMBULATORY);
		enc.setPeriod(new PeriodDt().setStart(new DateTimeDt("2001-01-02T11:11:00")));
		enc.setType(EncounterTypeEnum.ANNUAL_DIABETES_MELLITUS_SCREENING);

		String title = gen.generateTitle(enc);
		assertEquals("1234567 / ADMS / ambulatory / Tue Jan 02 11:11:00 EST 2001 - ?", title);
		ourLog.info(title);

	}

	@Test
	public void testGenerateOrganization() throws DataFormatException {
		Organization enc = new Organization();

		enc.addIdentifier("urn:visits", "1234567");
		enc.setName("Some Test Org");
		enc.addAddress().addLine("123 Fake St").setCity("Toronto").setState("ON").setCountry("Canada").setZip("12345");
		
		String title = gen.generateTitle(enc);
		assertEquals("Some Test Org", title);
		ourLog.info(title);

	}

	
	@Test
	public void testGenerateServerConformance() throws DataFormatException {
		Conformance value = myCtx.newXmlParser().parseResource(Conformance.class, new InputStreamReader(getClass().getResourceAsStream("/server-conformance-statement.xml")));
		
		String output = gen.generateNarrative(value).getDiv().getValueAsString();

		ourLog.info(output);
	}

	
	@Test
	public void testGenerateDiagnosticReport() throws DataFormatException {
		DiagnosticReport value = new DiagnosticReport();
		value.getName().setText("Some Diagnostic Report");

		value.addResult().setReference("Observation/1");
		value.addResult().setReference("Observation/2");
		value.addResult().setReference("Observation/3");

		String output = gen.generateNarrative("http://hl7.org/fhir/profiles/DiagnosticReport", value).getDiv().getValueAsString();

		ourLog.info(output);
		assertThat(output,StringContains.containsString(value.getName().getText().getValue()));
	}

	@Test
	public void testGenerateDiagnosticReportWithObservations() throws DataFormatException {
		DiagnosticReport value = new DiagnosticReport();

		value.getIssued().setValueAsString("2011-02-22T11:13:00");
		value.setStatus(DiagnosticReportStatusEnum.FINAL);

		value.getName().setText("Some & Diagnostic Report");
		{
			Observation obs = new Observation();
			obs.getName().addCoding().setCode("1938HB").setDisplay("Hemoglobin");
			obs.setValue(new QuantityDt(null, 2.223, "mg/L"));
			obs.addReferenceRange().setLow(new QuantityDt(2.20)).setHigh(new QuantityDt(2.99));
			obs.setStatus(ObservationStatusEnum.FINAL);
			obs.setComments("This is a result comment");

			ResourceReferenceDt result = value.addResult();
			result.setResource(obs);
		}
		{
			Observation obs = new Observation();
			obs.setValue(new StringDt("HELLO!"));
			value.addResult().setResource(obs);
		}
		{
			Observation obs = new Observation();
			obs.setName(new CodeableConceptDt("AA", "BB"));
			value.addResult().setResource(obs);
		}
		NarrativeDt generateNarrative = gen.generateNarrative("http://hl7.org/fhir/profiles/DiagnosticReport", value);
		String output = generateNarrative.getDiv().getValueAsString();

		ourLog.info(output);
		assertThat(output, StringContains.containsString("<div class=\"hapiHeaderText\"> Some &amp; Diagnostic Report </div>"));

		String title = gen.generateTitle(value);
//		ourLog.info(title);
		assertEquals("Some & Diagnostic Report - final - 3 observations", title);

		
		// Now try it with the parser

		output = myCtx.newXmlParser().setPrettyPrint(true).encodeResourceToString(value);
		ourLog.info(output);
		assertThat(output, StringContains.containsString("<div class=\"hapiHeaderText\"> Some &amp; Diagnostic Report </div>"));

	}

}
