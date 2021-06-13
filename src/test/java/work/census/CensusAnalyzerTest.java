package work.census;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
	final String INDIA_CENSUS_CSV_FILE_PATH = "./IndiaCensus.csv";
	private static final String WRONG_CSV_FILE_PATH = "IndiaCensus.csv";

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectNumOfRecords() throws IOException, CensusAnalyzerException {
		CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
		int numOfRecords = censusAnalyZer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
	}

	@Test
	public void given_IndiaCensusData_WithWrongFile_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyzerException.class);
			censusAnalyZer.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
		}
	}

}