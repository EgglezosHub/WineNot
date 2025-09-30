package winepackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignUp.class.getName());

    public SignUp() {
        initComponents();
    }

    public JPasswordField getConfirmPasswordPasswordField() {
        return confirmPasswordPasswordField;
    }

    public void setConfirmPasswordPasswordField(JPasswordField confirmPasswordPasswordField) {
        this.confirmPasswordPasswordField = confirmPasswordPasswordField;
    }

    public JPasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }

    public void setPasswordPasswordField(JPasswordField passwordPasswordField) {
        this.passwordPasswordField = passwordPasswordField;
    }
    

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JTextField getFullnameTextField() {
        return fullnameTextField;
    }

    public void setFullnameTextField(JTextField fullnameTextField) {
        this.fullnameTextField = fullnameTextField;
    }

    public JTextField getPhonenumberTextField() {
        return phonenumberTextField;
    }

    public void setPhonenumberTextField(JTextField phonenumberTextField) {
        this.phonenumberTextField = phonenumberTextField;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }
    
    public static String registerUser(String urlString, String username, String fullname, String phone, String email, String password) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = String.format(
                "{\"username\": \"%s\", \"fullname\": \"%s\", \"phone\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
                username, fullname, phone, email, password
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return "Success";
            } else {
                StringBuilder response = new StringBuilder();
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    while (scanner.hasNext()) {
                        response.append(scanner.nextLine());
                    }
                }
                return "Error: " + response.toString();
            }

        } catch (IOException e) {
            return "Error: Unable to connect to the server";
        }
    }
    

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        leftSignupBackround = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        rightSignupBackround = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        phonenumberLabel = new javax.swing.JLabel();
        fullnameTextField = new javax.swing.JTextField();
        fullnameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmpasswordLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        emailTextField = new javax.swing.JTextField();
        phonenumberTextField = new javax.swing.JTextField();
        haveaccountLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passwordPasswordField = new javax.swing.JPasswordField();
        confirmPasswordPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        leftSignupBackround.setBackground(new java.awt.Color(0, 0, 0));
        leftSignupBackround.setPreferredSize(new java.awt.Dimension(400, 500));

        nameLabel.setFont(new java.awt.Font("URW Bookman", 0, 48)); 
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/backg1.jpg"))); 
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout leftSignupBackroundLayout = new javax.swing.GroupLayout(leftSignupBackround);
        leftSignupBackround.setLayout(leftSignupBackroundLayout);
        leftSignupBackroundLayout.setHorizontalGroup(
            leftSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftSignupBackroundLayout.setVerticalGroup(
            leftSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftSignupBackroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel)
                .addGap(168, 168, 168))
        );

        jPanel1.add(leftSignupBackround);
        leftSignupBackround.setBounds(0, 0, 400, 500);

        rightSignupBackround.setBackground(new java.awt.Color(255, 255, 255));
        rightSignupBackround.setPreferredSize(new java.awt.Dimension(400, 500));

        loginLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); 
        loginLabel.setForeground(new java.awt.Color(115, 1, 18));
        loginLabel.setText("SING UP");

        usernameLabel.setText("Username :");

        usernameTextField.setMinimumSize(new java.awt.Dimension(350, 30));
        usernameTextField.setPreferredSize(new java.awt.Dimension(350, 30));
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        emailLabel.setText("Email :");

        phonenumberLabel.setText("Phone number :");

        fullnameTextField.setMinimumSize(new java.awt.Dimension(350, 30));
        fullnameTextField.setPreferredSize(new java.awt.Dimension(350, 30));
        fullnameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullnameTextFieldActionPerformed(evt);
            }
        });

        fullnameLabel.setText("First and Last name :");

        passwordLabel.setText("Password:");

        confirmpasswordLabel.setText("Confirm-Password:");

        confirmButton.setBackground(new java.awt.Color(115, 1, 18));
        confirmButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        emailTextField.setToolTipText("");
        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        phonenumberTextField.setToolTipText("");
        phonenumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonenumberTextFieldActionPerformed(evt);
            }
        });

        haveaccountLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
        haveaccountLabel.setText("I've an account");

        loginButton.setBackground(new java.awt.Color(204, 204, 204));
        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        loginButton.setForeground(new java.awt.Color(115, 1, 18));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightSignupBackroundLayout = new javax.swing.GroupLayout(rightSignupBackround);
        rightSignupBackround.setLayout(rightSignupBackroundLayout);
        rightSignupBackroundLayout.setHorizontalGroup(
            rightSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSignupBackroundLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(loginLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightSignupBackroundLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(rightSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(confirmPasswordPasswordField)
                    .addComponent(passwordPasswordField)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rightSignupBackroundLayout.createSequentialGroup()
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(haveaccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginButton))
                    .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phonenumberLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fullnameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fullnameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmpasswordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phonenumberTextField))
                .addGap(24, 24, 24))
        );
        rightSignupBackroundLayout.setVerticalGroup(
            rightSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSignupBackroundLayout.createSequentialGroup()
                .addComponent(loginLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullnameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phonenumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phonenumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addGap(2, 2, 2)
                .addComponent(passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmpasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightSignupBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(haveaccountLabel)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel1.add(rightSignupBackround);
        rightSignupBackround.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void fullnameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = getUsernameTextField().getText();
        String fullname = getFullnameTextField().getText();
        String phone = getPhonenumberTextField().getText();
        String email = getEmailTextField().getText();
        String pass = new String(getPasswordPasswordField().getPassword());
        String passconf = new String(getConfirmPasswordPasswordField().getPassword());  
        if (username.isEmpty() || fullname.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || passconf.isEmpty()){
            
            String messagesArray = "ΠΑΡΑΚΑΛΩ ΑΠΑΝΤΗΣΤΕ ΣΕ ΟΛΑ ΤΑ ΠΕΔΙΑ.";
            JOptionPane.showMessageDialog(
                this,
                messagesArray,
                "ΑΠΟΤΥΧΙΑ ΔΗΜΙΟΥΡΓΙΑΣ ΛΟΓΑΡΙΑΣΜΟΥ",
                JOptionPane.ERROR_MESSAGE
            );
            
        }else if(pass.equals(passconf) && pass.length()>=7 && passconf.length()>=7){
            
            String result = registerUser("http://localhost:5000/register", username, fullname, phone, email, pass);
            if (result.equals("Success")) {
                JOptionPane.showMessageDialog(this, "Ο λογαριασμός δημιουργήθηκε με επιτυχία!", "ΕΠΙΤΥΧΙΑ", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // closes the current JFrame
                HomePage home = new HomePage();
                home.pack();
                home.setUser(username);
                home.setLocationRelativeTo(null);
                home.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, result, "ΑΠΟΤΥΧΙΑ", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            String messagesArray = "ΛΑΘΟΣ ΣΤΑ ΠΕΔΙΑ ΤΟΥ ΚΩΔΙΚΟΥ, ΠΡΕΠΕΙ ΝΑ ΕΙΝΑΙ ΙΔΙΑ ΚΑΙ >= ΑΠΟ 7 ΧΑΡΑΚΤΗΡΕΣ.";
            JOptionPane.showMessageDialog(
                this,
                messagesArray,
                "ΑΠΟΤΥΧΙΑ ΔΗΜΙΟΥΡΓΙΑΣ ΛΟΓΑΡΙΑΣΜΟΥ",
                JOptionPane.ERROR_MESSAGE
            );            
        }
        
        
        
        
    }

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void phonenumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new SignUp().setVisible(true));
    }

    private javax.swing.JButton confirmButton;
    private javax.swing.JPasswordField confirmPasswordPasswordField;
    private javax.swing.JLabel confirmpasswordLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel fullnameLabel;
    private javax.swing.JTextField fullnameTextField;
    private javax.swing.JLabel haveaccountLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel leftSignupBackround;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordPasswordField;
    private javax.swing.JLabel phonenumberLabel;
    private javax.swing.JTextField phonenumberTextField;
    private javax.swing.JPanel rightSignupBackround;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
}
