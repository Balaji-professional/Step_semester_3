import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LibraryMember {
    private static final int FIRST_MEMBER_NUMBER = 101;
    private static final AtomicInteger NEXT_MEMBER_NUMBER = new AtomicInteger(FIRST_MEMBER_NUMBER);

    private final String memberId;
    private final int borrowLimit;
    public final String memberNumber;
    private final int[] fineHistory = new int[10];
    private final List<String> borrowedGenres = new ArrayList<>();
    private int booksBorrowed;
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.isBlank() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Member ID must contain at least four non-whitespace characters.");
        }
        this.memberId = memberId.trim();
        this.borrowLimit = requirePositiveBorrowLimit(borrowLimit);
        this.memberNumber = nextMemberNumber();
    }

    public LibraryMember(int borrowLimit) {
        this.borrowLimit = requirePositiveBorrowLimit(borrowLimit);
        this.memberNumber = nextMemberNumber();
        this.memberId = memberNumber;
    }

    private static int requirePositiveBorrowLimit(int borrowLimit) {
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be positive.");
        }
        return borrowLimit;
    }

    private static String nextMemberNumber() {
        return "LIB-" + NEXT_MEMBER_NUMBER.getAndIncrement();
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected int getEffectiveBorrowLimit() {
        return borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed >= getEffectiveBorrowLimit()) {
            throw new IllegalStateException("Borrow limit reached.");
        }
        booksBorrowed++;
    }

    public void borrowBook(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be blank.");
        }
        borrowedGenres.add(genre.trim());
        borrowBook();
    }

    public List<String> getBorrowedGenres() {
        return List.copyOf(borrowedGenres);
    }

    protected void chargeFine(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Fine cannot be negative.");
        }
        if (fineCount == fineHistory.length) {
            throw new IllegalStateException("A membership can record at most 10 fines.");
        }
        fineHistory[fineCount++] = amount;
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        if (memberIds == null) {
            return "Enrolled: 0 | Rejected: 0";
        }
        for (String memberId : memberIds) {
            try {
                new LibraryMember(memberId, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException exception) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof StudentMember) {
            return "Direct descendant (2 generations deep)";
        }
        return "Base generation";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();
        for (LibraryMember member : members) {
            report.append(member.displayInfo());
            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append(" [Course via downcast: ").append(student.getCourse()).append(']');
            }
            report.append(" | ");
        }
        return report.toString();
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return NEXT_MEMBER_NUMBER.get() - FIRST_MEMBER_NUMBER;
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;
        if (members == null) {
            return "0 processed | 0 null skipped | 0 faculty | 0 regular";
        }
        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + faculty
                + " faculty | " + regular + " regular";
    }
}