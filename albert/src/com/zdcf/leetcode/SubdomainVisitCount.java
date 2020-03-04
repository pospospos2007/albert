package com.zdcf.leetcode;
//811.Subdomain Visit Count
//A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
//
//Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
//
//We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
//
//Example 1:
//Input: 
//["9001 discuss.leetcode.com"]
//Output: 
//["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
//Explanation: 
//We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
//
//Example 2:
//Input: 
//["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
//Output: 
//["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
//Explanation: 
//We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
//
//Notes:
//
//The length of cpdomains will not exceed 100. 
//The length of each domain name will not exceed 100.
//Each address will have either 1 or 2 "." characters.
//The input count in any count-paired domain will not exceed 10000.
//The answer output can be returned in any order.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//给定一串域名List，将访问的相同域名和父域名的次数分别累加，返回各个域名访问次数列表
//这道题就是主要要处理好分割字符串“.”的问题，应该有更好的空间复杂度的解法
public class SubdomainVisitCount {
	public List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        String domain=null;
        for(int i=0,count = 0;i<cpdomains.length;i++){
            count = Integer.valueOf(cpdomains[i].split(" ")[0]);
            domain = cpdomains[i].split(" ")[1];
            boolean isTail = false;
            while(domain!=null){
                if(map.containsKey(domain)){
                    map.put(domain,count+map.get(domain));
                }else{
                    map.put(domain,count);
                }
                if(domain.indexOf(".")!=-1){
                    domain = domain.substring(domain.indexOf(".")+1,domain.length());
                }else{
                    isTail = true;
                }
                if(isTail){
                    break;
                }
                
            }
        }
        List<String> result = new ArrayList<String>();
        int i=0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  
            result.add(entry.getValue()+" "+entry.getKey());
        }  
        return result;
    }
}
