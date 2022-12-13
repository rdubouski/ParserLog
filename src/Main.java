import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File taboo = new File("./res/taboo.txt");
        FileReader fileReader = new FileReader(taboo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] tabooLine = new String[72];
        int i = 0;

        String fileName = "./res/output.txt";
        BufferedWriter bf = Files.newBufferedWriter(Path.of(fileName),
                StandardOpenOption.TRUNCATE_EXISTING);

        while ((line = bufferedReader.readLine()) != null) {
            tabooLine[i] = line;
            i++;
        }
        for (int j = 0; j < tabooLine.length; j++) {
            System.out.println(tabooLine[j]);
        }

        List<String> ban = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (i = 0; i < 72; i++) {
            temp = readFileByFilter("./res/access.log", tabooLine[i]);
            ban.addAll(temp);
            System.out.println(i);
        }

        if (ban.isEmpty()) {
            System.out.println("empty");
            ban.add("Посещения запрещеных сайтов не найдено!");
        } else {
            System.out.println("full");
        }
        Files.write(Paths.get("./res/output.txt"), ban, StandardOpenOption.CREATE);

        for(int k = 0; k < ban.size(); k++) {
            String value = ban.get(k);
            System.out.println(value);
        }

    }


    public static List<String> readFileByFilter(String fileName, String filter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String tmp = reader.readLine();
            if (tmp.contains(filter)) {
                list.add(tmp);
            }
        }
        reader.close();
        return list;
    }
}