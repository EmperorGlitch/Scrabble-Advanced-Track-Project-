/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial1;

import Trial1.Trie.wNode;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Sagnik Dey
 */
public class Scrabble extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Scrabble
     */
    String LetterSet[]=new String[98];
    int player=1;
    DefaultListModel model1=new DefaultListModel();
    int numLetters=98;
    String P1Rack[]=new String[7];
    String P2Rack[]=new String[7];
    int P1score=0;
    int P2score=0;
    int placePos[]=new int[2];
    String helpRack="";
    String helpBoard="";
    boolean firstmove=true;
    boolean replacemode=false;
    Trie Tester=new Trie();
    ImageIcon Tileimg[]=new ImageIcon[27];
    String selected="";
    JButton Tile[][]=new JButton[15][15];
    JButton RTile[]=new JButton[7];
    public Scrabble() throws IOException {
        for(int i=0;i<26;++i)
        {
            if(i<8)
            {Tileimg[i]=new ImageIcon(getClass().getResource("/Tiles/scrabble-banner-0"+(i+2)+".png"));}
            else
            {Tileimg[i]=new ImageIcon(getClass().getResource("/Tiles/scrabble-banner-"+(i+2)+".png"));}   
        }
        Tileimg[26]=new ImageIcon(getClass().getResource("/Tiles/scrabble-banner-28.png"));
        this.setUndecorated(true);
        this.setVisible(true);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
        int xsize=(int)tk.getScreenSize().getWidth();
        int ysize=(int)tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        buttonGroup2.add(jRadioButton5);
        buttonGroup2.add(jRadioButton6);
        buttonGroup2.add(jRadioButton7);
        buttonGroup2.add(jRadioButton8);
        int index=0;
        placePos[0]=-1;
        for(int i=0;i<9;++index,++i)
            LetterSet[index]="A";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="B";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="C";
        for(int i=0;i<4;++index,++i)
            LetterSet[index]="D";
        for(int i=0;i<12;++index,++i)
            LetterSet[index]="E";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="F";
        for(int i=0;i<3;++index,++i)
            LetterSet[index]="G";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="H";
        for(int i=0;i<9;++index,++i)
            LetterSet[index]="I";
        for(int i=0;i<1;++index,++i)
            LetterSet[index]="J";
        for(int i=0;i<1;++index,++i)
            LetterSet[index]="K";
        for(int i=0;i<4;++index,++i)
            LetterSet[index]="L";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="M";
        for(int i=0;i<6;++index,++i)
            LetterSet[index]="N";
        for(int i=0;i<8;++index,++i)
            LetterSet[index]="O";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="P";
        for(int i=0;i<1;++index,++i)
            LetterSet[index]="Q";
        for(int i=0;i<6;++index,++i)
            LetterSet[index]="R";
        for(int i=0;i<4;++index,++i)
            LetterSet[index]="S";
        for(int i=0;i<6;++index,++i)
            LetterSet[index]="T";
        for(int i=0;i<4;++index,++i)
            LetterSet[index]="U";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="V";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="W";
        for(int i=0;i<1;++index,++i)
            LetterSet[index]="X";
        for(int i=0;i<2;++index,++i)
            LetterSet[index]="Y";
        for(int i=0;i<1;++index,++i)
            LetterSet[index]="Z";
        Board.setBackground(new Color(168,116,38));
        getContentPane().setBackground(new Color(66,140,64));
        
        Tester.insert();
        Board.setLayout(new GridLayout(15,15));
        Rack.setLayout(new GridLayout(1,7));
        for(int i=0;i<15;++i)
        {
            for(int j=0;j<15;++j)
            {
                Tile[i][j]=new JButton();
                Tile[i][j].setVisible(true);
                Tile[i][j].addActionListener(this);
                Tile[i][j].setBackground(new Color(209,149,58));
                Tile[i][j].setFont(new Font("Arial", Font.PLAIN, 10));
                Tile[i][j].setText("");
                Board.add(Tile[i][j]);
            }
        }
        Tile[7][7].setBackground(Color.WHITE);
        Tile[7][7].setActionCommand("dw");
        for(int i=0;i<7;++i)
        {
            RTile[i]=new JButton();
            RTile[i].setVisible(true);
            RTile[i].setSize(91, 91);
            RTile[i].addActionListener(this);
            RTile[i].setFont(new Font("Arial", Font.PLAIN, 35));
            RTile[i].setText("");
            Rack.add(RTile[i]);
        }
        Tile[0][0].setBackground(Color.red);
        Tile[0][0].setActionCommand("tw");
        Tile[0][7].setBackground(Color.red);
        Tile[0][7].setActionCommand("tw");
        Tile[0][14].setBackground(Color.red);
        Tile[0][14].setActionCommand("tw");
        Tile[7][0].setBackground(Color.red);
        Tile[7][0].setActionCommand("tw");
        Tile[14][0].setBackground(Color.red);
        Tile[14][0].setActionCommand("tw");
        Tile[7][14].setBackground(Color.red);
        Tile[7][14].setActionCommand("tw");
        Tile[14][7].setBackground(Color.red);
        Tile[14][7].setActionCommand("tw");
        Tile[14][14].setBackground(Color.red);
        Tile[14][14].setActionCommand("tw");
        for(int i=1;i<5;++i)
        {
            Tile[i][i].setBackground(Color.PINK);
            Tile[i][i].setActionCommand("dw");
            Tile[14-i][i].setBackground(Color.PINK);
            Tile[14-i][i].setActionCommand("dw");
            Tile[i][14-i].setBackground(Color.PINK);
            Tile[i][14-i].setActionCommand("dw");
            Tile[14-i][14-i].setBackground(Color.PINK);
            Tile[14-i][14-i].setActionCommand("dw");
        }
        Tile[1][5].setBackground(Color.BLUE);
        Tile[1][5].setActionCommand("tl");
        Tile[13][5].setBackground(Color.BLUE);
        Tile[13][5].setActionCommand("tl");
        Tile[1][9].setBackground(Color.BLUE);
        Tile[1][9].setActionCommand("tl");
        Tile[13][9].setBackground(Color.BLUE);
        Tile[13][9].setActionCommand("tl");
        for(int i=1;i<15;i=i+4)
        {
            Tile[5][i].setBackground(Color.BLUE);
            Tile[5][i].setActionCommand("tl");
            Tile[9][i].setBackground(Color.BLUE);
            Tile[9][i].setActionCommand("tl");
        }
        Tile[0][3].setBackground(Color.CYAN);
        Tile[0][3].setActionCommand("dl");
        Tile[14][3].setBackground(Color.CYAN);
        Tile[14][3].setActionCommand("dl");
        Tile[0][11].setBackground(Color.CYAN);
        Tile[0][11].setActionCommand("dl");
        Tile[14][11].setBackground(Color.CYAN);
        Tile[14][11].setActionCommand("dl");
        Tile[3][0].setBackground(Color.CYAN);
        Tile[3][0].setActionCommand("dl");
        Tile[11][14].setBackground(Color.CYAN);
        Tile[11][14].setActionCommand("dl");
        Tile[3][14].setBackground(Color.CYAN);
        Tile[3][14].setActionCommand("dl");
        Tile[11][0].setBackground(Color.CYAN);
        Tile[11][0].setActionCommand("dl");
        Tile[2][6].setBackground(Color.CYAN);
        Tile[2][6].setActionCommand("dl");
        Tile[12][6].setBackground(Color.CYAN);
        Tile[12][6].setActionCommand("dl");
        Tile[12][8].setBackground(Color.CYAN);
        Tile[12][8].setActionCommand("dl");
        Tile[2][8].setBackground(Color.CYAN);
        Tile[2][8].setActionCommand("dl");
        Tile[6][2].setBackground(Color.CYAN);
        Tile[6][2].setActionCommand("dl");
        Tile[8][2].setBackground(Color.CYAN);
        Tile[8][2].setActionCommand("dl");
        Tile[8][12].setBackground(Color.CYAN);
        Tile[8][12].setActionCommand("dl");
        Tile[6][12].setBackground(Color.CYAN);
        Tile[6][12].setActionCommand("dl");
        Tile[7][3].setBackground(Color.CYAN);
        Tile[7][3].setActionCommand("dl");
        Tile[3][7].setBackground(Color.CYAN);
        Tile[3][7].setActionCommand("dl");
        Tile[11][7].setBackground(Color.CYAN);
        Tile[11][7].setActionCommand("dl");
        Tile[7][11].setBackground(Color.CYAN);
        Tile[7][11].setActionCommand("dl");
        Tile[6][6].setBackground(Color.CYAN);
        Tile[6][6].setActionCommand("dl");
        Tile[8][6].setBackground(Color.CYAN);
        Tile[8][6].setActionCommand("dl");
        Tile[8][8].setBackground(Color.CYAN);
        Tile[8][8].setActionCommand("dl");
        Tile[6][8].setBackground(Color.CYAN);
        Tile[6][8].setActionCommand("dl");
        for(int i=0;i<7;++i){
        Random rand=new Random();
        int n=rand.nextInt(numLetters);
        if(n<0)
            n=-1*n;
        P1Rack[i]=LetterSet[n];
        for(int j=n;j<numLetters-1;++j)
        {
            LetterSet[j]=LetterSet[j+1];
        }
        numLetters--;
        if(numLetters==0)
        {
            if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
            for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
            System.exit(0);
        }
        }
        for(int i=0;i<7;++i){
        Random rand=new Random();
        int n=rand.nextInt(numLetters);
        if(n<0)
            n=-1*n;
        P2Rack[i]=LetterSet[n];
        for(int j=n;j<numLetters-1;++j)
        {
            LetterSet[j]=LetterSet[j+1];
        }
        numLetters--;
        if(numLetters==0)
        {
            if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
            for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
            
            System.exit(0);
        }
        }
        for(int i=0;i<7;++i)
            RTile[i].setText(P1Rack[i]);
        /*
        RTile[0].setText("A");
        RTile[1].setText("P");
        RTile[2].setText("P");
        RTile[3].setText("L");
        RTile[4].setText("E");
        RTile[5].setText("T");
        RTile[6].setText("A");
        */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Board = new javax.swing.JPanel();
        Rack = new javax.swing.JPanel();
        accept = new javax.swing.JButton();
        pass = new javax.swing.JButton();
        rep = new javax.swing.JButton();
        selectile = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        end = new javax.swing.JButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 51));
        setResizable(false);

        javax.swing.GroupLayout BoardLayout = new javax.swing.GroupLayout(Board);
        Board.setLayout(BoardLayout);
        BoardLayout.setHorizontalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BoardLayout.setVerticalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RackLayout = new javax.swing.GroupLayout(Rack);
        Rack.setLayout(RackLayout);
        RackLayout.setHorizontalGroup(
            RackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        RackLayout.setVerticalGroup(
            RackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        accept.setText("Accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        pass.setText("Pass");
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });

        rep.setText("Replace Tiles");
        rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repActionPerformed(evt);
            }
        });

        selectile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tiles/scrabble-banner-28.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tiles/scrabble_logo_small1.png"))); // NOI18N

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Score!", "Score!", "Score!", "Score!", "Score!" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));

        jRadioButton1.setText("Easy");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Medium");

        jRadioButton3.setText("::NiGhTmArE::");

        end.setText("End Game");
        end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endActionPerformed(evt);
            }
        });

        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Player 2");

        jRadioButton8.setSelected(true);
        jRadioButton8.setText("Player 1");

        jRadioButton5.setText("Easy");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jRadioButton6.setText("Medium");

        jRadioButton7.setText("::NiGhTmArE::");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Player 2");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Player 1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(Rack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(selectile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(98, 98, 98)
                .addComponent(Board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(141, 141, 141)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(rep)
                .addGap(175, 175, 175)
                .addComponent(end)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton3)
                                .addGap(23, 23, 23))
                            .addComponent(jRadioButton4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rep, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Rack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int xstart=0;
    int ystart=0;
    boolean alongRow;
    boolean checkAcceptable()
    {
        xstart=0;
        ystart=0;
        boolean flg=false;
        alongRow=true;
        for(int i=0;i<15;++i)
        {
            for(int j=0;j<15;++j)
            {
                if(!Tile[i][j].getActionCommand().equals("disabled")&&!Tile[i][j].getText().equals(""))
                {
                    xstart=j;
                    ystart=i;
                    flg=true;
                    for(int k=ystart+1;k<15;++k)
                    {
                        if(!Tile[k][xstart].getActionCommand().equals("disabled")&&!Tile[k][xstart].getText().equals(""))
                        {
                            alongRow=false;
                            break;
                        }
                    }
                    break;
                }
            }
            if(flg)
                break;
        }
        if(alongRow)
        {
            for(int i=0;i<15;++i)
                for(int j=0;j<15;++j)
                {
                    if(i!=ystart&&!Tile[i][j].getActionCommand().equals("disabled")&&!Tile[i][j].getText().equals(""))
                    {
                        jLabel4.setText("Wrong input format");
                        return false;
                    }
                }
        }
        else
        {
            for(int i=0;i<15;++i)
                for(int j=0;j<15;++j)
                {
                    if(j!=xstart&&!Tile[i][j].getActionCommand().equals("disabled")&&!Tile[i][j].getText().equals(""))
                    {
                        jLabel4.setText("Wrong input format");
                        return false;
                    }
                }
        }
        if(alongRow)
        {
            boolean broken=false;
            for(int x=xstart;x<15;++x)
            {
                if(Tile[ystart][x].getText().equals(""))
                {
                    broken=true;
                    continue;
                }
                if(broken&&!Tile[ystart][x].getActionCommand().equals("disabled")&&!Tile[ystart][x].getText().equals(""))
                {
                    jLabel4.setText("Wrong input format");
                    return false;
                }
            }
            
        }
        else
        {
            boolean broken=false;
            for(int y=ystart;y<15;++y)
            {
                if(Tile[y][xstart].getText().equals(""))
                {
                    broken=true;
                    continue;
                }
                if(broken&&!Tile[y][xstart].getActionCommand().equals("disabled")&&!Tile[y][xstart].getText().equals(""))
                {
                    jLabel4.setText("Wrong input format");
                    return false;
                }
            }
            
        }
        if(firstmove)
        {
            if(Tile[7][7].getText().equals(""))
            {
                jLabel4.setText("Wrong input format");
                return false;
            }
            else
                return true;
        }
        if(alongRow)
        {
            for(int x=xstart;x<15&&!Tile[ystart][x].getText().equals("");++x)
            {
                if(x<14&&Tile[ystart][x+1].getActionCommand().equals("disabled"))
                    return true;
                if(x>0&&Tile[ystart][x-1].getActionCommand().equals("disabled"))
                    return true;
                if(ystart<14&&Tile[ystart+1][x].getActionCommand().equals("disabled"))
                    return true;
                if(ystart>0&&Tile[ystart-1][x].getActionCommand().equals("disabled"))
                    return true;
            }
        }
        else
        {
            for(int y=ystart;y<15&&!Tile[y][xstart].getText().equals("");++y)
            {
                if(y<14&&Tile[y+1][xstart].getActionCommand().equals("disabled"))
                    return true;
                if(y>0&&Tile[y-1][xstart].getActionCommand().equals("disabled"))
                    return true;
                if(xstart<14&&Tile[y][xstart+1].getActionCommand().equals("disabled"))
                    return true;
                if(xstart>0&&Tile[y][xstart-1].getActionCommand().equals("disabled"))
                    return true;
            }
        }
        jLabel4.setText("Wrong input format");
        return false;
    };
    
    int score(String word)
    {
        if(word.length()==1)
            return 0;
        int score=0;
        for(int i=0;i<word.length();++i)
        {
            switch(word.charAt(i))
            {
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                case 'T':
                case 'N':
                case 'R':
                case 'S':
                case 'L':   score+=1;
                            break;
                case 'D':
                case 'G':   score+=2;
                            break;
                case 'B':
                case 'C':
                case 'M':
                case 'P':   score+=3;
                            break;
                case 'F':
                case 'H':
                case 'V':
                case 'W':
                case 'Y':   score+=4;
                            break;
                case 'K':   score+=5;
                            break;
                case 'J':
                case 'X':   score+=8;
                            break;
                case 'Q':
                case 'Z':   score+=10;
                            break;
            }
        }
        return score; 
    };
    int wordmakescore()
    {
        int wordscore=0;
            int wordmult=1;
            String word="";
            String nword="";
            //Make words
            if(alongRow)
            {
                while(xstart>=0&&!Tile[ystart][xstart].getText().equals(""))
                    xstart--;
                xstart++;
                for(int i=xstart;i<15&&!Tile[ystart][i].getText().equals("");++i)
                {
                    word=word+Tile[ystart][i].getText();
                    nword=nword+Tile[ystart][i].getText();
                    if(Tile[ystart][i].getActionCommand().equals("dl"))
                        nword=nword+Tile[ystart][i].getText();
                    if(Tile[ystart][i].getActionCommand().equals("tl"))
                        nword=nword+Tile[ystart][i].getText()+Tile[ystart][i].getText();
                    if(Tile[ystart][i].getActionCommand().equals("dw"))
                        wordmult*=2;
                    if(Tile[ystart][i].getActionCommand().equals("tw"))
                        wordmult*=3;
                }
                jLabel4.setText(nword);
                if(word.length()>1)
                wordscore+=score(nword)*wordmult;
                wordmult=1;
                if(firstmove&&word.length()==1)
                    return wordscore;
                if(Tester.search(word)||word.length()==1)
                    ;
                else
                {
                    jLabel4.setText("\""+word+"\" is wrong");
                    return -1;
                }
                for(int x=xstart;x<15&&!Tile[ystart][x].getText().equals("");++x)
                {
                    if(Tile[ystart][x].getActionCommand().equals("disabled"))
                        continue;
                    String bword="";
                    String nbword="";
                    int bystart;
                    for(bystart=ystart;bystart>=0&&!Tile[bystart][x].getText().equals("");--bystart);
                    bystart++;
                    for(int y=bystart;y<15&&!Tile[y][x].getText().equals("");++y)
                    {
                        bword=bword+Tile[y][x].getText();
                        nbword=nbword+Tile[y][x].getText();
                        if(Tile[y][x].getActionCommand().equals("dl"))
                         nbword=nbword+Tile[y][x].getText();
                        if(Tile[y][x].getActionCommand().equals("tl"))
                            nbword=nbword+Tile[y][x].getText()+Tile[ystart][x].getText();
                        if(Tile[y][x].getActionCommand().equals("dw"))
                            wordmult*=2;
                        if(Tile[y][x].getActionCommand().equals("tw"))
                            wordmult*=3;
                    }
                    jLabel4.setText(bword);
                    if(bword.length()>1)
                        wordscore+=score(nbword)*wordmult;
                    wordmult=1;
                    if(bword.length()==1||Tester.search(bword))
                       ;
                    else
                    {
                    jLabel4.setText("\""+bword+"\" is wrong");
                    return -1;
                    }
                    
                }
            }
            else
            {
                while(ystart>=0&&!Tile[ystart][xstart].getText().equals(""))
                    ystart--;
                ystart++;
                for(int i=ystart;i<15&&!Tile[i][xstart].getText().equals("");++i)
                {
                    word=word+Tile[i][xstart].getText();
                    nword=nword+Tile[i][xstart].getText();
                    if(Tile[i][xstart].getActionCommand().equals("dl"))
                        nword=nword+Tile[i][xstart].getText();
                    if(Tile[i][xstart].getActionCommand().equals("tl"))
                        nword=nword+Tile[i][xstart].getText()+Tile[i][xstart].getText();
                    if(Tile[i][xstart].getActionCommand().equals("dw"))
                        wordmult*=2;
                    if(Tile[i][xstart].getActionCommand().equals("tw"))
                        wordmult*=3;
                }
                jLabel4.setText(word);
                if(word.length()>1)
                    wordscore+=score(nword)*wordmult;
                wordmult=1;
                if(firstmove&&word.length()==1)
                    return -1;
                if(Tester.search(word))
                    ;
                else
                {
                    jLabel4.setText("\""+word+"\" is wrong");
                    return -1;
                }
                for(int y=ystart;y<15&&!Tile[y][xstart].getText().equals("");++y)
                {
                    if(Tile[y][xstart].getActionCommand().equals("disabled"))
                        continue;
                    String bword="";
                    String nbword="";
                    int bxstart;
                    for(bxstart=xstart;bxstart>=0&&!Tile[y][bxstart].getText().equals("");--bxstart);
                    bxstart++;
                    for(int x=bxstart;x<15&&!Tile[y][x].getText().equals("");++x)
                    {
                        bword=bword+Tile[y][x].getText();
                        nbword=nbword+Tile[y][x].getText();
                        if(Tile[y][x].getActionCommand().equals("dl"))
                            nbword=nbword+Tile[y][x].getText();
                        if(Tile[y][x].getActionCommand().equals("tl"))
                            nbword=nbword+Tile[y][x].getText()+Tile[ystart][x].getText();
                        if(Tile[y][x].getActionCommand().equals("dw"))
                            wordmult*=2;
                        if(Tile[y][x].getActionCommand().equals("tw"))
                            wordmult*=3;
                    }
                    jLabel4.setText(bword);
                    if(bword.length()>1)
                        wordscore+=score(nbword)*wordmult;
                    wordmult=1;
                    if(bword.length()==1||Tester.search(bword))
                        ;
                    else
                    {
                    jLabel4.setText("\""+bword+"\" is wrong");
                    return -1;
                    } 
                }
            }
            return wordscore;
    }
    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        // TODO add your handling code here:
        if(selected.equals("")){
        if(checkAcceptable())
        {
            int wordscore=wordmakescore();
            String word="";
            if(wordscore==-1)
                return;
            if(alongRow)
            {
                for(int i=xstart;i<15&&!Tile[ystart][i].getText().equals("");++i)
                {
                    
                    Tile[ystart][i].setActionCommand("disabled");
                    Tile[ystart][i].setBackground(new Color(168,116,38));
                    Tile[ystart][i].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            else
            {
                for(int i=ystart;i<15&&!Tile[i][xstart].getText().equals("");++i)
                {
                    
                    Tile[i][xstart].setActionCommand("disabled");
                    Tile[i][xstart].setBackground(new Color(168,116,38));
                    Tile[i][xstart].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            
            
            jLabel4.setText("All right");
            
            /*
            addScore();
            
            */
            for(int i=0;i<7;++i)
            {
                if(RTile[i].getText().equals(""))
                {
                    Random rand=new Random();
                    int n=rand.nextInt(numLetters);
                    if(n<0)
                        n=-1*n;
                    RTile[i].setText(LetterSet[n]);
                    for(int j=n;j<numLetters-1;++j)
                    {
                        LetterSet[j]=LetterSet[j+1];
                    }
                    numLetters--;
                    if(numLetters==0)
                    {
                        if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
                         for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        System.exit(0);
                    }
                }
            }
            if(player==1)
            {
                model1.addElement("Player1: "+wordscore);
                P1score+=wordscore;
                jLabel6.setText(""+P1score);
            }
            else
            {
                model1.addElement("Player2: "+wordscore);
                P2score+=wordscore;
                jLabel7.setText(""+P2score);
            }
            jList1.setModel(model1);
            if(firstmove)
                firstmove=false;
            if(player==1)
                    {
                        for(int i=0;i<7;++i)
                            P1Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P2Rack[i]);
                    }
                    else
                    {
                        for(int i=0;i<7;++i)
                            P2Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P1Rack[i]);
                    }
                    player=3-player;
                    for(int i=0;i<15;++i)
                            for(int j=0;j<15;++j)
                            {
                                if(!Tile[i][j].getText().equals(""))
                                    Tile[i][j].setActionCommand("disabled");
                            }
                    if((player==1&&!jRadioButton8.isSelected())||(player==2&&!jRadioButton4.isSelected()))
                    {
                        Thread thread=new Thread(new myRunnable());
                        thread.start();
                    }
                    }    
        
        }
    }//GEN-LAST:event_acceptActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
        if(player==1)
                    {
                        for(int i=0;i<7;++i)
                            P1Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P2Rack[i]);
                    }
                    else
                    {
                        for(int i=0;i<7;++i)
                            P2Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P1Rack[i]);
                    }
                    player=3-player;
                    if((player==1&&!jRadioButton8.isSelected())||(player==2&&!jRadioButton4.isSelected()))
                    {
                        Thread thread=new Thread(new myRunnable());
                        thread.start();
                    }
    }//GEN-LAST:event_passActionPerformed

    private void repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repActionPerformed
        // TODO add your handling code here:
        for(int i=0;i<7;++i)
            {
                if(RTile[i].getText().equals(""))
                {
                    return;
                }
            }
        if(replacemode){
        for(int i=0;i<7;++i)
        {
            if(RTile[i].getActionCommand().equals("replace"))
            {
                RTile[i].setActionCommand("");
                RTile[i].setBackground(Color.lightGray);
                String temp=RTile[i].getText();
                Random rand=new Random();
                int n=rand.nextInt(numLetters);
                if(n<0)
                    n=-1*n;
                RTile[i].setText(LetterSet[n]);
                for(int j=n;j<numLetters-1;++j)
                {
                    LetterSet[j]=LetterSet[j+1];
                }
                numLetters--;
                if(numLetters==0)
                {
                    if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
                    for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    System.exit(0);
                }
                LetterSet[numLetters]=temp;
                numLetters++;
            }
        }
        }
        if(replacemode)
        {
            
            replacemode=false;
            accept.setEnabled(true);
            pass.setEnabled(true);
            /*
            for(int i=0;i<15;++i)
            {
                for(int j=0;j<15;++j)
                {
                    if(!Tile[i][j].getActionCommand().equals("disable")&&!Tile[i][j].getText().equals(""))
                    {
                        for(int k=0;k<7;++k)
                        {
                            if(RTile[k].getText().equals(""))
                            {
                                RTile[k].setText(Tile[i][j].getText());
                                Tile[i][j].setText("");
                                break;
                            }
                        }
                    }
                }
            }
            if(!selected.equals(""))
            {
                for(int i=0;i<7;++i)
                {
                    if(RTile[i].getText().equals(""))
                    {
                        RTile[i].setText(selected);
                        selected="";
                        selectile.setIcon(Tileimg[26]);
                        break;
                    }
                }
            }*/
            if(player==1)
                    {
                        for(int i=0;i<7;++i)
                            P1Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P2Rack[i]);
                    }
                    else
                    {
                        for(int i=0;i<7;++i)
                            P2Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P1Rack[i]);
                    }
                    player=3-player;
                    if((player==1&&!jRadioButton8.isSelected())||(player==2&&!jRadioButton4.isSelected()))
                    {
                        Thread thread=new Thread(new myRunnable());
                        thread.start();
                    }
            
        }
        else
        {
            replacemode=true;
            accept.setEnabled(false);
            pass.setEnabled(false);
            jLabel4.setText("Select Tiles for Replacement");
        }
    }//GEN-LAST:event_repActionPerformed
    int fits(wNode crawl,int pos[],String letter)
    {
        if(pos[0]==-1)
            return 0;
        for(int i=0;i<crawl.word.length();++i)
        {
            if(crawl.word.charAt(i)==letter.charAt(0))
            {
                boolean verpossible=true;
                if(pos[0]-i<0)
                    verpossible=false;
                if(verpossible)
                {
                    
                    int y=pos[0]-i;
                    int x=pos[1];
                    
                    
                    for(int j=0;j<crawl.word.length();++j)
                    {
                        if(y+j>14)
                        {
                            verpossible=false;
                            break;
                        }
                        if(!Tile[y+j][x].getText().equals("")&&!Tile[y+j][x].getText().equals(Character.toString(crawl.word.charAt(j))))
                        {
                            verpossible=false;
                            break;
                        }
                    }
                    
                }
                if(verpossible)
                {
                    crawl.start[0]=pos[0]-i;
                    crawl.start[1]=pos[1];
                    return 2;
                }
                boolean horpossible=true;
                if(pos[1]-i<0)
                    horpossible=false;
                if(horpossible)
                {
                    int y=pos[0];
                    int x=pos[1]-i;
                    
                    for(int j=0;j<crawl.word.length();++j)
                    {
                        if(x+j>14)
                        {
                            horpossible=false;
                            break;
                        }
                        if(!(Tile[y][x+j].getText().equals("")||Tile[y][x+j].getText().equals(Character.toString(crawl.word.charAt(j)))))
                        {
                            horpossible=false;
                            break;
                        }
                    }
                    
                }
                
                if(horpossible)
                {
                    crawl.start[0]=pos[0];
                    crawl.start[1]=pos[1]-i;
                    return 1;
                }
            }
        }
        return 0;
    }
    void rmRack(String letter)
    {
        for(int i=0;i<7;++i)
        {
            if(RTile[i].getText().equals(letter))
            {
                RTile[i].setText("");
                return;
            }
        }
    }
    void put(wNode crawl,boolean endput)
    {
        if(crawl==null)
            return;
        int x=crawl.start[1];
        int y=crawl.start[0];
        if(crawl.horizontal)
        {
            for(int i=0;i<crawl.word.length();++i)
            {
                Tile[y][x+i].setText(Character.toString(crawl.word.charAt(i)));
                if(!Tile[y][x+i].getActionCommand().equals("disabled")&&endput)
                    rmRack(Tile[y][x+i].getText());
            }
        }
        else
        {
            for(int i=0;i<crawl.word.length();++i)
            {
                Tile[y+i][x].setText(Character.toString(crawl.word.charAt(i)));
                if(!Tile[y+i][x].getActionCommand().equals("disabled")&&endput)
                    rmRack(Tile[y+i][x].getText());
            }
        }
    }
    void clear()
    {
        for(int i=0;i<15;++i)
            for(int j=0;j<15;++j)
            {
                if(!Tile[i][j].getActionCommand().equals("disabled")&&!Tile[i][j].getText().equals(""))
                {
                    Tile[i][j].setText("");
                }
            }
    }
    void putCheck(wNode crawl)
    {
        while(crawl!=null)
        {
            if(!crawl.possible)
            {
                crawl=crawl.next;
                continue;
            }
            int newscore;
            put(crawl,false);
            checkAcceptable();
            newscore=wordmakescore();
            if(newscore==-1)
                 crawl.possible=false;
            else
                crawl.wordScore=newscore;
            clear();
            crawl=crawl.next;
        }
    }
    void compPlayer()
    {                                     
        // TODO add your handling code here:
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fit;
        int maxscore=0;
        wNode maxnode=null;
        if(firstmove)
        {
            firstmove=false;
            placePos[0]=7;
            placePos[1]=7;
            helpRack="";
            helpBoard="";
            for(int j=0;j<7;++j)
                {
                    helpRack+=RTile[j].getText();
                }
            Tester.disp(helpRack,helpBoard);
            wNode crawl1=Tester.head.next;
            while(crawl1!=null)
            {
                crawl1.start[0]=7;
                crawl1.start[1]=7;
                crawl1=crawl1.next;
            }
            putCheck(Tester.head.next);
            crawl1=Tester.head.next;
                while(crawl1!=null)
                {
                    if(!crawl1.possible)
                    {
                        crawl1=crawl1.next;
                        continue;
                    }
                    if(crawl1.wordScore>maxscore)
                    {
                        
                        maxscore=crawl1.wordScore;
                        maxnode=crawl1;
                    }
                    crawl1=crawl1.next;
                }
                Tester.delete();
                put(maxnode,true);
                int wordscore=wordmakescore();
            String word="";
            if(wordscore==-1)
                return;
            if(alongRow)
            {
                for(int i=xstart;i<15&&!Tile[ystart][i].getText().equals("");++i)
                {
                    
                    Tile[ystart][i].setActionCommand("disabled");
                    Tile[ystart][i].setBackground(new Color(168,116,38));
                    Tile[ystart][i].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            else
            {
                for(int i=ystart;i<15&&!Tile[i][xstart].getText().equals("");++i)
                {
                    
                    Tile[i][xstart].setActionCommand("disabled");
                    Tile[i][xstart].setBackground(new Color(168,116,38));
                    Tile[i][xstart].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            jLabel4.setText("All right");
            for(int i=0;i<7;++i)
            {
                if(RTile[i].getText().equals(""))
                {
                    Random rand=new Random();
                    int n=rand.nextInt(numLetters);
                    if(n<0)
                        n=-1*n;
                    RTile[i].setText(LetterSet[n]);
                    for(int j=n;j<numLetters-1;++j)
                    {
                        LetterSet[j]=LetterSet[j+1];
                    }
                    numLetters--;
                    if(numLetters==0)
                    {
                        if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
                            for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        System.exit(0);
                    }
                }
            }
            if(player==1)
            {
                model1.addElement("Player1: "+wordscore);
                P1score+=wordscore;
                jLabel6.setText(""+P1score);
            }
            else
            {
                model1.addElement("Player2: "+wordscore);
                P2score+=wordscore;
                jLabel7.setText(""+P2score);
            }
            jList1.setModel(model1);
            if(firstmove)
                firstmove=false;
            if(player==1)
                    {
                        for(int i=0;i<7;++i)
                            P1Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P2Rack[i]);
                    }
                    else
                    {
                        for(int i=0;i<7;++i)
                            P2Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P1Rack[i]);
                    }
                    player=3-player;
                    for(int i=0;i<15;++i)
                            for(int j=0;j<15;++j)
                            {
                                if(!Tile[i][j].getText().equals(""))
                                    Tile[i][j].setActionCommand("disabled");
                            }
                    return;
        }
        for(int y=0;y<15;++y)
            for(int x=0;x<15;++x)
            {
                helpRack="";
                helpBoard="";
                placePos[0]=y;
                placePos[1]=x;
                if(!Tile[y][x].getActionCommand().equals("disabled"))
                    continue;
                for(int j=0;j<7;++j)
                {
                    helpRack+=RTile[j].getText();
                }
                helpRack+=Tile[y][x].getText();
                helpBoard+=Tile[y][x].getText();
                Tester.disp(helpRack,helpBoard);
                wNode crawl=Tester.head;
                crawl=crawl.next;
                while(crawl!=null)
                {
                    if(fits(crawl,placePos,helpBoard)==0)
                    {
                        fit="not";
                        crawl.possible=false;
                        crawl=crawl.next;
                        continue;
                    }
                    else if(fits(crawl,placePos,helpBoard)==1)
                    {
                        fit="hor";
                        crawl.horizontal=true;
                    }
                    else
                    {
                        fit="ver";
                        crawl.horizontal=false;
                    }
                    crawl=crawl.next;
                }
                
                putCheck(Tester.head.next);
                int counter=0;
                wNode sorter=Tester.head.next;
                while(sorter!=null)
                {
                    counter++;
                    sorter=sorter.next;
                }
                sorter=Tester.head.next;
                for(int i=0;i<counter;++i)
                {
                    while(sorter.next!=null)
                    {
                        if(sorter.wordScore<sorter.next.wordScore)
                        {
                            int wordScore=sorter.wordScore;
                            String word=sorter.word;
                            boolean possible=sorter.possible;
                            int start[]=new int[2];
                            start[0]=sorter.start[0];
                            start[1]=sorter.start[1];
                            boolean horizontal=sorter.horizontal;
                            sorter.wordScore=sorter.next.wordScore;
                            sorter.word=sorter.next.word;
                            sorter.possible=sorter.next.possible;
                            sorter.start[0]=sorter.next.start[0];
                            sorter.start[1]=sorter.next.start[1];
                            sorter.horizontal=sorter.next.horizontal;
                            sorter.next.word=word;
                            sorter.next.possible=possible;
                            sorter.next.wordScore=wordScore;
                            sorter.next.start[0]=start[0];
                            sorter.next.start[1]=start[1];
                            sorter.next.horizontal=horizontal;
                        }
                        sorter=sorter.next;
                    }
                    sorter=Tester.head.next;
                }
                counter=0;
                while(sorter!=null)
                {
                    if(sorter.possible)
                        counter++;
                    sorter=sorter.next;
                }
                wNode crawl1=Tester.head.next;
                if(player==2)
                {
                    if(jRadioButton1.isSelected())
                    {
                        for(int i=0;i<counter*2/3;++i)
                        {
                            if(!crawl1.possible)
                                --i;
                            crawl1=crawl1.next;
                        }
                    }
                    else if(jRadioButton2.isSelected())
                    {
                        for(int i=0;i<counter/3;++i)
                        {
                            if(!crawl1.possible)
                                --i;
                            crawl1=crawl1.next;
                        }
                    }
                }
                else
                {
                    if(jRadioButton5.isSelected())
                    {
                        for(int i=0;i<counter*2/3;++i)
                        {
                            if(!crawl1.possible)
                                --i;
                            crawl1=crawl1.next;
                        }
                    }
                    else if(jRadioButton6.isSelected())
                    {
                        for(int i=0;i<counter/3;++i)
                        {
                            if(!crawl1.possible)
                                --i;
                            crawl1=crawl1.next;
                        }
                    }
                }
                while(crawl1!=null)
                {
                    if(!crawl1.possible)
                    {
                        crawl1=crawl1.next;
                        continue;
                    }
                    if(crawl1.wordScore>maxscore)
                    {
                        
                        maxscore=crawl1.wordScore;
                        maxnode=crawl1;
                    }
                    crawl1=crawl1.next;
                }
                Tester.delete();
            }
        put(maxnode,true);
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(checkAcceptable())
        {
            int wordscore=wordmakescore();
            String word="";
            if(wordscore==-1)
                return;
            if(alongRow)
            {
                for(int i=xstart;i<15&&!Tile[ystart][i].getText().equals("");++i)
                {
                    
                    Tile[ystart][i].setActionCommand("disabled");
                    Tile[ystart][i].setBackground(new Color(168,116,38));
                    Tile[ystart][i].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            else
            {
                for(int i=ystart;i<15&&!Tile[i][xstart].getText().equals("");++i)
                {
                    
                    Tile[i][xstart].setActionCommand("disabled");
                    Tile[i][xstart].setBackground(new Color(168,116,38));
                    Tile[i][xstart].setFont(new Font("Arial", Font.BOLD, 10));
                }
            }
            jLabel4.setText("All right");
            for(int i=0;i<7;++i)
            {
                if(RTile[i].getText().equals(""))
                {
                    Random rand=new Random();
                    int n=rand.nextInt(numLetters);
                    if(n<0)
                        n=-1*n;
                    RTile[i].setText(LetterSet[n]);
                    for(int j=n;j<numLetters-1;++j)
                    {
                        LetterSet[j]=LetterSet[j+1];
                    }
                    numLetters--;
                    if(numLetters==0)
                    {
                        if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
                         for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        System.exit(0);
                    }
                }
            }
            if(player==1)
            {
                model1.addElement("Player1: "+wordscore);
                P1score+=wordscore;
                jLabel6.setText(""+P1score);
            }
            else
            {
                model1.addElement("Player2: "+wordscore);
                P2score+=wordscore;
                jLabel7.setText(""+P2score);
            }
            jList1.setModel(model1);
            if(firstmove)
                firstmove=false;
            if(player==1)
                    {
                        for(int i=0;i<7;++i)
                            P1Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P2Rack[i]);
                    }
                    else
                    {
                        for(int i=0;i<7;++i)
                            P2Rack[i]=RTile[i].getText();
                        for(int i=0;i<7;++i)
                            RTile[i].setText(P1Rack[i]);
                    }
                    player=3-player;
                    for(int i=0;i<15;++i)
                            for(int j=0;j<15;++j)
                            {
                                if(!Tile[i][j].getText().equals(""))
                                    Tile[i][j].setActionCommand("disabled");
                            }
                    if((player==1&&!jRadioButton8.isSelected())||(player==2&&!jRadioButton4.isSelected()))
                        compPlayer();
        }
        
    }
    public class myRunnable implements Runnable{

        @Override
        public void run() {
            while(((player==1&&!jRadioButton8.isSelected())||(player==2&&!jRadioButton4.isSelected())))
                compPlayer();
        }
        
    }
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endActionPerformed
        // TODO add your handling code here:
        if(P1score>P2score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("1");
                            jLabel1.setText("Wins!!");
                        }
                        else if(P2score>P1score)
                        {
                            RTile[0].setText("P");
                            RTile[1].setText("L");
                            RTile[2].setText("A");
                            RTile[3].setText("Y");
                            RTile[4].setText("E");
                            RTile[5].setText("R");
                            RTile[6].setText("2");
                            jLabel1.setText("Wins!!");
                        }    
                        else
                        {
                            RTile[0].setText("D");
                            RTile[1].setText("R");
                            RTile[2].setText("A");
                            RTile[3].setText("W");
                            RTile[4].setText("!");
                            RTile[5].setText("!");
                            RTile[6].setText("");
                        }
        for(int l=0;l<15;++l)
                for(int m=0;m<15;++m)
                    Tile[l][m].setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
        System.exit(0);
    }//GEN-LAST:event_endActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Scrabble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Scrabble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Scrabble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Scrabble.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Scrabble().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Board;
    private javax.swing.JPanel Rack;
    private javax.swing.JButton accept;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton end;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pass;
    private javax.swing.JButton rep;
    private javax.swing.JLabel selectile;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean foundsource=false;
        jLabel4.setText("");
        for(int i=0;i<7;++i)
        {
            if(foundsource)
                break;
            if(e.getSource().equals(RTile[i]))
            {
                foundsource=true;
                
                if(replacemode)
                {
                    if(RTile[i].getText().equals(""))
                        return;
                    if(RTile[i].getActionCommand().equals("replace"))
                    {
                        RTile[i].setActionCommand("");
                        RTile[i].setBackground(Color.lightGray);
                    }
                    else
                    {
                        RTile[i].setActionCommand("replace");
                        RTile[i].setBackground(Color.BLUE);
                    }
                    return;
                }
                if(selected.equals(""))
                {
                    selected=RTile[i].getText();
                    RTile[i].setText(""); 
                }
                else
                {
                    String temp=selected;
                    selected=RTile[i].getText();
                    RTile[i].setText(temp);
                }
            }    
        }
        boolean flg=false;
        for(int i=0;i<15;++i)
        {
            if(flg)
                break;
            if(foundsource)
                break;
            for(int j=0;j<15;++j)
            {
                if(foundsource)
                    break;
                if(e.getSource().equals(Tile[i][j]))
                {
                    foundsource=true;
                    
                    if(Tile[i][j].getActionCommand().equals("disabled"))
                    {
                        flg=true;
                        if(!selected.equals(""))
                            selectile.setIcon(Tileimg[selected.charAt(0)-'A']);
                        break;
                    }
                    if(selected.equals("")&&!Tile[i][j].getText().equals(""))
                    {
                        selected=Tile[i][j].getText();
                        Tile[i][j].setText("");
                    }
                    else if(selected.equals("")||Tile[i][j].getText().equals(""))
                    {
                        Tile[i][j].setText(selected);
                        selected="";
                    }
                }
            }
        }
        if(selected.equals(""))
            selectile.setIcon(Tileimg[26]);
        else
        {
           int index;
           index=(int)selected.charAt(0)-(int)"A".charAt(0);
           selectile.setIcon(Tileimg[index]);
        }
    }
}
