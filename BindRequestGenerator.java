import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BindRequestGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for user input
        System.out.print("Enter DN (e.g., cn=Directory Manager): ");
        String dn = scanner.nextLine();
        // String dn="cn=Directory Manager";

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // String password="chandan";
        // Create LDAP Bind request
        byte[] bindRequest;
        try {
            bindRequest = createBindRequest(dn, password);
            System.out.println("LDAP Bind Request (hex): " + bytesToHex(bindRequest));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static byte[] createBindRequest(String dn, String password) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // LDAP version number
        byte ldapVersion = 3;

        // ASN.1 encoding for the sequence of the LDAP message
        outputStream.write(0x30);  // Sequence tag
        ByteArrayOutputStream innerStream = new ByteArrayOutputStream();

        // Message ID
        innerStream.write(0x02);  // Integer tag
        innerStream.write(0x01);  // Length
        innerStream.write(0x01);  // Message ID = 1

        // Bind request
        innerStream.write(0x60);  // Bind request tag
        ByteArrayOutputStream bindStream = new ByteArrayOutputStream();

        // LDAP version
        bindStream.write(0x02);  // Integer tag
        bindStream.write(0x01);  // Length
        bindStream.write(ldapVersion);  // Version 3

        // DN (distinguished name)
        byte[] dnBytes = dn.getBytes(StandardCharsets.UTF_8);
        bindStream.write(0x04);  // Octet string tag
        bindStream.write(dnBytes.length);  // Length
        bindStream.write(dnBytes);  // DN value

        // Password
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        bindStream.write(0x80);  // Context-specific tag (for simple authentication)
        bindStream.write(passwordBytes.length);  // Length
        bindStream.write(passwordBytes);  // Password value

        // Complete bind request
        byte[] bindContent = bindStream.toByteArray();
        innerStream.write(bindContent.length);  // Length of bind content
        innerStream.write(bindContent);

        // Complete LDAP message
        byte[] innerContent = innerStream.toByteArray();
        outputStream.write(innerContent.length);  // Length of inner content
        outputStream.write(innerContent);

        return outputStream.toByteArray();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            hexString.append(String.format("%02x", bytes[i]));  // Use %02x for lowercase hex
            if ((i + 1) % 2 == 0 && i < bytes.length - 1) {
                hexString.append(" ");
            }
        }
        return hexString.toString();
    }
    
}
