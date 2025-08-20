/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.adavanced_programming.matchingpairsgame;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author nicola
 */
public class Card extends JButton {

    public static enum State {
        FACE_UP,
        FACE_DOWN,
        EXCLUDED
    }

    private int value;
    private State state;
    
    public Card(){
        super();
    }
    
    public Card(int value, State state){
        super();
        this.value=value;
        this.state=state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        this.setColour();
    }
    
    private void setColour(){
        switch(state) {
            case FACE_UP:
                this.setText(Integer.toString(value));
                this.setBackground(Color.gray);
                break;
            case FACE_DOWN:
                this.setBackground(Color.green);
                break;
            case EXCLUDED:
                this.setBackground(Color.red);
                break;
            default:
                break;
        }
    }
    
}
