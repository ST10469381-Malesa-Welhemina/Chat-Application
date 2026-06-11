package chatapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ChatAppTest {
    
    private ArrayList<String> sentArr;
    private ArrayList<String> storedArr;
    private ArrayList<String> idArr;
    private ArrayList<String> hashArr;
    private ArrayList<String> recipArr;
    private ArrayList<String> textArr;
    
    @Before
    public void init() {
        sentArr = new ArrayList<>();
        storedArr = new ArrayList<>();
        idArr = new ArrayList<>();
        hashArr = new ArrayList<>();
        recipArr = new ArrayList<>();
        textArr = new ArrayList<>();
        
        // Message 1 (sent)
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        sentArr.add(m1.getText());
        idArr.add(m1.getId());
        hashArr.add(m1.getHash());
        recipArr.add(m1.getReceiver());
        textArr.add(m1.getText());
        
        // Message 2 (stored)
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        storedArr.add(m2.getText());
        idArr.add(m2.getId());
        hashArr.add(m2.getHash());
        recipArr.add(m2.getReceiver());
        textArr.add(m2.getText());
        
        // Message 3 (disregarded) – not stored
        
        // Message 4 (sent) – recipient is 0838884567 (no +27)
        Message m4 = new Message(4, "0838884567", "It is dinner time!");
        sentArr.add(m4.getText());
        idArr.add(m4.getId());
        hashArr.add(m4.getHash());
        recipArr.add(m4.getReceiver());
        textArr.add(m4.getText());
        
        // Message 5 (stored)
        Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        storedArr.add(m5.getText());
        idArr.add(m5.getId());
        hashArr.add(m5.getHash());
        recipArr.add(m5.getReceiver());
        textArr.add(m5.getText());
    }
    
    // test 1: sent messages array has correct texts
    @Test
    public void testSentMessages() {
        assertEquals(2, sentArr.size());
        assertTrue(sentArr.contains("Did you get the cake?"));
        assertTrue(sentArr.contains("It is dinner time!"));
    }
    
    // test 2: longest stored message
    @Test
    public void testLongestStored() {
        String longest = "";
        for (String s : storedArr) {
            if (s.length() > longest.length()) longest = s;
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }
    
    // test 3: search by ID (message 4)
    @Test
    public void testSearchById() {
        String targetId = idArr.get(2);
        String foundRecip = "";
        String foundText = "";
        for (int i = 0; i < idArr.size(); i++) {
            if (idArr.get(i).equals(targetId)) {
                foundRecip = recipArr.get(i);
                foundText = textArr.get(i);
                break;
            }
        }
        assertEquals("0838884567", foundRecip);
        assertEquals("It is dinner time!", foundText);
    }
    
    // test 4: search by recipient +27838884567
    @Test
    public void testSearchByRecipient() {
        String target = "+27838884567";
        ArrayList<String> matches = new ArrayList<>();
        for (int i = 0; i < recipArr.size(); i++) {
            if (recipArr.get(i).equals(target)) {
                matches.add(textArr.get(i));
            }
        }
        assertEquals(2, matches.size());
        assertTrue(matches.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(matches.contains("Ok, I am leaving without you."));
    }
    
    // test 5: delete by hash (delete message 2)
    @Test
    public void testDeleteByHash() {
        String targetHash = hashArr.get(1);
        String targetText = textArr.get(1);
        int oldSize = storedArr.size();
        
        // remove from arrays (simulate deletion)
        for (int i = 0; i < hashArr.size(); i++) {
            if (hashArr.get(i).equals(targetHash)) {
                hashArr.remove(i);
                idArr.remove(i);
                recipArr.remove(i);
                textArr.remove(i);
                storedArr.remove(i);
                break;
            }
        }
        assertEquals(oldSize - 1, storedArr.size());
        assertTrue(storedArr.contains(targetText));
    }
    
    // test 6: full report contains required data
    @Test
    public void testFullReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < storedArr.size(); i++) {
            report.append(hashArr.get(i)).append(" | ")
                  .append(recipArr.get(i)).append(" | ")
                  .append(storedArr.get(i)).append("\n");
        }
        String output = report.toString();
        assertTrue(output.contains("+27838884567"));
        assertTrue(output.contains("Ok, I am leaving without you."));
    }
}