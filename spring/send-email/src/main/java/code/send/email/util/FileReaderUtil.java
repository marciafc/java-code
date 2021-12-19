package code.send.email.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderUtil {
	private static final ClassLoader src = Thread.currentThread().getContextClassLoader(); 
	public static Stream<String> stream(String filePath) throws IOException{
		return Files.lines(Paths.get(filePath));
	}
	public static List<String> list(String filePath) throws IOException{
		return stream(filePath).collect(Collectors.toList());
	}
	public static File resource(Object ... composition) throws Exception {
		String path = concat("/", composition);
    	URL resource = src.getResource(path);
    	return new File(resource.toURI());
	}
	private static String concat(String delimiter, Object ... values) {
    	List strs = new ArrayList<>(Arrays.asList(values));
    	return concat(delimiter, strs);
    }
	private static String concat(String delimiter,List values) {
    	StringJoiner sj = new StringJoiner(delimiter, "", "");
    	for(Object v: values) {
    		sj.add(Objects.toString(v,""));
    	}
    	return sj.toString();
	}
}