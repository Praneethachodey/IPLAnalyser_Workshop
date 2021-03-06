package IPLAnalyser_workshop;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRuns {

	 @CsvBindByName(column = "POS")
	    public int position;

	    @CsvBindByName(column = "PLAYER")
	    public String player;

	    @CsvBindByName(column = "Mat")
	    public int matches;

	    @CsvBindByName(column = "Inns")
	    public int innings;

	    @CsvBindByName(column = "NO")
	    public int notOuts;

	    @CsvBindByName(column = "Runs")
	    public int runs;

	    @CsvBindByName(column = "HS")
	    public String highest;

	    @CsvBindByName(column = "Avg")
	    public double avg;

	    @CsvBindByName(column = "BF")
	    public int bf;

	    @CsvBindByName(column = "SR")
	    public double strikeRate;

	    @CsvBindByName(column = "100")
	    public int hundreds;

	    @CsvBindByName(column = "50")
	    public int fifties;

	    @CsvBindByName(column = "4s")
	    public int fours;

	    @CsvBindByName(column = "6s")
	    public int sixes;

	    @Override
	    public String toString() {
	        return "IPLRuns{" +
	                "position=" + position +
	                ", player='" + player + '\'' +
	                ", matches=" + matches +
	                ", innings=" + innings +
	                ", notOuts=" + notOuts +
	                ", runs=" + runs +
	                ", highest='" + highest + '\'' +
	                ", avg=" + avg +
	                ", bf=" + bf +
	                ", strikeRate=" + strikeRate +
	                ", hundreds=" + hundreds +
	                ", fifties=" + fifties +
	                ", fours=" + fours +
	                ", sixes=" + sixes +
	                '}';
	    }

	    public int getPosition() {
	        return position;
	    }

	    public String getPlayer() {
	        return player;
	    }

	    public int getMatches() {
	        return matches;
	    }

	    public double getStrikeRate() {
	        return strikeRate;
	    }

	    public double getAvg() {
	        return avg;
	    }

	    public int getSixes() {
	        return sixes;
	    }

	    public int getInnings() {
	        return innings;
	    }

	    public int getNotOuts() {
	        return notOuts;
	    }

	    public int getRuns() {
	        return runs;
	    }

	    public String getHighest() {
	        return highest;
	    }

	    public int getBf() {
	        return bf;
	    }

	    public int getHundreds() {
	        return hundreds;
	    }

	    public int getFifties() {
	        return fifties;
	    }

	    public int getFours() {
	        return fours;
	    }
	}