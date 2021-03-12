/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddressBookPackage;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author ZMQ-19
 */
public class StudentAddressBookMidlet extends MIDlet implements CommandListener{
    
    private Display display;
    private StudentAddressBookDB sdb;
    private List mainmenu;
    private TextBox s_address;
    Command exit=new Command("exit", Command.STOP, 0);
    Command back=new Command("back", Command.BACK, 0);

    public StudentAddressBookMidlet() {
        sdb=new StudentAddressBookDB();
        sdb.addStudentAddressBook("sarfaraz", "aliganj ,bareilly");
        sdb.addStudentAddressBook("farid", "delhi");
        sdb.addStudentAddressBook("abdullah", "aligarh");
        
        
        
        
    }
    

    public void startApp() {
        display=Display.getDisplay(this);
        mainmenu=new List("Addresses", List.IMPLICIT);
        int count=sdb.recordcount();
        for (int i = 0; i < count; i++) {
            mainmenu.append(sdb.getName(i+1), null);
            
            
            
        }
        mainmenu.addCommand(exit);
        mainmenu.setCommandListener(this);
        display.setCurrent(mainmenu);
        System.out.println("i am running");
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c==exit) {
            destroyApp(true);
            notifyDestroyed();
            
        }
        else if (c==back) {
            display.setCurrent(mainmenu);
            
        }
        else {
            List select=(List)display.getCurrent();
            String txtSelect=sdb.getName(select.getSelectedIndex()+1)+","+sdb.getAddress(select.getSelectedIndex()+1);
            s_address=new TextBox("Address", txtSelect, 255, TextField.ANY);
            s_address.addCommand(back);
           s_address.setCommandListener(this);
           display.setCurrent(s_address);
            
        }
    }
}
