package finalproject;

public class Compiler {

    private Statement[] statements;
    private Error[] errors;

    public Compiler(Statement[] statements) {
        setStatements(statements);
        errors=compile();
    }

    public void setStatements(Statement[] statements){
        this.statements=statements;
    }

    public Error[] compile(){
        Error[] errors=new Error[statements.length];
        
        for (int i = 0; i < statements.length; i++) {
            errors[i]=statements[i].checkError();
        }
        return errors;
    }
    
    public Error[] getErrors(){
        return this.errors;
    }
}
