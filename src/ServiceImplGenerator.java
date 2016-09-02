import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ServiceImplGenerator {

    public static void generete(){
        File folder = new File("D://realtorTask//realtortask//src//main//java//com//realtortask//entities");

        File[] files = folder.listFiles();
        for(File file: files) {
            if (!file.getName().endsWith("PK.java")) {
                String entityName = removeExtention(file.getName());
                System.out.println(entityName);
                String id = findId(file);
                try (FileWriter writer = new FileWriter("D://serviceImpl//" + entityName + "ServiceImpl.java", false)) {

                    String text = "package com.realtortask.service.impl;";
                    writer.write(text);
                    writer.append('\n');
                    writer.append('\n');
                    String text1 = "import com.realtortask.entities." + entityName + ";";
                    writer.write(text1);
                    writer.append('\n');

                    String text5 = "import org.springframework.beans.factory.annotation.Autowired;";
                    writer.write(text5);
                    writer.append('\n');

                    String text6 = "import org.springframework.stereotype.Service;";
                    writer.write(text6);
                    writer.append('\n');

                    String text4 = "import com.realtortask.repositories."+entityName+"Repository"+";";
                    writer.write(text4);
                    writer.append('\n');

                    String text8 = "import com.realtortask.service."+entityName+"Service"+";";
                    writer.write(text8);
                    writer.append('\n');

                    String text9 = "import java.util.List;";
                    writer.write(text9);
                    writer.append('\n');

                    /*if(!(id.equals("Integer"))){
                        writer.append('\n');
                        String text7 = "import com.realtortask.entities." + id + ";";
                        writer.write(text7);
                    }*/



                    writer.append('\n');
                    writer.append('\n');
                    String text10 = "@Service";
                    writer.write(text10);
                    writer.append('\n');
                    String text2 = "public class " + entityName + "ServiceImpl implements "+ entityName + "Service {";
                    writer.write(text2);
                    writer.append('\n');
                    writer.append('\n');
                    String text11 = "   @Autowired";
                    writer.write(text11);
                    writer.append('\n');

                    String lowerEntityName = entityName.substring(0,1).toLowerCase()+entityName.substring(1)+"Repository";
                    String text12 = "   private "+entityName+"Repository "+lowerEntityName+";";
                    writer.write(text12);
                    writer.append('\n');
                    writer.append('\n');

                    String text15 = "   @Override";
                    writer.write(text15);
                    writer.append('\n');

                    String text16 = "   public "+entityName+" add("+entityName+" "+lowerEntityName.substring(0,3)+") {" ;
                    writer.write(text16);
                    writer.append('\n');

                    String text17 = "       return "+lowerEntityName+".saveAndFlush("+lowerEntityName.substring(0,3)+");";
                    writer.write(text17);
                    writer.append('\n');

                    writer.append("   }");

                    writer.append('\n');

                    writer.append('\n');
                    String text18 = "   @Override";
                    writer.write(text18);
                    writer.append('\n');

                    String text19 = "   public void delete"+"("+entityName+" "+lowerEntityName.substring(0,3)+") {" ;
                    writer.write(text19);
                    writer.append('\n');

                    String text20 = "       "+lowerEntityName+".delete("+lowerEntityName.substring(0,3)+");";
                    writer.write(text20);
                    writer.append('\n');
                    writer.append("   }");

                    writer.append('\n');
                    writer.append('\n');
                    String text21 = "   @Override";
                    writer.write(text21);
                    writer.append('\n');

                    String text22 = "   public "+entityName+" edit"+"("+entityName+" "+lowerEntityName.substring(0,3)+") {" ;
                    writer.write(text22);
                    writer.append('\n');

                    String text23 = "       return "+lowerEntityName+".saveAndFlush("+lowerEntityName.substring(0,3)+");";
                    writer.write(text23);
                    writer.append('\n');
                    writer.append("   }");

                    writer.append('\n');

                    writer.append('\n');
                    String text24 = "   @Override";
                    writer.write(text24);
                    writer.append('\n');

                    String text25 = "   public List<"+entityName+"> getAll() {";
                    writer.write(text25);
                    writer.append('\n');

                    String text26 = "       return "+lowerEntityName+".findAll();";
                    writer.write(text26);
                    writer.append('\n');

                    writer.append('\n');
                    writer.append("    }");




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

