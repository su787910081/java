package com.tedu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ListenerDemo {
    /*
     * 需求：创建一个窗口，在窗口中添加一个按钮。为按钮绑定点击事件，点击按钮在控制台打印"hello Listener"
     */
    public static void main(String[] args) {
        // 1. 创建一个窗口对象
        JFrame frame = new JFrame();
        
        // 2. 设置窗口的大小和位置
        frame.setSize(350, 250);
        frame.setLocation(400, 300);
        
        // 3. 创建一个按钮
        JButton btn = new JButton("按钮");
        
        // 4. 将按钮添加到窗口中
        frame.add(btn);
        
        /*
         * 为按钮添加功能，点击按钮在控制台打印"hello Listener"
         * 事件源: 按钮
         * 事件: 按钮被点击
         * 监听器: listener
         * 当监听器注册到按钮上之后，监听器会一直监听着按钮，只要按钮被点击事件发生，就会通知监听器。接着执行监听器中的方法来处理事件。
         */
        // 创建一个监听器ActionListener(行为监听器)
        ActionListener listener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当按钮被点击，该方法被执行
                System.out.println("hello Listener");
            }
        };
        
        // 将监听器注册到按钮上(注册后，监听器将会一直监听着这个按钮)
        btn.addActionListener(listener);
        
        // 5. 设置窗口可见
        frame.setVisible(true);
    }
}
