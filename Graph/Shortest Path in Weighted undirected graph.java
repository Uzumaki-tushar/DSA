class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        //  Code Here.
        List<Integer> ans= new ArrayList<>();
        ArrayList<ArrayList<int[]>> arr = new ArrayList<>();
        for(int i=0;i<=n;i++) arr.add(new ArrayList<>());
        
        for(int i=0;i<m;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            
            arr.get(u).add(new int[]{v,w});
            arr.get(v).add(new int[]{u,w});
        }
        
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->{
            return Integer.compare(a[1],b[1]);
        });
        
        int[] minDist=new int[n+1];
        Arrays.fill(minDist,Integer.MAX_VALUE);
        int[] parent= new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
        
        pq.add(new int[]{1,0});
        minDist[1]=0;
        parent[1]=1;
        
        while(!pq.isEmpty()){
            int[] temp=pq.poll();
            int node=temp[0];
            int dist=temp[1];
            
            for(var i:arr.get(node)){
                int ele=i[0];
                int eleDist=i[1];
                
                if(minDist[ele]>dist+eleDist){
                    minDist[ele]=dist+eleDist;
                    parent[ele]=node;
                    pq.add(new int[]{ele,minDist[ele]});
                }
            }
        }
        
        if(minDist[n]==Integer.MAX_VALUE){
            ans.add(-1);
            return ans;
        }
        
        List<Integer> path= new ArrayList<>();
        int curr=n;
        while(parent[curr]!=curr){
            path.add(curr);
            curr=parent[curr];
        }
        path.add(1);
        
        Collections.reverse(path);
        ans.add(minDist[n]);
        ans.addAll(path);
        
        return ans;
    }
}