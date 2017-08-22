import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.HashMap;

// modified from https://github.com/rshaghoulian/HackerRank_solutions/blob/master/Cracking%20the%20Coding%20Interview/Techniques%2C%20Concepts/DP%20-%20Coin%20Change/Solution.java

// Use a HashMap as a cache to speed up runtime
// Must use "long" instead of "int" to avoid integer overflow

public class Solution {
    public static void main(String [] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("src/main/resources/change.data")));
        int n = scan.nextInt();
        int m = scan.nextInt();
        int [] coins = new int[m];
        for (int i = 0; i < m; i++) {
            coins[i] = scan.nextInt();
        }
        scan.close();

        System.out.println(countChange(n, coins));
    }

    private static long countChange(int amount, int [] coins) {
        if (amount < 0) {
            return 0;
        }
        return countChange(amount, coins, 0, new HashMap<String, Long>());
    }

    private static long countChange(int amount, int [] coins, int coinNumber, HashMap<String, Long> cache) {
        /* Check our cache */
        String key = amount + "," + coinNumber;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        /* Base case */
        if (coinNumber == coins.length - 1) {
            if (amount % coins[coinNumber] == 0) {
                cache.put(key, 1L);
                return 1;
            } else {
                cache.put(key, 0L);
                return 0;
            }
        }
        /* Recursive case */
        long ways = 0;
        for (int i = 0; i <= amount; i += coins[coinNumber]) {
            ways += countChange(amount - i, coins, coinNumber + 1, cache);
        }
        /* Cache and return solution */
        cache.put(key, ways);
        return ways;
    }
}
