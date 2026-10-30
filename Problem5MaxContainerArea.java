public class Problem5MaxContainerArea {
    public static long maxContainerArea(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException("Heights must not be null.");
        }
        for (int height : heights) {
            if (height < 0) {
                throw new IllegalArgumentException("Heights must be non-negative.");
            }
        }

        int left = 0;
        int right = heights.length - 1;
        long maximumArea = 0;
        while (left < right) {
            long area = (long) Math.min(heights[left], heights[right]) * (right - left);
            maximumArea = Math.max(maximumArea, area);
            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maximumArea;
    }

    public static void main(String[] args) {
        System.out.println(maxContainerArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
