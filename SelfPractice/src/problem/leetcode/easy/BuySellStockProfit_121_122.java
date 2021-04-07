package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Best time to buy and sell stocks.
 * 1) Only 1 transaction is allowed
 * 2) Infinite number transactions are allowed
 */
public class BuySellStockProfit_121_122 {

    public int oneProfitOneTransaction(int arr[]){
        int minPrice = arr[0];
        int maxProfit = 0;
        for(int i=1; i < arr.length; i++){
            if(arr[i] - minPrice > maxProfit){
                maxProfit = arr[i] - minPrice;
            }
            if(arr[i] < minPrice){
                minPrice = arr[i];
            }
        }
        return maxProfit;
    }
    
    public int allTimeProfitMultipleTransactions(int arr[]){
        int profit = 0;
        for(int i=1; i < arr.length;i++){
            if(arr[i-1] < arr[i]){
                profit += arr[i] - arr[i-1];
            }
        }
        return profit;
    }
    
    public static void main(String args[]){
        int arr[] = {7,10,15,5,11,2,7,9,3};
        int arr1[] = {6,4,1,3,5,7,3,1,3,4,7,9,2,5,6,0,1,2};
        BuySellStockProfit_121_122 bss = new BuySellStockProfit_121_122();
        System.out.println(bss.oneProfitOneTransaction(arr));
        System.out.print(bss.allTimeProfitMultipleTransactions(arr1));
        
    }
}
