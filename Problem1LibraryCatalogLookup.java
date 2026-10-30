public class Problem1LibraryCatalogLookup {
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
    }
}
