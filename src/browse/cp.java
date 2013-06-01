/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package browse;

/**
 *
 * @author king
 */
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/**
 * Simple Java program to copy files from one directory to another directory.
 * Java IO API doesn't provide any direct way to copy files but you can copy files
 * by copying its contents from InputStream to OutputStream. Though there are some
 * better ways to do it like Using Apache Commons Utils library has FileUtils class
 * to copy files in Java
 *
 * @author Javin
 */
class FileCopyExample 
{

   
    public static void main(String args[]) throws IOException {
        //absolute path for source file to be copied
        String source = "E:/commons.txt";
        //directory where file will be copied
        String target ="G:/";
     
        //name of source file
        File sourceFile = new File(source);
        String name = sourceFile.getName();
     
        File targetFile = new File(target+name);
        System.out.println("Copying file : " + sourceFile.getName() +" from Java Program");
     
        //copy file from one location to other
        FileUtils.copyFile(sourceFile, targetFile);
     
        System.out.println("copying of file from Java program is completed");
    }
   
}

