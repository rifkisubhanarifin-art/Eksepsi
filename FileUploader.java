/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception_Handling;
import java.io.*;
import java.net.*;
import java.io.IOException;
/**
 *
 * @author ASUS
 */
public class FileUploader {
     public boolean uploadFileToServer(String filename, String host, int port) {
        System.out.println("Mengunggah file: " + filename + " ke " + host + ":" + port);
        
        // ✅ Try-with-resources (auto close)
        try {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("Terhubung ke server");
            
            // Baca dan kirim data
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                out.println(line);
                lineCount++;
                System.out.println("Mengirim baris: " + line);
            }
            
            System.out.println("Upload berhasil! " + lineCount + " baris dikirim.");
            return true;
            
        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan: " + filename);
            return false;
        } catch (UnknownHostException e) {
            System.err.println("Host tidak dikenal: " + host);
            return false;
        } catch (ConnectException e) {
            System.err.println("Server menolak koneksi: " + host + ":" + port);
            return false;
        } catch (SocketTimeoutException e) {
            System.err.println("Timeout: Server tidak merespons");
            return false;
        } catch (SocketException e) {
            System.err.println("Error koneksi: " + e);
            return false;
        } catch (IOException ex) { 
             System.getLogger(FileUploader.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
         } 
        // ✅ Semua resource otomatis ditutup di sini
         return false;
    }
    
    // Main class untuk testing
    public static void main(String[] args) {
        FileUploader uploader = new FileUploader();
        
        // Test dengan file yang ada
        boolean success = uploader.uploadFileToServer(
            "data.txt", 
            "localhost", 
            8080
        );
        
        if (success) {
            System.out.println("Upload berhasil!");
        } else {
            System.out.println("Upload gagal!");
        }
    }
}