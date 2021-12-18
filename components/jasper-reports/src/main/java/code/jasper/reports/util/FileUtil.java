package code.jasper.reports.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class FileUtil {
	private static final ClassLoader src = Thread.currentThread().getContextClassLoader(); 
	public static InputStream stream(File path, String fileName) throws Exception{
		return new FileInputStream(new File(path, fileName));
	} 
	public static InputStream stream(Object ... composition) throws Exception{
		String path = concat("/", composition);
    	return src.getResourceAsStream(path);
	} 
	public static File resource(Object ... composition) throws Exception {
		String path = concat("/", composition);
    	URL resource = src.getResource(path);
    	return new File(resource.toURI());
	}
	public static Path path(String root, String ... composition) {
		return Paths.get(root, composition);
	}
	public static Path path(String filePath) {
		return Paths.get(filePath);
	}
	public static File file(String filePath) {
		return path(filePath).toFile();
	}
	public static File file(String root, String ... composition) {
		return path(root, composition).toFile();
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
