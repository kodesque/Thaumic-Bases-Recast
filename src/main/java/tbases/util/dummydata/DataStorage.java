package tbases.util.dummydata;


import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static String dataString = "";

    public DataStorage() {
    }

    public static void addDataToString(DummyData data) {
        dataString = dataString.concat(data.toString());
    }

    public static String getDataString() {
        String ret = dataString;
        dataString = "";
        return ret;
    }

    public static DummyData[] parseData(String s) {
        String field = "";
        Object value = null;
        List<DummyData> data = new ArrayList();

        for(int i = 0; i < s.length(); ++i) {
            if (i + 2 < s.length() && s.substring(i, i + 2).contains("||")) {
                int size = 0;

                for(int i1 = i; i1 < s.length() && s.charAt(i1) != ':'; ++i1) {
                    ++size;
                }

                field = s.substring(i + 2, i + size);
            }

            if (i + 1 < s.length() && s.substring(i, i + 1).contains(":")) {
                int size = 0;

                for(int i1 = i; i1 < s.length() && s.charAt(i1) != '|'; ++i1) {
                    ++size;
                }

                value = s.substring(i + 1, i + size);
            }

            if (field != "" && value != null) {
                DummyData date = new DummyData(field, value);
                data.add(date);
                field = "";
                value = null;
            }
        }

        DummyData[] ret = new DummyData[data.size()];

        for(int i = 0; i < ret.length; ++i) {
            ret[i] = (DummyData)data.get(i);
        }

        return ret;
    }
}
