public class HonorsStudentMember extends StudentMember {
    private final int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        if (bonusLimit < 0) {
            throw new IllegalArgumentException("Bonus limit cannot be negative.");
        }
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    protected int getEffectiveBorrowLimit() {
        return super.getEffectiveBorrowLimit() + bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}