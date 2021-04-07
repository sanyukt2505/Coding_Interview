package create.destroy;

/**
 * Created by Vijay on 2/27/16.
 */
public class NutritionFactsClient {
    public static void main(String[] args) {
        NutritionFacts  nutritionFactsCandy = new NutritionFacts.Builder(10, 50).calories(20).build();
        System.out.println(nutritionFactsCandy);
    }
}
