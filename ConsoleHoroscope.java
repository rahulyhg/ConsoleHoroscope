import java.util.*;
import java.text.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import org.json.*;

class ConsoleHoroscope {

  static HashMap<String, List<Date>> horoscopeList;

  public enum horocategory {
    TODAY, WEEK, MONTH, YEAR
  }

  public static Date stringToDate(String aDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
    Date resultDate = new Date();
    try {
      resultDate = sdf.parse(aDate);
    } catch(ParseException e) {
      System.out.println("Bad date input.");
      System.exit(0);
    }
    return resultDate;
  }

  public static horocategory stringToEnum(String aString) {
    horocategory resultType = null;
    switch(aString) {
      case "daily":
        resultType = horocategory.TODAY;
        break;
      case "weekly":
        resultType = horocategory.WEEK;
        break;
      case "monthly":
        resultType = horocategory.MONTH;
        break;
      case "yearly":
        resultType = horocategory.YEAR;
        break;
      default:
        System.out.println("Bad input. Default: Daily horoscope.");
        resultType = horocategory.TODAY;
        break;
    }
    return resultType;
  }

  public static String askForDetail(String aMessage) {
    System.out.print(aMessage);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String result = null;
    try {
      result = br.readLine();
    } catch(IOException e) {
      System.out.println("Bad input for details.");
      System.exit(0);
    }
    return result;
  }

  public static void populateHoroscopeList() {
    String lineRead;
    horoscopeList = new HashMap<String, List<Date>>();
    try {
      BufferedReader br = new BufferedReader(new FileReader("horoscopedates.txt"));
      while((lineRead = br.readLine()) != null) {
        String[] splitLine = lineRead.split(",");
        List<Date> dateRangeList = new ArrayList<Date>();
        dateRangeList.add(stringToDate(splitLine[1]));
        dateRangeList.add(stringToDate(splitLine[2]));
        horoscopeList.put(splitLine[0], dateRangeList);
      }
      br.close();
    } catch(IOException e) {
      System.out.println("Bad Population File.");
    }
  }

  public static String findHoroscope(Date aDate, Map aMap) {
    Iterator it = aMap.entrySet().iterator();
    String horoscopeResult = null;
    while(it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      ArrayList dateRangeArray = (ArrayList)entry.getValue();
      if((aDate.after((Date)dateRangeArray.get(0)) && (aDate.before((Date)dateRangeArray.get(1))))) {
        return (String)entry.getKey();
      }
    }
    System.out.println("No match.");
    return "";
  }

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  /*public static void stringToJSON(String aStringObject) {
    try {
      JSONArray jArray = new JSONArray(aStringObject);
      //String horoscope = json.getString("horoscope");
      //System.out.println(horoscope);
    } catch(JSONException e) {
      System.out.println("Could not handle JSON.");
    }
  }*/

  public static void getHoroscope(String horoscopeSignature, String horoscopeType) {
    String link = "http://horoscope-api.herokuapp.com/horoscope/" + horoscopeType.toLowerCase() + "/" + horoscopeSignature.toLowerCase();
    try {
      InputStream is = new URL(link).openStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String response = readAll(br);
      System.out.println(response);
    } catch(IOException e) {
      System.out.println("Bad URL.");
      return;
    }
  }

  public static void main(String[] args) {
    populateHoroscopeList();
    getHoroscope(
      findHoroscope(stringToDate(askForDetail("Please enter a birth date: (dd/mm): ")), horoscopeList),
      String.valueOf(stringToEnum(askForDetail("Would you like a daily, weekly, monthly or yearly horoscope: ")))
    );
  }
}
