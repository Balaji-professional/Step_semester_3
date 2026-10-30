import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem4ClassMarksGridAnalysis {
    private static final Pattern STUDENT_MARKS =
        Pattern.compile("^\\s*(\\S+)\\s*\\[\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*]\\s*$");

    private static final class StudentMarks {
        private final String name;
        private final int[] marks;

        private StudentMarks(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        private long total() {
            return (long) marks[0] + marks[1] + marks[2];
        }
    }

    public static void main(String[] args) {
        List<StudentMarks> students = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                Matcher matcher = STUDENT_MARKS.matcher(line);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Expected: <StudentName> [<Subject1>, <Subject2>, <Subject3>]");
                }
                students.add(new StudentMarks(
                    matcher.group(1),
                    new int[] {
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                    }
                ));
            }
        }

        if (students.isEmpty()) {
            return;
        }

        long[] subjectTotals = new long[3];
        StudentMarks topper = students.get(0);
        StringBuilder totals = new StringBuilder("Totals ");
        for (int i = 0; i < students.size(); i++) {
            StudentMarks student = students.get(i);
            if (i > 0) {
                totals.append(", ");
            }
            totals.append(student.name).append(' ').append(student.total());
            for (int subject = 0; subject < subjectTotals.length; subject++) {
                subjectTotals[subject] += student.marks[subject];
            }
            if (student.total() > topper.total()) {
                topper = student;
            }
        }

        double studentCount = students.size();
        System.out.println(totals);
        System.out.printf(
            Locale.US,
            "averages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / studentCount,
            subjectTotals[1] / studentCount,
            subjectTotals[2] / studentCount
        );
        System.out.println("topper " + topper.name + " (" + topper.total() + ")");
    }
}
