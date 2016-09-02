import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ServiceInterfaceGenerator {

    public static void generete() {
        generateTamplate();

        File folder = new File("D://realtorTask//realtortask//src//main//java//com//realtortask//entities");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (!file.getName().endsWith("PK.java")) {
                String entityName = removeExtention(file.getName());
                System.out.println(entityName);
                /*String id = findId(file);*/
                try (FileWriter writer = new FileWriter("D://service//" +entityName+"Service.java", false)) {

                    String text = "package com.realtortask.service;";
                    writer.write(text);
                    writer.append('\n');
                    writer.append('\n');
                    /*String text1 = "import java.util.List;";
                    writer.write(text1);
                    writer.append('\n');*/
                    String text7 = "import javax.transaction.Transactional;";
                    writer.write(text7);
                    writer.append('\n');

                    String text2 = "import com.realtortask.entities." + entityName + ";";
                    writer.write(text2);
                    writer.append('\n');
                    writer.append('\n');

                    String text4 = "@Transactional";
                    writer.write(text4);
                    writer.append('\n');

                    /*if (!(id.equals("Integer"))) {
                        writer.append('\n');
                        String text5 = "import com.realtortask.entities." + id + ";";
                        writer.write(text5);
                    }*/
                    String text5 = "public interface " + entityName + "Service extends GenericService<" + entityName + ">{";
                    writer.write(text5);
                    writer.append('\n');
                    writer.append('\n');
                    writer.append('}');
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println("Загляни в свою папку с service там тебя ждет сюрприз :)");

    }

    public static String removeExtention(String s) {

        return s.substring(0, s.length() - 5);

    }

    /*public static String findId(File file) {
        String fileString = "";
        Scanner in = null;
        try {
            in = new Scanner(file);
            while (in.hasNext()) {
                fileString = in.nextLine();
                if (fileString.contains("@Id")) {
                    return "Integer";
                } else if (fileString.contains("@EmbeddedId")) {
                    String[] field = in.nextLine().trim().split(" ");
                    return field[1];
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return fileString;
    }*/

    public static void generateTamplate() {
        try (FileWriter writer = new FileWriter("D://service//" + "GenericService.java", false)) {

            String text = "package com.realtortask.service;";
            writer.write(text);
            writer.append('\n');
            writer.append('\n');
            String text1 = "import java.util.List;";
            writer.write(text1);
            writer.append('\n');
            writer.append('\n');
            String text2 = "public interface GenericService<T> {";
            writer.write(text2);
            writer.append('\n');
            writer.append('\n');
            String text3 = "    T add(T obj);";
            writer.write(text3);
            writer.append('\n');
            writer.append('\n');
            String text4 = "    void delete(T entity);";
            writer.write(text4);
            writer.append('\n');
            writer.append('\n');
            String text5 = "    T edit(T obj);";
            writer.write(text5);
            writer.append('\n');
            writer.append('\n');
            String text6 = "    List<T> getAll();";
            writer.write(text6);
            writer.append('\n');

            writer.append('}');
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}


