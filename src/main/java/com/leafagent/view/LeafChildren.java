package com.leafagent.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LeafChildren extends JPanel {
    public LeafChildren() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(0,60,0,0));
    }
}
