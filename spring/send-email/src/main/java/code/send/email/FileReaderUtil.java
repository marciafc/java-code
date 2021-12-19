package code.send.email;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderUtil {
	public static Stream<String> stream(String filePath) throws IOException{
		return Files.lines(Paths.get(filePath));
	}
	public static List<String> list(String filePath) throws IOException{
		return stream(filePath).collect(Collectors.toList());
	}
	
}