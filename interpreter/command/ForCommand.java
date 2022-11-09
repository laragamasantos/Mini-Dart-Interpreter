package interpreter.command;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.Value;

public class ForCommand extends Command {
    private Variable var;
    private Expr expr;
    private Command cmds;

    public ForCommand(int line, Variable var, Expr expr, Command cmds) {
        super(line);
        this.var = var;
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute() {;
        Value<?> v = expr.expr();
        if (!(v instanceof ListValue))
            Utils.abort(super.getLine());
            
        ListValue lv = (ListValue) v;

        for(Value<?> value : lv.value()){
           this.var.setValue(value);
           cmds.execute();
        }
        
    }
    
}
