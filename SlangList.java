import java.io.*;  // Import the File class
import java.text.SimpleDateFormat;
import java.util.*; // Import the Scanner class to read text files

/**
 * Main
 */
public class SlangList {
  public static HashMap<String, String> slangList = new HashMap<String, String>();
  
  public static Scanner scanner = new Scanner(System.in);
  // get(slang), remove(slang), clear() : all

  SlangList(String filename){
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] slang = myReader.nextLine().split("`");
        if(slang.length >1){
          slangList.put(slang[0], slang[1]);              
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  // public static String DefListToStr(String deflist)
  // {
  //   if(deflist == null){
  //     return "";
  //   }
  //   String ans = "";
  //   for(String def: deflist)
  //   {
  //     ans += ", " + def;
  //   }
  //   return ans.substring(2);
  // }
  public static HashMap<String, String> FindSlang(String searchword)
  {
    UpdateHistory(searchword);
    HashMap<String, String> ans = new HashMap<String, String>();
    for(String slang: slangList.keySet())
    {
      String tmp = slangList.get(slang);
      if(tmp.contains(searchword)){
        ans.put(slang,slangList.get(slang));
      }
    }
    return ans;
  }
  public static String FindDef(String searchword){
    UpdateHistory(searchword);
    String ans = slangList.get(searchword);
    if(ans == ""){
      return "Can\'t find the definitions of this slang";
    }
    return ans;
  }
  public static void UpdateHistory(String searchword)
  {
    try(BufferedWriter hist = new BufferedWriter(new FileWriter("history.data", true))) {
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
    Object[] keys = slangList.keySet().toArray();
    Random r = new Random();
    String key = keys[r.nextInt(keys.length)].toString();
    return key + " " + slangList.get(key);
  }
  public static String DeleteSlangWord(String slang)
  {
    if(slangList.get(slang) != null)
    {
      slangList.remove(slang);
      return "Delete success";
      // Remove success
    }else{
      return "Can't find the slang";
      // Can't find slang
    }
  }
  public static void AddSlangWord(String nslang, String ndef)
  {
    if(slangList.get(nslang) == null)
    {
      slangList.put(nslang, ndef);
      //Success
    } else{
      // User choose duplicate/ add definition
      if(Math.random() <0.5){
        slangList.put(nslang, slangList.get(nslang) + ndef);
      }else{
        //User choose overwrite
        slangList.put(nslang, ndef);
      }

    }
  }
  
}