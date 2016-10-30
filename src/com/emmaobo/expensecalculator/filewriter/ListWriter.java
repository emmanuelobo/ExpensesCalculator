package com.emmaobo.expensecalculator.filewriter;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.emmaobo.expensecalculator.interfaces.Writer;

public class ListWriter implements Writer
{
    File file;
    private FileWriter fileWriter;
    private BufferedWriter writer;

    public ListWriter(String filename) throws IOException
    {
        file = new File(filename);
        if(!file.exists())
            file.createNewFile();
        fileWriter = new FileWriter(file, true);
        writer = new BufferedWriter(fileWriter);
    }

    @Override
    public void writeList(HashMap<String, BigDecimal> list)
    {
        int lineCount = 1;
        double total = 0;
        Iterator iterator = list.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry pair = (Map.Entry)iterator.next();
            try {
                writer.append(lineCount + ". " + pair.getKey() + ", $" +pair.getValue());
                writer.newLine();
                total += (Double)pair.getValue();
                lineCount++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.append("Total: $" + total);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("List successfully created.");
    }

}
