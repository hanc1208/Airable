package kr.co.airbridge.airable.map;

/* The authors of this work have released all rights to it and placed it
in the public domain under the Creative Commons CC0 1.0 waiver
(http://creativecommons.org/publicdomain/zero/1.0/).

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Retrieved from: http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?oldid=15444

Modified by 한창수 for Insider Path Finder
*/


import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {

    public static class Vertex implements Comparable<Vertex> {
        public Object object;
        public Edge[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public Vertex(Object object) {
            this.object = object;
        }

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }
    }

    public static class Edge {
        public Vertex target;
        public double weight;

        public Edge(Vertex argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static kr.co.airbridge.airable.map.Vertex[] getShortestPath(kr.co.airbridge.airable.map.Vertex[] vertexes, int start, int end) {
        Vertex[] builtVertexes = new Vertex[vertexes.length];
        Vertex startVertex = null, endVertex = null;

        for (int i = 0; i < vertexes.length; i++) {
            builtVertexes[i] = new Vertex(vertexes[i]);
            if (vertexes[i].getId() == start) {
                startVertex = builtVertexes[i];
            }
            if (vertexes[i].getId() == end) {
                endVertex = builtVertexes[i];
            }
        }

        for (int i = 0; i < vertexes.length; i++) {
            kr.co.airbridge.airable.map.Vertex data = vertexes[i];

            Edge[] adjacencies = new Edge[data.getAdjacencies().length];
            for (int j = 0; j < data.getAdjacencies().length; j++) {
                for (int k = 0; k < vertexes.length; k++) {
                    if (vertexes[k].getId() == data.getAdjacencies()[j]) {
                        adjacencies[j] = new Edge(builtVertexes[k], Math.sqrt(Math.pow(data.getX() - vertexes[k].getX(), 2) + Math.pow(data.getY() - vertexes[k].getY(), 2)));
                        break;
                    }
                }
            }

            builtVertexes[i].adjacencies = adjacencies;
        }

        Dijkstra.computePaths(startVertex);

        List<Vertex> builtResult = Dijkstra.getShortestPathTo(endVertex);
        kr.co.airbridge.airable.map.Vertex[] result = new kr.co.airbridge.airable.map.Vertex[builtResult.size()];

        for (int i = 0; i < builtResult.size(); i++) {
            result[i] = (kr.co.airbridge.airable.map.Vertex) builtResult.get(i).object;
        }

        return result;
    }
}
