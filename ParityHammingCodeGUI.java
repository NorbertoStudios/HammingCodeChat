import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ParityHammingCodeGUI extends Frame implements Runnable
{
    Thread              t;

    DataInputStream     input;
    DataOutputStream    output;

    Socket              socket;

    Label titleLabel, mLabel, p1,p2,p3,m1,m2,m3,m4,p1Label,p2Label
            , p3Label, m1Label, m2Label, m3Label, m4Label, results, dash,
            p1F,p2F,p3F,m1F,m2F,m3F,m4F,p1LabelF,p2LabelF
            , p3LabelF, m1LabelF, m2LabelF, m3LabelF, m4LabelF, dash2, resultTitle;

    Button clearBtn, generateBtn, sendBtn;

    TextField mTextField, rTextField, messageField;
    TextArea  logAreaField;  // limit the text field to only numbers maybe?

    String mInputString, userString;

    public ParityHammingCodeGUI(int x, int y, Socket socket)
    {
        this.socket = socket;

        setTitle("Hamming Correction Code");
        setLayout(null);
        setBounds(x,y,680,400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);

        stuffInsideTheFrame();  // calling the gui


        try
        {
            input  = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){}

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = messageField.getText();

                if(message.equalsIgnoreCase("exit"))
                {
                    exit();
                }

                try
                {
                    output.writeUTF(userString +": "+message);
                }catch (IOException ex){}

                messageField.setText("");
            }
        });
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = messageField.getText();

                if(message.equalsIgnoreCase("exit"))
                {
                    exit();
                }

                try
                {
                    output.writeUTF(userString +": "+message);
                }catch (IOException ex){}

                messageField.setText("");
            }
        });

        t = new Thread(this);
        t.start();

    }

    public void run()
    {
        while (true)
        {

            try{
                String message = input.readUTF();
                logAreaField.append(message+"\n");
            }catch (IOException exc){ }


        }


    }
    public void exit()
    {
        try{
            output.writeUTF(userString +": Left the chat!!");
        }catch (IOException ex){}


        this.dispose();

    }

    public void stuffInsideTheFrame()
    {
        titleLabel  = new Label("Parity Error Correcting Code");
        results     = new Label("Results");
        mLabel      = new Label("Message");
        p1Label     = new Label("p1");
        p2Label     = new Label("p2");
        p3Label     = new Label("p3");
        m1Label     = new Label("m1");
        m2Label     = new Label("m2");
        m3Label     = new Label("m3");
        m4Label     = new Label("m4");
        p1          = new Label("0");
        p2          = new Label("0");
        p3          = new Label("0");
        m1          = new Label("0");
        m2          = new Label("0");
        m3          = new Label("0");
        m4          = new Label("0");
        dash        = new Label("__       __      __         __      __       __         __");

        p1LabelF     = new Label("p1");
        p2LabelF     = new Label("p2");
        p3LabelF     = new Label("p3");
        m1LabelF     = new Label("m1");
        m2LabelF     = new Label("m2");
        m3LabelF     = new Label("m3");
        m4LabelF     = new Label("m4");
        p1F          = new Label("0");
        p2F          = new Label("0");
        p3F          = new Label("0");
        m1F          = new Label("0");
        m2F          = new Label("0");
        m3F          = new Label("0");
        m4F          = new Label("0");
        dash2        = new Label("__       __      __         __      __       __         __");
        resultTitle  = new Label("Corrected Error");

        clearBtn    = new Button("Clear");
        generateBtn = new Button("Generate");

        mTextField  = new TextField(7);
        rTextField  = new TextField();
        messageField = new TextField();

        sendBtn         = new Button("Send");
        logAreaField    = new TextArea();


        add(titleLabel);
        add(mLabel);
        add(p1Label);
        add(p2Label);
        add(p3Label);
        add(m1Label);
        add(m2Label);
        add(m3Label);
        add(m4Label);
        add(p1);
        add(p2);
        add(p3);
        add(m1);
        add(m2);
        add(m3);
        add(m4);
        add(dash);
        add(p1LabelF);
        add(p2LabelF);
        add(p3LabelF);
        add(m1LabelF);
        add(m2LabelF);
        add(m3LabelF);
        add(m4LabelF);
        add(p1F);
        add(p2F);
        add(p3F);
        add(m1F);
        add(m2F);
        add(m3F);
        add(m4F);
        add(dash2);
        add(resultTitle);
        add(results);
        add(mTextField);
        add(rTextField);
        add(clearBtn);
        add(generateBtn);
        add(sendBtn);
        add(logAreaField);
        add(messageField);


        titleLabel      .setBounds(90, 30, 200, 40);
        mLabel          .setBounds(30,80,70,30);
        mTextField      .setBounds(100,80,200,30);

        p1              .setBounds(30, 120, 20,20);
        p2              .setBounds(70, 120, 20,20);
        m1              .setBounds(110, 120, 20,20);
        p3              .setBounds(160, 120, 20,20);
        m2              .setBounds(200, 120, 20,20);
        m3              .setBounds(240, 120, 20,20);
        m4              .setBounds(290, 120, 20,20);

        dash            .setBounds(30, 130, 290,20);

        p1Label         .setBounds(30, 150, 20,20);
        p2Label         .setBounds(70, 150, 20,20);
        m1Label         .setBounds(110, 150, 20,20);
        p3Label         .setBounds(160, 150, 20,20);
        m2Label         .setBounds(200, 150, 20,20);
        m3Label         .setBounds(240, 150, 20,20);
        m4Label         .setBounds(290, 150, 20,20);

        resultTitle     .setBounds(30, 180, 200,20);

        p1F             .setBounds(30, 200, 20,20);
        p2F             .setBounds(70, 200, 20,20);
        m1F             .setBounds(110, 200, 20,20);
        p3F             .setBounds(160, 200, 20,20);
        m2F             .setBounds(200, 200, 20,20);
        m3F             .setBounds(240, 200, 20,20);
        m4F             .setBounds(290, 200, 20,20);

        dash2           .setBounds(30, 210, 290,20);

        p1LabelF        .setBounds(30, 230, 20,20);
        p2LabelF        .setBounds(70, 230, 20,20);
        m1LabelF        .setBounds(110, 230, 20,20);
        p3LabelF        .setBounds(160, 230, 20,20);
        m2LabelF        .setBounds(200, 230, 20,20);
        m3LabelF        .setBounds(240, 230, 20,20);
        m4LabelF        .setBounds(290, 230, 20,20);

        results         .setBounds(30,270,50,40);
        rTextField      .setBounds(90,270,210,40);

        clearBtn        .setBounds(30,330, 125,40);
        generateBtn     .setBounds(175,330, 125,40);


        logAreaField    .setBounds(330,30,320,280);
        messageField    .setBounds(330,330,240,40);
        sendBtn         .setBounds(580,330,80,40);

//

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mTextField.setText("");
                p1.setText("0");
                p2.setText("0");
                p3.setText("0");
                m1.setText("0");
                m2.setText("0");
                m3.setText("0");
                m4.setText("0");
                rTextField.setText("");

                p1F.setText("0");
                p1F.setForeground(Color.black);
                p2F.setText("0");
                p2F.setForeground(Color.black);
                p3F.setText("0");
                p3F.setForeground(Color.black);
                m1F.setText("0");
                m1F.setForeground(Color.black);
                m2F.setText("0");
                m2F.setForeground(Color.black);
                m3F.setText("0");
                m3F.setForeground(Color.black);
                m4F.setText("0");
                m4F.setForeground(Color.black);


            }
        });

        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mInputString = mTextField.getText();
                rTextField.setText(correctingCode(mInputString));
            }
        });


    }
    public String correctingCode(String m)
    {

        if(m.length() == 4) {

            // Parity Code

            // Testing 0110 even p1 = 0, p2 = 0, p3 = 1

            //char 0 = 48
            //char 1 = 49

            // This is hard code not efficient
            //  Temporary .............................. Start
            // 000 - even - 144
            // 001 - odd  - 145
            // 011 - even - 146
            // 111 - odd  - 147

            // p1 = 1,3,5,7
            // p2 = 2,3,6,7
            // p3 = 4,5,6,7

            //find p1 == 3,5,7 -> 1 if even 0 if odd
            //find p2 == 3,6,7 -> 1 if even 0 if odd
            //find p3 == 5,6,7 -> 1 if even 0 if odd

            int placeHolder = 0;

            String pResult;

            placeHolder = m.charAt(0) + m.charAt(1) + m.charAt(3);

            if(placeHolder == 144 || placeHolder == 146)
            {
                p1.setText("1");
            }else {
                p1.setText("0");
            }

            placeHolder = m.charAt(0) + m.charAt(2) + m.charAt(3);

            if(placeHolder == 144 || placeHolder == 146)
            {
                p2.setText("1");
            }else {
                p2.setText("0");
            }

            placeHolder = m.charAt(1) + m.charAt(2) + m.charAt(3);

            if(placeHolder == 144 || placeHolder == 146)
            {
                p3.setText("1");
            }else {
                p3.setText("0");
            }

            // Temporary ....End

            m1.setText(Character.toString(m.charAt(0)));//3
            m2.setText(Character.toString(m.charAt(1)));//5
            m3.setText(Character.toString(m.charAt(2)));//6
            m4.setText(Character.toString(m.charAt(3)));//7

            pResult = p1.getText()+p2.getText()+m1.getText()+p3.getText()+m2.getText()+m3.getText()+m4.getText();


            return pResult;
        }
        else if(m.length() == 7)
        {
            // correcting code

            p1.setText(Character.toString(m.charAt(0)));//1
            p2.setText(Character.toString(m.charAt(1)));//2
            m1.setText(Character.toString(m.charAt(2)));//3
            p3.setText(Character.toString(m.charAt(3)));//4
            m2.setText(Character.toString(m.charAt(4)));//5
            m3.setText(Character.toString(m.charAt(5)));//6
            m4.setText(Character.toString(m.charAt(6)));//7

            p1F.setText(Character.toString(m.charAt(0)));//1
            p2F.setText(Character.toString(m.charAt(1)));//2
            m1F.setText(Character.toString(m.charAt(2)));//3
            p3F.setText(Character.toString(m.charAt(3)));//4
            m2F.setText(Character.toString(m.charAt(4)));//5
            m3F.setText(Character.toString(m.charAt(5)));//6
            m4F.setText(Character.toString(m.charAt(6)));//7


            // maybe adding an Array for easy management

            // using a method for cleaner look
            int[] pIntegers = {parsedTo(m,0),parsedTo(m,1),parsedTo(m,2),
                                parsedTo(m,3),parsedTo(m,4),
                                parsedTo(m,5),parsedTo(m,6)};


            // Here we need to check for Parity

            // using the exclusive or we can check easily for the parity
            int parityP1 = ((pIntegers[0] ^ pIntegers[2]) ^ pIntegers[4]) ^ pIntegers[6];
            int parityP2 = ((pIntegers[1] ^ pIntegers[2]) ^ pIntegers[5]) ^ pIntegers[6];
            int parityP3 = ((pIntegers[3] ^ pIntegers[4]) ^ pIntegers[5]) ^ pIntegers[6];

            System.out.println(parityP1);
            System.out.println(parityP2);
            System.out.println(parityP3);




            //.......... Better i Think .............



            //_______________---------------------_________Make it Better_________Hard Coded________--------------------
            // Hint if the ps are added it give u the error -----

            if(!checkP(parityP1))
            {
                System.out.println("P1 doing the work here!");

                if(checkP(parityP2))
                {
                    System.out.println("errors could be on 1 or 5");
                    if(checkP(parityP3))
                    {
                        System.out.println("error has to be 1");      // Error on 1
                        p1F.setForeground(Color.red);
                        if(pIntegers[0] == 0)
                        {
                            pIntegers[0] = 1;
                            p1F.setText("1");
                        }
                        else {
                            pIntegers[0] = 0;
                            p1F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];

                    }
                }

                if(!checkP(parityP2))
                {
                    System.out.println("errors could be on 1, 2 or 3");

                    if(checkP(parityP3))
                    {
                        System.out.println("error has to be 3");     // Error on 3
                        m1F.setForeground(Color.red);
                        if(pIntegers[2] == 0)
                        {
                            pIntegers[2] = 1;
                            m1F.setText("1");
                        }
                        else {
                            pIntegers[2] = 0;
                            m1F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }

                if(!checkP(parityP3))
                {
                    System.out.println("errors could be on 1, 2 or 5");

                    if(checkP(parityP2))
                    {
                        System.out.println("error has to be 5");  // Error on 5

                        m2F.setForeground(Color.red);
                        if(pIntegers[4] == 0)
                        {
                            pIntegers[4] = 1;
                            m2F.setText("1");
                        }
                        else {
                            pIntegers[4] = 0;
                            m2F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }
            }

            if(!checkP(parityP2))
            {
                System.out.println("P2 doing the work here!");

                if(checkP(parityP1))
                {
                    System.out.println("errors could be on 2, 6 or 7");
                    if(checkP(parityP3))
                    {
                        System.out.println("error has to be 2");  // error on 2

                        p2F.setForeground(Color.red);
                        if(pIntegers[1] == 0)
                        {
                            pIntegers[1] = 1;
                            p2F.setText("1");
                        }
                        else {
                            pIntegers[1] = 0;
                            p2F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }

                if(!checkP(parityP1))
                {
                    System.out.println("errors could be on 2, 6 or 7");
                    if(!checkP(parityP3))
                    {
                        System.out.println("error has to be 7");  // error on 7

                        m4F.setForeground(Color.red);
                        if(pIntegers[6] == 0)
                        {
                            pIntegers[6] = 1;
                            m4F.setText("1");
                        }
                        else {
                            pIntegers[6] = 0;
                            m4F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }

            }

            if(!checkP(parityP3))
            {
                System.out.println("P3 doing the work here!");
                if(checkP(parityP1))
                {
                    System.out.println("error could be on 4 or 6");
                    if(checkP(parityP2))
                    {
                        System.out.println("error has to be 4");  // Error is on 4

                        p3F.setForeground(Color.red);
                        if(pIntegers[3] == 0)
                        {
                            pIntegers[3] = 1;
                            p3F.setText("1");
                        }
                        else {
                            pIntegers[3] = 0;
                            p3F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }

                if(!checkP(parityP2))
                {
                    System.out.println("error could be on 2, 4 or 6");
                    if(checkP(parityP1))
                    {
                        System.out.println("error has to be 6");  // Error is on 6

                        m3F.setForeground(Color.red);
                        if(pIntegers[5] == 0)
                        {
                            pIntegers[5] = 1;
                            m3F.setText("1");
                        }
                        else {
                            pIntegers[5] = 0;
                            m3F.setText("0");
                        }
                        return ""+pIntegers[2]+pIntegers[4]+pIntegers[5]+pIntegers[6];
                    }
                }
            }




            //--------------old code---------------//

//            // p1 = 1,3,5,7
//            // p2 = 2,3,6,7
//            // p3 = 4,5,6,7

//            //find p1 == 3,5,7 -> 1 if even 0 if odd
//            //find p2 == 3,6,7 -> 1 if even 0 if odd
//            //find p3 == 5,6,7 -> 1 if even 0 if odd

//            p1.setText(Character.toString(m.charAt(0)));//1
//            p2.setText(Character.toString(m.charAt(1)));//2
//            m1.setText(Character.toString(m.charAt(2)));//3
//            p3.setText(Character.toString(m.charAt(3)));//4
//            m2.setText(Character.toString(m.charAt(4)));//5
//            m3.setText(Character.toString(m.charAt(5)));//6
//            m4.setText(Character.toString(m.charAt(6)));//7
//
//            int[] testing = {Integer.parseInt(p1.getText()), Integer.parseInt(p2.getText()),
//                    Integer.parseInt(m1.getText()),Integer.parseInt(p3.getText()),Integer.parseInt(m2.getText()),
//                    Integer.parseInt(m3.getText()),Integer.parseInt(m4.getText())};


//            //p1 m1 m2 m4
//            int checkP1 = testing[0]+testing[2]+testing[4]+testing[6];
//            int checkP2 = testing[1]+testing[2]+testing[5]+testing[6];
//            int checkP3 = testing[3]+testing[4]+testing[5]+testing[6];
//

//            checkP(checkP1,testing[0]);
//            checkP(checkP2,testing[1]);
//            checkP(checkP3,testing[3]);


            // ------------------end of old code -------------

            return "No errors";
        }
        else
        {
            return "Only Binary Numbers with length of 4 or 7";
        }

    }


//    public boolean checkP(int root, int valueP)
//    {
//        if(root % 2 == 0)
//        {
//            System.out.println("even");
//
//            if(valueP == 0)
//            {
//                System.out.println("Correct");
//
//                return true;
//            }
//            else
//            {
//                System.out.println("Not correct Fix it");
//
//                return false;
//            }
//
//        }else
//        {
//            System.out.println("odd");
//
//            if(valueP == 1 || valueP == 3)
//            {
//                System.out.println("Correct");
//
//                return true;
//            }
//            else
//            {
//                System.out.println("Not correct Fix it");
//
//                return false;
//            }
//        }
//    }

    public int parsedTo(String m, int index)
    {

        return Integer.parseInt(Character.toString(m.charAt(index)),2);
    }


    public boolean checkP (int parity)
    {
        if(parity == 1)
        {
            return true;
        }
        return false;
    }


}
