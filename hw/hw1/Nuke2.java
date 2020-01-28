import java.io.*;

class Nuke2{
    public static void main(String[] args) throws Exception{

        BufferedReader keyboard;
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a word:");
        System.out.flush();

        String inputword = keyboard.readLine();

        int wordlength;
        wordlength = inputword.length();
        System.out.println(inputword.substring(0,1)+ inputword.substring(2,wordlength));
    }

}

