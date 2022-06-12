package finalproject;

enum ErrorType {

    NOT_EXIST, INVALID_PARAMETERS
}

public final class Error {

    private boolean discovered;
    private int lineNum;
    private ErrorType type;

    public Error() {

    }

    public Error(int lineNum, ErrorType error) {
        this.setLineNum(lineNum);
        this.errorFound(lineNum, error);
    }

    public boolean isDiscovered() {
        return this.discovered;
    }

    public void errorFound(int lineNum, ErrorType error) {
        this.discovered = true;
        this.setLineNum(lineNum);
        this.setErrorType(error);
    }

    public void errorNotFound() {
        this.discovered = false;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getLineNum() {
        return this.lineNum;
    }

    public void setErrorType(ErrorType error) {
        this.type = error;
    }

    @Override
    public String toString() {
        String str;
        if (this.type == ErrorType.INVALID_PARAMETERS) {
            str = "INVALID PARAMETERS line :" + this.getLineNum();
        } else {
            str = "NOT EXIST line :" + this.getLineNum();
        }
        return str;
    }
}
