package IPLAnalyser_workshop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPLAnalyserTest {
	private static final String IPL_CSV_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_CSV_WKTS_FILE_PATH = "./src/test/resources/WP DP Data_02 IPL2019FactsheetMostWkts.csv";

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
	    public void givenData_whenSortedOnBattingAverages_shouldReturnPlayerWithHighestAverage() {
	        try {
	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortCensusData = IPLAnalyser.getPlayersWithTopAverages();
	            IPLMostRuns[] iplMostRuns = new Gson().fromJson(sortCensusData, IPLMostRuns[].class);
	            Assert.assertEquals("MS Dhoni", iplMostRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnStrikingRates_shouldReturnHighestStrikingRatedCricketer() {
	        try {

	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortCensusData = IPLAnalyser.getPlayersWithHighestStrikingRates();
	            IPLMostRuns[] iplMostRuns = new Gson().fromJson(sortCensusData, IPLMostRuns[].class);
	            Assert.assertEquals("Ishant Sharma", iplMostRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOn6sAnd4s_shouldReturnHighestPlayer() {
	        try {

	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getPlayersWithTop6sAnd4s();
	            IPLMostRuns[] iplMostRuns = new Gson().fromJson(sortedIPLData, IPLMostRuns[].class);
	            Assert.assertEquals("Andre Russell", iplMostRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedonStrikingRate_shouldReturnHighestBoundary() {
	        try {
	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getPlayersWithTopStrikingRateAndBoundary();
	            IPLMostRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostRuns[].class);
	            Assert.assertEquals("Andre Russell", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnAvg_ShouldReturnHighestStrikeRate() {
	        try {
	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getPlayersWithTopStrikingRateAndAverage();
	            IPLMostRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostRuns[].class);
	            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSorted_ShouldReturnMaxRunsAndAverageCricketer() {
	        try {

	            IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getPlayersWithMaxRunsAndAverage();
	            IPLMostRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostRuns[].class);
	            Assert.assertEquals("David Warner ", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenIPLRunsWicketsFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
	        try {
	            int numOfRecords = IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            Assert.assertEquals(99, numOfRecords);
	        } catch (IPLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 @Test
	    public void givenData_whenSorted_shouldReturnHighestStrikingRateBowler() {
	        try {
	            IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getBowlersWithHighestStrikingRate();
	            IPLMostWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostWickets[].class);
	            Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighest() {
	        try {
	            IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getBowlersWithHighestEconomy();
	            IPLMostWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostWickets[].class);
	            Assert.assertEquals("Ben Cutting", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnStrikingRate_ShouldReturnBowlerWithHighest5w4w() {
	        try {
	            IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getBowlersWithHighestStrikingRateAnd4w5w();
	            IPLMostWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostWickets[].class);
	            Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnBowlingAvg_ShouldReturnBowlerWithHighestStrikingRate() {
	        try {
	            IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getBowlersWithTopStrikingRateAndAverage();
	            IPLMostWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostWickets[].class);
	            Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnwickets_ShouldReturnBowlerWithMaxAvg() {
	        try {
	            IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	            String sortedIPLData = IPLAnalyser.getBowlersWithMaxWicketsAndAvg();
	            IPLMostWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLMostWickets[].class);
	            Assert.assertEquals("Imran Tahir", iplRuns[0].player);
	        } catch (IPLException e) {
	            e.printStackTrace();

	        }
	    }
	 
	 @Test
	    public void givenData_whenSortedOnBowlingAndBatting_ShouldReturnTopPlayer() throws IPLException {
	        IPLAnalyser.loadIPLDataWkts(IPL_CSV_WKTS_FILE_PATH);
	        String sorted = IPLAnalyser.getBowlersWithTopAverage();
	        IPLMostWickets[] wickets = new Gson().fromJson(sorted, IPLMostWickets[].class);
	        IPLAnalyser.loadIPLData(IPL_CSV_RUNS_FILE_PATH);
	        String sortedBat = IPLAnalyser.getPlayersWithTopAverages();
	        IPLMostRuns[] average = new Gson().fromJson(sortedBat, IPLMostRuns[].class);
	        Assert.assertEquals("MS Dhoni",average[0].player
	        		);
	    }


}
