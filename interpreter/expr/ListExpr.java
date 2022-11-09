package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.value.ListValue;
import interpreter.value.Value;

public class ListExpr extends Expr{
   private List<ListItem> list;

    public ListExpr(int line){
        super(line);
    }

    public void addItem(ListItem item){
        if(this.list == null){
            this.list = new ArrayList<>();
        }
        this.list.add(item);
    }

    @Override
    public Value<?> expr(){
        List<Value<?>> l = new ArrayList<>();
    
        for(ListItem i : this.list){
            l.addAll(i.items());
        }

        return new ListValue(l);
    }
}