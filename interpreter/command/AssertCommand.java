package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class AssertCommand extends Command{
    private Expr expr;
    private Expr msg;

    public AssertCommand(int line, Expr expr, Expr msg){
        super(line);
        this.expr = expr;
        this.msg = msg;
    }

    public AssertCommand(int line, Expr expr){
        super(line);
        this.expr = expr;
    }

    @Override
    public void execute(){
        Value<?> v = expr.expr();
        if (!(v instanceof BoolValue))
            Utils.abort(super.getLine());
        
        BoolValue bv = (BoolValue) v;
        boolean b = bv.value();

        if (!b){
            if(msg == null){
                System.out.println("not true");
            }
            else{
            Value<?> m = msg.expr();
            System.out.println(m);
            }
        }
        
    }

}
