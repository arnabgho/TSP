import java.util.*;

class Main { 
  // DP TSP

  private static int i, j, TC, xsize, ysize, n,a,b,c,k;
  private static int[] x = new int[11], y = new int[11];
  private static int[][] dist = new int[11][11], memo = new int[11][1 << 11]; // Karel + max 10 beepers

  private static int tsp(int pos, int bitmask) { // bitmask stores the visited coordinates
    if (bitmask == (1 << (n + 1)) - 2)
      return dist[pos][1]; // return trip to close the loop assumed to start from 1
    if (memo[pos][bitmask] != -1)
      return memo[pos][bitmask];

    int ans = 2000000000;
    for (int nxt = 1; nxt <= n; nxt++) // O(n) here
      if (nxt != pos && (bitmask & (1 << nxt)) == 0) // if coordinate nxt is not visited yet
        ans = Math.min(ans, dist[pos][nxt] + tsp(nxt, bitmask | (1 << nxt)));
    return memo[pos][bitmask] = ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n=sc.nextInt();         // No of vertices
    a=sc.nextInt();         // Parameters a,b,c,k
    b=sc.nextInt();
    c=sc.nextInt();
    k=sc.nextInt();

    for (i = 1; i <= n; i++) // build distance table aka the cost matrix
      for (j = 1; j <= n; j++)
        dist[i][j] = a*i+b*j+c%k; // Ad Hoc distance measure

    for (i = 0; i < 11; i++)
      for (j = 0; j < (1 << 11); j++)
       memo[i][j] = -1;

      System.out.printf("The shortest path has length %d\n", tsp(1, 2)); // DP-TSP starting from vertex 1 and hence bitmask 00000..10 ~2
    
  }
}
