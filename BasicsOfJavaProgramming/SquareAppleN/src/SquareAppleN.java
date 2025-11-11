import comp102x.IO;
import java.io.PrintStream;
import java.util.Stack;

public class SquareAppleN
{
  public static Stack<Indices> s = new Stack();
  public static int[][] grid;
  public static int n;
  public static int startX;
  public static int startY;
  public static int choices = 4;
  public static int total;
  
  public SquareAppleN(int size, int x, int y)
  {
    n = size;
    startX = x;
    startY = y;
    grid = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = 0;
      }
    }
  }
  
  public int solve()
  {
    int maxStep = n * n;
    int i = 1;
    int j = 0;
    total = 0;
    Indices m = new Indices(startX, startY);
    int currentX = startX;
    int currentY = startY;
    grid[currentX][currentY] = 1;
    s.push(m);
    while (i < maxStep)
    {
      while (j < choices)
      {
        switch (j)
        {
        case 0: 
          m = move0(currentX, currentY); break;
        case 1: 
          m = move1(currentX, currentY); break;
        case 2: 
          m = move2(currentX, currentY); break;
        case 3: 
          m = move3(currentX, currentY);
        }
        if (isLegalMove(m) == true)
        {
          s.push(m);
          grid[currentX][currentY] = (j + 1);
          currentX = m.x;
          currentY = m.y;
          break;
        }
        j++;
      }
      if (s.isEmpty() == true) {
        break;
      }
      if (j >= choices)
      {
        m = (Indices)s.pop();
        grid[m.x][m.y] = 0;
        if ((m.x == startX) && (m.y == startY)) {
          break;
        }
        currentX = ((Indices)s.peek()).x;
        currentY = ((Indices)s.peek()).y;
        
        j = grid[currentX][currentY];
        i--;
      }
      else
      {
        i++;
        j = 0;
      }
      if (s.size() == maxStep)
      {
        total += 1;
        System.out.print(total + ": ");
        printSolution(s);
        
        m = (Indices)s.pop();
        grid[m.x][m.y] = 0;
        currentX = ((Indices)s.peek()).x;
        currentY = ((Indices)s.peek()).y;
        j = grid[currentX][currentY];
        i--;
      }
    }
    return total;
  }
  
  public Indices move0(int x, int y)
  {
    Indices m0 = new Indices(x + 1, y);
    return m0;
  }
  
  public Indices move1(int x, int y)
  {
    Indices m1 = new Indices(x, y + 1);
    return m1;
  }
  
  public Indices move2(int x, int y)
  {
    Indices m2 = new Indices(x - 1, y);
    return m2;
  }
  
  public Indices move3(int x, int y)
  {
    Indices m3 = new Indices(x, y - 1);
    return m3;
  }
  
  public boolean isLegalMove(Indices m)
  {
    int nextX = m.x;
    int nextY = m.y;
    if ((nextX < 0) || (nextX >= n) || (nextY < 0) || (nextY >= n)) {
      return false;
    }
    if (grid[nextX][nextY] != 0) {
      return false;
    }
    return true;
  }
  
  private void printSolution(Stack<Indices> s)
  {
    for (int i = 0; i < s.size(); i++) {
      System.out.print("(" + ((Indices)s.get(i)).x + "," + ((Indices)s.get(i)).y + "),");
    }
    System.out.println();
  }
  
  public static void main(String[] args)
  {
    int n = 3;
    int row = 0;
    int col = 0;
    IO.output("Enter size of n: ");
    n = IO.inputInteger();
    for (;;)
    {
      IO.output("Enter starting row (from 1 to " + n + "): ");
      row = IO.inputInteger();
      if ((row >= 1) && (row <= n)) {
        break;
      }
      IO.outputln("Input out-of-range, please try again.");
    }
    for (;;)
    {
      IO.output("Enter starting column (from 1 to " + n + "): ");
      col = IO.inputInteger();
      if ((col >= 1) && (col <= n)) {
        break;
      }
      IO.outputln("Input out-of-range, please try again.");
    }
    SquareAppleN apples = new SquareAppleN(n, row - 1, col - 1);
    
    int number = apples.solve();
    System.out.println("There is(are) " + number + " solution(s).");
  }
}
