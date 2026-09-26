import java.util.concurrent.atomic.AtomicInteger;

public abstract class Toy {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1001);

    private final String toyId;
    private final String name;

    protected Toy(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Toy name cannot be blank.");
        }
        this.name = name;
        this.toyId = "TOY-" + NEXT_ID.getAndIncrement();
    }

    public abstract String makeSound();

    public final String getToyId() {
        return toyId;
    }

    protected final String getName() {
        return name;
    }
}