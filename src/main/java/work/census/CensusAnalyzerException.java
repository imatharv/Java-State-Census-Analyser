package work.census;

public class CensusAnalyzerException extends Exception {
	enum ExceptionType {
		CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, CSV_FILE_PROBLEM
	}

	ExceptionType type;

	public CensusAnalyzerException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public CensusAnalyzerException(ExceptionType type, String message, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
