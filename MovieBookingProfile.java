public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        if (otp == null || otp.length() < 4 || otp.length() > 6) {
            throw new IllegalArgumentException("OTP must contain 4 to 6 digits.");
        }
        for (int index = 0; index < otp.length(); index++) {
            if (!Character.isDigit(otp.charAt(index))) {
                throw new IllegalArgumentException("OTP must contain only digits.");
            }
        }
        this.otp = otp;
    }
}