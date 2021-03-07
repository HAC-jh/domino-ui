package org.dominokit.domino.ui.forms.validations;

/**
 * This class represent the result of a single validation logic
 */
public class ValidationResult {

    private String errorMessage;
    private boolean valid;

    /**
     * Creates an instance of a validation result that indicate a <b>valid/invalid</b> state without an error message
     * @param valid boolean, if true the state is valid otherwise invalid
     */
    public ValidationResult(boolean valid) {
        this(valid, "");
    }

    /**
     * Creates an instance of a validation result that indicate a <b>valid/invalid</b> state with an error message
     * @param valid boolean, if true the state is valid otherwise invalid
     * @param errorMessage String error message
     */
    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    /**
     * Creates an instance of a validation result that indicate a <b>valid</b> state
     */
    public static ValidationResult valid() {
        return new ValidationResult(true);
    }

    /**
     * Creates an instance of a validation result that indicate an <b>invalid</b> state with an error message
     * @param errorMessage String error message
     */
    public static ValidationResult invalid(String errorMessage) {
        return new ValidationResult(false, errorMessage);
    }

    /**
     *
     * @return String error message, empty String for valid results
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @return boolean, true if there was no errors false if there was errors.
     */
    public boolean isValid() {
        return valid;
    }
}
