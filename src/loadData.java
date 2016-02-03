import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.collections4.map.MultiValueMap;

public class loadData {
	public static ArrayList<String[]> loadListBook(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = split[0];					// listId
				pair[1] = books[i];					// bookId
				list.add(pair);
			}	
		}
		
		return list;		
	}
	
	public static ArrayList<String[]> loadUserList(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
		
			if (split.length == 1)
				continue;
			String[] users = split[1].split(",");
			for(int i = 0; i < users.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = users[i];					// userId
				pair[1] = split[0];					// listId
				list.add(pair);
			}	
		}
		
		return list;
	}
	
	public static ArrayList<String[]> loadUserBook(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			if(split.length == 1)
				continue;
			String[] books = split[1].split(",");
			for(int i = 0; i < books.length; i++)	//
			{
				String[] pair = new String[2];
				pair[0] = split[0];					// userId
				pair[1] = books[i];					// bookId
				list.add(pair);
			}	
		}
		
		return list;
	}
	
	public static ArrayList<String[]> loadListPopu(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] pair = new String[2];
			pair[0] = split[0];						// listId
			pair[1] = split[1];						// list popularity
			list.add(pair);		
		}
		
		return list;	
	}
	
	public static MultiValueMap<String, String> loadBLMap(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<String, String> mhm = new MultiValueMap<String, String>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = split[0];					// listId
				pair[1] = books[i];					// bookId
				
				mhm.put(pair[1],pair[0]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<Integer,Integer> loadBLMap_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<Integer,Integer> mhm = new MultiValueMap<Integer,Integer>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				int[] pair = new int[2];
				pair[0] = Integer.valueOf(split[0]);					// listId
				pair[1] = Integer.valueOf(books[i]);					// bookId
				
				mhm.put(pair[1],pair[0]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<String, String> loadUBMap(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<String, String> mhm = new MultiValueMap<String, String>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			if (split.length == 1) {
				System.out.println(tmp);
				continue;
			}
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = split[0];					// userId
				pair[1] = books[i];					// bookId
				
				mhm.put(pair[0],pair[1]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<Integer,Integer> loadUBMap_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<Integer,Integer> mhm = new MultiValueMap<Integer,Integer>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			if (split.length == 1) {
				System.out.println(tmp);
				continue;
			}
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				int[] pair = new int[2];
				pair[0] = Integer.valueOf(split[0]);					// userId
				pair[1] = Integer.valueOf(books[i]);					// bookId
				
				mhm.put(pair[0],pair[1]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<String, String> loadLBMap(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<String, String> mhm = new MultiValueMap<String, String>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = split[0];					// listId
				pair[1] = books[i];					// bookId
				
				mhm.put(pair[0], pair[1]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<Integer, Integer> loadLBMap_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<Integer, Integer> mhm = new MultiValueMap<Integer, Integer>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			
			for(int i = 0; i < books.length; i++)	//-1 for the last element null
			{
				int[] pair = new int[2];
				pair[0] = Integer.valueOf(split[0]);					// listId
				pair[1] = Integer.valueOf(books[i]);					// bookId
				
				mhm.put(pair[0], pair[1]);
			}	
			
		}
		
		return mhm;
	}
	
	public static MultiValueMap<String, String> loadULMap(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<String, String> mhm = new MultiValueMap<String, String>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
		
			if (split.length == 1)
				continue;
			String[] users = split[1].split(",");
			for(int i = 0; i < users.length; i++)	//-1 for the last element null
			{
				String[] pair = new String[2];
				pair[0] = users[i];					// userId
				pair[1] = split[0];					// listId
				mhm.put(pair[0],pair[1]);
			}	
		}
		
		return mhm;		
	}
	
	public static MultiValueMap<Integer, Integer> loadULMap_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		MultiValueMap<Integer, Integer> mhm = new MultiValueMap<Integer, Integer>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
		
			if (split.length == 1)
				continue;
			String[] users = split[1].split(",");
			for(int i = 0; i < users.length; i++)	//-1 for the last element null
			{
				int[] pair = new int[2];
				pair[0] = Integer.valueOf(users[i]);					// userId
				pair[1] = Integer.valueOf(split[0]);					// listId
				mhm.put(pair[0],pair[1]);
			}	
		}
		
		return mhm;		
	}
	
	public static ArrayList<int[]> loadUserList_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<int[]> list = new ArrayList<int[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
		
			if (split.length == 1)
				continue;
			String[] users = split[1].split(",");
			for(int i = 0; i < users.length; i++)
			{
				int[] pair = new int[2];
				pair[0] = Integer.parseInt(users[i]);					// userId
				pair[1] = Integer.parseInt(split[0]);					// listId
				list.add(pair);
			}	
		}
		
		return list;
	}
	
	
	public static ArrayList<int[]> loadUserBook_int(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<int[]> list = new ArrayList<int[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			if(split.length == 1)
				continue;
			String[] books = split[1].split(",");
			for(int i = 0; i < books.length; i++)	
			{
				int[] pair = new int[2];
				pair[0] = Integer.parseInt(split[0]);					// userId
				pair[1] = Integer.parseInt(books[i]);					// bookId
				list.add(pair);
			}	
		}
		
		return list;
	}
	
	public static ArrayList<int[]> loadListBook_int(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<int[]> list = new ArrayList<int[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] books = split[1].split(",");
			for(int i = 0; i < books.length; i++)
			{
				int[] pair = new int[2];
				pair[0] = Integer.parseInt(split[0]);					// listId
				pair[1] = Integer.parseInt(books[i]);					// bookId
				list.add(pair);
			}	
		}
		
		return list;		
	}
	
	public static ArrayList<int[]> loadTraining(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<int[]> list = new ArrayList<int[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			int[] pair = new int[2];
			pair[0] = Integer.valueOf(split[0]);						// usrid
			pair[1] = Integer.valueOf(split[1]);						// listid
			list.add(pair);		
		}	
		return list;
	}
	
	public static ArrayList<int[]> loadTesting(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<int[]> list = new ArrayList<int[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			int[] pair = new int[2];
			pair[0] = Integer.valueOf(split[0]);						// usrid
			pair[1] = Integer.valueOf(split[1]);						// listid
			list.add(pair);		
		}	
		return list;
	}
	
	public static ArrayList<String[]> loadTraining_str(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] pair = new String[2];
			pair[0] = split[0];						// usrid
			pair[1] = split[1];						// listid
			list.add(pair);		
		}	
		return list;
	}
	
	public static ArrayList<String[]> loadTesting_str(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			String[] pair = new String[2];
			pair[0] = split[0];						// usrid
			pair[1] = split[1];						// listid
			list.add(pair);		
		}	
		return list;
	}
	
	public static Map<Integer, Map<Integer, Integer>> loadLB_pos(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			tmp = tmp.replace(" ", "\t");

			String[] split = tmp.split("\t");			
			Map<Integer, Integer> book_pos = new HashMap<Integer, Integer>();
			int list = Integer.valueOf(split[0]);
			if(split.length == 1)
				continue;
			// for stackoverflow
//			for (int pos = 1; pos < split.length; pos++) {
//				int book = Integer.valueOf(split[pos]);
//				book_pos.put(pos-1, book);
//			}
			// for goodreads
			String[] books = split[1].split(",");
			for (int pos = 0; pos < books.length; pos++) {
				int book = Integer.valueOf(books[pos]);
				book_pos.put(pos, book);			// pos as id, book as value
			}
			
			map.put(list, book_pos);
		}	
		return map;
	}
	
	public static Map<Integer, Map<Integer, Integer>> loadLB_book(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			tmp = tmp.replace(" ", "\t");

			String[] split = tmp.split("\t");			
			Map<Integer, Integer> book_pos = new HashMap<Integer, Integer>();
			int list = Integer.valueOf(split[0]);
			if(split.length == 1)
				continue;
//			for (int pos = 1; pos < split.length; pos++) {
//				int book = Integer.valueOf(split[pos]);
//				book_pos.put(book, pos-1);
//			}
			
			String[] books = split[1].split(",");
			for (int pos = 0; pos < books.length; pos++) {
				int book = Integer.valueOf(books[pos]);
				book_pos.put(book, pos);			// book as key, pos as value
			}
			
			map.put(list, book_pos);
		}	
		return map;
	}
	
	public static Map<Integer, Map<Integer, Integer>> loadUB(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			tmp = tmp.replace(" ", "\t");
			String[] split = tmp.split("\t");
			Map<Integer, Integer> book_pos = new HashMap<Integer, Integer>();
			int user = Integer.valueOf(split[0]);
			if(split.length == 1)
				continue;
			
//			for (int pos = 1; pos < split.length; pos++) {
//				int book = Integer.valueOf(split[pos]);
//				book_pos.put(book, pos-1);
//			}
			
			String[] books = split[1].split(",");
			for (int pos = 0; pos < books.length; pos++) {
				int book = Integer.valueOf(books[pos]);
				book_pos.put(book, pos);
			}
			
			map.put(user, book_pos);
		}	
		br.close();
		return map;
	}
	
	public static ArrayList<Integer> loadListRanked(String filename) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String tmp = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while((tmp = br.readLine()) != null) {
			if(tmp.equals(""))
				continue;
			tmp = tmp.replace("\r\n", "");
			String[] split = tmp.split("\t");
			int list_id = Integer.valueOf(split[0]);
			list.add(list_id);		
		}	
		return list;	
	}
}
