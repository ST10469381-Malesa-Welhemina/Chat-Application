package chatapp;

import java.util.regex.Pattern;

public class LoginFeature {
    
    private String storedUser;
    private String storedPass;
    private String storedFirst;
    private String storedLast;
    private String storedPhone;
    
    public boolean checkUserName(String s) {
        if (s == null) return false;
        return s.contains("_") && s.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String s) {
        if (s == null) return false;
        boolean len = s.length() >= 8;
        boolean cap = s.matches(".*[A-Z].*");
        boolean dig = s.matches(".*[0-9].*");
        boolean spec = s.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?~`].*");
        return len && cap && dig && spec;
    }
    
    // regex from https://regex101.com/
    public boolean checkCellPhoneNumber(String s) {
        if (s == null) return false;
        return Pattern.matches("^\\+27[0-9]{9}$", s);
    }
    
    public String registerUser(String u, String p, String f, String l, String ph) {
        StringBuilder out = new StringBuilder();
        boolean ok = true;
        
        if (!checkUserName(u)) {
            out.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
            ok = false;
        } else {
            out.append("Username successfully captured.\n");
        }
        
        if (!checkPasswordComplexity(p)) {
            out.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
            ok = false;
        } else {
            out.append("Password successfully captured.\n");
        }
        
        if (!checkCellPhoneNumber(ph)) {
            out.append("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.\n");
            ok = false;
        } else {
            out.append("Cell phone number successfully added.\n");
        }
        
        if (ok) {
            storedUser = u;
            storedPass = p;
            storedFirst = f;
            storedLast = l;
            storedPhone = ph;
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        } else {
            return out.toString().trim();
        }
    }
    
    public boolean loginUser(String u, String p) {
        if (u == null || p == null) return false;
        return u.equals(storedUser) && p.equals(storedPass);
    }
    
    public String returnLoginStatus(boolean success, String f, String l) {
        if (success)
            return "Welcome " + f + ", " + l + " it is great to see you again.";
        else
            return "Username or password incorrect, please try again.";
    }
}