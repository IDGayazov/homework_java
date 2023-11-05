package task4;

import java.io.*;

public class Main {
    // сложение матриц
    public static void addingMatrixInFile(String sourceFileName, String destFileName){
        try(
                BufferedReader br = new BufferedReader(new FileReader(sourceFileName));
                FileWriter fw = new FileWriter(destFileName)
        ){
            String x = br.readLine();
            if(x == null){
                System.out.println("Empty result!");
                return;
            }
            int t = Integer.parseInt(x);
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            int[][] res = new int[n][m];
            for(int i = 0; i < t; ++i){
                for(int j = 0; j < n; ++j){
                    String str = br.readLine();
                    if(str == null){
                        throw new RuntimeException("Not correct format!");
                    }
                    String[] nums = str.split(" ");
                    for(int k = 0; k < nums.length; ++k){
                        res[j][k] += Integer.parseInt(nums[k]);
                    }
                }
            }

            for(int i = 0; i < n; ++i){
                for(int j = 0; j < m; ++j){
                    fw.write(res[i][j] + "  ");
                }
                fw.write("\n");
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // удаление комментариев
    public static void deleteComments(String sourceFileName, String destFileName){
        try(
                FileReader fr = new FileReader(sourceFileName);
                FileWriter fw = new FileWriter(destFileName);
                ){

            int x = fr.read();
            StringBuilder sb = new StringBuilder();
            while(x != -1){
                if(x != '/'){
                    sb.append((char)x);
                }else{
                    x = fr.read();
                    if(x != '/' && x != '*'){
                        sb.append((char)x);
                    }else if(x == '/'){
                        sb.append('\n');
                        while(x != -1 && x != '\n'){
                            x = fr.read();
                        }
                    }else{
                        sb.append('\n');
                        while(true){
                            x = fr.read();
                            if(x == '*'){
                                x = fr.read();
                                if(x == '/'){
                                    break;
                                }
                            }
                            if(x == -1){
                                throw new RuntimeException("No correct java file");
                            }
                        }
                    }
                }
                x = fr.read();
            }
            fw.write(sb.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // удаление слова
    public static void deleteWord(String fileName, String pattern){
        long pos = 0;
        int q = 0;
        try(
                RandomAccessFile raf = new RandomAccessFile(fileName, "r")
                ){
            int x = raf.read();
            while(x != -1){
                StringBuilder sb = new StringBuilder();
                pos = raf.getFilePointer();
                q = 0;
                while(x != ' '){
                    q++;
                    sb.append((char)x);
                    x = raf.read();
                }
                if(pattern.equals(sb.toString())){
                    break;
                }
                sb.delete(0, sb.length());
                x = raf.read();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        try(
                RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        ){
            raf.seek(pos + q);
            int x = raf.read();
            StringBuilder sb = new StringBuilder();
            while(x != -1){
                sb.append((char)x);
                x = raf.read();
            }
            raf.seek(pos - 1);
            raf.write(sb.toString().getBytes());
            raf.setLength(raf.length() - q - 1);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        deleteComments(".\\src\\task4\\Main.java", "out1.txt");
        addingMatrixInFile("input", "out.txt");
        deleteWord("file.txt", "ground");
    }
}

//    public static void readSymbol(){
//        File sourceFile = new File(SOURCE_FILE_PATH);
//        try(FileReader fr = new FileReader(sourceFile); FileWriter fw = new FileWriter("out.txt")){
//            int x = fr.read();
//            while(x != -1){
//                fw.write(x);
//                x = fr.read();
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }

//    public static void readLine(){
//        File sourceFile = new File(SOURCE_FILE_PATH);
//        try(BufferedReader br = new BufferedReader(new FileReader(sourceFile));
//            BufferedWriter bw = new BufferedWriter(new FileWriter("out1.txt"))){
//            String x = br.readLine();
//            while(x != null){
//                bw.write(x + "\n");
//                x = br.readLine();
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    // задача 1
//    public static int getPosOfStringInFile(String pattern){
//        try(FileReader fr = new FileReader(SOURCE_FILE_PATH)){
//            int x = 0;
//            int pos = 1;
//            StringBuilder tmp = new StringBuilder();
//            while(x != -1){
//                x = fr.read();
//                if((char)x == ' '){
//                    if(tmp.toString().equals(pattern)){
//                        return pos + 1;
//                    }
//                    tmp.delete(0, tmp.length());
//                    pos += 1;
//                }else{
//                    tmp.append((char)x);
//                }
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
