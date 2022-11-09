package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.Value;

import java.util.List;
import java.util.ArrayList;

public class ForListItem extends ListItem{
    private Variable var;
    private Expr expr;
    private ListItem item;

    public ForListItem(int line, Variable var, Expr expr, ListItem item){
        super(line); 
        this.var = var;
        this.expr = expr;
        this.item = item;
    }

    @Override
    public List<Value<?>> items(){
        Value<?> v = expr.expr();
            if (!(v instanceof ListValue))
                Utils.abort(super.getLine());

            ListValue lv = (ListValue) v;
            List<Value<?>> list = new ArrayList<>();

        for(Value<?> i : lv.value()){
            var.setValue(i);
            list.addAll(item.items());
        }

        return list;
    }
}
