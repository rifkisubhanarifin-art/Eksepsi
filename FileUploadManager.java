/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception_Handling;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author ASUS
 */
public class FileUploadManager {
    public boolean uploadFile(String localFilePath, String serverHost, int serverPort) {
        // ✅ try-with-resources (auto close)
        try {
            FileInputStream fileInput = new FileInputStream(localFilePath);
             Socket socket = new Socket(serverHost, serverPort);
             OutputStream outputStream = socket.getOutputStream();
            
            System.out.println("Terhubung ke server: " + serverHost + ":" + serverPort);
            
            // Baca file dan kirim ke server
            byte[] buffer = new byte[4096];
            int bytesRead;
            
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            System.out.println("File berhasil diunggah!");
            return true;
            
        } catch (FileNotFoundException e) {
            System.err.println("❌ File tidak ditemukan: " + e.getMessage());
            return false;
            
        } catch (UnknownHostException e) {
            System.err.println("❌ Host tidak dikenal: " + serverHost);
            return false;
            
        } catch (ConnectException e) {
            System.err.println("❌ Tidak bisa terkoneksi ke server. Pastikan server aktif.");
            return false;
            
        } catch (SocketTimeoutException e) {
            System.err.println("❌ Timeout: Server tidak merespons.");
            return false;
            
        } catch (SocketException e) {
            System.err.println("❌ Error koneksi: " + e.getMessage());
            return false;
            
        } catch (IOException ex) {
            System.getLogger(FileUploadManager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
        // ✅ Semua resource otomatis ditutup di sini
        return false;
    }
    
    // Method utama untuk testing
    public static void main(String[] args) {
        FileUploadManager uploader = new FileUploadManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukkan path file lokal: ");
        String filePath = scanner.nextLine();
        
        System.out.print("Masukkan host server: ");
        String host = scanner.nextLine();
        
        System.out.print("Masukkan port server: ");
        int port = scanner.nextInt();
        
        boolean success = uploader.uploadFile(filePath, host, port);
        
        if (success) {
            System.out.println("✅ Upload selesai!");
        } else {
            System.out.println("❌ Upload gagal!");
        }
        
        scanner.close();
    }
}
