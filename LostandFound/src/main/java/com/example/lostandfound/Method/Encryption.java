package com.example.lostandfound.Method;

public class Encryption {
    String encrypted;
    int shift = 5;
    public String encrypt(String original){
        encShift(original);
        return encrypted;
    }
    private void encShift(String original){
        byte[] bytes = original.getBytes();
        for(byte b:bytes){
            b= (byte)(b+shift);
        }
        encrypted=bytes.toString();
    }
}
