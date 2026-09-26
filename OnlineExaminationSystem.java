import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OnlineExaminationSystem {
    private static final class Student {
        private final String name;

        private Student(String name) {
            this.name = name;
        }
    }

    private abstract static class Question {
        private final String id;
        private final double points;

        private Question(String id, double points) {
            this.id = id;
            this.points = points;
        }

        abstract boolean isCorrect(String answer);
    }

    private static final class MultipleChoiceQuestion extends Question {
        private final String correctOption;

        private MultipleChoiceQuestion(String id, double points, String correctOption) {
            super(id, points);
            this.correctOption = correctOption;
        }

        @Override
        boolean isCorrect(String answer) {
            return answer != null && correctOption.equalsIgnoreCase(answer.trim());
        }
    }

    private static final class TrueFalseQuestion extends Question {
        private final boolean correctAnswer;

        private TrueFalseQuestion(String id, double points, boolean correctAnswer) {
            super(id, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        boolean isCorrect(String answer) {
            return answer != null && Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
        }
    }

    private static final class ShortAnswerQuestion extends Question {
        private final String expectedAnswer;

        private ShortAnswerQuestion(String id, double points, String expectedAnswer) {
            super(id, points);
            this.expectedAnswer = expectedAnswer;
        }

        @Override
        boolean isCorrect(String answer) {
            return answer != null && expectedAnswer.trim().equalsIgnoreCase(answer.trim());
        }
    }

    private static final class Examination {
        private final String id;
        private final List<Question> questions;

        private Examination(String id, List<Question> questions) {
            this.id = id;
            this.questions = List.copyOf(questions);
        }
    }

    private static final class Attempt {
        private final Student student;
        private final Examination examination;
        private final ExaminationService service;
        private final Map<String, String> answers = new HashMap<>();
        private boolean submitted;

        private Attempt(Student student, Examination examination, ExaminationService service) {
            this.student = student;
            this.examination = examination;
            this.service = service;
        }

        private void answer(String questionId, String answer) {
            if (submitted) {
                throw new IllegalStateException("Cannot change answers for a submitted examination.");
            }
            boolean exists = examination.questions.stream().anyMatch(question -> question.id.equals(questionId));
            if (!exists) {
                throw new IllegalArgumentException("Question is not part of this examination: " + questionId);
            }
            answers.put(questionId, answer);
            System.out.println("Answer recorded for " + questionId + ".");
        }

        private void submit() {
            if (submitted) {
                throw new IllegalStateException("This examination attempt has already been submitted.");
            }
            if (!service.markSubmitted(student, examination)) {
                throw new IllegalStateException("Student already submitted this examination.");
            }
            submitted = true;
            double score = 0;
            double maximum = 0;
            for (Question question : examination.questions) {
                maximum += question.points;
                boolean correct = question.isCorrect(answers.get(question.id));
                double earned = correct ? question.points : 0;
                score += earned;
                System.out.printf("%s: %s (%.0f points)%n", question.id, correct ? "Correct" : "Incorrect", earned);
            }
            System.out.printf("Total score: %.0f/%.0f%n", score, maximum);
            System.out.println(examination.id + " submitted by " + student.name + ".");
        }
    }

    private static final class ExaminationService {
        private final Set<String> submittedAttempts = new HashSet<>();

        private Attempt start(Student student, Examination examination) {
            String key = student.name + "|" + examination.id;
            if (submittedAttempts.contains(key)) {
                throw new IllegalStateException("Student already submitted this examination.");
            }
            System.out.println(examination.id + " started by " + student.name + ".");
            return new Attempt(student, examination, this);
        }

        private boolean markSubmitted(Student student, Examination examination) {
            return submittedAttempts.add(student.name + "|" + examination.id);
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination examination = new Examination("Exam A", List.of(
                new MultipleChoiceQuestion("Question 1", 5, "C"),
                new TrueFalseQuestion("Question 2", 5, false)));
        ExaminationService service = new ExaminationService();
        Attempt attempt = service.start(student, examination);
        Attempt secondAttempt = service.start(student, examination);
        attempt.answer("Question 1", "C");
        attempt.answer("Question 2", "True");
        attempt.submit();
        try {
            secondAttempt.submit();
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
        try {
            attempt.answer("Question 1", "A");
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}