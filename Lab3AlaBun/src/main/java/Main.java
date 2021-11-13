public class Main {
    public static void main(String[] args) {
        SymbolTable lettersSymbolTable=new SymbolTable();
        System.out.println(lettersSymbolTable.insert("a"));
        System.out.println(lettersSymbolTable.insert("a"));
        System.out.println(lettersSymbolTable.insert("e"));
        System.out.println(lettersSymbolTable.insert("c"));

        SymbolTable digitsSymbolTable=new SymbolTable();
        System.out.println(digitsSymbolTable.insert("1"));
        System.out.println(digitsSymbolTable.insert("3"));
        System.out.println(digitsSymbolTable.insert("1"));
    }
}
