package test;

import java.util.Scanner;

public class CoinChanging {
	   static long getWays(long n, long[] c){
	        
	        int k = c.length;
	        long[][] f = new long[(int)n+1][k];	        
	        for(int i=0;i<k;i++){
	           f[0][i]= 1L; 
	        }
	        
	        for(int i = 1;i<=n;i++) {
	        	for(int j = 0;j<k;j++){
	        		f[i][j] = -1L;
	        	}
	        }
	        return getWaysReccurent((int)(n),k-1,c,f);
	         
	    }
	   
	static long getWaysReccurent(int i, int j, long[] c, long[][] f) {
		
		if(i<0 || j<0) return 0;
		if(f[i][j] != -1) return f[i][j];
		else {			
			f[i][j] =  getWaysReccurent(i-(int)c[j],j,c,f)+getWaysReccurent(i,j-1,c,f); 
			return f[i][j];			
		}
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] c = new long[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(n, c);
        System.out.println(ways);
        in.close();
    }
}
