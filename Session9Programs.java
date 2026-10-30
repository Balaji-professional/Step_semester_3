import java.util.HashSet;
import java.util.Set;

public class Session9Programs {
    public static final class BookRecord {
        private final String isbn;
        private final String title;

        public BookRecord(String isbn, String title) {
            if (isbn == null || title == null) {
                throw new IllegalArgumentException("ISBN and title must not be null.");
            }
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static final class WarehouseSummary {
        private final long totalItems;
        private final int maxRow;
        private final int maxColumn;

        private WarehouseSummary(long totalItems, int maxRow, int maxColumn) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxColumn = maxColumn;
        }

        public long getTotalItems() {
            return totalItems;
        }

        public int getMaxRow() {
            return maxRow;
        }

        public int getMaxColumn() {
            return maxColumn;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", (" + maxRow + ", " + maxColumn + "))";
        }
    }

    public static String findBook(BookRecord[] catalog, String targetIsbn) {
        if (catalog == null || targetIsbn == null) {
            throw new IllegalArgumentException("Catalog and target ISBN must not be null.");
        }

        int left = 0;
        int right = catalog.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            BookRecord book = catalog[middle];
            if (book == null) {
                throw new IllegalArgumentException("Catalog entries must not be null.");
            }
            int comparison = book.isbn.compareTo(targetIsbn);
            if (comparison == 0) {
                return book.title;
            }
            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return "Not Found";
    }

    public static WarehouseSummary warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must contain at least one cell.");
        }

        int columns = grid[0].length;
        long totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxColumn = 0;

        for (int row = 0; row < grid.length; row++) {
            if (grid[row] == null || grid[row].length != columns) {
                throw new IllegalArgumentException("Grid must be rectangular.");
            }
            for (int column = 0; column < columns; column++) {
                int items = grid[row][column];
                if (items < 0) {
                    throw new IllegalArgumentException("Item counts must be non-negative.");
                }
                totalItems += items;
                if (items > maxItems) {
                    maxItems = items;
                    maxRow = row;
                    maxColumn = column;
                }
            }
        }
        return new WarehouseSummary(totalItems, maxRow, maxColumn);
    }

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
        BookRecord[] catalog = {
            new BookRecord("0001112223", "Introduction to Algebra"),
            new BookRecord("0002223334", "Beginning Python"),
            new BookRecord("0003334445", "Classic Mythology"),
            new BookRecord("0004445556", "Data and Society"),
            new BookRecord("0005556667", "European History")
        };
        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));

        int[][] grid = {{4, 9, 2}, {7, 1, 6}, {3, 12, 5}};
        System.out.println(warehouseSummary(grid));

        System.out.println(hasPairWithSum(new int[] {2, 7, 11, 15}, 9));
        System.out.println(hasPairWithSum(new int[] {3, 4, 6}, 20));
        System.out.println(maxContainerArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
