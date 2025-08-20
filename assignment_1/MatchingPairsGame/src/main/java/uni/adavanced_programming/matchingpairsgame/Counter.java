/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.matchingpairsgame;

import javax.swing.JLabel;

/**
 *
 * @author nicola
 */
public class Counter extends JLabel{
    
    private int count=0;
    
    public Counter(){
        super();
        this.setText("Total flip: "+Integer.toString(count));
    }
    
    public Counter(int count){
        super();
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.setText("Total flip: "+Integer.toString(count));
    }
}
