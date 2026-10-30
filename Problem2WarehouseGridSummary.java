public class Problem2WarehouseGridSummary {
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

    public static void main(String[] args) {
        int[][] grid = {{4, 9, 2}, {7, 1, 6}, {3, 12, 5}};
        System.out.println(warehouseSummary(grid));
    }
}
