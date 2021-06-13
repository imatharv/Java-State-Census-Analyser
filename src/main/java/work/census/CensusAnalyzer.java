package work.census;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.StreamSupport;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CensusAnalyzer {
	public int loadDataFromCSVFile(String csvFilePath) throws IOException, CensusAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyzerException(e.getMessage(),
					CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	@SuppressWarnings("deprecation")
	public boolean loadIndiaCensusDataFile(String csvFilePath)
			throws FileNotFoundException, IOException, CensusAnalyzerException, CsvValidationException {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFilePath));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				return true;
			}
			reader.close();

		} catch (IllegalStateException e) {
			throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE);
		}
		return false;
	}
}