package chatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    
    private String id;
    private int num;
    private String recipient;
    private String body;
    private String hash;
    
    public Message(int n, String to, String txt) {
        num = n;
        recipient = to;
        body = txt;
        id = randomId();
        hash = buildHash();
    }
    
    // test constructor (fixed ID)
    public Message(int n, String to, String txt, String fixedId) {
        num = n;
        recipient = to;
        body = txt;
        id = fixedId;
        hash = buildHash();
    }
    
    private String randomId() {
        Random r = new Random();
        long val = 1000000000L + (long)(r.nextDouble() * 9000000000L);
        return Long.toString(val);
    }
    
    private String clean(String w) {
        return w.replaceAll("[^A-Za-z]", "");
    }
    
    public String buildHash() {
        String start = id.substring(0, 2);
        String[] parts = body.trim().split("\\s+");
        String first = clean(parts[0]).toUpperCase();
        String last = clean(parts[parts.length - 1]).toUpperCase();
        return start + ":" + num + ":" + first + last;
    }
    
    public boolean idOk() {
        return id != null && id.length() <= 10;
    }
    
    public String checkRecipient() {
        if (recipient == null || !recipient.matches("^\\+27[0-9]{9}$"))
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        return "Cell phone number successfully captured.";
    }
    
    // appends a single message to JSON (used when storing)
    public void saveToFile() {
        try (FileWriter fw = new FileWriter("chatdata.json", true)) {
            fw.write("{\"id\":\"" + id + "\", \"hash\":\"" + hash + "\", \"to\":\"" + recipient + "\", \"msg\":\"" + body + "\"}\n");
        } catch (IOException e) {
            System.out.println("JSON save error.");
        }
    }
    
    public String getId() { return id; }
    public int getNum() { return num; }
    public String getReceiver() { return recipient; }
    public String getText() { return body; }
    public String getHash() { return hash; }
}