/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author brettsa
 */
class IOUts {

    public static String readFileToString(String path, String enc) {
        try {
            Reader in = new InputStreamReader(new FileInputStream(path), enc);
            try {
                return readFully(in);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static byte[] readFileToByteArray(final String path) throws Exception {
        try {
            InputStream in = new FileInputStream(path);
            try {
                return readFully(in);
            } finally {
                in.close();
            }

        } catch (IOException ex) {
            throw new Exception (ex.getCause());
        }
    }

    public static byte[] byteArrayToGZipByteArray(byte[] data) throws IOException{
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        GZIPOutputStream gzip = new GZIPOutputStream(baos);
        gzip.
                
                }
        
    }
    
    
    public static byte[] readFully(InputStream in) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toByteArray();
    }
    
    public static String readFully(Reader in) throws IOException {

        StringWriter out = new StringWriter();
        copy(in, out);

        return out.toString();
    }

    private static void copy(Reader in, StringWriter out) throws IOException {
        if (out == null) {
            throw new NullPointerException("out");
        }
        char[] buffer = new char[2048];

        for (int read = in.read(buffer); read != -1; read = in.read(buffer)) {
            out.write(buffer, 0, read);
        }
    }
    
    private static void copy(InputStream in, OutputStream out) throws IOException{
        if (out == null){
            throw new NullPointerException("out");
            
        }
        byte[] buffer = new byte[4096];
        for (int read = in.read(buffer); read !=-1; read =in.read(buffer)){
            out.write(buffer,0,read);
        }
        
    }

}
