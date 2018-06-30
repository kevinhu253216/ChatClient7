import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient7 extends Frame{


    TextArea taContext = new TextArea();
    TextField tfTxt = new TextField();
    Socket s; //Member Variable  Share

    public static void main(String[] args){
        ChatClient7 cc = new ChatClient7();
        cc.launchFrame();
    }

    public void launchFrame(){
        setLocation(300,400);
        setSize(300,300);
        add(taContext,BorderLayout.NORTH);
        add(tfTxt, BorderLayout.SOUTH);
        pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfTxt.addActionListener(new TFListener());
        setVisible(true);
        connect();
    }

    public void connect(){
        try{
            s = new Socket("127.0.0.1",8888);
System.out.println("Connected!");
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException E){
            E.printStackTrace();
        }
    }

    private class TFListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String str = tfTxt.getText().trim();
            taContext.setText(str);
            tfTxt.setText("");
            try{//
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());//
                dos.writeUTF(str);//
                dos.flush();
                dos.close();
            }catch(IOException e1) {
                e1.printStackTrace();
            }//
        }
    }

}
