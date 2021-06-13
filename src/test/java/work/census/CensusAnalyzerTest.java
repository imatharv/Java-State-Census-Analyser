package work.census;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.opencsv.exceptions.CsvValidationException;

public class CensusAnalyzerTest {
	private final String INDIAN_CENSUS_CSV_FILE_PATH = "./IndiaCensus.csv";
	private static final String WRONG_CENSUS_CSV_FILE_PATH = "IndiaCensus.csv";
	private static final String WRONG_CENSUS_FILE_TYPE = "./IndiaCensus.pdf";
	private static final String INDIAN_STATE_CODE_CSV_FILE_PATH = "./StateCodes.csv";
	private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "StateCodes.csv";
	private static final String WRONG_STATE_CODE_FILE_TYPE_PATH = "./StateCodes.pdf";

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
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyzerException.class);
			censusAnalyZer.loadDataFromCSVFile(WRONG_CENSUS_CSV_FILE_PATH);
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
			censusAnalyZer.loadDataFromCSVFile(WRONG_CENSUS_FILE_TYPE);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_IndiaCensusData_WithCorrectFile_ButWrongDelimiter_ShoulThrewException() throws IOException, CsvValidationException {
		try {
			CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			censusAnalyZer.loadIndiaCensusDataFile(INDIAN_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE, e.type);
		}
	}

	// test for state code information
	@Test
	public void givenStateCodeCSVFileReturnsCorrectNumOfRecords() throws IOException, CensusAnalyzerException {
		CensusAnalyzer statecodeAnalyZer = new CensusAnalyzer();
		int numOfRecords = statecodeAnalyZer.loadDataFromCSVFile(INDIAN_STATE_CODE_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
	}

	@Test
	public void given_StateCodesData_WithWrongFile_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer statecodeAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyzerException.class);
			statecodeAnalyZer.loadDataFromCSVFile(WRONG_STATE_CODE_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CSV_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_StateCodesData_WithCorrectFile_ButWrongType_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer statecodeAnalyZer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			statecodeAnalyZer.loadDataFromCSVFile(WRONG_STATE_CODE_FILE_TYPE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_StateCodesData_WithCorrectFile_ButWrongDelimiter_ShoulThrewException() throws IOException {
		try {
			CensusAnalyzer statecodeAnalyzer = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(IOException.class);
			statecodeAnalyzer.loadDataFromCSVFile(INDIAN_STATE_CODE_CSV_FILE_PATH);
		} catch (CensusAnalyzerException e) {
			Assert.assertEquals(CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE, e.type);
		}
	}
}