package HackerrankSolutions;

import java.util.Arrays;
import java.util.Scanner;

public class KruskalMinimumSpanningTree {

	private static final Scanner scanner = new Scanner(System.in);
	
	static int find(int[] parent, int i) {
		if(parent[i] == -1) return i;
		else return find(parent, parent[i]);
	}
	
	static void union(int[] parent, int x, int y) {
		int xset = find(parent,x);
		int yset = find(parent,y);
		parent[xset] = yset;
	}
	
	static boolean isCycle(int[] parent, int vertOneIndex, int vertTwoIndex) {
		int x = find(parent, vertOneIndex);
		int y = find(parent, vertTwoIndex);
		
		if(x==y) return true;
		
		else{
			union(parent,x,y);
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		class Edge implements Comparable<Edge>{
	        int src, dest, weight;
	 
	        public int compareTo(Edge compareEdge)
	        {
	            return this.weight-compareEdge.weight;
	        }
		};       	
		
	    String[] gNodesEdges = scanner.nextLine().split(" ");
	    int gNodes = Integer.parseInt(gNodesEdges[0].trim());
	    int gEdges = Integer.parseInt(gNodesEdges[1].trim());
	
	    int[] gFrom = new int[gEdges];
	    int[] gTo = new int[gEdges];
	    int[] gWeight = new int[gEdges];
	    
	    Edge[] edges = new Edge[gEdges];
	    
	    for (int gItr = 0; gItr < gEdges; gItr++) {
	        String[] gFromToWeight = scanner.nextLine().split(" ");
	        edges[gItr] = new Edge();
	        edges[gItr].src=gFrom[gItr] = Integer.parseInt(gFromToWeight[0].trim());
	        edges[gItr].dest=gTo[gItr] = Integer.parseInt(gFromToWeight[1].trim());
	        edges[gItr].weight=gWeight[gItr] = Integer.parseInt(gFromToWeight[2].trim()); 
	    }
	    
	    Arrays.sort(edges);
	    
	    int res = 0;
	    int resEdges=0;
	    int[] parent = new int[edges.length];
	    
	    for(int i = 0;i<parent.length; i++) {
	    	parent[i] = -1;
	    }
	    
	    for(int i = 0; i < edges.length;i++) {
	    	if(!isCycle(parent,edges[i].src-1,edges[i].dest-1)) {	        	
	        	res+=edges[i].weight;
	        	resEdges++;	        	
	        	if(resEdges == gNodes-1) break;
	    	}
	    }	    
	    System.out.println(res);	
	}
}
