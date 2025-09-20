package lib;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class Login extends JFrame implements ActionListener{
    Container cp ;
    JLabel login , username , password , lRegister;
    JTextField t1;
    JPasswordField t2;
    JButton b1 ;
    public Login(){
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
        login = new JLabel("Login");
        username = new JLabel("Username (E-mail)");
        t1 = new JTextField();
        password = new JLabel("Password");
        t2 = new JPasswordField();
        b1 = new JButton("Submit");

        // กำหนดขนาดและตำแหน่ง
        login.setBounds(160,20,100,40);
        login.setFont(new Font("Arial",Font.BOLD,30));

        username.setBounds(50,100,200,30);
        username.setFont(new Font("Arial",Font.BOLD,15));
        t1.setBounds(50,130,300,30);

        password.setBounds(50,170,100,30);
        password.setFont(new Font("Arial",Font.BOLD,15));
        t2.setBounds(50,200,300,30);

        b1.setFont(new Font("Arial",Font.BOLD,15));
        b1.setBounds(130,290,140,40);
        b1.setBackground(Color.decode("#FF9999"));

        lRegister = new JLabel("<html><u>Register here</u></html>"); // ใช้ HTML เพื่อขีดเส้นใต้ข้อความ
        lRegister.setFont(new Font("Arial", Font.PLAIN, 14));
        lRegister.setForeground(Color.BLUE); // เปลี่ยนสีข้อความ
        lRegister.setBounds(260,240,120,30);
        lRegister.setCursor(new Cursor(Cursor.HAND_CURSOR)); // เปลี่ยนเคอร์เซอร์เมื่อชี้ไปที่ป้าย
        lRegister.addMouseListener(new MouseAdapter() { // เพิ่ม MouseListener
            @Override
            public void mouseClicked(MouseEvent e) { // เมื่อคลิกที่ป้าย Register
                Register(); // เรียกฟังก์ชัน Register()
            }
        });
        // เพิ่ม Event
        b1.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(login); 
        cp.add(username); cp.add(t1);
        cp.add(password); cp.add(t2);
        cp.add(b1); cp.add(lRegister);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket"); // = ชื่อ
        this.setSize(400,400); // = ขนาด
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // = ปิดโปรแกรม
        this.setVisible(true); // = แสดงผล
        this.setLocationRelativeTo(null);  // = แสดงผลตรงกลาง
        this.setResizable(false); // = ขยายขนาดไม่ได้
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)) { // ตรวจสอบว่ากดปุ่ม Submit หรือไม่
            ReadFile(t1.getText() , new String(t2.getPassword())); // อ่านไฟล์เพื่อตรวจสอบข้อมูล
        }
    }

    private void ReadFile(String text, String text2) {
        File f = null;
        java.io.FileReader fr = null;
        java.io.BufferedReader br = null;
        boolean found = false;
        try {
            f = new File("./File/Register.csv");
            fr = new java.io.FileReader(f);
            br = new java.io.BufferedReader(fr);
            String line; // อ่านทีละบรรทัด
            while ((line = br.readLine()) != null) { // อ่านจนกว่าจะหมดไฟล์
                String[] parts = line.split(","); // แยกข้อมูลด้วยเครื่องหมาย "," และเก็บในอาเรย์
                if (parts.length >= 2) { // ตรวจสอบว่ามีข้อมูลครบถ้วนหรือไม่โดยมีอย่างน้อย 2 ส่วน
                    String fileUsername = parts[0]; // ส่วนที่ 0 คือ username
                    String filePassword = parts[1]; // ส่วนที่ 1 คือ password
                    if (fileUsername.equals(text) && filePassword.equals(text2)) { // ตรวจสอบข้อมูลว่าตรงกันหรือไม่
                        found = true; // ถ้าตรงกันให้ตั้งค่า found เป็น true
                        break; // ออกจากลูป
                    }
                }
            }
            if (found) { // ถ้าพบข้อมูลที่ตรงกัน
                new Concert(); // เปิดหน้าต่าง Concert
                this.dispose(); // ปิดหน้าต่าง Login
            } else {
                Popup("Invalid username or password."); // แสดงข้อความผิดพลาด
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null) br.close(); // ถ้าไม่ใช่ null ให้ปิด BufferedReader
                if (fr != null) fr.close(); // ถ้าไม่ใช่ null ให้ปิด FileReader
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private void Popup(String s) { // แสดง Popup
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
    public void Register() {
        new Register(); // เปิดหน้าต่าง Register
        this.dispose(); // ปิดหน้าต่าง Login
    }
}
