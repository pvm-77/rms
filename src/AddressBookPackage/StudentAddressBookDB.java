package AddressBookPackage;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.AlertType;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;
import javax.microedition.lcdui.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZMQ-19
 */
public class StudentAddressBookDB {
    //creat eobject for RecordStore class
    RecordStore rs=null;
    
    
    
    String name;
    String address;

    public StudentAddressBookDB() {
        
        //create or open a ecord store
        try {
            rs=RecordStore.openRecordStore("StudentAddressBook", true);
        } catch (RecordStoreException e) {
//            Alert alert=new Alert("error to create recordstore", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
        }
        
    }
    
    public void addStudentAddressBook(String name,String address)
    {
        //convert string data into byte array to store in recordstore
        
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream output=new DataOutputStream(baos);
        try {
            output.writeUTF(name+","+address);
            
        } catch (Exception e) {
//            Alert alert=new Alert("error in stream", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
        }
        byte[] b=baos.toByteArray();
        //now we get the byte array form we would add record
        try {
            rs.addRecord(b, 0, b.length);
        } catch (RecordStoreException e) {
//            Alert alert=new Alert("error in writing", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
        }
    }
    

    public String getName(int index) {
        int counter=1;
        int end;
        try {
            RecordEnumeration rnum=rs.enumerateRecords(null, null, false);
            while ((counter<=index) && (rnum.hasNextElement())) {
                String temp=new String(rnum.nextRecord());
                
                end=temp.indexOf(',');
                name=temp.substring(2, end);
                
            }
        } catch (RecordStoreException e) {
            
//            Alert alert=new Alert("error reading name", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
        }
        
        
        return name;
    }

    

    public String getAddress(int index) {
         int counter=1;
        int end;
        try {
            RecordEnumeration rnum=rs.enumerateRecords(null, null, false);
            while ((counter<=index) && (rnum.hasNextElement())) {
                String temp=new String(rnum.nextRecord());
                
                end=temp.indexOf(',');
                address=temp.substring( end+1);
                
            }
        } catch (RecordStoreException e) {
            
//            Alert alert=new Alert("error read address", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
        }
        
        
        return address;
    }
    public int recordcount()
    {
        int count=0;
        try {
            rs.getNumRecords();
        } catch (RecordStoreNotOpenException e) {
//            Alert alert=new Alert("error read address", e.toString(), null, AlertType.WARNING);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
            
        }
        return count;
    }

   
    
    
    
}
