package HackerrankSolutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


class Graph{
	
	private List<Vertex> vertexes = new ArrayList<>();
		
	private Vertex start = null;
	
	public Graph(int[][] edgesArr, int n, int startVertex) {
		//add vertexes 
		for(int i = 1;i<=n;i++) {
			Vertex v = new Vertex(i);
			if(i == startVertex) start = v;
			vertexes.add(v);			
		}
		//add edges for each vertex
		for(int i = 0;i<edgesArr.length;i++) {
			Edge e1 = new Edge(vertexes.get(edgesArr[i][0]-1),vertexes.get(edgesArr[i][1]-1),edgesArr[i][2]);
			vertexes.get(edgesArr[i][0]-1).addEdge(e1);
			Edge e2 = new Edge(vertexes.get(edgesArr[i][1]-1),vertexes.get(edgesArr[i][0]-1),edgesArr[i][2]);
			vertexes.get(edgesArr[i][1]-1).addEdge(e2);	
		}		
	}
	
	public List<Vertex> getVertexes(){
		return this.vertexes;
	}
	
	public Vertex getStart() {
		return this.start;
	}
	
}

class Vertex{
	
	int vertex = 0;
	
	private List<Edge> edges = new ArrayList<>();
	
	public Vertex(int vertex){
		this.vertex = vertex;
	}
	
	public int getVertex() {
		return this.vertex;
	}
	
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	
	public List<Edge> getEdges(){
		return this.edges;
	}
	
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
}

class Edge implements Comparable<Edge>{
	Vertex start = null;
	Vertex end = null ;
	int cost = 0;
	
	Edge(Vertex vertStart,Vertex vertEnd,int cost){
		this.start = vertStart;
		this.end = vertEnd;
		this.cost = cost;
	}	
	
	public Vertex getStart() {
	return this.start;
	}
	
	public void setStart(Vertex start) {
	this.start = start;
	}
	
	public Vertex getEnd() {
	return this.end;
	}
	
	public void setEnd(Vertex end) {
	this.end = end;
	}
	
	public Integer getCost() {
	return cost;
	}
	
	public void setCost(Integer cost) {
	this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.cost < e.cost)
            return -1;
        if (this.cost > e.cost)
            return 1;       
        return 0;		
	}
}

public class PrimsMST {
	static int prims(int n, int[][] edges, int start) {
        // Complete this function				
		int cost = 0;
		Graph g = new Graph(edges,n,start);
				
		Set<Vertex> unvisited = new HashSet<Vertex>();
		unvisited.addAll(g.getVertexes());
		
		Vertex current = g.getStart();
		
		unvisited.remove(current);
				
        Queue<Edge> edgesAvailable = new PriorityQueue<Edge>();
        
        while (!unvisited.isEmpty()) {
        	
        	//find available edges for current vertex
        	for (Edge  e : current.getEdges()) {
                if (unvisited.contains(e.getEnd()))
                    edgesAvailable.add(e);
            }
        	
        	Edge e = edgesAvailable.remove();
        	
            cost += e.getCost();
           
            current = e.getEnd();
            
            int vertexCurrent = current.vertex;
            //remove edges to visited vertexes 
            edgesAvailable.removeIf(edge -> vertexCurrent == edge.end.vertex);
                                    
            unvisited.remove(current); 
        }
	        
		return cost;
    }
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[m][3];
        for(int edges_i = 0; edges_i < m; edges_i++){
            for(int edges_j = 0; edges_j < 3; edges_j++){
                edges[edges_i][edges_j] = in.nextInt();
            }
        }
        int start = in.nextInt();
        int result = prims(n, edges, start);
        System.out.println(result);
        in.close();
    }
}
