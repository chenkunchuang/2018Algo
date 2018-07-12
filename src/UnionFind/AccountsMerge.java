package UnionFind;
import java.util.*;
/*
Given a list accounts, each element accounts[i] is a list of strings,
where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

 */

public class AccountsMerge {

    //這個union-find 是優化, weighted union + path compression
    public static class UF {
        private int[] father;
        private int[] sz;
        private int count;
        private final int NUM_OF_ACCOUNTS = 10001;

        public UF() {
            father = new int[NUM_OF_ACCOUNTS];
            sz = new int[NUM_OF_ACCOUNTS];

            for(int i=0; i<NUM_OF_ACCOUNTS; i++) {
                father[i] = i;
                sz[i] = 1;
                count++;
            }
        }
        public int find(int i) {
            while(i!=father[i]) {
                father[i] = father[father[i]];
                i = father[i];
            }
            return i;
        }

        public boolean isConnected(int p, int q) {return find(p)==find(q);}

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);

            if(i==j) return;

            if(sz[i]<sz[j]) {
                father[i] = j;
                sz[j] +=sz[i];
            }
            else
            {
                father[j] = i;
                sz[i] +=sz[j];
            }
            count--;
            return;
        }
    }

    private List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> emailToName = new HashMap<>();
        HashMap<String, Integer> emailToID = new HashMap<>();
        UF uf = new UF();

        List<List<String>> ret = new ArrayList<>();
        int id = 0;
        for(int i=0; i<accounts.size(); i++) {
            String name = "";
            for(String email: accounts.get(i)){
                if(name.equals(""))
                {
                    name = email;
                    continue;
                }
                if(emailToName.get(email)==null)
                    emailToName.put(email, name);

                if(emailToID.containsKey(email)!=true)
                    emailToID.put(email, id++);

                //做union
                uf.union(emailToID.get(accounts.get(i).get(1)), emailToID.get(email));
            }
        }

        HashMap<Integer, List<String>> ans = new HashMap<>();

        for(String email:emailToName.keySet()) {
            int i = uf.find(emailToID.get(email));
            if(ans.get(i)==null)
                ans.put(i, new ArrayList<>());

            ans.get(i).add(email);
        }

        for(List<String> compound: ans.values()) {
            Collections.sort(compound);
            compound.add(0, emailToName.get(compound.get(0)));
        }

        System.out.println(ans.values());

        return new ArrayList<>(ans.values());
    }

    public static void main(String[] args) {
        String[][] accounts = {{"John", "johnsmith@mail.com", "john00@mail.com"}, {"John", "johnnybravo@mail.com"},
            {"John", "johnsmith@mail.com", "john_newyork@mail.com"},{"Mary", "mary@mail.com"} };

        List<List<String>> acc = new ArrayList<>();

        for(int i=0; i<accounts.length; i++)
            acc.add(Arrays.asList(accounts[i]));

        AccountsMerge sol = new AccountsMerge();
        sol.accountsMerge(acc);


    }
}
