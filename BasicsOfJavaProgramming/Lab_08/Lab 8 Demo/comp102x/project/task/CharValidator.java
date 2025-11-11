package comp102x.project.task;

public class CharValidator {
    public CharValidator() {
    }

    public boolean validateChar(char c) {
        boolean isValid = false;
        isValid = isValid || c >= 65 && c <= 90;
        isValid = isValid || c >= 97 && c <= 122;
        isValid = isValid || c >= 48 && c <= 57;
        return isValid;
    }
}