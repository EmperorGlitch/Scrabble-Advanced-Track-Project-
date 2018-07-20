/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Sagnik Dey
 */
public class Trie {
    
    public class wNode
    {
        String word;
        int wordScore;
        boolean possible;
        boolean horizontal;
        int start[]=new int[2];
        wNode next;
        wNode()
        {
            word="";
            wordScore=0;
            next=null;
            possible=true;
        }
    };
    wNode head=new wNode();
    void enterWord(String Word,int score)
    {
        wNode newword=new wNode();
        newword.word=Word;
        newword.wordScore=score;
        if(head.next==null)
        {
            head.next=newword;
            return;
        }
        newword.next=head;
        head=newword;
    }
    class TrieNode
    {
        TrieNode[] children=new TrieNode[26];
        boolean end;
        TrieNode()
        {
            for(int i=0;i<26;++i)
                children[i]=null;
            end=false;
        }
    };
    TrieNode root=new TrieNode();
    void insert() throws IOException
    {
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Tiles/words.txt")));
        String word;
        while(txtReader.ready())
        {
            word=txtReader.readLine();
            TrieNode put=root;
            for(int i=0;i<word.length();++i)
            {
                if(put.children[(word.charAt(i)-'A')]==null)
                    put.children[word.charAt(i)-'A']=new TrieNode();
                put=put.children[word.charAt(i)-'A'];
            }
        put.end=true;
        }
    }
    boolean search(String word)
    {
        TrieNode check=root;
        for(int i=0;i<word.length();++i)
        {
            if(check.children[word.charAt(i)-'A']==null)
                return false;
            check=check.children[word.charAt(i)-'A'];
        }
        if(check!=null&&check.end)
            return true;
        return false;
    } 
    int calcScore(char c)
    {
        switch(c)
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
                case 'L':   return 1;

                case 'D':
                case 'G':   return 2;

                case 'B':
                case 'C':
                case 'M':
                case 'P':   return 3;

                case 'F':
                case 'H':
                case 'V':
                case 'W':
                case 'Y':   return 4;

                case 'K':   return 5;

                case 'J':
                case 'X':   return 8;
                            
                case 'Q':
                case 'Z':   return 10;
                            
            }
            return 1;
    }
    
    String disp(String Rack,String required)
    {
        maxScore=0;
        maxWord="";
        req=required;
        return (""+maxscore(Rack,"",0,root,false)+" - "+maxWord);
    }
    String maxWord="";
    String req="";
    int maxScore=0;
    int maxscore(String Rack,String currWord,int currScore,TrieNode crawl,boolean wend)
    {
        if(wend)
        {
            if(currWord.contains(req)){
            if(currScore>maxScore)
            {
                maxScore=currScore;
                if(currWord.length()>0)
                maxWord=(currWord);
            }
            enterWord(currWord,currScore);
        }
        }
        for(int i=0;i<Rack.length();++i)
        {
            if(crawl.children[Rack.charAt(i)-'A']==null)
                continue;
            String newRack="";
            for(int j=0;j<Rack.length();++j)
                if(j!=i)
                    newRack=newRack+Rack.charAt(j);
            String ncurrWord=currWord;
            if(crawl.children[Rack.charAt(i)-'A'].end)
            maxscore(newRack,ncurrWord+Rack.charAt(i),currScore+calcScore(Rack.charAt(i)),crawl.children[Rack.charAt(i)-'A'],true);
            else
            maxscore(newRack,ncurrWord+Rack.charAt(i),currScore+calcScore(Rack.charAt(i)),crawl.children[Rack.charAt(i)-'A'],false);
        }
        return maxScore;
    }
    void delete()
    {
        wNode deleter=head;
        while(deleter!=null)
        {
            wNode temp=deleter.next;
            deleter.next=null;
            deleter=temp;
        }
        head=new wNode();
    }
}
