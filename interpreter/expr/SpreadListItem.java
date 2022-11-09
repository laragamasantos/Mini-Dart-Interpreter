package interpreter.expr;

import java.util.List;
import java.util.ArrayList;

import interpreter.value.ListValue;
import interpreter.value.Value;
import interpreter.util.Utils;

public class SpreadListItem extends ListItem{
    private Expr expr;

    public SpreadListItem(int line, Expr expr){
        super(line);
        this.expr = expr;
    }

    public List<Value<?>> items(){
        List<Value<?>> list = new ArrayList<>();
        if(this.expr.expr() instanceof ListValue){
            ListValue lv = (ListValue) this.expr.expr();
            list.addAll(lv.value());
        }
        else{
            Utils.abort(super.getLine());
        }
        return list; 
    }
}
