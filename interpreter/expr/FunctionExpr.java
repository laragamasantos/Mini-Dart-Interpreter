package interpreter.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.ListValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class FunctionExpr extends Expr {

    private FunctionOp op;
    private Expr expr;

    private static Scanner input = new Scanner(System.in);

    public FunctionExpr(int line, FunctionOp op, Expr expr) {
        super(line);

        this.op = op;
        this.expr = expr;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr.expr();

        switch (op) {
            case READ:
                return readOp(v);
            case RANDOM:
                return randomOp(v);
            case LENGTH:
                return lengthOp(v);
            case KEYS:
                return keysOp(v);
            case VALUES:
                return valuesOp(v);
            case TOBOOL:
                return toBoolOp(v);
            case TOINT:
                return toIntOp(v);
            case TOSTR:
                return toStrOp(v);
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private TextValue readOp(Value<?> v) {
        System.out.print(v);

        String text = input.nextLine().trim();
        return text.isEmpty() ? null : new TextValue(text);
    }

    private NumberValue randomOp(Value<?> v) {
        NumberValue nv = (NumberValue) v;
        int argument = nv.value();
        
        Random random = new Random();
        int n = random.nextInt(argument);

        return new NumberValue(n);
    }

    private NumberValue lengthOp(Value<?> v) {
        if (v instanceof ListValue){
            ListValue lv = (ListValue) v;
            List list = lv.value();
            int n = list.size();

            return new NumberValue(n);
        }
        else {
            Utils.abort(super.getLine());
            return null; 
        }
    }

    private ListValue keysOp(Value<?> v) { //retornar lista com todas as chaves de um mapa
        if (v instanceof MapValue){
            MapValue mv = (MapValue) v;
            Map map = mv.value();
            List list = new ArrayList();
            
            for(Object key : map.keySet()){
                list.add(key);
            }

            return new ListValue(list);
        }
        else {
            Utils.abort(super.getLine());
            return null; 
        }
    }

    private ListValue valuesOp(Value<?> v) {
        if (v instanceof MapValue){
            MapValue mv = (MapValue) v;
            Map map = mv.value();
            List list = new ArrayList();
            
            for(Object value : map.values()){
                list.add(value);
            }

            return new ListValue(list);
        }
        else {
            Utils.abort(super.getLine());
            return null; 
        }
    }

    private BoolValue toBoolOp(Value<?> v) {
        boolean b;
        if (v == null){
            b = false;
        }
        else if (v instanceof BoolValue){
            BoolValue bv = (BoolValue) v;
            b = bv.value();
        } 
        else if (v instanceof NumberValue){
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            if (n == 0)
                b = false;
            else 
                b = true;
        }
        else if (v instanceof TextValue){
            TextValue sv = (TextValue) v;
            String s = sv.value();

            if(s.equals(null))
                b = false;
            else 
                b = true;
        }
        else if (v instanceof ListValue){
            ListValue lv = (ListValue) v;
            List list = lv.value();
            
            if(list.isEmpty())
                b = false;
            else 
                b = true;
        }
        else if (v instanceof MapValue){
            MapValue mv = (MapValue) v;
            Map map = mv.value();

            if(map.isEmpty())
                b = false;
            else
                b = true;
        }
        else {
            b = false;
        }

        return new BoolValue(b);
    }

    private NumberValue toIntOp(Value<?> v) {
        int n;
        if (v == null) {
            n = 0;
        } 
        else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            n = b ? 1 : 0;
        } 
        else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            n = nv.value();
        } 
        else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            String s = sv.value();

            try {
                n = Integer.parseInt(s);
            } catch (Exception e) {
                n = 0;
            }
        } 
        else {
            n = 0;
        }

        return new NumberValue(n);
    }

    private TextValue toStrOp(Value<?> v) {
        String s;
        if (v == null){
            s = null;
        } 
        else if (v instanceof BoolValue){
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if(b)
                s = "true";
            else
                s = "false";   
        } 
        else if (v instanceof NumberValue){
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            try {
                s = Integer.toString(n);
            } catch (Exception e) {
                s = null;
            }
        } 
        else if (v instanceof TextValue){
            TextValue sv = (TextValue) v;
            s = sv.value();
        }
        else if(v instanceof ListValue){
            ListValue lv = (ListValue) v;
            s = lv.value().toString();
        }
        else if(v instanceof MapValue){
            MapValue mv  = (MapValue) v;
            s = mv.value().toString();
        }
        else{
            s = null;
        }

        return new TextValue(s);
    }

}