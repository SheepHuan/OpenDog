import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromFile {

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName, String writefileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行,正在读取中");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                line++;

                appendMethodB(writefileName, tempString + "\r\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 递归读取目录下的所有文件及子目录下所有文件
     */
    public static void findDir(String patn, String writefileName) {
        File dir = new File(patn);

        if (dir.isDirectory()) {
            System.out.println("获取"+dir.getName()+"文件夹下的集合:");
            String[] children = dir.list();
            // 递归读取目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                String chidPaath = patn + '/' + children[i];
                File child = new File(chidPaath);
                if (child.isDirectory()) {
                    System.out.println(child.getName()+"文件夹:");
                    findDir(chidPaath, writefileName);
                } else {
                    System.out.println("文件-----读取:" + child.getName());
                    readFileByLines(chidPaath, writefileName);
                }
            }
        } else {
            readFileByLines(patn, writefileName);
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "E:\\研一（下）\\实验室\\数据实验\\GitHub2021\\2021.1\\1.1";// 选择你的文件
        String writeFile = "E:\\研一（下）\\实验室\\数据实验\\GitHub2021\\2021.1\\2021.1.1.json";//选择你要生成的文件
        File file = new File(writeFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ReadFromFile.findDir(path, writeFile);
    }
}
