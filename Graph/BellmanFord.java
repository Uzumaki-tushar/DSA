// User function Template for Java
import java.util.*;

Public class BellmanFprd {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int INM=(int)1e8;
        int[] minDist= new int[V];
        Arrays.fill(minDist,INM);
        minDist[src]=0;
        
        for(int i=0;i<V-1;i++){
            for(var j:edges){
                int u=j[0];
                int v=j[1];
                int w=j[2];
                
                if(minDist[u]!=INM &&  minDist[u]+w<minDist[v]){
                    minDist[v]=minDist[u]+w;
                    
                }
            }
        }
        
        
        for(var i:edges){
            int u=i[0];
            int v=i[1];
            int w=i[2];
            
            if(minDist[u]!=INM  && minDist[v]>minDist[u]+w){
                return new int[]{-1};            }
        }
        
        return minDist;
    }
}
