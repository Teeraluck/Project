package lib;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class Login extends JFrame implements KeyListener,ActionListener{
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

        lRegister = new JLabel("Register here");
        lRegister.setFont(new Font("Arial", Font.PLAIN, 14));
        lRegister.setForeground(Color.BLUE);
        lRegister.setBounds(260,240,120,30);
        lRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Register Label Clicked!");
                Register(); // เรียกฟังก์ชัน Register()
            }
        });
        // เพิ่ม Event
        t1.addKeyListener(this);//อย่าลืม
        t2.addKeyListener(this);//อย่าลืม

        b1.addActionListener(this); //อย่าลืม

        // เพิ่ม Component ลงใน Container
        cp.add(login); 
        cp.add(username); cp.add(t1);
        cp.add(password); cp.add(t2);
        cp.add(b1); cp.add(lRegister);
    }

    private void Finally() {
        this.setTitle("Login"); // = ชื่อ
        this.setSize(400,400); // = ขนาด
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
        if (e.getSource().equals(b1)) {
            ReadFile(t1.getText() , t2.getText());
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String fileUsername = parts[0];
                    String filePassword = parts[1];
                    if (fileUsername.equals(text) && filePassword.equals(text2)) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                this.dispose(); // Close the login window
                // Open the next window or perform other actions here
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void Register() {
        new Register(); // เปิดหน้าต่าง Register
        this.dispose(); // ปิดหน้าต่าง Login
    }
}
