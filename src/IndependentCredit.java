import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class IndependentCredit {
	private Map<Integer, Map<Integer, Integer>> user_map;
	private Map<Integer, Map<Integer, Integer>> list_map;
	public int thres = 100;
	
	private double computeCredit(Set<Integer> books, Map<Integer, Integer> list) {		
		double credit = 0.0;
		for (Integer book: books) {
			// rel(u,t) * pos(t,l)
			if (list.containsKey(book) && list.get(book) < thres) {
				double rel_ut = (list.size() - list.get(book) ) * 1.0 / (list.size() + 1);
				credit += rel_ut;
			}
		}
		return credit;
	}
	
	public void topk(String input_ub, String input_lb, String input_ranked, String output, int k) throws IOException {
		user_map = loadData.loadUB(input_ub);
		list_map = loadData.loadLB_book(input_lb);
		Set<Integer> users = user_map.keySet();
		List<Integer>  lists;
		if (input_ranked != "")
			lists = loadData.loadListRanked(input_ranked);
		else {
			lists = new ArrayList<Integer>();
			for (int list: list_map.keySet()) {
				lists.add(list);
			}
		}
		FileWriter fw = new FileWriter(output);
		
		PairComparator comparator = new PairComparator();
		PriorityQueue<Pair> topk = new PriorityQueue<Pair>(k, comparator);
		//List<Pair> topk = new ArrayList<Pair>();
		int count = 0;
		for (Integer user: users) {	
			topk.clear();
			System.out.println(user);
			// set of relevant books
			Set<Integer> books = user_map.get(user).keySet();
			// calculate coverage for every list
			for (int i = 0; i < lists.size(); i++) {
				int list = lists.get(i);
				double credit = computeCredit(books, list_map.get(list));
				topk.offer(new Pair(list, credit));
				System.out.println(list + "," + credit);
			}
			// store top k lists
			fw.write(Integer.toString(user));
			for(int i = 0; i < k; i++) {
				fw.write(" ");
				int id = topk.peek().list_id;
				System.out.println(topk.peek().credit);
				topk.poll();
				//int id = topk.get(i).list_id;
				fw.write(Integer.toString(id));
			}
			fw.write(System.getProperty("line.separator"));
		}
		
		fw.close();
	}
	
	
	
	class PairComparator implements Comparator<Pair>
	{
	    @Override
	    public int compare(Pair x, Pair y)
	    {
	        if (x.credit < y.credit)
	        {
	            return 1;
	        }
	        if (x.credit > y.credit)
	        {
	            return -1;
	        }
	        return 0;
	    }
	}
	
	class Pair {
		int list_id;
		double credit;
		Pair(int l, double c) {
			list_id = l;
			credit = c;
		}
	}
}