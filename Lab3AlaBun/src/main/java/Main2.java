import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main2 {
    private static final List<String> CHARACTERS = new ArrayList<>();
    private static final SymbolTable symbolTable=new SymbolTable();
    private static final Set<String> SEPARATORS = new HashSet<>(){{
        add(" ");
    }};
    private static final Set<String> RESERVED_WORDS=new HashSet<>();
    private static final Map<String,Integer> PIF=new HashMap<>();
    private static final SymbolTable ST =new SymbolTable();

    private static void getSeparators(String path)
    {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                SEPARATORS.add(nextLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void getReservedWords(String path)
    {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                RESERVED_WORDS.add(nextLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static List<String> findIntegersInAString(String stringToSearch) {
        Pattern integerPattern = Pattern.compile("[0-9]+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        List<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(matcher.group());
        }

        return integerList;
    }

    private static void getAtoms(String path) {
        List<String> CHARACTERS2 = new ArrayList<>();
        CHARACTERS2.clear();
        int currentLine=0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                currentLine+=1;
                if(nextLine.charAt(0)=='$' && nextLine.charAt(1)=='$')
                {

                }
                else {
                    var parsedWords=Arrays.asList(nextLine.split("[" + String.join("", SEPARATORS) + "]"));
                    for (var elem: parsedWords) {
                        if((elem.startsWith("\"") && elem.endsWith("\"")) || !(elem.startsWith("\"") || elem.endsWith("\""))){
                            CHARACTERS2.add(elem);
                        }
                        else{
                            System.out.println("Lexical error line "+currentLine+": "+elem);
                        }
                    }

                    //System.out.println(Arrays.asList(nextLine.split("[" + String.join("", SEPARATORS) + "]")));
                    CHARACTERS2.addAll(findIntegersInAString(nextLine));
                    CHARACTERS2.addAll(SEPARATORS.stream().filter(nextLine::contains).collect(Collectors.toSet()));
                    if (nextLine.contains("--")) {
                        CHARACTERS2.add("--");
                    }
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        CHARACTERS2.forEach(x->
        {
            if(x.length()!=0 && !(x.length()==1 && x.equals(" ")) ) {
                CHARACTERS.add(x);
                System.out.println(x);
                if(!SEPARATORS.contains(x) && !RESERVED_WORDS.contains(x)){
                    int index= ST.insert(x);
                    PIF.put(x,index);
                    System.out.println("ST:("+x+","+index+")");
                    System.out.println("PIF:("+x+","+index+")");
                }
                else{
                    PIF.put(x,-1);
                    System.out.println("PIF:("+x+",-1)");
                }
                System.out.println("---------");
            }
        });

    }

    public static void main(String[] args) {
        getSeparators("E:\\university\\Year3Sem1\\flcd\\Labs\\Lab3AlaBun\\src\\main\\resources\\symbols.txt");

        getAtoms("E:\\university\\Year3Sem1\\flcd\\Labs\\Lab3AlaBun\\src\\main\\resources\\p1.txt");

        getReservedWords("E:\\university\\Year3Sem1\\flcd\\Labs\\Lab3AlaBun\\src\\main\\resources\\reservedWords.txt");
        System.out.println("========================================================================================");

        System.out.println(CHARACTERS);
    }
}
