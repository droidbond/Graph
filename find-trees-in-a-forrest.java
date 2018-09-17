import java.io.*;
import java.util.*;

public class Main {
    private static boolean[] visited;
    private static Map<Integer, List<Integer>> edgesMap;
    private static int count = 0;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        edgesMap = new HashMap<>();
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        Set<Integer> set = new HashSet<>();

        visited = new boolean[nodes];
        Arrays.fill(visited, false);

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            set.add(u);
            set.add(v);

            if (!edgesMap.containsKey(u)) {
                edgesMap.put(u, new ArrayList<>());
            }

            edgesMap.get(u).add(v);
        }

        set.forEach(x -> {
            if (!visited[x]) {
                dfs(x);
                count++;
            }
        });

        out.println(count);
        out.close();
    }

    private static void dfs(int node) {
        if (visited[node]) return;

        visited[node] = true;
        edgesMap.getOrDefault(node, new ArrayList<>()).forEach(n -> {
                    if (!visited[n]) dfs(n);
                }
        );
    }


    static class Scanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
