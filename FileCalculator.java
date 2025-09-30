/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception_Handling;
import java.io.*;
/**
 *
 * @author ASUS
 */
public class FileCalculator {
    public static void main(String[] args) {
        System.out.println("=== PROGRAM KALKULATOR DARI FILE ===\n");
        
        // Panggil method untuk memproses file
        bacaDanHitungDariFile();
        
        System.out.println("\n=== PROGRAM SELESAI ===");
    }
    
    public static void bacaDanHitungDariFile() {
        System.out.println("Membaca file 'data.txt'...");
        
        try (FileReader fr = new FileReader("data.txt");
             BufferedReader br = new BufferedReader(fr)) {  //try-with-resources
            
            String line = br.readLine();
            
            if (line == null || line.trim().isEmpty()) {
                System.out.println("File kosong!");
                return;
            }
            
            System.out.println("Data dari file: '" + line + "'");
            
            int angka = Integer.parseInt(line);
            
            if (angka == 0) {
                System.out.println("Error: Pembagian dengan nol!");
            } else {
                int hasil = 10 / angka;
                System.out.println("Hasil perhitungan: 10 / " + angka + " = " + hasil);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e);
            System.out.println("Pastikan file 'data.txt' ada di folder project!");
        } catch (NumberFormatException e) {
            System.out.println("Format angka tidak valid: " + e);
            System.out.println("Pastikan file berisi angka yang benar!");
        } catch (ArithmeticException e) {
            System.out.println("Error matematika: " + e);
            System.out.println("Pembagian dengan nol tidak diperbolehkan!");
        } catch (Exception e) {
            System.out.println("Error tidak terduga: " + e);
        }
        // Resource otomatis ditutup oleh try-with-resources
    }
}