// https://leetcode.com/problems/top-k-frequent-elements/description/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        int len = nums.length;
        List[] buckets = new List[len + 1];
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int freq = entry.getValue();
            if (buckets[freq] == null){
                buckets[freq] = new ArrayList<Integer>();
            }
            buckets[freq].add(entry.getKey());
        }
        
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        
        for (int i = len; i >= 0 && res.size() < k; i --){
            if (buckets[i] != null){
                res.addAll(buckets[i]);
                cnt ++;
            }
        }
        
        return res;
    }
}
