/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?

*/
class Solution {
    public String minWindow(String s1, String s2) {
        String ans="";
		HashMap<Character,Integer> map2 =new HashMap<>();
		for(int i=0;i<s2.length();i++){
		    char ch=s2.charAt(i);
		    map2.put(ch,map2.getOrDefault(ch,0)+1);
		}
		int matchCount=0;
		int desiredMatchCount=s2.length();
		
		HashMap<Character,Integer> map1 =new HashMap<>();

		int i=-1;
		int j=-1;
		while(true){
		    boolean flag1=false;
		    boolean flag2=false;
		    //acquire till matchcounnt is not equal to desired one
		    while(i<s1.length()-1 && matchCount<desiredMatchCount){
		        i++;
		        char ch=s1.charAt(i);
		        map1.put(ch,map1.getOrDefault(ch,0)+1);
		        if(map1.getOrDefault(ch,0) <= map2.getOrDefault(ch,0)){
		            matchCount++;
		        }
		        flag1=true;
		    }
		     
		     //releasae and collect answers
		     while(j<i && matchCount==desiredMatchCount){
		         String possibleAns=s1.substring(j+1,i+1);
		         if(ans.length()==0 || possibleAns.length()<ans.length()){
		             ans=possibleAns;
		         }
		         j++;
		         char ch=s1.charAt(j);
		         
		         if(map1.get(ch)==1){
		             map1.remove(ch);
		         }
		         else{
		             map1.put(ch,map1.get(ch)-1);
		         }
		         if(map1.getOrDefault(ch,0)<map2.getOrDefault(ch,0)){
		             matchCount--;
		         }
		         flag2=true;
		     }
		     if(flag1==false && flag2==false)
		        break;
		     
		}
		return ans;
    }
}


