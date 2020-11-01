package IPLAnalyser_workshop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
	private static final String IPL_CSV_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_CSV_WKTS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

	@Before
	public void welcomeMessage()
	{
		IPLAnalyser.welcomeMessage();
	}

	@Test
	public void givenRunsCSVFile_whenLoaded_shouldMatchCount() {
		try {
			int numOfRecords = IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
			Assert.assertEquals(101, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}
}
