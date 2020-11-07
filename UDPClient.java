import java.util.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.*;
import java.io.*;
public class UDPClient
{
    private static DatagramSocket socket;
   
 
public UDPClient()
{
      try
    {
        this.socket = new DatagramSocket(53);
    }catch (Exception e)
    {
        e.printStackTrace();
    }
}

public void start()
{
    System.out.println("----------------------------------------------------------------");
    while(true)
    {
        try
        {
            byte[] buffer = new byte[1024];
                 DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                 socket.receive(request);
                 
                 InetAddress sourceIpAddr = request.getAddress();
                 int sourcePort = request.getPort();
                 System.out.println("\nsourceIpAddr = " + sourceIpAddr.toString() + "\nsourcePort = " + sourcePort);
                 /*
                 Message indata = new Message(request.getData());
                 System.out.println("\nindata = " + indata.toString());
                 Record question = indata.getQuestion();
                 System.out.println("question = " + question);
                 String domain = indata.getQuestion().getName().toString();
                 System.out.println("domain = " + domain);                
                 InetAddress answerIpAddr = Address.getByName(domain);
                 Message outdata = (Message)indata.clone();        
                 Record answer = new Record(question.getName(), question.getDClass(), 64, answerIpAddr);
                 outdata.addRecord(answer, Section.ANSWER);                
                 byte[] buf = outdata.toWire();
                 DatagramPacket response = new DatagramPacket(buf, buf.length, sourceIpAddr, sourcePort);
                 socket.send(response);
                 */
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("hostname");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes();
      DatagramPacket sendPacket =
      new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket =
      new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String modifiedSentence =
      new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + modifiedSentence);

      clientSocket.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

// send the message to the dns server
public static void send()
{ 
    try
    {
        DatagramSocket socket = new DatagramSocket();
        String text = "test";
        byte[] buf = text.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 9002);
        socket.send(packet);
        socket.close();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}
 public static void main(String args[])
 {
     send();
 }
}
/*
public void Server ()
{
    DatagramSocket socket = new DatagramSocket(9002);
            while(true){
               
                byte[] buf = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                String msg = new String(data, 0, packet.getLength());
                System.out.println(msg);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
           
        }

}
*/