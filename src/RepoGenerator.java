import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RepoGenerator {

    public static void generete(){
        File folder = new File("D://realtorTask//realtortask//src//main//java//com//realtortask//entities");

        File[] files = folder.listFiles();
        for(File file: files) {
            if (!file.getName().endsWith("PK.java")) {
                String entityName = removeExtention(file.getName());
                System.out.println(entityName);
                String id = findId(file);
                try (FileWriter writer = new FileWriter("D://repo//" + entityName + "Repository.java", false)) {

                    String text = "package com.realtortask.repositories;";
                    writer.write(text);
                    writer.append('\n');
                    writer.append('\n');
                    String text1 = "import com.realtortask.entities." + entityName + ";";
                    writer.write(text1);
                    writer.append('\n');
                    String text4 = "import org.springframework.data.jpa.repository.JpaRepository;";
                    writer.write(text4);

                    if(!(id.equals("Integer"))){
                        writer.append('\n');
                        String text5 = "import com.realtortask.entities." + id + ";";
                        writer.write(text5);
                    }

                    writer.append('\n');
                    writer.append('\n');
                    String text2 = "public interface " + entityName + "Repository extends JpaRepository<"+ entityName + ","+" "+id+">{";
                    writer.write(text2);
                    writer.append('\n');
                    writer.append('\n');
                    writer.append('}');
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println("Загляни в свою папку с репозиториями там тебя ждет сюрприз :)");

    }

    public static String removeExtention(String s) {

        return s.substring(0,s.length()-5);

    }
    public static  String findId(File file){
        String fileString = "";
        Scanner in = null;
        try {
            in = new Scanner(file);
            while(in.hasNext()) {
                fileString = in.nextLine();
                if(fileString.contains("@Id")){
                    return "Integer";
                }else  if (fileString.contains("@EmbeddedId")){
                    String[] field = in.nextLine().trim().split(" ");
                    return field[1];
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            in.close();
        }
        return fileString;
    }
}
