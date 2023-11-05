package task5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Main {

    // Задача 1
    public static void writeRandomFile(final String fileName, final int size){
        try(
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))
                ){

            Random rand = new Random();

            for(int i = 0; i < size; ++i){
                dos.writeInt(rand.nextInt() % 1000);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void reverseFile(final String sourceFile, final String outFile){
        try(RandomAccessFile raf = new RandomAccessFile(sourceFile, "r");
            RandomAccessFile wfile = new RandomAccessFile(outFile, "rw")){
            for(long i = raf.length() - 4; i >= 0; i -= 4){
                raf.seek(i);
                int end = raf.readInt();
                wfile.writeInt(end);
            }
            wfile.setLength(raf.length());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Задача 2
    public static void writeFile(String outputFile){
        try(RandomAccessFile raf = new RandomAccessFile(outputFile, "rw")){
            for(int i = 0; i < 10; ++i){
                raf.writeInt(i);
            }
            for(int i = 9; i >= 0; --i){
                raf.writeInt(i);
            }
            raf.setLength(20*4);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static boolean isSymmetric(final String sourceFileName){
        try(RandomAccessFile raf = new RandomAccessFile(sourceFileName, "r")){
            final long len = raf.length();
            for(long i = len - 4; i > len/2; i -= 4){
                raf.seek(i);
                int right = raf.readInt();
                raf.seek(len - i - 4);
                int left = raf.readInt();
                if(left != right){
                    return false;
                }
            }
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    // Задача 3
    public static void writeSignedFile(final String dest){
        try(
                DataOutputStream dis = new DataOutputStream(new FileOutputStream(dest));
        ){

            int[] arr = {-1, 1, 2, -1, 3, - 4, -2, -1, 4, 9, -2, 15};
            for(int x: arr){
                dis.writeInt(x);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void makeSignAlternatingFile(final String fileName){
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw")){

            final long len = raf.length();

            long l = 0;
            long r = len - 4;

            while(l < r){

                raf.seek(l);
                int left = raf.readInt();
                l += 4;
                while(l < len && left > 0){
                    left = raf.readInt();
                    l += 4;
                }

                raf.seek(r);
                int right = raf.readInt();
                r -= 4;
                while(r > 0 && right <= 0) {
                    raf.seek(r);
                    right = raf.readInt();
                    r -= 4;
                }

                if(l - 4 <= r + 4) {
                    raf.seek(r + 4);
                    raf.writeInt(left);
                    raf.seek(l - 4);
                    raf.writeInt(right);
                }
            }

            final long mid = len / 2;
            for(int i = 0; i < mid; i += 8){
                raf.seek(i);
                int pos = raf.readInt();
                raf.seek(mid + i + 4);
                int neg = raf.readInt();
                raf.seek(mid + i + 4);
                raf.writeInt(pos);
                raf.seek(i);
                raf.writeInt(neg);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Задача 4
    public static void writeMatrix(final String input, final String destFileName){
        try(DataOutputStream dis =
                    new DataOutputStream(Files.newOutputStream(Paths.get(destFileName)));
            BufferedReader bf = new BufferedReader(new FileReader(input))){

            int size = Integer.parseInt(bf.readLine());
            dis.writeInt(size);
            String[] e = bf.readLine().split(" ");
            int n = Integer.parseInt(e[0]);
            int m = Integer.parseInt(e[1]);
            dis.writeInt(n);
            dis.writeInt(m);
            for(int t = 0; t < size; ++t) {
                for (int i = 0; i < n; ++i) {
                    e = bf.readLine().split(" ");
                    for (int j = 0; j < m; ++j) {
                        dis.writeInt(Integer.parseInt(e[j]));
                    }
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void addMatrix(final String sourceFileName, final String destFile){
        try(DataInputStream source = new DataInputStream(Files.newInputStream(Paths.get(sourceFileName)));
            DataOutputStream dest = new DataOutputStream(new FileOutputStream(destFile))){

            int size = source.readInt();
            int n = source.readInt();
            int m = source.readInt();
            int[][] res = new int[n][m];
            for(int t = 0; t < size; ++t){
                for(int i = 0; i < n; ++i){
                    for(int j = 0; j < m; ++j){
                        res[i][j] += source.readInt();
                    }
                }
            }

            for(int i = 0; i < n; ++i){
                for(int j = 0; j < m; ++j){
                    dest.writeInt(res[i][j]);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void readFile(String sourceFile){
        try(RandomAccessFile raf = new RandomAccessFile(sourceFile, "rw")){
            for(int i = 0; i < raf.length(); i += 4){
                System.out.print(raf.readInt() + " ");
            }
            System.out.println();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        writeRandomFile("rand_file", 20);
        readFile("rand_file");
        reverseFile("rand_file", "output");
        readFile("output");

        writeFile("input2");
        System.out.println(isSymmetric("input2"));
        readFile("input2");

        writeMatrix("matrix", "out3");
        addMatrix("out3", "out2");
        readFile("out2");

        writeSignedFile("output1");
        makeSignAlternatingFile("output1");
        readFile("output1");
    }
}
