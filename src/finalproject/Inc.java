package finalproject;

public class Inc extends Statement {

    public Inc(String text) {
        super(text);
    }

    @Override
    public void run() {
        String[] pars=statementText.substring(statementText.indexOf("(")+1,statementText.indexOf(")")).split(",");
        Statement.getVariable(pars[0]).increase(Integer.parseInt(pars[1]));
    }

    @Override
    public Error checkError() {
        Error error = new Error();
        if (!statementText.startsWith("INC(")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!statementText.endsWith(")")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!parsePar()) {
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
        }
        return error;
    }

    public boolean parsePar() {
        String par = statementText.substring(statementText.indexOf("(")+1, statementText.indexOf(")"));
        String[] pars = par.split(",");

        if (pars.length != 2) {
            return false;
        }
        if (!((pars[0].charAt(0) > 64 && pars[0].charAt(0) < 91)
                || (pars[0].charAt(0) > 96 && pars[0].charAt(0) < 123))) {
            return false;
        }
        Variable var = Statement.getVariable(pars[0]);
        if (var == null) {
            return false;
        }
        if (pars[1].charAt(0) > 47 && pars[1].charAt(0) < 58) {
            for (int i = 0; i < pars[1].length(); i++) {
                if (pars[1].charAt(i) <= 47 || pars[1].charAt(i) >= 58) {
                    return false;
                }
            }
        } else {
            var = super.getVariable(pars[1]);
            if (var == null) {
                return false;
            }
        }
        return true;
    }
    
        @Override
    public void proRun() {
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        this.run();
    }
}
