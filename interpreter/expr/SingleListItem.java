package interpreter.expr;

import java.util.List;
import java.util.ArrayList;
import interpreter.value.Value;

public class SingleListItem extends ListItem{
    private Expr expr;

    public SingleListItem(int line, Expr expr){
        super(line);
        this.expr = expr;
    }

    public List<Value<?>> items(){
        List<Value<?>> list = new ArrayList<>();
        list.add(expr.expr());
        return list;
    }
}
