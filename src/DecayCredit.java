import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DecayCredit {
	private Map<Integer, Map<Integer, Integer>> user_map;
    private Map<Integer, Map<Integer, Integer>> list_map;
    public double decay = 0.5;
    public int thres = 100;
    
    
    public void topk (String input_ub, String input_lb, String input_ranked, String output, int k) throws IOException {
    	user_map = loadData.loadUB(input_ub);
        list_map = loadData.loadLB_book(input_lb);
        FileWriter fw = new FileWriter(output);

        // for each user, calculate the weighted coverage of each list independently
        Set<Integer> users= user_map.keySet();

        for (Integer user: users) {
        	System.out.println(user);
        	fw.write(Integer.toString(user));
        	// already_count counts the number of occurrence
        	Map<Integer, Integer> already_count = new HashMap<Integer, Integer>();
        	Set<Integer> books = user_map.get(user).keySet();
        	List<Integer> lists = loadData.loadListRanked(input_ranked);
        	for (int i = 0; i < k; i++) {
        		int max = extractMax(books, lists, already_count);
        		fw.write(" " + Integer.toString(max));
        	}
        	fw.write(System.getProperty("line.separator"));
        }
        fw.close();
    }
    
	private int extractMax(Set<Integer> books, List<Integer> lists,
			Map<Integer, Integer> already_count) {
		// TODO Auto-generated method stub
		double max_credit = -1;
		int max_id = -1, max_index = -1;
		for (int i = 0; i < lists.size(); i++) {
			int list = lists.get(i);
			Map<Integer, Integer> cur_list_map = list_map.get(list);
			double credit = computeCredit(books, cur_list_map, already_count);
			if (credit > max_credit) {
				max_credit = credit;
				max_id = list;
				max_index = i;
			}
		}
		lists.remove(max_index);
		return max_id;
	}
	
	private double computeCredit(Set<Integer> books, Map<Integer, Integer> list,
			Map<Integer, Integer> already_count) {
		double credit = 0;
		for (Integer book: books) {
			if (list.containsKey(book)) {
				// only look at the top thres
				if (list.get(book) >= thres)
					continue;
				double cur_credit = (list.size() - list.get(book) ) * 1.0 / (list.size() + 1);
				if (already_count.containsKey(book)) {
					credit += cur_credit * Math.pow(decay, already_count.get(book));
					already_count.put(book, already_count.get(book)+1);
				}
				else {
					credit += cur_credit;
					already_count.put(book, 1);
				}
			}
		}
		return credit;
	}
}