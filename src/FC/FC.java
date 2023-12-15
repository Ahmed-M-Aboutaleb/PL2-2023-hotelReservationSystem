package FC;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FC {
    private final String dbFolder = "C:\\hotel\\";
    private final String fileName;

    public FC() throws Exception {
        this("temp");
    }

    public FC(String fileName) throws Exception {
        this.fileName = fileName;
    }

    private boolean createFile() throws Exception {
        File New_File = new File(this.dbFolder + this.fileName + ".txt");
        if (New_File.createNewFile() || New_File.exists()) {
            return false;
        }
        return true;
    }

    public boolean writeFile(String text) throws Exception {
        try (FileWriter writeFile = new FileWriter(this.dbFolder + this.fileName + ".txt")) {
            writeFile.write(text);
            writeFile.close();
            return true;
        }
    }

    public boolean appendFile(String text) throws Exception {
        if (createFile())
            throw new Exception("System Error");
        try (FileWriter writeFile = new FileWriter(this.dbFolder + this.fileName + ".txt", true)) {
            writeFile.append(text).append("\n");
            writeFile.close();
            return true;
        }
    }

    public ArrayList<String> readFile() throws Exception {
        if (createFile())
            throw new Exception("System Error");
        File myFile = new File(this.dbFolder + this.fileName + ".txt");
        ArrayList<String> myData = new ArrayList<>();
        try (Scanner readFile = new Scanner(myFile)) {
            while (readFile.hasNextLine()) {
                String data = readFile.nextLine();
                myData.add(data);
            }
        }
        return myData;
    }

    public boolean updateFile(String oldTex, String text) throws Exception {
        if (createFile())
            throw new Exception("System Error");
        File myFile = new File(this.dbFolder + this.fileName + ".txt");
        File myTempFile = new File(this.dbFolder + "temp." + this.fileName + ".txt");
        try (Scanner fileInput = new Scanner(myFile); PrintWriter fileOutput = new PrintWriter(myTempFile)) {
            while (fileInput.hasNext()) {
                String original = fileInput.nextLine();
                String trimmedLine = original.trim();
                if (trimmedLine.equals(oldTex)) {
                    if (text.isEmpty()) continue;
                    fileOutput.println(text);
                } else {
                    fileOutput.println(original);
                }
            }
        }
        return myFile.delete() && myTempFile.renameTo(myFile);
    }

    public static String encode(ArrayList<String> words) {
        StringBuilder strBuilder = new StringBuilder();
        for (String str : words) {
            strBuilder.append(str.length()).append("#").append(str);
        }
        return strBuilder.toString();
    }

    public static ArrayList<String> decode(String s) {
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            String str = s.substring(j + 1, j + 1 + length);
            result.add(str);
            i = j + 1 + length;
        }

        return result;
    }

}
