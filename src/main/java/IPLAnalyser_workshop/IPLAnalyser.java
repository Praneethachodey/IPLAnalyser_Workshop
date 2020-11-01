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
	static List<IPLMostWickets> IPLCSVWickets = null;

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
	 * 
	 * @return json with sorted players on striking rates
	 * @throws IPLException
	 */
	public static String getPlayersWithHighestStrikingRates() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSR.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> iplComparator = Comparator.comparing(census -> census.strikeRate);
			descendingSort(iplComparator);
			String json = new Gson().toJson(IPLCSVRuns);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(IPLCSVRuns, writer);
			return json;
		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	/**
	 * 
	 * @return json with sorted players on borders
	 * @throws IPLException
	 */
	public static String getPlayersWithTop6sAnd4s() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> iplComparator = Comparator.comparing(census -> census.fours + census.sixes);
			descendingSort(iplComparator);
			String json = new Gson().toJson(IPLCSVRuns);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(IPLCSVRuns, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}

	}

	/**
	 * 
	 * @return json with sorted players on striking rate and boundaries
	 * @throws IPLException
	 */
	public static String getPlayersWithTopStrikingRateAndBoundary() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMaxRuns.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> iplComparator = Comparator.comparing(IPLMostRuns::getSixes)
					.thenComparing(ipl -> ipl.fours).thenComparing(ipl -> ipl.strikeRate);
			descendingSort(iplComparator);
			String json = new Gson().toJson(IPLCSVRuns);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(IPLCSVRuns, writer);
			return json;
		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}

	}

	/**
	 * 
	 * @return json sorted with players on avg and strike rate
	 * @throws IPLException
	 */
	public static String getPlayersWithTopStrikingRateAndAverage() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("the file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> iplComparator = Comparator.comparing(IPLMostRuns::getAvg)
					.thenComparing(ipl -> ipl.getStrikeRate());
			descendingSort(iplComparator);
			String json = new Gson().toJson(IPLCSVRuns);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(IPLCSVRuns, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}

	}

	/**
	 * 
	 * @return json with players sorted on max runs and avg
	 * @throws IPLException
	 */
	public static String getPlayersWithMaxRunsAndAverage() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
			if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
				throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLMostRuns> iplComparator = Comparator.comparing(IPLMostRuns::getRuns)
					.thenComparing(census -> census.getAvg());
			descendingSort(iplComparator);
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

	public static int loadIPLDataWkts(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IPLCSVWickets = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IPLMostWickets.class);
            return IPLCSVWickets.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

	public static String getBowlersWithHighestStrikingRate() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSR.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLMostWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.strikeRate);
            descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

	private static void descendingSortWickets(Comparator<IPLMostWickets> iplComparator) {
		 for (int position = 0; position < IPLCSVWickets.size() - 1; position++) {
	            for (int positionnext = 0; positionnext < IPLCSVWickets.size() - position - 1; positionnext++) {
	                IPLMostWickets ipl1 = IPLCSVWickets.get(positionnext);
	                IPLMostWickets ipl2 = IPLCSVWickets.get(positionnext + 1);
	                if (iplComparator.compare(ipl1, ipl2) < 0) {
	                    IPLCSVWickets.set(positionnext, ipl2);
	                    IPLCSVWickets.set(positionnext + 1, ipl1);
	                }
	            }
	        }
	    }

	public static String getBowlersWithHighestEconomy() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestEconomy.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLMostWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.economy);
            descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

	public static String getBowlersWithHighestStrikingRateAnd4w5w() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSRWithWickets.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("The file contains no data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLMostWickets> iplComparator = Comparator.comparing(IPLMostWickets::getStrikeRate).thenComparing(IPLMostWickets::getFiveWicket).thenComparing(IPLMostWickets::getFourWicket);
            descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

}
