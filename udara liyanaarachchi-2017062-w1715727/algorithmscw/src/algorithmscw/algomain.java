//algorithms cw 1 
//module code 5SENG002C
//2017062-w1715727

package algorithmscw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class algomain {

	// number of vertices
	static int node;
	// Graph Size
	int MAXIMUMSIZE = 51;
	// 2D Array
	int f[][] = new int[MAXIMUMSIZE][MAXIMUMSIZE];

	int residualCapacity[][] = new int[MAXIMUMSIZE][MAXIMUMSIZE] ;
	// Using arraylist 
	ArrayList path = new ArrayList();
	int p[] = new int[MAXIMUMSIZE];
	
	public algomain(graph G, int source, int sink)
	{
            //graph G , source node and sink node  . first flow in all edges to 0
            //if there is an a augmentingpath  between source to sink in residual network graph 
		
    	    	
	
		for (int i=1;i<=node;i++)
		{
			for (int j=1;j<=node;j++)
			{
			if (G.adj[i][j]==1)
				f[i][j]=0;
				residualCapacity[i][j] = G.cf[i][j]-f[i][j];

				f[j][i]=0;
				residualCapacity[i][j] = G.cf[i][j]-f[i][j];

			}
		}
		// while there exists an augmenting path,  the programe display it 
		while(hasAugmentingPath(G,source,sink ))
		{
			
			path.clear();
			path.add(source);
			System.out.print("\n"+source);
			int j=source;
			while(j!=sink)
			{
				System.out.print("-->"+p[j]);
				path.add(p[j]);
				j=p[j];
				
				
			}
			// display  residual Capacity
			for(int i =1; i<=node;i++)
				System.out.print(" "+i+"-"+p[i]);
			
			int mincf = residualCapacity[(int) path.get(0)][(int) path.get(1)];
			
			// augmunt flow of the programe  
			for(int i=1;i<path.size()-1;i++)
			{	
				int x=(int) path.get(i);
				int y=(int) path.get(i+1);
				
				if (residualCapacity[x][y]<mincf)
					mincf=residualCapacity[x][y];
				
				
			}
				System.out.println("Minimum Capacity on the path "+mincf);
				
				// Check optimality conditions of the programe 
				for(int i=0;i<path.size()-1;i++)
				{	
					int x=(int) path.get(i);
					int y=(int) path.get(i+1);
					
					f[x][y]=f[x][y]+mincf;
					residualCapacity[x][y] = G.cf[x][y]-f[x][y];
					
					f[y][x]=-f[x][y];
					residualCapacity[y][x] = G.cf[y][x]-f[y][x];
	
				}
		}
		
		
	}
	
	private boolean hasAugmentingPath(graph G, int source , int sink ) {
		
		
		
		int visited[]= new int[MAXIMUMSIZE];
		
		for(int i = 1; i<MAXIMUMSIZE; i++)
			{
				visited[i]=0;
				p[i]=0;
			}
		
		// breadth-first search methode use to thos progrme
		Stack<Integer> q = new Stack<Integer>();
		q.push(source);
		System.out.println("\nBFS traversal for given graph is : ");

		while(!q.isEmpty())
		{
			int u =  q.pop();
			if(visited[u]==0)
			{
				System.out.print("\n"+u);
				visited[u]=1;
			}
			for (int i=1;i<=node;i++)
			{

				if((G.adj[u][i]==1) && (visited[i]==0) && (residualCapacity[u][i]>0) )
				{
					
					q.push(u);
					visited[i]=1;
					p[u]=i;
					if(i==sink)
					{
						
						System.out.println("\nPath found!");
						return true;
					}
					u = i;
					i=1;
				}
			}
		}
		System.out.println("\nPath not found");	// if there is no path found it  display path nnot found 	
		
		// checking augmenting path?
		return false;
	}

	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader ( new InputStreamReader (System.in));
                System.out.println("please put  number of vertices upto 50  ");                
		System.out.println("Enter number of vertices:");
		node=Integer.parseInt(br.readLine());
		
		graph g = new graph(node);
		g.createGraph();
		
		
                //user can enter source node here  
		System.out.println("\nEnter the source node:");
		int s = g.getNumber();
                // user can enter target node here
		System.out.println("\nEnter the target node:");
		int t = g.getNumber();
		long start = System.currentTimeMillis();
		algomain ff = new algomain(g,s,t);
		
		System.out.println("\nFinal Residual Network with no augmenting path");
		
		System.out.print("\nCapacity Flow matrix for your graph is :\n");
		
		// compute maximum flow and minimum cut accouding  to the user inputs 
		for (int i=1;i<=node;i++)
		{
			for (int j=1;j<=node;j++)
			System.out.print("     "+ff.f[i][j]+"/"+g.cf[i][j]);
			System.out.print("\n");
		}

		int maxflow=0;
		long end = System.currentTimeMillis();
		System.out.println("\n Max flow is: ");//this will display max flow here 
		for(int i=1; i<=node;i++)
			if(g.adj[s][i]==1)
				maxflow=maxflow + ff.f[s][i];
		// it  will displlay time here 
		System.out.println(maxflow);
		System.out.println("Time taken (ms):"+ (end-start));

	}

}
