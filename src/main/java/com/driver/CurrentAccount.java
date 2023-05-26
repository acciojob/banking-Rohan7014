package com.driver;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    private static Double minBalance=5000.0;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,minBalance);
        this.tradeLicenseId=tradeLicenseId;
        validateLicenseId();
    }
    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int len=tradeLicenseId.length();
        Map<Character,Integer>freq=new HashMap<>();
        getFrequencyMap(len, freq);
        int max=getMaxFreq(freq);
        if(max>len/2){
            throw new Exception("");
        }
        while(!isTradeIdValid()){
            char arr[]=new char[len];
            int index=0;
            int index2=1;
            for(var entry:freq.entrySet()) {
                int fr = entry.getValue();
                int i = 0;
                for (; i < fr && index < len; i++) {
                    arr[index] = entry.getKey();
                    index += 2;
                }
                if (index >= len) {
                    for (; i < fr && index2 < len; i++) {
                        arr[index2] = entry.getKey();
                        index2 += 2;
                    }
                }
            }
            StringBuilder sb=new StringBuilder();
            for(char ch:arr){
                sb.append(ch);
            }
            this.tradeLicenseId=sb.toString();
        }
    }
    private int getMaxFreq(Map<Character, Integer> freq) {
        int max=Integer.MIN_VALUE;
        for(Map.Entry<Character,Integer> val:freq.entrySet()){
            if(val.getValue()>max){
                max=val.getValue();
            }
        }
        return max;
    }

    private void getFrequencyMap(int len, Map<Character, Integer> freq) {
        for(int i = 0; i< len; i++) {
            char ch=tradeLicenseId.charAt(i);
            freq.put(ch,freq.getOrDefault(ch,0)+1);
        }
    }
    public boolean isTradeIdValid(){
        for(int i=0; i<tradeLicenseId.length()-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }
}