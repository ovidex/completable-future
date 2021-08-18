import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

//        URL oracle = new URL("https://jsonplaceholder.typicode.com/users");
//        URLConnection yc = oracle.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                yc.getInputStream()));
//        String inputLine;
//        StringBuilder jsonArray=new StringBuilder();
//        while ((inputLine = in.readLine()) != null)
//        {
//            jsonArray.append(inputLine);
////            System.out.println(inputLine);
//        }
//        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
//        List<User> yourClassList = new Gson().fromJson(String.valueOf(jsonArray), listType);
//        System.out.println(yourClassList);
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {

                    try {
                        URL oracle = new URL("https://jsonplaceholder.typicode.com/users");
                        URLConnection yc = oracle.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                yc.getInputStream()));

                        String inputLine;
                        StringBuilder jsonArray = new StringBuilder();
                        while (true) {
                            if (!((inputLine = in.readLine()) != null)) break;
                            jsonArray.append(inputLine);
                        }
                        Type listType = new TypeToken<ArrayList<User>>() {
                        }.getType();
                        List<User> yourClassList = new Gson().fromJson(String.valueOf(jsonArray), listType);
                        List stream = yourClassList.stream().map(x -> x.getName()).collect(Collectors.toList());
//                        System.out.println(stream);
                        in.close();
                        return stream;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

        );
        System.out.println(completableFuture.get());
//        List stream=yourClassList.stream().map(x->x.getName()).collect(Collectors.toList());
//        System.out.println(stream);
//        in.close();
    }
}
