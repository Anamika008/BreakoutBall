/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package breakout_ball;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author HP
 */
public class Breakout_ball {

    public static void main(String[] args) {
        
        JFrame frame=new JFrame();
        GamePlay gameplay=new GamePlay();
        
//        frame.setBounds(10, 10,700 , 500);
//        set frame in full screen default
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("--Break the Bricks--");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameplay);
        frame.setVisible(true);
    }
}
