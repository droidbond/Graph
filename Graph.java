public class GraphTest {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        PrintWriter out;

        void solve(int testNumber, Scanner in, PrintWriter out) {
            this.out = out;
            Graph<Integer> graph = new Graph<>();

            int t = in.nextInt();

            for (int i = 0; i < t; i++) {
                Integer u = in.nextInt();
                Integer v = in.nextInt();
                graph.addEdge(u, v);
            }
            graph.startBFS(in.nextInt());

        }

        public class Graph<T> {
            private Map<T, List<T>> edges = new HashMap<>();
            private Map<T, Boolean> visited = new HashMap<>();

            Graph() {
            }

            public void clearEdges() {
                edges = new HashMap<>();
                visited = new HashMap<>();
            }

            void dfs(T startNode, Map<T, Boolean> visited) {

                if (visited.get(startNode)) {
                    return;
                }

                visited.put(startNode, true);
                out.println(startNode);

                for (T node : edges.get(startNode)) {
                    dfs(node, visited);
                }
            }

            void bfs(T startNode, Map<T, Boolean> visited) {
                visited.put(startNode, true);
                Queue<T> queue = new LinkedList<>();

                queue.add(startNode);
                while (!queue.isEmpty()) {
                    T node = queue.poll();
                    out.print(node+ " ");
                    visited.put(node, true);

                    for (T n : edges.get(node)) {
                        if (!visited.get(n)) {
                            queue.add(n);
                            visited.put(n, true);
                        }
                    }
                }
            }

            void startBFS(T node) {
                bfs(node, visited);
            }

            void startDFS(T node) {
                dfs(node, visited);
            }

            void addEdge(T from, T to) {
                List<T> nodes = new ArrayList<>();

                if (edges.containsKey(from)) {
                    nodes = edges.get(from);
                }
                nodes.add(to);
                edges.put(from, nodes);

                if (!edges.containsKey(to)) {
                    edges.put(to, new ArrayList<>());
                }

                visited.put(from, false);
                visited.put(to, false);
            }
        }
    }

    static class Scanner {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
