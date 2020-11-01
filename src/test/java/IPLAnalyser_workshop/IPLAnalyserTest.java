package IPLAnalyser_workshop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

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
	 @Test
	    public void givenData_whenSortedOnBattingAverages_shouldReturnBatsmanWithHighestAverageCricketer() {
	        try {
	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortCensusData = IPLAnalyser.getPlayersWithTopAverages();
	            IPLMostRuns[] iplMostRuns = new Gson().fromJson(sortCensusData, IPLMostRuns[].class);
	            Assert.assertEquals("MS Dhoni", iplMostRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
}
