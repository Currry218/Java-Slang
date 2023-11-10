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
          slangList.put(slang[0], slang[1].replace("|", " |"));              
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static void ResetList(){
    try {
      File myObj = new File("slang.txt");
      slangList.clear();
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] slang = myReader.nextLine().split("`");
        if(slang.length >1){
          slangList.put(slang[0], slang[1].replace("|", " |"));              
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static void SaveList(){
    try(BufferedWriter data = new BufferedWriter(new FileWriter("slangedit.data"))) {
      for(String slang: slangList.keySet())
        data.write(slang + "`" + slangList.get(slang).replace(" | ", "| ") + "\n");           
      data.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public static HashMap<String, String> FindSlang(String searchword) //Find by def
  {
    searchword = searchword.toUpperCase();
    UpdateHistory(searchword);
    HashMap<String, String> ans = new HashMap<String, String>();
    for(String slang: slangList.keySet())
    {
      if(slangList.get(slang).toUpperCase().contains(searchword)){
        ans.put(slang,slangList.get(slang));
      }
    }
    return ans;
  }
  public static HashMap<String, String> FindDef(String searchword){
    // UpdateHistory(searchword);
    // String ans = slangList.get(searchword);
    // if(ans == ""){
    //   return "Can\'t find the definitions of this slang";
    // }
    // return ans;
    searchword = searchword.toUpperCase();
    UpdateHistory(searchword);
    HashMap<String, String> ans = new HashMap<String, String>();
    for(String slang: slangList.keySet())
    {
      if(slang.contains(searchword)){
        ans.put(slang,slangList.get(slang));
      }
    }
    return ans;
  }
  public static void UpdateHistory(String searchword)
  {
    if(searchword == "")
      return;
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
    return key + ": " + slangList.get(key);
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
  public static void AddSlangWord(String nslang, String ndef, boolean dup)
  {
    if(slangList.get(nslang) == null)
    {
      slangList.put(nslang, ndef);
      //Success
    } else{
      // User choose duplicate/ add definition
      if(dup){
        slangList.put(nslang, slangList.get(nslang) + ndef);
      }else{
        //User choose overwrite
        slangList.put(nslang, ndef);
      }
    

    }
    
  }
  public static void Edit(String oldslang, String nslang, String ndef)
  {
    slangList.remove(oldslang);
    slangList.put(nslang, ndef);
  }
  public static String[] GetQuiz(boolean isSlangQuiz)
  {
    String[] ans = new String[6];
    Object[] keys = slangList.keySet().toArray();
    Random r = new Random();
    if(isSlangQuiz)
    {
      // random.nextInt(max - min) + min;
      int ques = r.nextInt(keys.length);
      ans[0] = keys[ques].toString();
      int ans_idx = r.nextInt(4) + 1;
      ans[ans_idx] = slangList.get(keys[ques]);
      ans[5] = Integer.toString(ans_idx);
      for(int i = 1; i <= 4; i++)
      {
        if(ans[i] == null){
          ans[i] = slangList.get(keys[r.nextInt(keys.length)]);
        }
      }
    }
    else
    {
      int ques = r.nextInt(keys.length);
      int ans_idx = r.nextInt(4) + 1;
      ans[0] = slangList.get(keys[ques]);
      ans[ans_idx] = keys[ques].toString();
      ans[5] = Integer.toString(ans_idx);
      for(int i = 1; i <= 4; i++)
      {
        if(ans[i] == null){
          ans[i] = keys[r.nextInt(keys.length)].toString();
        }
      }
    }
    return ans;

  }
}
