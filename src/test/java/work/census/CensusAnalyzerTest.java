package work.census;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {

	final String INDIA_CENSUS_CSV_FILE_PATH = "./IndiaCensus.csv";

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectNumOfRecords() throws IOException {
		CensusAnalyzer censusAnalyZer = new CensusAnalyzer();
		int numOfRecords = censusAnalyZer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		Assert.assertEquals(1, numOfRecords);
  }
}
