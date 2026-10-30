import java.util.HashSet;
import java.util.Set;

public class Problem3PairWithTargetSum {
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Numbers must not be null.");
        }
        for (int first = 0; first < nums.length; first++) {
            for (int second = first + 1; second < nums.length; second++) {
                if ((long) nums[first] + nums[second] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Numbers must not be null.");
        }
        Set<Long> previousNumbers = new HashSet<>();
        for (int number : nums) {
            long complement = (long) target - number;
            if (previousNumbers.contains(complement)) {
                return true;
            }
            previousNumbers.add((long) number);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasPairWithSum(new int[] {2, 7, 11, 15}, 9));
        System.out.println(hasPairWithSum(new int[] {3, 4, 6}, 20));
    }
}
