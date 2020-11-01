package IPLAnalyser_workshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IPLAnalyser {
	static List<IPLMostRuns> IPLCSVRuns = null;
	List<IPLMostWickets> IPLCSVWickets = null;

	public static void welcomeMessage() {
		System.out.println("Welcome to IPL Analyser");
	}

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

}
