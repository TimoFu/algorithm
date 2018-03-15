Sliding window problem we can have a template to solve it;

模板：
int j = 0;
for (int i = 0; i < n; i ++){
	while (j < n){
		if (meet some case){
			j ++;
			//更新j状态
		}else{
			break;
		}
	}
}


例题(lc: Sliding window problem)：
https://leetcode.com/problems/minimum-size-subarray-sum/description/
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/


Other problems: 

    /**
     * Return distinct substrings of input string of size K with K distinct characters
     * find("awaglknagawunagwkwagl", 4) -> [wagl, aglk, glkn, lkna, knag, gawu, awun, wuna, unag, nagw, agwk, kwag]
     * @param s
     * @param k
     * @return
     */
    public static List<String> find(String s, int k){
        Set<Character> set = new HashSet<>();
        int j = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++){
            while (j < s.length()){
                char c = s.charAt(j);
                if (j - i == k){
                    break;
                }

                if (set.contains(c)){
                    break;
                }else{
                    if (set.size() == k){
                        break;
                    }
                    set.add(c);
                }
                j ++;
            }

            if (set.size() == k && j - i == k && !res.contains(s.substring(i, j))){
                res.add(new String(s.substring(i, j)));
            }
            set.remove(s.charAt(i));
        }

        return res;
    }

    /**
     * Find shortest tags in all tags;
     * new String[]{"made","in","china"}, new String[]{"made", "a","b","made", "c","in", "china","made","b","c","d"})
     * return [5, 7]
     * @param target
     * @param tags
     * @return
     */
    public static int[] findAllTag(String[] target, String[] tags){
        Map<String, Integer> targetMap = new HashMap<>();
        Map<String, Integer> tagMap = new HashMap<>();

        initTargetMap(targetMap, target);

        int j = 0, minLen = Integer.MAX_VALUE;
        int[] ans = new int[]{-1, -1};

        for (int i = 0; i < tags.length; i ++){
            while (j < tags.length && !match(targetMap, tagMap)){
                tagMap.put(tags[j], tagMap.getOrDefault(tags[j], 0) + 1);
                j ++;
            }

            if (match(targetMap, tagMap)){
                if (minLen > j - i){
                    minLen = j - i;
                    ans[0] = i;
                    ans[1] = j- 1;
                }
            }

            tagMap.put(tags[i], tagMap.getOrDefault(tags[i], 0) - 1);
            if (tagMap.get(tags[i]) == 0){
                tagMap.remove(tags[i]);
            }
        }

        return ans;
    }

    private static void initTargetMap(Map<String, Integer> targetMap, String[] args){
        for (String s : args){
            targetMap.put(s, targetMap.getOrDefault(s, 0) + 1);
        }
    }

    private static boolean match(Map<String, Integer> targetMap, Map<String, Integer> tagMap){
        for (String key : targetMap.keySet()){
            if (!tagMap.containsKey(key)){
                return false;
            }
            if (targetMap.get(key) > tagMap.get(key)){
                return false;
            }
        }

        return true;
    }

