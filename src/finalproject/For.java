package finalproject;

public class For extends Statement {

    private int availableLines;
    private Statement[] stms;

    public For(String text, int lines, Statement[] stms) {
        super(text);
        this.stms = stms;
        setAvailableLines(lines);
    }

    @Override
    public void run() {
        for (int i = 0; i < parsePar()[1]; i++) {
            for (int j = this.lineNum; j < parsePar()[0] + this.lineNum; j++) {
                stms[j].run();
            }

        }
    }

    @Override
    public Error checkError() {
        Error error = new Error();

        if (!this.statementText.startsWith("FOR(")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
            return error;
        }

        if (!this.statementText.endsWith(")")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
            return error;
        }
        if (!statementText.substring(statementText.indexOf("(") + 1, statementText.indexOf(")")).contains(",")) {
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
            return error;
        }

        return error;
    }

    public int[] parsePar() {
        String[] pars = statementText.substring(statementText.indexOf("(") + 1, statementText.indexOf(")")).split(",");
        int[] parameters = new int[2];
        if (pars.length != 2) {
            return null;
        }
        if (pars[0].charAt(0) > 47 && pars[0].charAt(0) < 58) {
            for (int i = 0; i < pars[0].length(); i++) {
                if (!(pars[0].charAt(i) > 47 && pars[0].charAt(i) < 58)) {
                    return null;
                }
            }
            if (Integer.parseInt(pars[0]) < 0) {
                return null;
            }
            parameters[0] = Integer.parseInt(pars[0]);
        } else {
            Variable var = Statement.getVariable(pars[0]);
            if (var == null) {
                return null;
            }
            if (var.getValue() < 0) {
                return null;
            }
            parameters[0] = var.getValue();
        }

        if (pars[1].charAt(0) > 47 && pars[1].charAt(0) < 58) {
            for (int i = 0; i < pars[1].length(); i++) {
                if (!(pars[1].charAt(i) > 47 && pars[1].charAt(i) < 58)) {
                    return null;
                }
            }
            if (Integer.parseInt(pars[1]) < 0) {
                return null;
            }
            parameters[1] = Integer.parseInt(pars[1]);
        } else {
            Variable var = super.getVariable(pars[1]);
            if (var == null) {
                return null;
            }
            if (var.getValue() < 0) {
                return null;
            }
            parameters[1] = var.getValue();
        }
        if (parameters[0] > availableLines) {
            return null;
        }
        return parameters;
    }

    public void setAvailableLines(int lines) {
        this.availableLines = lines;
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
