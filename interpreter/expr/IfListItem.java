package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

import java.util.List;
import java.util.ArrayList;

public class IfListItem extends ListItem{
    private Expr expr;
    private ListItem thenItem;
    private ListItem elseItem;

    public IfListItem(int line, Expr expr, ListItem thenItem, ListItem elseItem){
        super(line); 
        this.expr = expr;
        this.thenItem = thenItem;
        this.elseItem = elseItem;
    }

    public IfListItem(int line, Expr expr, ListItem thenItem){
        super(line); 
        this.expr = expr;
        this.thenItem = thenItem;
    }

    @Override
    public List<Value<?>> items(){
        Value<?> v = expr.expr();
            if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();
                
            List<Value<?>> list = new ArrayList<>();

            if(b)                
                list.addAll(this.thenItem.items());
            else
                list.addAll(this.elseItem.items());
        return list;
    }
}
