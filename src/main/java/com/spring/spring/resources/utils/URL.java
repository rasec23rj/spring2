package com.spring.spring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class URL {


	 public static String decodeParam(String s)  
     {  
               try {  
                    String prevURL="";  
                    String decodeURL=s;  
                    while(!prevURL.equals(decodeURL))  
                    {  
                         prevURL=decodeURL;  
                         decodeURL=URLDecoder.decode( decodeURL, "UTF-8" );  
                    }  
                    return decodeURL;  
               } catch (UnsupportedEncodingException e) {  
                    return "Issue while decoding" +e.getMessage();  
               }  
     }  
     public static String encode(String s)  
     {  
               try {  
                    String encodeURL=URLEncoder.encode( s, "UTF-8" );  
                    return encodeURL;  
               } catch (UnsupportedEncodingException e) {  
                    return "Issue while encoding" +e.getMessage();  
               }  
     }  
     
	
	public static List<Integer> decodeIntList(String s){
		
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
}
