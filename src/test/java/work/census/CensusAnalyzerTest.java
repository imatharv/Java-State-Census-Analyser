package work.census;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.opencsv.exceptions.CsvValidationException;

public class CensusAnalyzerTest {
	private final String INDIAN_CENSUS_CSV_FILE_PATH = "./IndiaCensus.csv";
	private static final String WRONG_CENSUS_CSV_FILE_PATH = "IndiaCensus.csv";
	private static final String WRONG_CENSUS_FILE_TYPE_PATH = "./IndiaCensus.pdf";
	private final String INDIAN_STATE_CODE_CSV_FILE_PATH = "./StateCodes.csv";

	// test for census analysis
	@Test
	public void givenIndianCensusCSVFileReturnsCorrectNumOfRecords() throws IOException, CensusAnalyzerException {
		CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
		int numOfRecords = censusAnalyZer.loadDataFromCSVFile(INDIAN_CENSUS_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
	}

	@Test
	public void given_IndiaCensusData_WithWrongFile_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyzerException.class);
			censusAnalyzer.loadDataFromCSVFile(WRONG_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_IndiaCensusData_WithCorrectFile_ButWrongType_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			censusAnalyZer.loadDataFromCSVFile(WRONG_CENSUS_FILE_TYPE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_IndiaCensusData_WithCorrectFile_ButWrongDelimiter_ShoulThrewException() throws IOException, CsvValidationException {
		try {
			CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			censusAnalyzer.loadIndiaCensusDataFile(INDIAN_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE, e.type);
		}
	}

	// test for state code information
	@Test
	public void givenStateCodeCSVFileReturnsCorrectNumOfRecords() throws IOException, CensusAnalyzerException {
		CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
		int numOfRecords = censusAnalyZer.loadDataFromCSVFile(INDIAN_STATE_CODE_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
	}
	

}