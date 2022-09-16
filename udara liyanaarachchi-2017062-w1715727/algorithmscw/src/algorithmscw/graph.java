
//algorithms cw 1 
//module code 5SENG002C
//2017062-w1715727
package algorithmscw;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class graph {

	int MAXIMUMSIZE=51;
	int adj[][]=new int[MAXIMUMSIZE][MAXIMUMSIZE];
	int cf[][]=new int[MAXIMUMSIZE][MAXIMUMSIZE];

	int visited[]=new int [MAXIMUMSIZE];
	int node;
	
	graph(int x)
	{
		node=x;
	}
	public graph() {
		
	}
	void createGraph()

	{
		int i,j,parent,adj_parent;
		int  ans=0,ans1=0;

		for ( i=1;i<=node;i++)
			for( j=1;j<=node;j++)
				{
					adj[i][j]=0;
				
					cf[i][j]=0;
				}


		//All graph nodes are unvisited, hence assigned zero to visited field of each node //
		for (int c=1;c<=50;c++)
			visited[c]=0;
		System.out.println("\n----------Enter graph structure------------");//user can enter graph structure here  

		do
		{
			System.out.print("\nEnter parent node :");//user can enter parent node here 
			parent=getNumber();
			do
			{
                            //user can enter adjacent node for parent node 
				System.out.print("\nEnter adjacent node for node "+parent+ " : ");
				adj_parent=getNumber();
				adj[parent][adj_parent]=1;
                                //user can enter capacity flow for the edge 
				System.out.print("\nEnter the capacity flow for edge "+parent+ " , "+adj_parent+ " : ");
				cf[parent][adj_parent]=getNumber();
                                System.out.print("if you want to continue please put 1 .");
                                System.out.print("if you dont want to continue  please put  any number without  1 .");
				System.out.print("\nContinue to add adjacent node for "+parent+":");
				ans1= getNumber();
			} while (ans1==1);
                        System.out.print("if you want to continue please put 1 .");
                                System.out.print("if you dont want to continue  please put  any number without  1 .");
			System.out.print("\nContinue to add graph node:");
			ans= getNumber();
		}while (ans ==1);


		System.out.print("\nAdjacency matrix for your graph is :\n");
		for (i=1;i<=node;i++)
		{
			for (j=1;j<=node;j++)
			System.out.print(" "+adj[i][j]);
			System.out.print("\n");
		}
		//this will  display capacity flow of the graph 
		System.out.print("\nCapacity Flow matrix for your graph is :\n");
		for (i=1;i<=node;i++)
		{
			for (j=1;j<=node;j++)
			System.out.print(" "+cf[i][j]);
			System.out.print("\n");
		}

//this will display directed graph here 
		System.out.println("\nYour Directed Graph is :");
		for (i=1;i<=node;i++)
		{
			System.out.print("\nVertex "+i+"is connected to : ");
			for (j=1;j<=node;j++)
			{
			if (adj[i][j]==1)
			System.out.print(" "+j+" with capacity edge flow of "+cf[i][j]+"; ");
			}
		}
		

	}

	int getNumber()
 	{
 		 String str;
 		 int ne=0;
 		try
		 {
 		
 		 InputStreamReader input=new InputStreamReader(System.in);
 		 BufferedReader in=new BufferedReader(input);
 		 
 			 str=in.readLine();
 			 ne=Integer.parseInt(str);
 		 }
 		catch(Exception e)
 		 {
 			System.out.println("I/O Error");//if user put wrong input programe display this  
 		 }
 		
 		 return ne;
	}


}
