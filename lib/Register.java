package lib;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame implements ActionListener, KeyListener {
    Container cp ;
    JLabel register , username , password1 , password2;
    JTextField t1;
    JPasswordField t2 , t3;
    JButton b1 ;
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
        password2 = new JLabel("Re-enter Password");
        t3 = new JPasswordField();
        b1 = new JButton("Submit");

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
        b1.setBounds(130,350,140,40);
        b1.setBackground(Color.decode("#FF9999"));

        // เพิ่ม Event
        t1.addKeyListener(this);//อย่าลืม
        t2.addKeyListener(this);//อย่าลืม
        b1.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(register); 
        cp.add(username); cp.add(t1);
        cp.add(password1); cp.add(t2);
        cp.add(password2); cp.add(t3);
        cp.add(b1);
    }
    private void Finally() {
        this.setTitle("Register"); // = ชื่อ
        this.setSize(400,450); // = ขนาด
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // = ปิดโปรแกรม
        this.setVisible(true); // = แสดงผล
        this.setLocationRelativeTo(null);  // = แสดงผลตรงกลาง
        this.setResizable(false); // = ขยายขนาดไม่ได้
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b1)){
            String user = t1.getText();
            String pass1 = new String(t2.getPassword());
            String pass2 = new String(t3.getPassword());
            if(user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            } else if(!pass1.equals(pass2)){
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
            } else if(pass1.length() < 8 || pass1.length() > 16){
                JOptionPane.showMessageDialog(this, "Password must be between 8 and 16 characters.");
            } else {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                WriteFile(t1.getText() , t2.getText());
                this.dispose(); // Close the register window
                new Login(); // Open the login window
            }
        }
    }
    private void WriteFile(String text, String text2) {
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File("./File/Register.csv");
            fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);
            bw.write(text+","+text2+"\n");
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

}
