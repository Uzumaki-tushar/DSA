import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    class Pair{
    int row;
    int col;
    int time;

    Pair(int row,int col,int time){
        this.row=row;
        this.col=col;
        this.time=time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        boolean[][] vis= new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        int[] delrow={-1,0,1,0};
        int[] delcol={0,1,0,-1};

        boolean isTwo=false;
        boolean isOne=false;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!vis[i][j] && grid[i][j]==2){
                    q.add(new Pair(i,j,0));
                    vis[i][j]=true;
                    isTwo=true;
                }
                if(grid[i][j]==1) isOne=true;
            }
        }

        if(!isTwo && !isOne) return 0;
        if(!isTwo) return -1;


        int finalTime=0;

        while(!q.isEmpty()){
            int size=q.size();
            finalTime++;

            for(int i=0;i<size;i++){
                Pair temp=q.poll();

                int r=temp.row;
                int c=temp.col;
                int t=temp.time;

                // finalTime=Math.max(t,finalTime);

                for(int j=0;j<4;j++){
                    int nr=r+delrow[j];
                    int nc=c+delcol[j];

                    if(nr>=0 && nr<n && nc>=0 && nc<m && !vis[nr][nc] && grid[nr][nc]==1){
                        q.add(new Pair(nr,nc,t+1));
                        vis[nr][nc]=true;
                        grid[nr][nc]=2;
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) return -1;
            }
        }

        return finalTime-1;
    }
}
}
