package HackerrankSolutions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

class Vertex{
	private boolean isVisited;	
	private int dist;
	Vertex(){
		this.isVisited = false;
		this.dist = -1;
	}
	
	public boolean getIsVisited() {
		return this.isVisited;
	}
	
	public void setIsVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	public int getDistance() {
		return this.dist;
	}
	
	public void setDistance(int distance) {
		this.dist = distance;
	}
	
}

class Graph{
	private int[][] matrix;
	private Vertex[] vertexList;
	private int vertexCount;
	private int edgeCount;
	private int start;
	
	public Graph(int[][] matrix,int vertexCount, int edgeCount, int start) {
		this.matrix = matrix;
		this.vertexCount = vertexCount;
		this.edgeCount = edgeCount;
		vertexList = new Vertex[vertexCount];
		for(int i = 0;i<vertexCount;i++) {
			vertexList[i] = new Vertex();
		}
		
		this.start = start - 1;
	}
	
	ArrayDeque <Integer> queue = new ArrayDeque<>();
	
	int[] bfs() {
		int vertex;
		vertexList[start].setIsVisited(true);
		vertexList[start].setDistance(0);
		queue.addFirst(start);
		
		//if a adjacent has not been visited, 
        //then mark it visited and enqueue it
		while(!queue.isEmpty()) {
			int current = queue.remove();
			while((vertex = getAdjacent(current))!=-1) {
				vertexList[vertex].setIsVisited(true);
				vertexList[vertex].setDistance(vertexList[current].getDistance()+6);
				queue.add(vertex);
			}
		}
		
		 int[] res = new int[vertexCount-1];	
	
			
		 res = Arrays.stream(vertexList).mapToInt(v->v.getDistance()).filter(i->i!=0).toArray();
		 return res;
	}
	
	//get all adjacent vertices of the dequeued
	private int getAdjacent(int v) {
		
		for(int i = 0;i<edgeCount;i++) {
			
			if((matrix[i][0]==v+1) && !vertexList[matrix[i][1]-1].getIsVisited()) {
				return matrix[i][1]-1;
			}
			if(matrix[i][1]==v+1 && !vertexList[matrix[i][0]-1].getIsVisited()) {
				return matrix[i][0]-1;
			}
		}
		return -1;		
	}	
}

public class BFSearchGraph {	

    public static void main(String[] args) {    	
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        Graph[] graphList = new Graph[q];
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] edges = new int[m][2];
            for(int edges_i = 0; edges_i < m; edges_i++){
                for(int edges_j = 0; edges_j < 2; edges_j++){
                    edges[edges_i][edges_j] = in.nextInt();
                }
            }
            int s = in.nextInt();           
           
            graphList[a0] = new Graph(edges,n,m,s);      
        }
        in.close();
        
        for(int i =0;i<q;i++) {
        	int[] result = graphList[i].bfs();
        	for (int j = 0; j < result.length; j++) {
        		System.out.print(result[j] + (j != result.length - 1 ? " " : ""));
        	}
        	System.out.println("");
        }        
    }
}
