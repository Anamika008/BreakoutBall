/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakout_ball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author HP
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private int totalBricks=0;
    private MapGenerator map;
    private int playerX=getWidth()/2 + 580 ;
    private boolean play=false;
    private int score=0;
    
    // initial ball
    private int ballposX = getWidth()/2-10;
    private int ballposY = getHeight()-100;
    private int x = -1;
    private int y = -2;
    
    // Timer
    private Timer time;
    private final int delay=5;
    
    public GamePlay(){
        map=new MapGenerator(4,6);
        totalBricks=4*6;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        
        time.start(); // start the timer...
    }
    
    @Override
    public void paint(Graphics g){
        
//        draw the background, set color 
        g.setColor(Color.black);
        
//        getWidth() - return width of panel
//        getHeight() - return height of panel
        g.fillRect(0, 0, getWidth(), getHeight());
        
//        paddle

        g.setColor(Color.white);
        g.fillRoundRect(playerX, getHeight()-25, 200, 10, 2, 2);

//       draw ball     
        
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY , 20, 20);

//        Border

        int borderWidth=3;
        g.setColor(Color.cyan);
        g.fillRect(5,5,getWidth()-10,borderWidth); // top
        g.fillRect(5, 5, borderWidth, getHeight()-10); // left
        g.fillRect(getWidth()-8, 5, borderWidth, getHeight()-10); //right
//        g.fillRect(5, getHeight()-8, getWidth()-10, borderWidth);
        
//        draw bricks
        map.draw((Graphics2D) g);
        
//        SCORE
        g.setColor(Color.BLUE);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Score : "+score, getWidth()-150, 40);
        
        if(totalBricks==0){
            
            play=false;
            ballposX = getWidth()/2-10;
            ballposY = getHeight()-45;
            playerX=getWidth()/2 -100;
            g.setColor(Color.GREEN);
            g.setFont(new Font("dialogInput",Font.BOLD,100));
            g.drawString("You Won", getWidth()/2 - 200, getHeight()/2-10 );
            
            g.setColor(Color.lightGray);
            g.setFont(new Font("serif",Font.BOLD, 50));           
            g.drawString("Press (Enter) to Restart", getWidth()/2-240,getHeight()/2+85);
        }
        
        
        if(ballposY>getHeight()-10) {
            play=false;
            
            g.setColor(Color.red);
            g.setFont(new Font("dialogInput",Font.BOLD,100));
            g.drawString("You Lose!!!", getWidth()/2 - 300, getHeight()/2-10 );
            
            g.setColor(Color.lightGray);
            g.setFont(new Font("serif",Font.BOLD, 50));           
            g.drawString("Press (Enter) to Restart", getWidth()/2-240,getHeight()/2+85);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
//        System.out.println("keyPressed() is invoked..");
        
        if(keyCode==KeyEvent.VK_LEFT){
            if(playerX>=20){
                playerX -= 10;
//                System.out.println("Left key is pressed : "+playerX);
            }
                
        }
        
        if(keyCode==KeyEvent.VK_RIGHT){
            if(playerX<=getWidth()-220){
                playerX += 10;
//                System.out.println("Right key is pressed : "+playerX);
            }    
        }
        
        if(keyCode==KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                score=0;
                playerX=getWidth()/2 -100;
                ballposX = getWidth()/2-10;
                ballposY = getHeight()-45;
                x = -1;
                y = -2;// direction for movind the ball
                map=new MapGenerator(4,6);
                totalBricks=4*6;
                
                repaint();
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        time.start();
        if(play){
            ballposX += x;
            ballposY += y;
            
            if(ballposX<=10){
                x = -x;
            }
            if(ballposY<=10){
                y = -y;
            }
            if(ballposX>=getWidth()-30){
                x = -x;
            }
            
            // check the collision between paddle and ball
            if(ballposY>=getHeight()-45 && (playerX<ballposX && ballposX<=playerX+200)){
                y = -y;
            }
            
            // find the collision of ball with bricks.
            STOP: for(int i=0 ; i<map.map.length ; i++ ){
                for(int j=0 ; j<map.map[i].length ; j++ ){
                    if(map.map[i][j]==1){
                        int brickX=j*map.width+50;
                        int brickY=i*map.height+50;
                        int brickWidth = map.width;
                        int brickHeight = map.height;
                        
                        Rectangle brickrect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballrect= new Rectangle(ballposX,ballposY,20,20);
                        
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0, i, j);
                            score += 5;
                            totalBricks--;
                            
                            if(ballposX<=brickX || ballposX>=brickX + brickWidth){
                                x = -x;
                            }
                            else if(ballposY<=brickY || ballposY>=brickY + brickHeight){
                                y = -y;
                            }
                            
                            break STOP;
                        }
                    }
                }
            }
            
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("KeyTyped() is invoked ... ");
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("KeyReleased() is invoked ... ");
    }
}
