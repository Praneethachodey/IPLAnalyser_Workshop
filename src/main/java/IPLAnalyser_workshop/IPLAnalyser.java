package IPLAnalyser_workshop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IPLAnalyser {
	static List<IPLMostRuns> IPLCSVRuns = null;
	List<IPLMostWickets> IPLCSVWickets = null;

	public static void welcomeMessage() {
		System.out.println("Welcome to IPL Analyser");
	}

	/**
	 * 
	 * @param csvFilePath
	 * @return list of players
	 * @throws IPLException
	 */
	public static int loadIPLData(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			IPLCSVRuns = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLMostRuns.class);
			return IPLCSVRuns.size();

		} catch (IOException | CSVBuilderException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.IPL_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}

	}

	/**
	 * 
	 * @return json with top batting avg
	 * @throws IPLException
	 */
	public static String getPlayersWithTopAverages() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingAvg.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> censusComparator = Comparator.comparing(census -> census.avg);
			descendingSort(censusComparator);
			String json = new Gson().toJson(IPLCSVRuns);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(IPLCSVRuns, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}

	}

	/**
	 * sort in descending order
	 * 
	 * @param iplComparator
	 */
	private static void descendingSort(Comparator<IPLMostRuns> iplComparator) {
		for (int position = 0; position < IPLCSVRuns.size() - 1; position++) {
			for (int positionOfNext = 0; positionOfNext < IPLCSVRuns.size() - position - 1; positionOfNext++) {
				IPLMostRuns ipl1 = IPLCSVRuns.get(positionOfNext);
				IPLMostRuns ipl2 = IPLCSVRuns.get(positionOfNext + 1);
				if (iplComparator.compare(ipl1, ipl2) < 0) {
					IPLCSVRuns.set(positionOfNext, ipl2);
					IPLCSVRuns.set(positionOfNext + 1, ipl1);
				}
			}
		}
	}

}
