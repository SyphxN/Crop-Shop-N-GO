package main.java;

import java.net.URI;
import java.awt.Desktop;
import java.io.IOException;


public class LinkReDirect {
    public static void main(String args[]) throws IOException{
        /*String url = "https://underarmour.scene7.com/is/image/Underarmour/PS1345317-866_HF?rp=standard-0pad|pdpMainDesktop&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0&wid=566&hei=708&size=566,708";
        String command[] = {"python","from serpapi import GoogleSearch","import json","params = {","\"api_key\": \"eeb332563bfc822f9412cc8db88139c113f2ea9b05ab11dc38a7028c2ebf52a6\",","\"engine\": \"google_lens\",",("\"url\":\"" +url+"\","),"}","search = GoogleSearch(params)","results = search.get_dict()","with open(r'src\\\\main\\\\java\\\\serpAPI.json','w') as f:","\t"+"json.dump(results,f,indent = 4)","exit()"};
        System.out.println(command);
        for(int i = 0; i<command.length;i++){
            System.out.println(command[i]);
        }   
        for(int i = 0; i<command.length;i++){
            Process p = Runtime.getRuntime().exec(command[i]);
        }    */
        String url = "https://underarmour.scene7.com/is/image/Underarmour/PS1345317-866_HF?rp=standard-0pad|pdpMainDesktop&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0&wid=566&hei=708&size=566,708";

String[] command = {
    "python",
    "-c",
    "from serpapi import GoogleSearch; import json; import sys; params = {\"api_key\": \"eeb332563bfc822f9412cc8db88139c113f2ea9b05ab11dc38a7028c2ebf52a6\", \"engine\": \"google_lens\", \"url\": sys.argv[1]}; search = GoogleSearch(params); results = search.get_dict(); with open('src/main/java/serpAPI.json', 'w') as f: json.dump(results, f, indent=4)",
    url
};

try {
    Process p = Runtime.getRuntime().exec(command);
    p.waitFor(); // Wait for the process to complete
} catch (IOException e) {
    e.printStackTrace();
} catch (InterruptedException e) {
    e.printStackTrace();
}


    }
}

