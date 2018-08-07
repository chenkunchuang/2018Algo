import java.util.*;

/*
* Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
* and the rest of the elements are emails representing emails of the account.
* Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts.
* Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
* A person can have any number of accounts initially, but all of their accounts definitely have the same name.
* After merging the accounts, return the accounts
* in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order.
* The accounts themselves can be returned in any order.
 */
public class AccountMerger {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        AccountMerger sol = new AccountMerger();
        String[][] data= {{"John", "johnsmith@mail.com", "john00@mail.com"},
            {"John", "johnnybravo@mail.com"}, {"John", "johnsmith@mail.com","john_newyork@mail.com"},
            {"Mary", "mary@mail.com"}};
        List<List<String>> input = new ArrayList<>();
        for(int i=0; i<data.length; i++) {
            List<String> temp = new ArrayList<>();
            for(int j=0; j<data[i].length; j++)
                temp.add(data[i][j]);
            input.add(temp);
        }


        List<List<String>> ans = sol.accountsMerge(input);

        for(List<String> acc:ans)
            System.out.println("account:" + acc);

    }

}
