package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
		
//	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
//	{
//		//read json and convert it to string
//		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\LoginFunctionality.json"),
//				StandardCharsets.UTF_8);
//		//Need to use jackson databind utility to convert jsonContent string to HashMap
//		
//		ObjectMapper mapper = new ObjectMapper(); 
//		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
//			});
//		//The data is now retreived in the form of a List
//		return data;
//		
//	}
}
