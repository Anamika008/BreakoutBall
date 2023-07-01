/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakout_ball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

/**
 *
 * @author HP
 */
public class MapGenerator {
    
    
    public int map[][];
    public int width;
    public int height;
    
    public MapGenerator(int row,int col){
        
        map=new int[row][col];
        for(int[] arr:map){
            Arrays.fill(arr, 1);
        }
        
        width=1266/col;
        height=200/row;
    }
    
    public void draw(Graphics2D g){
        
        for(int i=0 ; i<map.length ; i++ ){
            for(int j=0 ; j<map[i].length ; j++ ){
                if(map[i][j]!=0){
                    g.setColor(Color.white);
                    g.fillRect(j*width+50,i*height+50,width,height);
                    
//                    giving outline to bricks 
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.black);
                    g.drawRect(j*width+50,i*height+50,width,height);                }
            }
        }
    }
    
    public void setBrickValue(int value,int row,int col){
        map[row][col]=value;
    }
    
}
