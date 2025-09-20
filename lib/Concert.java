package lib;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Concert extends JFrame implements ActionListener {
    Container cp ;
    JLabel concert ;
    JButton b1 , b2 , b3 , b4 , b5 , b6 ;
    public Concert() {
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
        ImageIcon userIcon = new ImageIcon("./img/ticket.png");

        // ปรับขนาด
        Image img = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(img);

        JLabel lblUser = new JLabel(userIcon);
        lblUser.setBounds(100, 120, 100, 100);
        cp.add(lblUser);
    }

    private void Finally() {
        this.setTitle("Concert KU Ticket");
        this.setSize(900,700);
        this.setLocationRelativeTo(null); // หน้าต่างแสดงตรงกลางจอ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ปิดโปรแกรมเมื่อกดปิด
        this.setResizable(false); // ปิดการปรับขนาดหน้าต่าง
        this.setVisible(true); // แสดงหน้าต่าง
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
