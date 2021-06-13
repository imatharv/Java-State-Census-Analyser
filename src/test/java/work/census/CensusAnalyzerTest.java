package work.census;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.opencsv.exceptions.CsvValidationException;

public class CensusAnalyzerTest {
	final String INDIA_CENSUS_CSV_FILE_PATH = "./IndiaCensus.csv";
	private static final String WRONG_CSV_FILE_PATH = "IndiaCensus.csv";
	private static final String WRONG_FILE_TYPE = "./IndiaCensus.pdf";

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectNumOfRecords() throws IOException, CensusAnalyzerException {
		CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
		int numOfRecords = censusAnalyZer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
	}

	@Test
	public void given_IndiaCensusData_With_Wrong_File_Should_Throw_Exception() throws IOException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyzerException.class);
			censusAnalyZer.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_IndiaCensusData_With_Correct_File_ButWrongType_Should_Throw_Exception() throws IOException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			censusAnalyZer.loadIndiaCensusData(WRONG_FILE_TYPE);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_IndiaCensusData_WithCorrect_File_But_Wrong_Delimiter_Should_Throw_Exception() throws IOException, CsvValidationException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			censusAnalyZer.loadIndiaCensusDataFile(INDIA_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE, e.type);
		}
	}

}