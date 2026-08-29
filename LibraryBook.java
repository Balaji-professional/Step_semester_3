class LibraryBook {
    String title;
    String isbn;
    boolean catalogued;

    // Constructor for books with ISBN
    public LibraryBook(String title, String isbn) {
        this.title = title;

        if (isbn == null || isbn.isEmpty()) {
            this.isbn = "PENDING";
        } else {
            this.isbn = isbn;
        }

        this.catalogued = true;
    }

    // Constructor for books without ISBN
    public LibraryBook(String title) {
        this(title, "PENDING"); // Constructor chaining
    }

    public void display() {
        System.out.println(title + " | " + isbn +
                           " | Catalogued: " + catalogued);
    }
}

public class Main {
    public static void main(String[] args) {

        String[] titles = {
            "Clean Code",
            "Untitled Draft",
            "1984",
            "Notes"
        };

        String[] isbns = {
            "978-0132350884",
            "",
            "9780451524935",
            ""
        };

        // Process every entry in a single pass
        for (int i = 0; i < titles.length; i++) {

            LibraryBook book;

            if (isbns[i] == null || isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }

            book.display();
        }
    }
}
