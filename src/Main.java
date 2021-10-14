public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable=new SymbolTable();
        System.out.println(symbolTable.insert("a"));
        System.out.println(symbolTable.insert("a"));
        System.out.println(symbolTable.insert("e"));
        System.out.println(symbolTable.insert("c"));

    }
}
