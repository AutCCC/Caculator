package com.zfuture.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zfuture.calculator.util.Const.*;

public class Calculator extends JFrame implements ActionListener {

    private JPanel jp_north = new JPanel();
    private JTextField input_text =new JTextField();
    private JButton c_Btn = new JButton("c");

    private JPanel jp_center = new JPanel();

    public Calculator() throws HeadlessException {
        this.init();
        this.addNorthComponent();
        this.addCenterButton();
    }

    public void init(){
        this.setTitle(TITLE);
        this.setSize(FRAME_W,FRAME_H);
        this.setLocation(FRAME_X,FRAME_Y);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void addNorthComponent(){
        input_text.setPreferredSize(new Dimension(230,50));
        jp_north.add(input_text);

        c_Btn.setPreferredSize(new Dimension(45,48));
        c_Btn.setForeground(Color.red);
        jp_north.add(c_Btn);

        c_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });

        this.add(jp_north,BorderLayout.NORTH);
    }

    public void addCenterButton(){
        String btn_text = "789+456-123*.0=/";
//        char[] ch = "789+456-123*.0=/".toCharArray();
        this.jp_center.setLayout(new GridLayout(4,4));
        for(int i = 0;i<16;i++){
            String temp = btn_text.substring(i,i+1);
            JButton btn = new JButton();
            btn.setText(temp);
            jp_center.add(btn);
            btn.addActionListener(this);
        }
        this.add(jp_center,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }

    private String firstInput = null;
    private String operator = null;
    @Override
    public void actionPerformed(ActionEvent e) {
        String clickStr = e.getActionCommand();
        if (".0123456789".indexOf(clickStr) != -1){
            this.input_text.setText(input_text.getText()+clickStr);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
            //JOptionPane.showMessageDialog(this,clickStr);
        }else if(clickStr.matches("[\\+\\-*/]{1}")){
            operator = clickStr;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        }else if(clickStr.equals("=")){
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double r = null;
            switch (operator){
                case "+":
                    r = a + b;
                    break;
                case "-":
                    r = a - b;
                    break;
                case "*":
                    r = a * b;
                    break;
                case "/":
                    if (b==0){
                        r = null;
                        break;
                    }else {
                        r = a / b;
                        break;
                    }

            }

            if (r == null){
                this.input_text.setText("ERROR:/0");
            }else {
                this.input_text.setText(r.toString());
            }
        }

    }
}
