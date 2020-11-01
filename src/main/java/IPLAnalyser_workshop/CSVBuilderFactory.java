package IPLAnalyser_workshop;

public class CSVBuilderFactory {
	
	public static <E> ICSVBuilder<E> createCSVBuilder(){
	return new OpenCSVBuilder<E>();
	}
}
