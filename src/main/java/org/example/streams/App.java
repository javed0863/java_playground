package org.example.streams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class App {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"root\": [\n" +
                "    {\n" +
                "      \"id\": \"6301\",\n" +
                "      \"someBoolean\": true,\n" +
                "      \"Resources\": [\n" +
                "        {\n" +
                "          \"name\": \"Credit\",\n" +
                "          \"value\": 0,\n" +
                "          \"startFrom\": \"2020-08-10\",\n" +
                "          \"endOn\": \"2023-08-09\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6302\",\n" +
                "      \"someBoolean\": true,\n" +
                "      \"Resources\": [\n" +
                "        {\n" +
                "          \"name\": \"DB_CALLS\",\n" +
                "          \"value\": 1000,\n" +
                "          \"startFrom\": \"2022-08-10\",\n" +
                "          \"endOn\": \"2023-08-09\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6303\",\n" +
                "      \"someBoolean\": false,\n" +
                "      \"Resources\": [\n" +
                "        {\n" +
                "          \"name\": \"DB_CALLS\",\n" +
                "          \"value\": 800,\n" +
                "          \"startFrom\": \"2020-08-10\",\n" +
                "          \"endOn\": \"2021-08-09\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Store_Views\",\n" +
                "          \"value\": 1000,\n" +
                "          \"startFrom\": \"2020-08-10\",\n" +
                "          \"endOn\": \"2021-08-09\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"API_CALLS\",\n" +
                "          \"value\": 2000,\n" +
                "          \"startFrom\": \"2020-08-10\",\n" +
                "          \"endOn\": \"2021-08-09\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6304\",\n" +
                "      \"someBoolean\": true,\n" +
                "      \"Resources\": [\n" +
                "        {\n" +
                "          \"name\": \"DB_CALLS\",\n" +
                "          \"value\": 600,\n" +
                "          \"startFrom\": \"2021-01-10\",\n" +
                "          \"endOn\": \"2022-03-09\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"API_CALLS\",\n" +
                "          \"value\": 3000,\n" +
                "          \"startFrom\": \"2021-01-10\",\n" +
                "          \"endOn\": \"2022-03-09\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        System.out.println(pickResources(new JSONObject(json)).toString());

        System.out.println(pickResources3(new JSONObject(json)).toString());

    }

    static List<JSONObject> pickResources (JSONObject InpJson) {
        List<JSONObject> records = new ArrayList<>();
        JSONArray rootArray = InpJson.getJSONArray("root");
        for (int i = 0; i < rootArray.length(); i++) {
            JSONObject purchaseObject = rootArray.getJSONObject(i);
            if( ! purchaseObject.getJSONArray("Resources").getJSONObject(0).getString("name").equalsIgnoreCase("Credit")) {
                String this_id = purchaseObject.getString("id");
                JSONArray ResourcesArray = purchaseObject.getJSONArray("Resources");
                for(int j=0; j<ResourcesArray.length(); j++) {
                    JSONObject record = ResourcesArray.getJSONObject(j);
                    record.put("id", this_id);
                    records.add(record);
                }
            }
        }
        return records;
    }

    static Stream<JSONObject> toStream(JSONArray arr) {
        return StreamSupport.stream(arr.spliterator(), true) // <=
                .map(JSONObject.class::cast);
    }

    static List<JSONObject> pickResources3 (JSONObject InpJson) {
        return toStream(InpJson.getJSONArray("root"))
                .flatMap(record -> toStream(record.getJSONArray("Resources"))
                                    .filter(resource -> ! resource.getString("name").equals("Credit"))
                                    .map(resource -> resource.put("id", record.getString("id"))))
                .collect(Collectors.toList());
    }
}
