package br.com.api.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	public static List<Integer> decodeIntList(String s){
		List<Integer> list = new ArrayList<>();
		String [] vet = s.split(",");
		for (String string : vet) {
			list.add(Integer.parseInt(string));
			
		}
		return list;
		
		/* poderia rezudir esse codigo para:
		 * return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		 */
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
