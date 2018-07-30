package a_a;

import java.io.*;
import java.util.*;

public class fest_sol {
	public static void main(String args[]){
		Scanner s = new Scanner(System.in);
		String tc = s.nextLine();
		int t = Integer.valueOf(tc);
		for (int i=0;i<t;i++){
			s.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String nsp = s.nextLine();			
			int sp = Integer.valueOf(nsp);
			Map<String, ArrayList<Long>> map = new LinkedHashMap<>();
			Map<String, Long> map1 = new LinkedHashMap<>();
			for (int j=0;j<sp;j++){			  
				s.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
				String[] line = s.nextLine().split(" ");	
				if(map.containsKey(line[0])){
					ArrayList<Long> list = map.get(line[0]);
					list.add((long)Integer.parseInt(line[1]));
					map.put(line[0], list);
				}
				else{
					map.put(line[0], new ArrayList<>(Arrays.asList((long)Integer.parseInt(line[1]))));
				}				
			}
			map.forEach((k,v)->{
				Collections.sort(v);
				Collections.reverse(v);
				long sum=0;
				for (int m=0;m < v.size() && m < 3;m++){
					sum = sum + v.get(m);
					
				}
				map1.put(k, sum);
			});
			Map<String,Long> tree = new TreeMap<String, Long>(map1);
			long max=0;
			for (long l: tree.values()){
				if(l>max){
					max=l;
				}
			}
			
			for (Map.Entry m:tree.entrySet() ){
				if(m.getValue().equals(max)){
					System.out.println(m.getKey()+" "+m.getValue());
					break;
				}
				
			}
			
			
		}
		s.close();
	}

}
