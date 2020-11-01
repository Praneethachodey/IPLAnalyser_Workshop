package IPLAnalyser_workshop;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E>{
	public Iterator<E> getCSSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    public  <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;


}
