import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateResult {
	private int topK = 20, topItems = 100;
	private double nestedThreshold = 0.7;
	// recommend map: <User, <Pos, List>>
	private Map<Integer, Map<Integer, Integer>> mapRecommend;
	// ground truth map: <User, <Pos, List>>
	private Map<Integer, Map<Integer, Integer>> mapGround;
	// evaluation result
	private Map<Integer, Double> mapEvaluation;
	// list-book table: <list, <pos, book>>
	private Map<Integer, Map<Integer, Integer>> mapListPosBook;
	
	/**
	 * Initialization
	 * @param recommend
	 * @param ground
	 * @throws IOException
	 */
	private void init(String recommend, String ground, String measure, String listBookTable) throws IOException {
		mapEvaluation = new HashMap<Integer, Double>();
		mapRecommend = new HashMap<Integer, Map<Integer, Integer>>();
		mapGround = new HashMap<Integer, Map<Integer, Integer>>();
		// read from files
		BufferedReader br = new BufferedReader(new FileReader(recommend));
		String tmp;
		while ((tmp = br.readLine()) != null) {
			String[] split = tmp.split("\t");
			int userId = Integer.valueOf(split[0]);
			String[] lists = split[1].split(",");
			Map<Integer, Integer> mapPosList = new HashMap<Integer, Integer>();
			for (int i = 0; i < lists.length; i++) {
				mapPosList.put(i, Integer.valueOf(lists[i]));
			}
			mapRecommend.put(userId, mapPosList);
		}

		br = new BufferedReader(new FileReader(ground));
		while ((tmp = br.readLine()) != null) {
			String[] split = tmp.split("\t");
			int userId = Integer.valueOf(split[0]);
			String[] lists = split[1].split(",");
			Map<Integer, Integer> mapPosList = new HashMap<Integer, Integer>();
			for (int i = 0; i < lists.length; i++) {
				mapPosList.put(i, Integer.valueOf(lists[i]));
			}
			mapGround.put(userId, mapPosList);
		}
		
		// if nested jaccard, load list book table
		if (measure.equals("nested")) {
			mapListPosBook = loadData.loadLB_pos(listBookTable);
		}
		br.close();
	}
	
	/**
	 * Jaccard measurement
	 * @param userId
	 * @param nested
	 * @return
	 */
	private double jaccard (int userId, boolean nested) {
		double res = 0.0;
		// <pos, list>
		Map<Integer, Integer> userRecommend = mapRecommend.get(userId);
		Map<Integer, Integer> userGround = mapGround.get(userId);
		
		Set<Integer> userRelevantLists = (Set<Integer>) userGround.values();
		int numIntersection = 0, numUnion = userRelevantLists.size();
		for (int i = 0; i < topK; i++) {
			int predictListId = userRecommend.get(i);
			if (nested) {		// nested Jaccard
				int similarList = findSimilarList(predictListId, userRelevantLists);
				if (userRelevantLists.contains(similarList)) {
					numIntersection++;
					userRelevantLists.remove(similarList);
				} else {
					numUnion++;
				}
			} else {
				if (userRelevantLists.contains(predictListId)) {
					numIntersection++;
				} else {
					numUnion++;
				}
			}
		}
		res = numIntersection * 1.0 / numUnion;
		return res;
	}
	
	/**
	 * Find out a relevant list which is most similar to the predict list. If the similarity is less than the threshold, return -1
	 * @param predictListId
	 * @param userRelevantLists
	 * @return The most similar consumed list, or -1.
	 */
	private int findSimilarList(int predictListId,
			Set<Integer> userRelevantLists) {
		double maxSimilarity = 0.0;
		int maxId = -1;
		for (int consumedListId : userRelevantLists) {
			double sim = getJaccardBooks(consumedListId, predictListId);
			if (sim > maxSimilarity) {
				maxId = consumedListId;
				maxSimilarity = sim;
			}
		}
		return maxSimilarity >= nestedThreshold ? maxId : -1;
	}
	
	/**
	 * Get the jaccard measure of two user-generated lists
	 * @param consumedListId
	 * @param predictListId
	 * @return
	 */
	private double getJaccardBooks(int consumedListId, int predictListId) {
		Set<Integer> consumedListBooks = (Set<Integer>) mapListPosBook.get(consumedListId).values();
		Set<Integer> predictListBooks = (Set<Integer>) mapListPosBook.get(predictListId).values();
		int same = 0, union = consumedListBooks.size();
		for (int book : predictListBooks) {
			if (consumedListBooks.contains(book)) {
				same++;
			} else {
				union++;
			}
		}
		return same * 1.0 / union;
	}

	/**
	 * Evaluate the quality of recommendation result compared with the ground truth.
	 * @param recommend
	 * @param ground
	 * @param measure
	 * @throws IOException
	 */
	public void evaluate(String recommend, String ground, String measure, String listBookTable) throws IOException {
		// init maps
		init(recommend, ground, measure, listBookTable); 
		
		// for every user, get the measurement value
		for (Integer userId : mapRecommend.keySet()) {
			double similarity = 0;
			if (measure.equals("jaccard")) {
				similarity = jaccard(userId, false);
			} else if (measure.equals("nested")) {
				similarity = jaccard(userId, true);
			}
			mapEvaluation.put(userId, similarity);
		}
		
		// Visualization for evaluation result
		
	}
}