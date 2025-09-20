package lib;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.*;

public class Register extends JFrame implements ActionListener {
    Container cp ;
    JLabel register , username , password1 , password2;
    JTextField t1;
    JPasswordField t2 , t3;
    JButton b1 , b2 ;
    public Register(){
        Initial(); // ตั้งค่าเริ่มต้น
        setComponent(); // เพิ่ม Component
        Finally(); // ตั้งค่าขั้นสุดท้าย
    }
    private void Initial() {
        ImageIcon img = new ImageIcon("./img/ticket.png");
        this.setIconImage(img.getImage());
        cp = this.getContentPane(); // สร้าง Container
        cp.setLayout(null); // ปิดการจัดการ Layout
        cp.setBackground(Color.decode("#FFCCCC")); // กำหนดสีพื้นหลัง
    }
    private void setComponent() {
        // เพิ่ม Component
        register = new JLabel("Register");
        username = new JLabel("Username (E-mail)");
        t1 = new JTextField();
        password1 = new JLabel("Password (Should be 8 - 16 characters)");
        t2 = new JPasswordField();
        password2 = new JLabel("Confirm Password");
        t3 = new JPasswordField();
        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");

        // กำหนดขนาดและตำแหน่ง
        register.setBounds(140,20,150,40);
        register.setFont(new Font("Arial",Font.BOLD,30));
        // ช่องกรอก Username
        username.setBounds(50,90,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(50,120,300,30);
        // ช่องกรอก Password
        password1.setBounds(50,170,300,30);
        password1.setFont(new Font("Arial",Font.BOLD,15));
        t2.setBounds(50,200,300,30);
        // ช่องกรอก Re-enter Password
        password2.setBounds(50,250,200,30);
        password2.setFont(new Font("Arial",Font.BOLD,15));
        t3.setBounds(50,280,300,30);

        // ปุ่ม Submit
        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(210,350,140,40);
        b1.setBackground(Color.decode("#FF9999"));

        // ปุ่ม Cancel
        b2.setFont(new Font("Arial",Font.BOLD,15));
        b2.setBounds(50,350,140,40);
        b2.setBackground(Color.decode("#FF9999"));

        // เพิ่ม Event
        b1.addActionListener(this); //อย่าลืม
        b2.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(register); 
        cp.add(username); cp.add(t1);
        cp.add(password1); cp.add(t2);
        cp.add(password2); cp.add(t3);
        cp.add(b1); cp.add(b2);
    }
    private void Finally() {
        this.setTitle("Concert KU Ticket"); // = ชื่อ
        this.setSize(400,450); // = ขนาด
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // = ปิดโปรแกรม
        this.setVisible(true); // = แสดงผล
        this.setLocationRelativeTo(null);  // = แสดงผลตรงกลาง
        this.setResizable(false); // = ขยายขนาดไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) { // ตรวจสอบว่าปุ่มไหนถูกกด
        if (e.getSource() == b1) { // ถ้ากดปุ่ม Submit
            String tmp = t1.getText();
            String pass1 = new String(t2.getPassword()); // แปลง JPasswordField เป็น String
            String pass2 = new String(t3.getPassword()); // แปลง JPasswordField เป็น String
            for (int i = 0 ; i < tmp.length(); i++){
                if (tmp.charAt(i) == '@') { // ตรวจสอบว่ามี @ ในอีเมลหรือไม่
                    WriteFile(t1.getText() , new String(t2.getPassword())); // เขียนไฟล์
                    this.dispose(); // Close the register window
                    new Login(); // Open the login window
                    Popup("Complete!!!");
                    return;
                }
                else if (i == tmp.length() - 1) { // ถ้าไม่มี @ เลย
                    Popup("Invalid E-mail format.");
                    return;
                }
                else if (pass1.length() < 8 || pass1.length() > 16) { // ตรวจสอบความยาวรหัสผ่าน
                    Popup("Password must be 8 - 16 characters.");
                    return;
                }
                else if (!pass1.equals(pass2)) { // ตรวจสอบว่ารหัสผ่านทั้งสองช่องตรงกันหรือไม่
                    Popup("Passwords do not match.");
                    return;
                }
            }
        }
        else if (e.getSource() == b2) { // ถ้ากดปุ่ม Cancel
            this.dispose(); // Close the register window
            new Login(); // Open the login window
        }
    }
    private void WriteFile(String text1, String text2) { // เขียนไฟล์
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File("./File/Register.csv");
            fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            bw.write(text1+","+text2+"\n");
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            try {
                bw.close(); fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void Popup(String s) { // แสดง Popup
        JDialog d = new JDialog();
        JLabel l = new JLabel(s);
        l.setFont(new Font("",Font.PLAIN,18));
        d.getContentPane();
        d.add(l);
        d.pack();
        d.setLocationRelativeTo(null);
        //d.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        d.setVisible(true);
    }
}
