import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
/*
@author Ananda Farid S
 */
public class Tugas {

    static void menu() throws IOException {
        try {
            Scanner in = new Scanner(System.in);
            int pilihan;
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            pilihan = in.nextInt();
            switch (pilihan) {
                case 1:
                    input();
                    break;
                case 2:
                    lihatData();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
            }
        } catch (Exception e) {
            System.out.println("Inputan salah Harus Berupa Angka");
        } finally {
            menu();
        }
    }


    static void lihatData() throws IOException {

        try {
            FileReader file = new FileReader("data.txt");
            BufferedReader baca = new BufferedReader(file);
            String data = baca.readLine();
            while (data != null) {
                System.out.println(data);
                data = baca.readLine();
            }
            baca.close();
        } catch (Exception e) {
            System.out.println("File tidak ditemukan");
        } finally {
            menu();
        }
    }

    static void input() throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Membuat array inNumber dan inString
        ArrayList<Integer> inNumber = new ArrayList<Integer>();
        ArrayList<String> inString = new ArrayList<String>();
        String userInput;

        // Membuat loop untuk menerima input dari user
        while (true) {
            try {
                // Menerima input dari user
                System.out.print("Masukkan sebuah angka atau string: ");
                userInput = scanner.nextLine();
                // Memeriksa apakah input merupakan angka atau string
                if (userInput.matches("^[0-9]*$")) {
                    // Jika merupakan angka, maka tambahkan ke array inNumber
                    inNumber.add(Integer.parseInt(userInput));
                } else {
                    // Jika merupakan string, maka tambahkan ke array inString
                    inString.add(userInput);
                }
            } catch (Exception e) {
                // Menangani exception yang terjadi
                System.out.println("Terjadi kesalahan: " + e);
            } finally {
                // Menanyakan apakah user ingin menginput lagi
                System.out.print("Apakah anda ingin menginput lagi? (y/n): ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("n")) {
                    // Jika tidak, maka keluar dari loop
                    break;
                }
            }
        }

        try {
            FileWriter fw = new FileWriter("data.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (inNumber.size() > 0) {
                bw.write("InNumber: " + inNumber);
                bw.newLine();
            }
            if (inString.size() > 0) {
                bw.write("InString: " + inString);
                bw.newLine();
            }
            if (inNumber.size() == 0 && inString.size() == 0) {
                System.out.println("Tidak ada data yang diinput");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e);
        } finally {
            menu();
        }
    }

    public static void main(String[] args) {
        try {
            menu();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e);
        }
    }
}