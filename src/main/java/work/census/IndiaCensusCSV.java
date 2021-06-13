package work.census;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

	public String state;

	public double population;

	public double areaInSqKm;

	public double densityPerSqKm;

	public IndiaCensusCSV() {
		// TODO Auto-generated constructor stub
	}

	public IndiaCensusCSV(String state, int population, double areaInsqKm, double densityPerSqKm) {
		super();
		this.state = state;
		this.population = population;
		this.areaInSqKm = areaInsqKm;
		this.densityPerSqKm = densityPerSqKm;
	}

	@Override
	public String toString() {
		return "IndiaCensusCSV [state=" + state + ", population=" + population + ", areaInsqKm=" + areaInSqKm
				+ ", densityPerSqKm=" + densityPerSqKm + "]";
	}

}