/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tugas1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author farhan
 */
public class MainController implements Initializable {
    
         private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     
   private void showAlertTrue(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
     
             private ButtonType showAlertConfirm(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
      
        return  alert.showAndWait().orElse(ButtonType.CANCEL);
    }
             
             
        private ButtonType showAlertUpdate(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
      
        return  alert.showAndWait().orElse(ButtonType.CANCEL);
    }
   
   
    
     @FXML
    private TableView<Buku> Tblbuku;
  
    @FXML
    private TableColumn<Buku, String> colKdBuku;

    @FXML
    private TableColumn<Buku, String> colJudul;
    
    @FXML
    private TableColumn<Buku, String> colPengarang;

    @FXML
    private TableColumn<Buku, String> colPenerbit;
    
    @FXML
    private TableColumn<Buku, String> colTahunterbit;

    @FXML
    private TableColumn<Buku, String> colEdisi;
    
    
    @FXML
    private Button btnAdd;
    
     @FXML
    private Button btnUpdate;
     
     @FXML
    private Button btnDelete;
     
     
    @FXML
    private TextField txtKodebuku;
    
    @FXML
    private TextField txtJudul;
    @FXML
    private TextField txtPengarang;
    @FXML
    private TextField txtPenerbit;
    @FXML
    private TextField txtTahunTerbit;
    @FXML
    private TextField txtEdisi;
    
    
    private BukuDAO BukuDAO; 
@FXML
private void loadDataBuku() {
    BukuDAO = new BukuDAO(); 
    ObservableList<Buku> bukuList = FXCollections.observableArrayList(BukuDAO.getBuku());
    Tblbuku.setItems(bukuList);
}
    
    
    @FXML
    private Buku selectedBuku;
    
        private void clearFields() {
        txtKodebuku.clear();
        txtKodebuku.setEditable(true); 
        txtKodebuku.setFocusTraversable(true); 
        txtJudul.clear();
        txtPengarang.clear();
        txtPenerbit.clear();
        txtTahunTerbit.clear();
        txtEdisi.clear();
        
        selectedBuku = null; 
    }
    
 

    @FXML
    private void selectBuku(Buku buku) {
        if (buku != null) {
        selectedBuku = buku;
        txtKodebuku.setText(buku.getKode_buku());
        txtKodebuku.setEditable(false); 
        txtKodebuku.setFocusTraversable(false); 

        txtJudul.setText(buku.getJudul());
        txtPengarang.setText(buku.getPengarang());
        txtPenerbit.setText(buku.getPenerbit());
        txtTahunTerbit.setText(buku.getTahun_terbit());
        txtEdisi.setText(buku.getEdisi());
        }
    }
    
       
    
    @FXML
    private void addBuku() {  
    String kdbuku = txtKodebuku.getText();
    String judul = txtJudul.getText();
    String pengarang = txtPengarang.getText();
    String penerbit = txtPenerbit.getText();
    String tahun_terbit = txtTahunTerbit.getText();
    String edisi = txtEdisi.getText();
    
   if (!kdbuku.isEmpty() && !judul.isEmpty() && !pengarang.isEmpty() && !penerbit.isEmpty() && !tahun_terbit.isEmpty() && !edisi.isEmpty()) {
       
        ButtonType results = showAlertUpdate("Konfirmasi Data", "Apakah Data Sudah Benar?");
        if(tahun_terbit.length() < 4){
        showAlert("Error", "Tahun Terbit Wajib 4 Digit Angka");
        return;
        }
        
       if (results == ButtonType.OK){
                try {
                   Buku newBuku = new Buku(kdbuku, judul, pengarang, penerbit, tahun_terbit, edisi);
                   BukuDAO.addBuku(newBuku);
                   loadDataBuku();
                   clearFields();
                   showAlertTrue("Berhasil", "Data berhasil Di Buat!");
                } catch (Exception e) {
                   e.printStackTrace();
                   showAlert("Error", "Terjadi kesalahan: " + e.getMessage());
                }        
       }
       
   

    }else if (kdbuku.isEmpty() && judul.isEmpty() && pengarang.isEmpty() ||
        penerbit.isEmpty() && tahun_terbit.isEmpty() && edisi.isEmpty()) {
        showAlert("Input Error", "Semua field harus diisi!");
        return;
    
    } 
    

}
     
     
         @FXML
private void updateBuku() {  
     if (selectedBuku == null) {
        showAlert("Selection Error", "Tidak ada buku yang dipilih!");
        return;
    }
    
    String kdbuku = txtKodebuku.getText();  
    String judul = txtJudul.getText();
    String pengarang = txtPengarang.getText();
    String penerbit = txtPenerbit.getText();
    String tahun_terbit = txtTahunTerbit.getText();
    String edisi = txtEdisi.getText();

    // Validasi input
    if (kdbuku.isEmpty() && judul.isEmpty() && pengarang.isEmpty() ||
        penerbit.isEmpty() && tahun_terbit.isEmpty() && edisi.isEmpty()) {
        showAlert("Input Error", "Semua field harus diisi!");
        return;
        

    }else if(!kdbuku.isEmpty() && !judul.isEmpty() && !pengarang.isEmpty() && !penerbit.isEmpty() && !tahun_terbit.isEmpty() && !edisi.isEmpty()){
        
        ButtonType results = showAlertUpdate("Konfirmasi Update", "Yakin Untuk Mengubah Data?");
        
        if (results == ButtonType.OK){
             try {
    selectedBuku.setKode_buku(kdbuku);
    selectedBuku.setJudul(judul);
    selectedBuku.setPengarang(pengarang);
    selectedBuku.setPenerbit(penerbit);
    selectedBuku.setTahun_terbit(tahun_terbit);
    selectedBuku.setEdisi(edisi);
    

   
    BukuDAO.updateBuku(selectedBuku);

   
    loadDataBuku();
    clearFields();
            showAlertTrue("Berhasil", "Data berhasil diperbarui!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan: " + e.getMessage());
        }
        }
        
        
        
   
         
        
    }else{
        showAlert("Selection Error", "Tidak ada buku yang dipilih!");
        return;
    }  
        
        
    
    


}
  
     
       @FXML
private void deleteBuku() {
    if (selectedBuku == null) {
        showAlert("Selection Error", "Tidak ada buku yang dipilih!");
        return;
    }

    ButtonType result = showAlertConfirm("Konfirmasi Hapus", "Yakin ingin menghapus data?");

    if (result == ButtonType.OK) {
        try {
            BukuDAO.deleteBuku(selectedBuku.getKode_buku());
            loadDataBuku();
            clearFields();
            showAlertTrue("Berhasil", "Data berhasil dihapus!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         colKdBuku.setCellValueFactory(new PropertyValueFactory<>("kode_buku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        colTahunterbit.setCellValueFactory(new PropertyValueFactory<>("tahun_terbit"));
        colEdisi.setCellValueFactory(new PropertyValueFactory<>("edisi"));

        loadDataBuku();
       
        Tblbuku.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selectBuku(newValue)
        );
        
        
    txtEdisi.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { 
            txtEdisi.setText(newValue.replaceAll("[^\\d]", "")); 
            showAlert("Error Input", "Hanya Boleh Angka!");
            }
        }); 
    txtKodebuku.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { 
            txtKodebuku.setText(newValue.replaceAll("[^\\d]", "")); 
            showAlert("Error Input", "Hanya Boleh Angka!");
            }
        }); 
        txtTahunTerbit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { 
            txtTahunTerbit.setText(newValue.replaceAll("[^\\d]", "")); 
            showAlert("Error Input", "Hanya Boleh Angka!");
            }else if(newValue.length() > 4){
             txtTahunTerbit.setText(newValue.substring(0, 4));
            }
        }); 
    
  
        
    }    
    }    
 