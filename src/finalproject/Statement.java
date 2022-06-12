package finalproject;

import java.util.LinkedList;

public abstract class Statement {

    protected final Pen pen = Pen.getPen();
    protected String statementText;
    protected int lineNum;
    protected static LinkedList variables = new LinkedList();

    public Statement(String text) {
        this.setStatementText(text);
    }

    public void setStatementText(String text) {
        this.statementText = text;
    }

    public static void addVariable(Variable var) {
        variables.addLast(var);
    }

    public static Variable getVariable(String name) {
//        Variable[] vars= (Variable[])this.variables.toArray();
        Object[] obj = variables.toArray();

        for (Object o : obj) {
            if (o instanceof Variable) {
                Variable v=(Variable)o;
                if (v.getName().equals(name)) {
                    return v;
                }
            }
        }
        return null;
    }

    public abstract Error checkError();

    public abstract void run();

    @Override
    public String toString() {
        return this.getStatementText();
    }

    public String getStatementText() {
        return statementText;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
    
    public abstract void proRun();

}
