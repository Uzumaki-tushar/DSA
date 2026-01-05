import java.util.*;

class AlienDictionary {
    public String findOrder(String[] words) {

        // Step 1: Find maximum character
        char maxChar = 'a';
        for (String w : words) {
            for (char c : w.toCharArray()) {
                maxChar = (char) Math.max(maxChar, c);
            }
        }

        int maxVal = maxChar - 'a';

        // Graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= maxVal; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[maxVal + 1];
        boolean[] present = new boolean[maxVal + 1];

        // Mark present characters
        for (String w : words) {
            for (char c : w.toCharArray()) {
                present[c - 'a'] = true;
            }
        }

        // Step 2: Build graph
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            int len = Math.min(w1.length(), w2.length());
            int j = 0;

            while (j < len && w1.charAt(j) == w2.charAt(j)) {
                j++;
            }

            // Prefix invalid case
            if (j == len && w1.length() > w2.length()) {
                return "";
            }

            // Add edge
            if (j < len) {
                int u = w1.charAt(j) - 'a';
                int v = w2.charAt(j) - 'a';

                if (!adj.get(u).contains(v)) {
                    adj.get(u).add(v);
                    indegree[v]++;
                }
            }
        }

        // Step 3: Kahn's Algorithm
        Queue<Integer> q = new LinkedList<>();
        int totalChars = 0;

        for (int i = 0; i <= maxVal; i++) {
            if (present[i]) {
                totalChars++;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!q.isEmpty()) {
            int node = q.poll();
            ans.append((char) ('a' + node));

            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        // Step 4: Cycle check
        if (ans.length() != totalChars) {
            return "";
        }

        return ans.toString();
    }
}