import java.util.Set;

public final class AccessChecker {
    private static final Set<String> MODIFIERS = Set.of("private", "default", "protected", "public");
    private static final Set<String> CONTEXTS = Set.of(
            "SAME_CLASS",
            "SAME_PACKAGE",
            "DIFFERENT_PACKAGE",
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE",
            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE");

    private AccessChecker() {
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (!MODIFIERS.contains(fieldModifier) || !CONTEXTS.contains(accessorContext)) {
            return "DENIED";
        }

        return switch (fieldModifier) {
            case "private" -> accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            case "default" -> accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
            case "protected" -> accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
                    ? "ALLOWED" : "DENIED";
            case "public" -> "ALLOWED";
            default -> "DENIED";
        };
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length == 2
                        && classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}