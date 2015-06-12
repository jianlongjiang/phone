package test3;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  str ="18258234555,18258234112,18258234113,18258234224,18258234225,18258234226,18258234227,18258235551,18258235563,18258235564"
				+"18258234892,18258234894,18258234897,18258234905,18258234909,18258234881,18258234111,18258234222,18258234333,18258234444"
				+"13211111111,13211111114,13211111115,13282138841,13506795557,18042309452,13282138842,15257149665,15257149666,18258234885";
		
		String[] arrays =  str.split(",");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String string : arrays) {
			if(map.get(string) != null){
				int   value = map.get(string);
				map.put(string, value+1); 
				System.out.println(string +    "---------------"+ (value+1));
			}else {
				map.put(string, 0+1); 
			}
		}
	}

}
