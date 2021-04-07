package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class CountPickUpDeliveryOptions_1359 {
    public int countOrders(int n) {
        List<String> pickup = new ArrayList<>();
        List<String> delivery = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            String p = "P" + i;
            String d = "D" + i;
            pickup.add(p);
            delivery.add(d);
        }
        List<List<String>> res = new ArrayList<>();
        getCombos(pickup, delivery, res, new ArrayList<>(), new boolean[n], new boolean[n]);
        System.out.println(res);
        return res.size();
    }

    public void getCombos (List<String> pickup, List<String> delivery, List<List<String>> res, List<String> curr, boolean[] picked, boolean[] delivered) {
        if (curr.size() == pickup.size() * 2)
            res.add(new ArrayList<>(curr));

        for (int i=0; i<pickup.size(); i++) {
            if (!picked[i]) {
                curr.add(pickup.get(i));
                picked[i] = true;
                getCombos(pickup, delivery, res, curr, picked, delivered);
                curr.remove(curr.size()-1);
                picked[i] = false;
            }
        }
        for (int i=0; i<delivery.size(); i++) {
            if (picked[i] && !delivered[i]) {
                curr.add(delivery.get(i));
                delivered[i] = true;
                getCombos(pickup, delivery, res, curr, picked, delivered);
                curr.remove(curr.size()-1);
                delivered[i] = false;
            }
        }
    }
    /**
     * Maths solution
     *
     * F(1) = 1
     * To compute F(2) we can do the following:
     *
     * Put P1 and place D1 anywhere (there 3 possibilities) and compute F(1) (P2 and D2 are left);
     * Put P2 and place D2 anywhere (there 3 possibilities) and compute F(1) (P1 and D1 are left);
     * So, F(2) = 3 + 3 = 6
     * The same way we can compute F(3):
     *
     * Put P1 and place D1 anywhere (there (3 * 2 - 1 = 5) possibilities) and compute F(2) (P2, P3, D2, D3 are left);
     * Put P2 and place D2 anywhere (there (3 * 2 - 1 = 5) possibilities) and compute F(2) (P1, P3, D1, D3 are left);
     * Put P3 and place D3 anywhere (there (3 * 2 - 1 = 5) possibilities) and compute F(2) (P1, P2, D1, D2 are left);
     * So, F(3) = 5 * F(2) + 5 * F(2) + 5 * F(2) = 3 * (3 * 2 - 1) * F(2) = 3 * 5 * 6 = 90
     */
    private static final long MOD = (long) 1e9 + 7;

    public int countOrdersMaths(int n) {
        long current = 1;

        for (int i = 1; i <= n; i++) {
            current = (i + i - 1) * i * current % MOD;
        }

        return (int) current;
    }
}
