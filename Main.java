import java.io.*;  // Import the File class
import java.text.SimpleDateFormat;
// import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files

/**
 * Main
 */
public class Main {
  static HashMap<String, List<String>> slangList = new HashMap<String, List<String>>();
  static Scanner scanner = new Scanner(System.in);
  // get(slang), remove(slang), clear() : all
  public static void main(String[] args) {
        FileImport("D:\\CPP\\Java\\CODE\\slang.txt");
        System.out.println(DefListToStr(slangList.get("XS")));
        
    }
  public static void FileImport(String filename){
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] slang = myReader.nextLine().split("`");
        if(slang.length >1){
          String[] def = slang[1].split("\\| ");
          slangList.put(slang[0], Arrays.asList(def));
          System.out.println(slangList.get(slang[0]));
          // System.out.println(data);                
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static String DefListToStr(List<String> deflist)
  {
    if(deflist == null){
      return "";
    }
    String ans = "";
    for(String def: deflist)
    {
      ans += ", " + def;
    }
    return ans.substring(2);
  }
  public static HashMap<String, List<String>> FindSlang(String searchword)
  {
    static HashMap<String, List<String>> ans = new HashMap<String, List<String>>();
    for(String slang: slangList.keySet())
    {
      String tmp = DefListToStr(slangList.get(slang));
      if(tmp.contains(searchword)){
        ans.put(slang,slangList.get(slang));
      }
    }
    return ans;
  }
  public static String FindDef(String searchword){
    String ans = DefListToStr(slangList.get(searchword));
    if(ans == ""){
      return "Can\'t find the definitions of this slang";
    }
    return ans;
  }
  public static void UpdateHistory(String searchword)
  {
    try(BufferedWriter hist = new BufferedWriter(new FileWriter("history.txt", true))) {
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
          Date date = new Date();
          hist.write(formatter.format(date)+"`"+ searchword + "\n");           
          hist.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static String OnThisDaySlangWord() {
    String[] keys = (String[]) slangList.keySet().toArray();
    String key = keys[(int)(Math.random() * keys.length)];
    return key + " " + DefListToStr(slangList.get(key));
  }
  public static void AddSlangWord()
  {
    String s = scanner.nextLine();

  }
}