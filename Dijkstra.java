import java.io.*;
import java.util.*;

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

            int n = in.nextInt();
            int m = in.nextInt();

            for (int i = 0; i < m; i++) {
                Integer u = in.nextInt();
                Integer v = in.nextInt();
                Integer cost = in.nextInt();

                graph.addEdge(u, v, cost);
                graph.addEdge(v, u, cost);
            }
            graph.startDisjkstra(1);
            graph.getPath(n);

        }

        public class Graph<T> {

            Map<Node, List<Node>> vertices = new HashMap<>();
            Map<Edge, Integer> edges = new HashMap<>();
            Map<Node, Integer> costs = new HashMap<>();
            Map<Node, Boolean> visited = new HashMap<>();
            Map<Node, Node> path = new HashMap<>();

            Graph() {
            }

            private void dijkstra(Node startNode, Map<Node, Boolean> visited) {
                PriorityQueue<Node> nodesQueue = new PriorityQueue<>(new Comparator());

                nodesQueue.add(startNode);
                visited.put(startNode, true);
                costs.put(startNode, 0);

                while (!nodesQueue.isEmpty()) {
                    Node source = nodesQueue.poll();
                    visited.put(source, true);

                    for (Node node : vertices.get(source)) {

                        if ((edges.get(new Edge(source, node)) + costs.get(source)) < costs.get(node)) {
                            costs.put(node, edges.get(new Edge(source, node)) + costs.get(source));
                            path.put(node, source);
                        }
                        if (!visited.get(node)) {
                            nodesQueue.add(node);
                        }
                    }
                }

            }

            public void startDisjkstra(T start) {
                dijkstra(new Node(start), visited);
            }

            public void addEdge(T from, T to, Integer cost) {

                List<Node> nodes = new ArrayList<>();

                if (vertices.containsKey(new Node(from))) {
                    nodes = vertices.get(new Node(from));
                }
                nodes.add(new Node(to));
                vertices.put(new Node(from), nodes);

                if (!vertices.containsKey(new Node(to))) {
                    vertices.put(new Node(to), new ArrayList<>());
                }

                visited.put(new Node(from), false);
                visited.put(new Node(to), false);

                edges.put(new Edge(new Node(from), new Node(to)), cost);
                costs.put(new Node(from), Integer.MAX_VALUE);
                costs.put(new Node(to), Integer.MAX_VALUE);
            }

            public void getPath(T node) {

                Node target = new Node(node);
                Node step = target;
                List<Node> steps = new ArrayList<>();

                steps.add(target);

                while (path.get(step) != null) {
                    step = path.get(step);
                    steps.add(step);
                }
                Collections.reverse(steps);
                if(steps.size() ==1){
                    out.println(-1);
                    return;
                }
                for (Node n : steps) {
                    out.print(n.name + " ");
                }
            }

            class Comparator implements java.util.Comparator<Node> {

                @Override
                public int compare(Node o1, Node o2) {
                    return costs.get(o1) - costs.get(o2);
                }
            }

            class Edge {
                Node source;
                Node destination;

                public Edge(Node source, Node destination) {
                    this.source = source;
                    this.destination = destination;
                }

                @Override
                public int hashCode() {
                    final int prime = 31;
                    int result = 1;
                    result = prime * result + (source.hashCode() + destination.hashCode());
                    return result;
                }

                @Override
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null) {
                        return false;
                    }
                    if (getClass() != obj.getClass()) {
                        return false;
                    }

                    Edge edge = (Edge) obj;
                    if (edge.source.equals(source) && edge.destination.equals(destination)) {
                        return true;
                    }

                    return false;
                }
            }

            class Node {
                T name;

                public Node(T name) {
                    this.name = name;
                }


                @Override
                public int hashCode() {
                    final int prime = 31;
                    int result = 1;
                    result = prime * result + ((name == null) ? 0 : name.hashCode());
                    return result;
                }

                @Override
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null) {
                        return false;
                    }
                    if (getClass() != obj.getClass()) {
                        return false;
                    }
                    if (((Node) obj).name.equals(name)) {
                        return true;
                    }
                    return false;
                }
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
