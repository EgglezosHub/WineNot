package winepackage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.swing.JTextField;

public class HomePage extends javax.swing.JFrame {
    public static HomePage homepage;
    public String user;

    public void setUser(String usernam) {
        this.user = usernam;
    }

    public String getUser() {
        return user;
    }
    

    public HomePage() {
        initComponents();
        homepage = this;
        setResizable(false);
        setTitle("Wine E-Shop");
        setIconImage(new ImageIcon(getClass().getResource("/winepackage/images/wine.png")).getImage());
        
        makeLabelClickable(wineTitle, 1, "Wine Title");
        makeLabelClickable(redWine, 2, "Red Wine");
        makeLabelClickable(roseWine, 3, "Rose Wine");
        makeLabelClickable(whiteWine, 4, "White Wine");
        makeLabelClickable(homeLogo, 5, "Home");
        makeLabelClickable(contactTitle, 6, "Contact");
        makeLabelClickable(aboutTitle, 7, "About");
        makeLabelClickable(accountIcon, 8, "Account");
        makeLabelClickable(cartIcon, 9, "Cart");
        makeLabelClickable(heartIcon, 10, "Favorites");
        
        hideAllPanels();
        homePanel.setVisible(true);
                
    }

    // Setters & Getters
    public JLabel getHomeRedTitle() {
        return homeRedTitle;
    }

    public void setHomeRedTitle(JLabel homeRedTitle) {
        this.homeRedTitle = homeRedTitle;
    }

    public JLabel getHomeRoseTitle() {
        return homeRoseTitle;
    }

    public void setHomeRoseTitle(JLabel homeRoseTitle) {
        this.homeRoseTitle = homeRoseTitle;
    }

    public JLabel getHomeWhiteTitle() {
        return homeWhiteTitle;
    }

    public void setHomeWhiteTitle(JLabel homeWhiteTitle) {
        this.homeWhiteTitle = homeWhiteTitle;
    }

    public JLabel getEmailInfo() {
        return emailInfo;
    }

    public void setEmailInfo(String emailInfo) {
        if (this.emailInfo != null) {
            this.emailInfo.setText(emailInfo);
        }
    }

    public JLabel getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        if (this.fullname != null) {
            this.fullname.setText(fullname);
        }
    }

    public JLabel getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (this.phone != null) {
            this.phone.setText(phone);
        }
    }

    public JLabel getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (this.username != null) {
            this.username.setText(username);
        }
    }

    public JLabel getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(String subtotalPrice) {
        this.subtotalPrice.setText(subtotalPrice);
    }

    public JLabel getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.setText(totalPrice);
    }

    public JTextField getFirstname() {
        return firstname;
    }

    public void setFirstname(JTextField firstname) {
        this.firstname = firstname;
    }

    public JTextField getSurename() {
        return surename;
    }

    public void setSurename(JTextField surename) {
        this.surename = surename;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JTextField getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(JTextField phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    

    public JTextField getAddress() {
        return address;
    }

    public void setAddress(JTextField address) {
        this.address = address;
    }

    public JTextField getCity() {
        return city;
    }

    public void setCity(JTextField city) {
        this.city = city;
    }

    public JTextField getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(JTextField postalCode) {
        this.postalCode = postalCode;
    }
    
    
      
    
    private void makeLabelClickable(JLabel label, int actionCode, String name) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setForeground(Color.WHITE);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(name + " clicked! Action code: " + actionCode);
            }
        });
    }

    private void hideAllPanels() {
        homePanel.setVisible(false);
        winePanel.setVisible(false);
        redPanel.setVisible(false);
        rosePanel.setVisible(false);
        whitePanel.setVisible(false);
        contactPanel.setVisible(false);
        aboutPanel.setVisible(false);
        heartPanel.setVisible(false);
        cartPanel.setVisible(false);
        accountPanel.setVisible(false);
    }    
    
    public static String[][] load(String urlString, String username, int number) {
        try {
            // Create URL and Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configure Connection
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON Payload
            String jsonPayload = String.format("{\"username\": \"%s\", \"number\": %d}", username, number);

            // Write Payload
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Server responded with " + responseCode);
                return null;
            }
            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            String json = responseBuilder.toString();

            // Parse JSON using Gson
            Gson gson = new Gson();
            String[][] result = gson.fromJson(json, String[][].class);

            // Debug output
            System.out.println("---- Server Response ----");
            if (result != null) {
                for (String[] row : result) {
                    for (String cell : row) {
                        System.out.print(cell + "\t");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No data received or an error occurred.");
            }
            return result;

        } catch (IOException | JsonSyntaxException e) {
            return null;
        }
    }

    public static String Update(String urlString, String username, String Title, int CartWish, int AddMin) {
        try {
            // Create URL and Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configure Connection
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON Payload
            String jsonPayload = String.format(
                "{\"username\": \"%s\", \"Title\": \"%s\", \"CartWish\": %d, \"AddMin\": %d}",
                username, Title, CartWish, AddMin
            );

            // Write Payload
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: Server responded with " + responseCode;
            }

            StringBuilder response;
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }
            return response.toString();

        } catch (IOException e) {
            return "Error: Unable to connect to the server";
        }
    }
    
    public static String cartManage(String urlString, String username, String Title, int AddMin) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = String.format(
                "{\"username\": \"%s\", \"Title\": \"%s\", \"AddMin\": %d}",
                username, Title, AddMin
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: Server responded with " + responseCode;
            }

            StringBuilder response;
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }

            return response.toString();

        } catch (IOException e) {
            return "Error: Unable to connect to the server";
        }
    }
    
    public void loadWishlist() {
        String[][] wishlistResponse = load("http://localhost:5000/load", user, 0);

        productContainerPanel.removeAll(); // Clear old components

        for (int i = 1; i < wishlistResponse.length; i++) {
            String[] wine = wishlistResponse[i];
            if (wine.length < 5) {
                continue;
            }

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];

            ProductBoxPanel box = new ProductBoxPanel(this);
            box.setProductTitle(title);
            box.setProductInfo(info);
            box.setProductPrice(price);
            box.setPic(photo);

            productContainerPanel.add(box);
        }

        productContainerPanel.revalidate();
        productContainerPanel.repaint();
    }

    public void loadCart() {    
        String [][] cartResponse = load("http://localhost:5000/load", user ,4);

        cartContainerPanel.removeAll();
        float cartSum = 0;
        for (int i = 1; i < cartResponse.length; i++) {

            String[] wine = cartResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            String count = wine[5];
            CartBoxPanel cartBox = new CartBoxPanel(this);
            cartBox.setCartTitle(title);
            cartBox.setCartInfo(info);
            cartBox.setCartPrice(price);
            float price1 = Float.parseFloat(price);
            float count1 = Float.parseFloat(count);
            float sum = price1 * count1;
            cartSum += sum;
            String stringSum = "" + sum;
            cartBox.setCartSumPrice(stringSum);
            cartBox.setCartNumbDisplay(count);  
            cartBox.setPic(photo);

            cartContainerPanel.add(cartBox);
        }
        setSubtotalPrice(String.format("%.2f", cartSum));
        setTotalPrice(String.format("%.2f", cartSum+3));        

        cartContainerPanel.revalidate();
        cartContainerPanel.repaint();
    }
    
    
    public static String submitOrder(String urlString, String firstname, String lastname, String phone, String email, String address, String pcode, String city, String username) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = String.format(
                "{\"firstname\": \"%s\", \"lastname\": \"%s\", \"phone\": \"%s\", \"email\": \"%s\", " +
                "\"address\": \"%s\", \"pcode\": \"%s\", \"city\": \"%s\", \"username\": \"%s\"}",
                firstname, lastname, phone, email, address, pcode, city, username
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            StringBuilder response = new StringBuilder();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }

            if (responseCode != 200) {
                return "Error: Server responded with " + responseCode + " → " + response;
            }

            return response.toString();

        } catch (IOException e) {
            return "Error: Unable to connect to the server";
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Background = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        homePanel = new javax.swing.JPanel();
        homeTitle = new javax.swing.JLabel();
        whiteBest = new javax.swing.JPanel();
        whitePhotoBest = new javax.swing.JLabel();
        whiteInfoBestPanel = new javax.swing.JPanel();
        homeWhiteTitle = new javax.swing.JLabel();
        homeWhiteInfo = new javax.swing.JLabel();
        homeWhitePrice = new javax.swing.JLabel();
        homeBuyWhite = new javax.swing.JButton();
        homeLikeWhite = new javax.swing.JButton();
        redBest = new javax.swing.JPanel();
        redPhotoBest = new javax.swing.JLabel();
        redInfoBestPanel = new javax.swing.JPanel();
        homeRedTitle = new javax.swing.JLabel();
        homeRedInfo = new javax.swing.JLabel();
        homeRedPrice = new javax.swing.JLabel();
        homeBuyRed = new javax.swing.JButton();
        homeLikeRed = new javax.swing.JButton();
        roseBest = new javax.swing.JPanel();
        rosePhotoBest = new javax.swing.JLabel();
        roseInfoBestPanel = new javax.swing.JPanel();
        homeRoseTitle = new javax.swing.JLabel();
        homeRoseInfo = new javax.swing.JLabel();
        homeRosePrice = new javax.swing.JLabel();
        homeBuyRose = new javax.swing.JButton();
        homeLikeRose = new javax.swing.JButton();
        backgroundImg = new javax.swing.JLabel();
        contactPanel = new javax.swing.JPanel();
        contactContent = new javax.swing.JPanel();
        msgTitle1 = new javax.swing.JLabel();
        msgTitle2 = new javax.swing.JLabel();
        fullnameBax = new javax.swing.JTextField();
        fullnameMsg = new javax.swing.JLabel();
        emailMsg = new javax.swing.JLabel();
        emailBox = new javax.swing.JTextField();
        subtitleMsg = new javax.swing.JLabel();
        messageBox = new javax.swing.JTextField();
        submitBtn = new javax.swing.JButton();
        contTitle = new javax.swing.JLabel();
        topImage = new javax.swing.JLabel();
        aboutPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        aboutUsTitle = new javax.swing.JLabel();
        ourPhotoAboutUs = new javax.swing.JLabel();
        aboutBackground = new javax.swing.JLabel();
        heartPanel = new javax.swing.JPanel();
        titleWishlist = new javax.swing.JLabel();
        wishlistScrollPane = new javax.swing.JScrollPane();
        productContainerPanel = new javax.swing.JPanel();
        cartPanel = new javax.swing.JPanel();
        cartScrollPane = new javax.swing.JScrollPane();
        cartContainerPanel = new javax.swing.JPanel();
        cartInfoPanel = new javax.swing.JPanel();
        infoTitle = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        surename = new javax.swing.JTextField();
        phoneNumber = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        postalCode = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        firstnameLabel = new javax.swing.JLabel();
        surenameLabel = new javax.swing.JLabel();
        firstnameLabel2 = new javax.swing.JLabel();
        firstnameLabel3 = new javax.swing.JLabel();
        firstnameLabel4 = new javax.swing.JLabel();
        firstnameLabel5 = new javax.swing.JLabel();
        firstnameLabel6 = new javax.swing.JLabel();
        pricesPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        subtotalLb = new javax.swing.JLabel();
        shippingLb = new javax.swing.JLabel();
        totalLb = new javax.swing.JLabel();
        subtotalPrice = new javax.swing.JLabel();
        shippingPrice = new javax.swing.JLabel();
        totalPrice = new javax.swing.JLabel();
        submitCartBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        accountPanel = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        phoneLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        emailInfo = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        profileInfoTitle = new javax.swing.JLabel();
        topImageProfile = new javax.swing.JLabel();
        winePanel = new javax.swing.JPanel();
        redWinePanel = new javax.swing.JPanel();
        redTextLine1 = new javax.swing.JLabel();
        redTextLine2 = new javax.swing.JLabel();
        redTextLine3 = new javax.swing.JLabel();
        whiteWinePanel = new javax.swing.JPanel();
        whiteTextLine1 = new javax.swing.JLabel();
        whiteTextLine2 = new javax.swing.JLabel();
        whiteTextLine3 = new javax.swing.JLabel();
        roseWinePanel = new javax.swing.JPanel();
        roseTextLine1 = new javax.swing.JLabel();
        roseTextLine2 = new javax.swing.JLabel();
        roseTextLine3 = new javax.swing.JLabel();
        winePageTitle = new javax.swing.JLabel();
        winePageBackground = new javax.swing.JLabel();
        redPanel = new javax.swing.JPanel();
        redTitle = new javax.swing.JLabel();
        redScrollPane = new javax.swing.JScrollPane();
        paddingPanel = new javax.swing.JPanel();
        redContainerPanel = new javax.swing.JPanel();
        redWineBackground = new javax.swing.JLabel();
        rosePanel = new javax.swing.JPanel();
        roseTitle = new javax.swing.JLabel();
        roseScrollPane = new javax.swing.JScrollPane();
        paddingPanel1 = new javax.swing.JPanel();
        roseContainerPanel = new javax.swing.JPanel();
        roseWineBackground = new javax.swing.JLabel();
        whitePanel = new javax.swing.JPanel();
        whiteTitle = new javax.swing.JLabel();
        whiteScrollPane = new javax.swing.JScrollPane();
        paddingPanel2 = new javax.swing.JPanel();
        whiteContainerPanel = new javax.swing.JPanel();
        whiteWineBackground = new javax.swing.JLabel();
        topbarPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        accountIcon = new javax.swing.JLabel();
        cartIcon = new javax.swing.JLabel();
        heartIcon = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        sidebarPanel = new javax.swing.JPanel();
        homeLogo = new javax.swing.JLabel();
        wineTitle = new javax.swing.JLabel();
        redWine = new javax.swing.JLabel();
        roseWine = new javax.swing.JLabel();
        whiteWine = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        contactTitle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        aboutTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1166, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setForeground(new java.awt.Color(115, 1, 18));
        Background.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 1170, 10));

        jSeparator5.setForeground(new java.awt.Color(115, 1, 18));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Background.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, -1, 10, 650));

        homePanel.setBackground(new java.awt.Color(153, 153, 255));
        homePanel.setLayout(null);

        homeTitle.setFont(new java.awt.Font("Serif", 3, 40)); // NOI18N
        homeTitle.setForeground(new java.awt.Color(255, 255, 255));
        homeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeTitle.setText("Our Best Sellers");
        homePanel.add(homeTitle);
        homeTitle.setBounds(340, 300, 350, 40);

        whiteBest.setBackground(new java.awt.Color(255, 255, 255));
        whiteBest.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        whitePhotoBest.setBackground(new java.awt.Color(255, 255, 255));
        whitePhotoBest.setForeground(new java.awt.Color(255, 255, 255));
        whitePhotoBest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whitePhotoBest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WH002.jpg"))); // NOI18N
        whitePhotoBest.setText(" ");
        whiteBest.add(whitePhotoBest, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 130));

        whiteInfoBestPanel.setBackground(new java.awt.Color(255, 255, 255));
        whiteInfoBestPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeWhiteTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeWhiteTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeWhiteTitle.setText("ΑΓΙΟΣ ΧΡΟΝΟΣ ΑΒΑΝΤΙΣ");
        whiteInfoBestPanel.add(homeWhiteTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 158, -1));

        homeWhiteInfo.setForeground(new java.awt.Color(51, 51, 51));
        homeWhiteInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeWhiteInfo.setText("Ήπιος 0,75L");
        whiteInfoBestPanel.add(homeWhiteInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 32, 158, -1));

        homeWhitePrice.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        homeWhitePrice.setText("20.56 €");
        whiteInfoBestPanel.add(homeWhitePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));

        homeBuyWhite.setBackground(new java.awt.Color(179, 110, 120));
        homeBuyWhite.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeBuyWhite.setText("Buy");
        homeBuyWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBuyWhiteActionPerformed(evt);
            }
        });
        whiteInfoBestPanel.add(homeBuyWhite, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 54, 58, -1));

        homeLikeWhite.setBackground(new java.awt.Color(179, 110, 120));
        homeLikeWhite.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeLikeWhite.setText("♥︎");
        homeLikeWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeLikeWhiteActionPerformed(evt);
            }
        });
        whiteInfoBestPanel.add(homeLikeWhite, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 54, 50, -1));

        whiteBest.add(whiteInfoBestPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 170, -1));

        homePanel.add(whiteBest);
        whiteBest.setBounds(430, 350, 170, 210);

        redBest.setBackground(new java.awt.Color(255, 255, 255));
        redBest.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        redPhotoBest.setBackground(new java.awt.Color(255, 255, 255));
        redPhotoBest.setForeground(new java.awt.Color(255, 255, 255));
        redPhotoBest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redPhotoBest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/RE008.jpg"))); // NOI18N
        redPhotoBest.setText(" ");
        redBest.add(redPhotoBest, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 130));

        redInfoBestPanel.setBackground(new java.awt.Color(255, 255, 255));
        redInfoBestPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeRedTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeRedTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeRedTitle.setText("ΚΤΗΜΑ ΑΛΦΑ SYRAH");
        redInfoBestPanel.add(homeRedTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 158, -1));

        homeRedInfo.setForeground(new java.awt.Color(51, 51, 51));
        homeRedInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeRedInfo.setText("Ξηρός 0,75L");
        redInfoBestPanel.add(homeRedInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 32, 158, -1));

        homeRedPrice.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        homeRedPrice.setText("19.08 €");
        redInfoBestPanel.add(homeRedPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));

        homeBuyRed.setBackground(new java.awt.Color(179, 110, 120));
        homeBuyRed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeBuyRed.setText("Buy");
        homeBuyRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBuyRedActionPerformed(evt);
            }
        });
        redInfoBestPanel.add(homeBuyRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 54, 58, -1));

        homeLikeRed.setBackground(new java.awt.Color(179, 110, 120));
        homeLikeRed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeLikeRed.setText("♥︎");
        homeLikeRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeLikeRedActionPerformed(evt);
            }
        });
        redInfoBestPanel.add(homeLikeRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 54, 50, -1));

        redBest.add(redInfoBestPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 170, -1));

        homePanel.add(redBest);
        redBest.setBounds(110, 350, 170, 210);

        roseBest.setBackground(new java.awt.Color(255, 255, 255));
        roseBest.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rosePhotoBest.setBackground(new java.awt.Color(255, 255, 255));
        rosePhotoBest.setForeground(new java.awt.Color(255, 255, 255));
        rosePhotoBest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rosePhotoBest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/RO003.jpg"))); // NOI18N
        rosePhotoBest.setText(" ");
        roseBest.add(rosePhotoBest, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, 150, 130));

        roseInfoBestPanel.setBackground(new java.awt.Color(255, 255, 255));
        roseInfoBestPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeRoseTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeRoseTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeRoseTitle.setText("MINUTI M");
        roseInfoBestPanel.add(homeRoseTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 158, -1));

        homeRoseInfo.setForeground(new java.awt.Color(51, 51, 51));
        homeRoseInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeRoseInfo.setText("Ήπιος 0,75L");
        roseInfoBestPanel.add(homeRoseInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 32, 158, -1));

        homeRosePrice.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        homeRosePrice.setText("22.42 €");
        roseInfoBestPanel.add(homeRosePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));

        homeBuyRose.setBackground(new java.awt.Color(179, 110, 120));
        homeBuyRose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeBuyRose.setText("Buy");
        homeBuyRose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBuyRoseActionPerformed(evt);
            }
        });
        roseInfoBestPanel.add(homeBuyRose, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 54, 58, -1));

        homeLikeRose.setBackground(new java.awt.Color(179, 110, 120));
        homeLikeRose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        homeLikeRose.setText("♥︎");
        homeLikeRose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeLikeRoseActionPerformed(evt);
            }
        });
        roseInfoBestPanel.add(homeLikeRose, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 54, 50, -1));

        roseBest.add(roseInfoBestPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 170, -1));

        homePanel.add(roseBest);
        roseBest.setBounds(750, 350, 170, 210);

        backgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WINE_NOT__20250521_223250_0000.png"))); // NOI18N
        backgroundImg.setText(" ");
        homePanel.add(backgroundImg);
        backgroundImg.setBounds(0, 0, 1030, 580);

        Background.add(homePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        contactPanel.setBackground(new java.awt.Color(0, 153, 153));
        contactPanel.setLayout(null);

        contactContent.setBackground(new java.awt.Color(171, 1, 26));

        msgTitle1.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        msgTitle1.setForeground(new java.awt.Color(255, 255, 255));
        msgTitle1.setText("Let's Start a Convertation");

        msgTitle2.setFont(new java.awt.Font("Noto Serif", 0, 15)); // NOI18N
        msgTitle2.setForeground(new java.awt.Color(255, 255, 255));
        msgTitle2.setText("Please note: all fields are required.");

        fullnameBax.setText(" ");

        fullnameMsg.setFont(new java.awt.Font("Noto Serif", 0, 15)); // NOI18N
        fullnameMsg.setForeground(new java.awt.Color(255, 255, 255));
        fullnameMsg.setText("Fullname:");

        emailMsg.setFont(new java.awt.Font("Noto Serif", 0, 15)); // NOI18N
        emailMsg.setForeground(new java.awt.Color(255, 255, 255));
        emailMsg.setText("Email:");

        emailBox.setText("  ");

        subtitleMsg.setFont(new java.awt.Font("Noto Serif", 0, 15)); // NOI18N
        subtitleMsg.setForeground(new java.awt.Color(255, 255, 255));
        subtitleMsg.setText("Write the reason for contacting:");

        messageBox.setText("  ");

        submitBtn.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(235, 4, 36));
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contactContentLayout = new javax.swing.GroupLayout(contactContent);
        contactContent.setLayout(contactContentLayout);
        contactContentLayout.setHorizontalGroup(
            contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactContentLayout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addGroup(contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgTitle2)
                    .addComponent(msgTitle1))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactContentLayout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addGroup(contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailBox, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullnameBax, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailMsg)
                    .addComponent(fullnameMsg))
                .addGap(85, 85, 85)
                .addGroup(contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageBox, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subtitleMsg))
                .addGap(172, 172, 172))
            .addGroup(contactContentLayout.createSequentialGroup()
                .addGap(459, 459, 459)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contactContentLayout.setVerticalGroup(
            contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactContentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(msgTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgTitle2)
                .addGap(17, 17, 17)
                .addGroup(contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fullnameMsg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subtitleMsg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contactContentLayout.createSequentialGroup()
                        .addComponent(fullnameBax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(emailMsg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(messageBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        contactPanel.add(contactContent);
        contactContent.setBounds(0, 290, 1030, 290);

        contTitle.setFont(new java.awt.Font("Noto Serif", 1, 48)); // NOI18N
        contTitle.setForeground(new java.awt.Color(255, 255, 255));
        contTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contTitle.setText("Contact Us");
        contactPanel.add(contTitle);
        contTitle.setBounds(340, 190, 340, 110);

        topImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/pexels-adrian-odogwu-2078202-3820514(1)(1).jpg"))); // NOI18N
        topImage.setText(" ");
        contactPanel.add(topImage);
        topImage.setBounds(0, 0, 1030, 290);

        Background.add(contactPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        aboutPanel.setBackground(new java.awt.Color(171, 1, 26));
        aboutPanel.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("που ενώνουμε την αγάπη μας για την τεχνολογία με την κουλτούρα");
        aboutPanel.add(jLabel1);
        jLabel1.setBounds(280, 290, 450, 16);

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ψηφιακή εποχή. Θέλουμε να κάνουμε το καλό κρασί πιο προσβάσιμο,");
        aboutPanel.add(jLabel2);
        jLabel2.setBounds(280, 350, 450, 16);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Είμαστε μια ομάδα τεσσάρων φοιτητών Πληροφορικής από το ΠΑΔΑ,");
        aboutPanel.add(jLabel3);
        jLabel3.setBounds(280, 270, 450, 16);

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("απλή και απολαυστική εμπειρία χρήστη. Είμαστε στο ξεκίνημα – και");
        aboutPanel.add(jLabel4);
        jLabel4.setBounds(280, 390, 450, 16);

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("από το πανεπιστήμιο, με στόχο να φέρει την αγορά κρασιού στη νέα");
        aboutPanel.add(jLabel5);
        jLabel5.setBounds(280, 330, 450, 16);

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("του κρασιού. Η εφαρμογή μας γεννήθηκε ως μια startup ιδέα μέσα");
        aboutPanel.add(jLabel6);
        jLabel6.setBounds(280, 310, 450, 16);

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("προωθώντας μικρούς παραγωγούς και προσφέροντας μια μοντέρνα,");
        aboutPanel.add(jLabel7);
        jLabel7.setBounds(280, 370, 450, 16);

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 3, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("το καλύτερο έρχεται!");
        aboutPanel.add(jLabel8);
        jLabel8.setBounds(280, 410, 450, 16);

        aboutUsTitle.setFont(new java.awt.Font("Noto Serif", 1, 48)); // NOI18N
        aboutUsTitle.setForeground(new java.awt.Color(255, 255, 255));
        aboutUsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutUsTitle.setText("About Us");
        aboutPanel.add(aboutUsTitle);
        aboutUsTitle.setBounds(340, 150, 340, 110);

        ourPhotoAboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/14547757_rm218batch6-mynt-26.jpg"))); // NOI18N
        ourPhotoAboutUs.setText(" ");
        aboutPanel.add(ourPhotoAboutUs);
        ourPhotoAboutUs.setBounds(280, 230, 450, 230);

        aboutBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/pexels-adrian-odogwu-2078202-3820514(1)(1).jpg"))); // NOI18N
        aboutBackground.setText(" ");
        aboutBackground.setPreferredSize(new java.awt.Dimension(1030, 290));
        aboutPanel.add(aboutBackground);
        aboutBackground.setBounds(0, 0, 1038, 290);

        Background.add(aboutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        heartPanel.setBackground(new java.awt.Color(91, 0, 13));

        titleWishlist.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        titleWishlist.setForeground(new java.awt.Color(255, 255, 255));
        titleWishlist.setText("Wishlist");

        String [][] wishlistResponse = load("http://localhost:5000/load", user ,0);
        for (int i = 1; i < wishlistResponse.length; i++) {
            String[] wine = wishlistResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            ProductBoxPanel box = new ProductBoxPanel(this);
            box.setProductTitle(title);
            box.setProductInfo(info);
            box.setProductPrice(price);
            box.setPic(photo);

            productContainerPanel.add(box);
        }
        productContainerPanel.revalidate();
        productContainerPanel.repaint();
        productContainerPanel.setLayout(new javax.swing.BoxLayout(productContainerPanel, javax.swing.BoxLayout.Y_AXIS));
        wishlistScrollPane.setViewportView(productContainerPanel);

        javax.swing.GroupLayout heartPanelLayout = new javax.swing.GroupLayout(heartPanel);
        heartPanel.setLayout(heartPanelLayout);
        heartPanelLayout.setHorizontalGroup(
            heartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heartPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(heartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wishlistScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        heartPanelLayout.setVerticalGroup(
            heartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heartPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(titleWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wishlistScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        Background.add(heartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        float cartSum = 0;
        cartPanel.setBackground(new java.awt.Color(255, 255, 255));

        cartScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        cartScrollPane.setForeground(new java.awt.Color(255, 255, 255));
        cartScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        String [][] cartResponse = load("http://localhost:5000/load", user ,4);
        for (int i = 1; i < cartResponse.length; i++) {

            String[] wine = cartResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            String count = wine[5];
            CartBoxPanel cartBox = new CartBoxPanel(this);
            cartBox.setCartTitle(title);
            cartBox.setCartInfo(info);
            cartBox.setCartPrice(price);
            float price1 = Float.parseFloat(price);
            float count1 = Float.parseFloat(count);
            float sum = price1 * count1;
            cartSum += sum;
            String stringSum = "" + sum;
            cartBox.setCartSumPrice(stringSum);
            cartBox.setCartNumbDisplay(count);  
            cartBox.setPic(photo);
            cartContainerPanel.add(cartBox);
        }
        cartContainerPanel.revalidate();
        cartContainerPanel.repaint();
        cartContainerPanel.setLayout(new javax.swing.BoxLayout(cartContainerPanel, javax.swing.BoxLayout.Y_AXIS));
        cartScrollPane.setViewportView(cartContainerPanel);

        cartInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        cartInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        infoTitle.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        infoTitle.setText("Your information");

        firstname.setForeground(new java.awt.Color(102, 102, 102));
        firstname.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        surename.setForeground(new java.awt.Color(102, 102, 102));
        surename.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        phoneNumber.setForeground(new java.awt.Color(102, 102, 102));
        phoneNumber.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        address.setForeground(new java.awt.Color(102, 102, 102));
        address.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        postalCode.setForeground(new java.awt.Color(102, 102, 102));
        postalCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        city.setForeground(new java.awt.Color(102, 102, 102));
        city.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        firstnameLabel.setText("Firstname");

        surenameLabel.setText("Surename");

        firstnameLabel2.setText("Phone");

        firstnameLabel3.setText("Email");

        firstnameLabel4.setText("Address");

        firstnameLabel5.setText("P.Code");

        firstnameLabel6.setText("City");

        javax.swing.GroupLayout cartInfoPanelLayout = new javax.swing.GroupLayout(cartInfoPanel);
        cartInfoPanel.setLayout(cartInfoPanelLayout);
        cartInfoPanelLayout.setHorizontalGroup(
            cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoTitle)
                    .addGroup(cartInfoPanelLayout.createSequentialGroup()
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstnameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surename, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(surenameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstnameLabel2)
                            .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cartInfoPanelLayout.createSequentialGroup()
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(firstnameLabel3))
                            .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(postalCode)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartInfoPanelLayout.createSequentialGroup()
                                    .addComponent(firstnameLabel5)
                                    .addGap(102, 102, 102))))
                        .addGap(18, 18, 18)
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstnameLabel4)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstnameLabel6))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        cartInfoPanelLayout.setVerticalGroup(
            cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartInfoPanelLayout.createSequentialGroup()
                        .addComponent(infoTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cartInfoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cartInfoPanelLayout.createSequentialGroup()
                                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(surenameLabel)
                                    .addComponent(firstnameLabel2))
                                .addGap(1, 1, 1)
                                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(surename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cartInfoPanelLayout.createSequentialGroup()
                                .addComponent(firstnameLabel)
                                .addGap(1, 1, 1)
                                .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstnameLabel3)
                    .addComponent(firstnameLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstnameLabel5)
                    .addComponent(firstnameLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pricesPanel.setBackground(new java.awt.Color(255, 255, 255));
        pricesPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        title.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        title.setText("Cart Summary");

        subtotalLb.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        subtotalLb.setText("Subtotal:");

        shippingLb.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        shippingLb.setText("Shipping:");

        totalLb.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        totalLb.setText("Total:");

        subtotalPrice.setFont(new java.awt.Font("Liberation Sans", 3, 19)); // NOI18N
        subtotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subtotalPrice.setText("36 €");
        setSubtotalPrice(String.format("%.2f", cartSum));

        shippingPrice.setFont(new java.awt.Font("Liberation Sans", 3, 19)); // NOI18N
        shippingPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        shippingPrice.setText("3 €");

        totalPrice.setFont(new java.awt.Font("Liberation Sans", 3, 19)); // NOI18N
        totalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalPrice.setText("39 €");
        setTotalPrice(String.format("%.2f", cartSum+3));

        submitCartBtn.setBackground(new java.awt.Color(179, 110, 120));
        submitCartBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        submitCartBtn.setText("SUBMIT");
        submitCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCartBtnActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(179, 110, 120));
        refreshBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/refresh(1).png"))); // NOI18N
        refreshBtn.setText(" ");
        refreshBtn.setActionCommand(" ");
        refreshBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pricesPanelLayout = new javax.swing.GroupLayout(pricesPanel);
        pricesPanel.setLayout(pricesPanelLayout);
        pricesPanelLayout.setHorizontalGroup(
            pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pricesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pricesPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(submitCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pricesPanelLayout.createSequentialGroup()
                        .addComponent(subtotalLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(subtotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pricesPanelLayout.createSequentialGroup()
                        .addComponent(shippingLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(shippingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pricesPanelLayout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pricesPanelLayout.createSequentialGroup()
                        .addComponent(totalLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        pricesPanelLayout.setVerticalGroup(
            pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pricesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalLb)
                    .addComponent(subtotalPrice))
                .addGap(25, 25, 25)
                .addGroup(pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shippingLb)
                    .addComponent(shippingPrice))
                .addGap(26, 26, 26)
                .addGroup(pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLb)
                    .addComponent(totalPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pricesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartScrollPane)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cartInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pricesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addComponent(cartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cartInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pricesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Background.add(cartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        accountPanel.setBackground(new java.awt.Color(40, 0, 6));
        accountPanel.setLayout(null);

        userIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/user(1).png"))); // NOI18N
        userIcon.setText("  ");
        accountPanel.add(userIcon);
        userIcon.setBounds(110, 130, 110, 102);

        username.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Username");
        accountPanel.add(username);
        username.setBounds(220, 160, 270, 50);

        phoneLabel.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        phoneLabel.setText("Τηλέφωνο:");

        nameLabel.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        nameLabel.setText("Ονοματεπώνυμο:");

        emailLabel.setFont(new java.awt.Font("Noto Serif", 1, 18)); // NOI18N
        emailLabel.setText("Email:");

        fullname.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        fullname.setText("Όναμα Επώνημο");

        emailInfo.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        emailInfo.setText("exampleemail@email.com");

        phone.setFont(new java.awt.Font("Noto Serif", 0, 18)); // NOI18N
        phone.setText("6901234567");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailInfo)
                            .addComponent(fullname)))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(phone)))
                .addContainerGap(383, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullname))
                .addGap(42, 42, 42)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailInfo))
                .addGap(42, 42, 42)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phone))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        accountPanel.add(infoPanel);
        infoPanel.setBounds(110, 260, 810, 290);

        profileInfoTitle.setFont(new java.awt.Font("Noto Serif", 1, 24)); // NOI18N
        profileInfoTitle.setForeground(new java.awt.Color(255, 255, 255));
        profileInfoTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profileInfoTitle.setText("Your Info");
        accountPanel.add(profileInfoTitle);
        profileInfoTitle.setBounds(440, 220, 130, 40);

        topImageProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/pexels-adrian-odogwu-2078202-3820514(1)(1).jpg"))); // NOI18N
        topImageProfile.setText("  ");
        accountPanel.add(topImageProfile);
        topImageProfile.setBounds(0, 0, 1030, 290);

        Background.add(accountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        winePanel.setBackground(new java.awt.Color(0, 204, 51));
        winePanel.setLayout(null);

        redWinePanel.setBackground(new java.awt.Color(51, 51, 51));
        redWinePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                redWinePanelMouseClicked(evt);
            }
        });
        redWinePanel.setLayout(null);

        redTextLine1.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        redTextLine1.setForeground(new java.awt.Color(204, 0, 0));
        redTextLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redTextLine1.setText("Check our ");
        redTextLine1.setToolTipText("");
        redTextLine1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        redTextLine1.setName(""); // NOI18N
        redWinePanel.add(redTextLine1);
        redTextLine1.setBounds(0, -10, 200, 70);

        redTextLine2.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        redTextLine2.setForeground(new java.awt.Color(204, 0, 0));
        redTextLine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redTextLine2.setText("list of");
        redTextLine2.setToolTipText("");
        redTextLine2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        redTextLine2.setName(""); // NOI18N
        redWinePanel.add(redTextLine2);
        redTextLine2.setBounds(0, 30, 200, 70);

        redTextLine3.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        redTextLine3.setForeground(new java.awt.Color(204, 0, 0));
        redTextLine3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redTextLine3.setText("red wines");
        redTextLine3.setToolTipText("");
        redTextLine3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        redTextLine3.setName(""); // NOI18N
        redWinePanel.add(redTextLine3);
        redTextLine3.setBounds(0, 70, 200, 70);

        winePanel.add(redWinePanel);
        redWinePanel.setBounds(420, 300, 200, 210);

        whiteWinePanel.setBackground(new java.awt.Color(51, 51, 51));
        whiteWinePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                whiteWinePanelMouseClicked(evt);
            }
        });
        whiteWinePanel.setLayout(null);

        whiteTextLine1.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        whiteTextLine1.setForeground(new java.awt.Color(255, 255, 255));
        whiteTextLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteTextLine1.setText("Check our ");
        whiteTextLine1.setToolTipText("");
        whiteTextLine1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        whiteTextLine1.setName(""); // NOI18N
        whiteWinePanel.add(whiteTextLine1);
        whiteTextLine1.setBounds(0, -10, 200, 70);

        whiteTextLine2.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        whiteTextLine2.setForeground(new java.awt.Color(255, 255, 255));
        whiteTextLine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteTextLine2.setText("list of");
        whiteTextLine2.setToolTipText("");
        whiteTextLine2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        whiteTextLine2.setName(""); // NOI18N
        whiteWinePanel.add(whiteTextLine2);
        whiteTextLine2.setBounds(0, 30, 200, 70);

        whiteTextLine3.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        whiteTextLine3.setForeground(new java.awt.Color(255, 255, 255));
        whiteTextLine3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteTextLine3.setText("white wines");
        whiteTextLine3.setToolTipText("");
        whiteTextLine3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        whiteTextLine3.setName(""); // NOI18N
        whiteWinePanel.add(whiteTextLine3);
        whiteTextLine3.setBounds(0, 70, 200, 70);

        winePanel.add(whiteWinePanel);
        whiteWinePanel.setBounds(80, 300, 200, 210);

        roseWinePanel.setBackground(new java.awt.Color(51, 51, 51));
        roseWinePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roseWinePanelMouseClicked(evt);
            }
        });
        roseWinePanel.setLayout(null);

        roseTextLine1.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        roseTextLine1.setForeground(new java.awt.Color(255, 153, 153));
        roseTextLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roseTextLine1.setText("Check our ");
        roseTextLine1.setToolTipText("");
        roseTextLine1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roseTextLine1.setName(""); // NOI18N
        roseWinePanel.add(roseTextLine1);
        roseTextLine1.setBounds(0, -10, 200, 70);

        roseTextLine2.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        roseTextLine2.setForeground(new java.awt.Color(255, 153, 153));
        roseTextLine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roseTextLine2.setText("list of");
        roseTextLine2.setToolTipText("");
        roseTextLine2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roseTextLine2.setName(""); // NOI18N
        roseWinePanel.add(roseTextLine2);
        roseTextLine2.setBounds(0, 30, 200, 70);

        roseTextLine3.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        roseTextLine3.setForeground(new java.awt.Color(255, 153, 153));
        roseTextLine3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roseTextLine3.setText("rose wines");
        roseTextLine3.setToolTipText("");
        roseTextLine3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roseTextLine3.setName(""); // NOI18N
        roseWinePanel.add(roseTextLine3);
        roseTextLine3.setBounds(0, 70, 200, 70);

        winePanel.add(roseWinePanel);
        roseWinePanel.setBounds(760, 300, 200, 210);

        winePageTitle.setFont(new java.awt.Font("Serif", 3, 48)); // NOI18N
        winePageTitle.setForeground(new java.awt.Color(255, 255, 255));
        winePageTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winePageTitle.setText("Our Wines");
        winePanel.add(winePageTitle);
        winePageTitle.setBounds(380, 50, 290, 40);

        winePageBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WINE_NOT__20250521_223250_0000.png"))); // NOI18N
        winePageBackground.setText(" ");
        winePanel.add(winePageBackground);
        winePageBackground.setBounds(0, 0, 1030, 580);

        Background.add(winePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        redPanel.setBackground(new java.awt.Color(255, 255, 255));
        redPanel.setLayout(null);

        redTitle.setFont(new java.awt.Font("Serif", 1, 40)); // NOI18N
        redTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redTitle.setText("Red Wines");
        redPanel.add(redTitle);
        redTitle.setBounds(400, 10, 250, 50);

        redScrollPane.setBackground(new java.awt.Color(179, 110, 120));
        redScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        paddingPanel.setBackground(new java.awt.Color(255, 255, 255));
        paddingPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 0, 0));
        paddingPanel.setLayout(new java.awt.BorderLayout());

        redContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        redContainerPanel.setAutoscrolls(true);
        redContainerPanel.setMaximumSize(new java.awt.Dimension(180, 180));

        String[][] redResponse = load("http://localhost:5000/load", user ,3);
        redContainerPanel.setLayout(new java.awt.GridLayout(0, 4, 20, 20));

        for (int i = 1; i < redResponse.length; i++) {

            String[] wine = redResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            ProductSquerePanel box = new ProductSquerePanel(this);
            box.setTitleSquere(title);
            box.setinfoSquere(info);
            box.setPriceSquere(price);
            box.setPic(photo);

            redContainerPanel.add(box);
        }
        redContainerPanel.revalidate();
        redContainerPanel.repaint();
        redContainerPanel.setLayout(new java.awt.GridLayout(0, 4));
        paddingPanel.add(redContainerPanel, java.awt.BorderLayout.CENTER);

        redScrollPane.setViewportView(paddingPanel);

        redPanel.add(redScrollPane);
        redScrollPane.setBounds(40, 60, 940, 510);

        redWineBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/freepik__make-the-background-abstract-with-soft-pastel-colo__79196.png"))); // NOI18N
        redWineBackground.setText(" ");
        redPanel.add(redWineBackground);
        redWineBackground.setBounds(-6, -2, 1040, 580);

        Background.add(redPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        rosePanel.setBackground(new java.awt.Color(255, 255, 255));
        rosePanel.setLayout(null);

        roseTitle.setFont(new java.awt.Font("Serif", 1, 40)); // NOI18N
        roseTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roseTitle.setText("Rose Wines");
        rosePanel.add(roseTitle);
        roseTitle.setBounds(0, 10, 1030, 50);

        roseScrollPane.setBackground(new java.awt.Color(179, 110, 120));
        roseScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        paddingPanel1.setBackground(new java.awt.Color(255, 255, 255));
        paddingPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 0, 0));
        paddingPanel1.setLayout(new java.awt.BorderLayout());

        roseContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        roseContainerPanel.setAutoscrolls(true);
        roseContainerPanel.setMaximumSize(new java.awt.Dimension(180, 180));
        String[][] roseResponse = load("http://localhost:5000/load", user ,1);
        roseContainerPanel.setLayout(new java.awt.GridLayout(0, 4, 20, 20));

        for (int i = 1; i < roseResponse.length; i++) {

            String[] wine = roseResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            ProductSquerePanel box = new ProductSquerePanel(this);
            box.setTitleSquere(title);
            box.setinfoSquere(info);
            box.setPriceSquere(price);
            box.setPic(photo);

            roseContainerPanel.add(box);
        }
        roseContainerPanel.revalidate();
        roseContainerPanel.repaint();
        roseContainerPanel.setLayout(new java.awt.GridLayout(0, 4));
        paddingPanel1.add(roseContainerPanel, java.awt.BorderLayout.CENTER);

        roseScrollPane.setViewportView(paddingPanel1);

        rosePanel.add(roseScrollPane);
        roseScrollPane.setBounds(40, 60, 940, 510);

        roseWineBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/freepik__make-the-background-abstract-with-soft-pastel-colo__79196.png"))); // NOI18N
        roseWineBackground.setText(" ");
        rosePanel.add(roseWineBackground);
        roseWineBackground.setBounds(-6, -2, 1040, 580);

        Background.add(rosePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        whitePanel.setBackground(new java.awt.Color(255, 255, 255));
        whitePanel.setLayout(null);

        whiteTitle.setFont(new java.awt.Font("Serif", 1, 40)); // NOI18N
        whiteTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteTitle.setText("White Wines");
        whitePanel.add(whiteTitle);
        whiteTitle.setBounds(0, 10, 1030, 50);

        whiteScrollPane.setBackground(new java.awt.Color(179, 110, 120));
        whiteScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        paddingPanel2.setBackground(new java.awt.Color(255, 255, 255));
        paddingPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 0, 0));
        paddingPanel2.setLayout(new java.awt.BorderLayout());

        whiteContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        whiteContainerPanel.setAutoscrolls(true);
        whiteContainerPanel.setMaximumSize(new java.awt.Dimension(180, 180));
        whiteContainerPanel.setLayout(new java.awt.GridLayout(0, 4, 20, 20));
        String[][] whiteResponse = load("http://localhost:5000/load", user ,2);
        for (int i = 1; i < whiteResponse.length; i++) {

            String[] wine = whiteResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            ProductSquerePanel box = new ProductSquerePanel(this);
            box.setTitleSquere(title);
            box.setinfoSquere(info);
            box.setPriceSquere(price);
            box.setPic(photo);
            whiteContainerPanel.add(box);
        }
        whiteContainerPanel.revalidate();
        whiteContainerPanel.repaint();
        whiteContainerPanel.setLayout(new java.awt.GridLayout(0, 4));
        paddingPanel2.add(whiteContainerPanel, java.awt.BorderLayout.CENTER);

        whiteScrollPane.setViewportView(paddingPanel2);

        whitePanel.add(whiteScrollPane);
        whiteScrollPane.setBounds(40, 60, 940, 510);

        whiteWineBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/freepik__make-the-background-abstract-with-soft-pastel-colo__79196.png"))); // NOI18N
        whiteWineBackground.setText(" ");
        whitePanel.add(whiteWineBackground);
        whiteWineBackground.setBounds(-6, -2, 1040, 580);

        Background.add(whitePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 1030, 580));

        topbarPanel.setBackground(new java.awt.Color(179, 110, 120));

        jSeparator2.setForeground(new java.awt.Color(115, 1, 18));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setForeground(new java.awt.Color(115, 1, 18));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        accountIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accountIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/account.png"))); // NOI18N
        accountIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountIconMouseClicked(evt);
            }
        });

        cartIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/grocery-store.png"))); // NOI18N
        cartIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartIconMouseClicked(evt);
            }
        });

        heartIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        heartIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/heart.png"))); // NOI18N
        heartIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                heartIconMouseClicked(evt);
            }
        });

        searchField.setBackground(new java.awt.Color(254, 199, 207));
        searchField.setForeground(new java.awt.Color(255, 255, 255));
        searchField.setText("Search for the wines you want...");

        searchButton.setBackground(new java.awt.Color(252, 129, 147));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");

        jSeparator6.setForeground(new java.awt.Color(115, 1, 18));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout topbarPanelLayout = new javax.swing.GroupLayout(topbarPanel);
        topbarPanel.setLayout(topbarPanelLayout);
        topbarPanelLayout.setHorizontalGroup(
            topbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topbarPanelLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addGap(118, 118, 118)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(heartIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );
        topbarPanelLayout.setVerticalGroup(
            topbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(topbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addComponent(cartIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addGap(38, 38, 38))
            .addGroup(topbarPanelLayout.createSequentialGroup()
                .addGroup(topbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(topbarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(heartIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topbarPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(topbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(accountIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Background.add(topbarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 1030, 70));

        sidebarPanel.setBackground(new java.awt.Color(179, 110, 120));

        homeLogo.setBackground(new java.awt.Color(179, 110, 120));
        homeLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/home (3).png"))); // NOI18N
        homeLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeLogoMouseClicked(evt);
            }
        });

        wineTitle.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        wineTitle.setForeground(new java.awt.Color(255, 255, 255));
        wineTitle.setText("Wines");
        wineTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wineTitleMouseClicked(evt);
            }
        });

        redWine.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        redWine.setForeground(new java.awt.Color(255, 255, 255));
        redWine.setText("▸Red");
        redWine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                redWineMouseClicked(evt);
            }
        });

        roseWine.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        roseWine.setForeground(new java.awt.Color(255, 255, 255));
        roseWine.setText("▸Rose");
        roseWine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roseWineMouseClicked(evt);
            }
        });

        whiteWine.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        whiteWine.setForeground(new java.awt.Color(255, 255, 255));
        whiteWine.setText("▸White");
        whiteWine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                whiteWineMouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(115, 1, 18));

        contactTitle.setFont(new java.awt.Font("Serif", 1, 28)); // NOI18N
        contactTitle.setForeground(new java.awt.Color(255, 255, 255));
        contactTitle.setText("Contact");
        contactTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        contactTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactTitleMouseClicked(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(115, 1, 18));

        aboutTitle.setFont(new java.awt.Font("Serif", 1, 27)); // NOI18N
        aboutTitle.setForeground(new java.awt.Color(255, 255, 255));
        aboutTitle.setText("About Us");
        aboutTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        aboutTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutTitleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(homeLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sidebarPanelLayout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(redWine)
                            .addGap(22, 22, 22))
                        .addGroup(sidebarPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(whiteWine, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roseWine, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(contactTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wineTitle))
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(aboutTitle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(homeLogo)
                .addGap(24, 24, 24)
                .addComponent(wineTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redWine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roseWine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(whiteWine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contactTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        Background.add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contactTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactTitleMouseClicked
        hideAllPanels();
        contactPanel.setVisible(true);
    }//GEN-LAST:event_contactTitleMouseClicked

    private void aboutTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutTitleMouseClicked
        hideAllPanels();
        aboutPanel.setVisible(true);
    }//GEN-LAST:event_aboutTitleMouseClicked

    private void whiteWineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whiteWineMouseClicked
        hideAllPanels();
        whitePanel.setVisible(true);
    }//GEN-LAST:event_whiteWineMouseClicked

    private void roseWineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roseWineMouseClicked
        hideAllPanels();
        rosePanel.setVisible(true);
    }//GEN-LAST:event_roseWineMouseClicked

    private void redWineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redWineMouseClicked
        hideAllPanels();
        redPanel.setVisible(true);
    }//GEN-LAST:event_redWineMouseClicked

    private void wineTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wineTitleMouseClicked
        hideAllPanels();
        winePanel.setVisible(true);
    }//GEN-LAST:event_wineTitleMouseClicked

    private void homeLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLogoMouseClicked
        hideAllPanels();
        homePanel.setVisible(true);
    }//GEN-LAST:event_homeLogoMouseClicked

    private void heartIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_heartIconMouseClicked
        hideAllPanels();
        heartPanel.setVisible(true);
        HomePage.homepage.loadWishlist();
    }//GEN-LAST:event_heartIconMouseClicked

    private void cartIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseClicked
        hideAllPanels();
        cartPanel.setVisible(true);
        HomePage.homepage.loadCart();
    }//GEN-LAST:event_cartIconMouseClicked

    private void accountIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountIconMouseClicked
        hideAllPanels();
        accountPanel.setVisible(true);
    }//GEN-LAST:event_accountIconMouseClicked

    private void homeLikeRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeLikeRedActionPerformed
        String title1 = getHomeRedTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 2, 1);
    }//GEN-LAST:event_homeLikeRedActionPerformed

    private void homeLikeWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeLikeWhiteActionPerformed
        String title1 = getHomeWhiteTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 2, 1);
    }//GEN-LAST:event_homeLikeWhiteActionPerformed

    private void homeLikeRoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeLikeRoseActionPerformed
        String title1 = getHomeRoseTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 2, 1);
    }//GEN-LAST:event_homeLikeRoseActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
            String messagesArray = "Ευχαριστουμε για το μηνυμα σας! Θα επικοινωνισουμε μαζι σας συντομα.";
            JOptionPane.showMessageDialog(
                this,
                messagesArray,
                "ΜΗΝΥΜΑ ΕΣΤΑΛΕΙ",
                JOptionPane.INFORMATION_MESSAGE
            );
            fullnameBax.setText("");
            emailBox.setText("");
            messageBox.setText("");
    }//GEN-LAST:event_submitBtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        String[][] accountResponse = load("http://localhost:5000/load", user ,5);

        if (accountResponse == null) {
            return;
        }

        if (accountResponse.length <= 1 || accountResponse[1].length < 4) {
            System.err.println("accountResponse has insufficient data!");
            return;
        }

        setUsername(accountResponse[1][0]);            
        setFullname(accountResponse[1][1]);    
        setEmailInfo(accountResponse[1][2]);
        setPhone(accountResponse[1][3]);
    }//GEN-LAST:event_formWindowOpened

    private void homeBuyRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBuyRedActionPerformed
        String title1 = getHomeRedTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 1, 1);
    }//GEN-LAST:event_homeBuyRedActionPerformed

    private void homeBuyWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBuyWhiteActionPerformed
        String title1 = getHomeWhiteTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 1, 1);
    }//GEN-LAST:event_homeBuyWhiteActionPerformed

    private void homeBuyRoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBuyRoseActionPerformed
        String title1 = getHomeRoseTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", user, title1, 1, 1);
    }//GEN-LAST:event_homeBuyRoseActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        String [][] cartResponse = load("http://localhost:5000/load", user ,4);
        float cartSum = 0;
        cartContainerPanel.removeAll(); 
        for (int i = 1; i < cartResponse.length; i++) {

            String[] wine = cartResponse[i];

            String title = wine[0];
            String info = wine[1];
            String price = wine[2];
            String photo = wine[3];
            String stock = wine[4];
            String count = wine[5];
            CartBoxPanel cartBox = new CartBoxPanel(this);
            cartBox.setCartTitle(title);
            cartBox.setCartInfo(info);
            cartBox.setCartPrice(price);
            float price1 = Float.parseFloat(price);
            float count1 = Float.parseFloat(count);
            float sum = price1 * count1;
            cartSum += sum;
            String stringSum = "" + sum;
            cartBox.setCartSumPrice(stringSum);
            cartBox.setCartNumbDisplay(count);  
            cartBox.setPic(photo);

            cartContainerPanel.add(cartBox);
        }
        cartContainerPanel.revalidate();
        cartContainerPanel.repaint();
        setSubtotalPrice(String.format("%.2f", cartSum));
        setTotalPrice(String.format("%.2f", cartSum+3));        
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void submitCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCartBtnActionPerformed
        String firstnam = firstname.getText();
        String surenam = surename.getText();
        String phone = phoneNumber.getText();
        String emaill = email.getText();
        String addr = address.getText();
        String postCode = postalCode.getText();
        String place = city.getText();
        
        String user_name = getUser() ;
        
        String response = submitOrder("http://127.0.0.1:5000/order", firstnam, surenam, phone, emaill, addr, postCode, place, user_name);

        // Show success/failure
        if (response.contains("Success")) {
            String messagesArray = "Ευχαριστούμε για τη παραγγελία σας! Θα είναι κοντά σας σύντομα.";
            JOptionPane.showMessageDialog(
                this,
                messagesArray,
                "Παραγγελία Ελήφθη",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Αποτυχία υποβολής παραγγελίας: " + response,
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
        
        
    }//GEN-LAST:event_submitCartBtnActionPerformed

    private void whiteWinePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whiteWinePanelMouseClicked
        hideAllPanels();
        whitePanel.setVisible(true);
    }//GEN-LAST:event_whiteWinePanelMouseClicked

    private void redWinePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redWinePanelMouseClicked
        hideAllPanels();
        redPanel.setVisible(true);
    }//GEN-LAST:event_redWinePanelMouseClicked

    private void roseWinePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roseWinePanelMouseClicked
        hideAllPanels();
        rosePanel.setVisible(true);
    }//GEN-LAST:event_roseWinePanelMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Background;
    private javax.swing.JLabel aboutBackground;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JLabel aboutTitle;
    private javax.swing.JLabel aboutUsTitle;
    private javax.swing.JLabel accountIcon;
    private javax.swing.JPanel accountPanel;
    private javax.swing.JTextField address;
    private javax.swing.JLabel backgroundImg;
    private javax.swing.JPanel cartContainerPanel;
    private javax.swing.JLabel cartIcon;
    private javax.swing.JPanel cartInfoPanel;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JScrollPane cartScrollPane;
    private javax.swing.JTextField city;
    private javax.swing.JLabel contTitle;
    private javax.swing.JPanel contactContent;
    private javax.swing.JPanel contactPanel;
    private javax.swing.JLabel contactTitle;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emailBox;
    private javax.swing.JLabel emailInfo;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailMsg;
    private javax.swing.JTextField firstname;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JLabel firstnameLabel2;
    private javax.swing.JLabel firstnameLabel3;
    private javax.swing.JLabel firstnameLabel4;
    private javax.swing.JLabel firstnameLabel5;
    private javax.swing.JLabel firstnameLabel6;
    private javax.swing.JLabel fullname;
    private javax.swing.JTextField fullnameBax;
    private javax.swing.JLabel fullnameMsg;
    private javax.swing.JLabel heartIcon;
    private javax.swing.JPanel heartPanel;
    private javax.swing.JButton homeBuyRed;
    private javax.swing.JButton homeBuyRose;
    private javax.swing.JButton homeBuyWhite;
    private javax.swing.JButton homeLikeRed;
    private javax.swing.JButton homeLikeRose;
    private javax.swing.JButton homeLikeWhite;
    private javax.swing.JLabel homeLogo;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel homeRedInfo;
    private javax.swing.JLabel homeRedPrice;
    private javax.swing.JLabel homeRedTitle;
    private javax.swing.JLabel homeRoseInfo;
    private javax.swing.JLabel homeRosePrice;
    private javax.swing.JLabel homeRoseTitle;
    private javax.swing.JLabel homeTitle;
    private javax.swing.JLabel homeWhiteInfo;
    private javax.swing.JLabel homeWhitePrice;
    private javax.swing.JLabel homeWhiteTitle;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel infoTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField messageBox;
    private javax.swing.JLabel msgTitle1;
    private javax.swing.JLabel msgTitle2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel ourPhotoAboutUs;
    private javax.swing.JPanel paddingPanel;
    private javax.swing.JPanel paddingPanel1;
    private javax.swing.JPanel paddingPanel2;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField postalCode;
    public static javax.swing.JPanel pricesPanel;
    private javax.swing.JPanel productContainerPanel;
    private javax.swing.JLabel profileInfoTitle;
    private javax.swing.JPanel redBest;
    private javax.swing.JPanel redContainerPanel;
    private javax.swing.JPanel redInfoBestPanel;
    private javax.swing.JPanel redPanel;
    private javax.swing.JLabel redPhotoBest;
    private javax.swing.JScrollPane redScrollPane;
    private javax.swing.JLabel redTextLine1;
    private javax.swing.JLabel redTextLine2;
    private javax.swing.JLabel redTextLine3;
    private javax.swing.JLabel redTitle;
    private javax.swing.JLabel redWine;
    private javax.swing.JLabel redWineBackground;
    private javax.swing.JPanel redWinePanel;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JPanel roseBest;
    private javax.swing.JPanel roseContainerPanel;
    private javax.swing.JPanel roseInfoBestPanel;
    private javax.swing.JPanel rosePanel;
    private javax.swing.JLabel rosePhotoBest;
    private javax.swing.JScrollPane roseScrollPane;
    private javax.swing.JLabel roseTextLine1;
    private javax.swing.JLabel roseTextLine2;
    private javax.swing.JLabel roseTextLine3;
    private javax.swing.JLabel roseTitle;
    private javax.swing.JLabel roseWine;
    private javax.swing.JLabel roseWineBackground;
    private javax.swing.JPanel roseWinePanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel shippingLb;
    private javax.swing.JLabel shippingPrice;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JButton submitBtn;
    private javax.swing.JButton submitCartBtn;
    private javax.swing.JLabel subtitleMsg;
    private javax.swing.JLabel subtotalLb;
    private javax.swing.JLabel subtotalPrice;
    private javax.swing.JTextField surename;
    private javax.swing.JLabel surenameLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleWishlist;
    private javax.swing.JLabel topImage;
    private javax.swing.JLabel topImageProfile;
    private javax.swing.JPanel topbarPanel;
    private javax.swing.JLabel totalLb;
    private javax.swing.JLabel totalPrice;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel username;
    private javax.swing.JPanel whiteBest;
    private javax.swing.JPanel whiteContainerPanel;
    private javax.swing.JPanel whiteInfoBestPanel;
    private javax.swing.JPanel whitePanel;
    private javax.swing.JLabel whitePhotoBest;
    private javax.swing.JScrollPane whiteScrollPane;
    private javax.swing.JLabel whiteTextLine1;
    private javax.swing.JLabel whiteTextLine2;
    private javax.swing.JLabel whiteTextLine3;
    private javax.swing.JLabel whiteTitle;
    private javax.swing.JLabel whiteWine;
    private javax.swing.JLabel whiteWineBackground;
    private javax.swing.JPanel whiteWinePanel;
    private javax.swing.JLabel winePageBackground;
    private javax.swing.JLabel winePageTitle;
    private javax.swing.JPanel winePanel;
    private javax.swing.JLabel wineTitle;
    private javax.swing.JScrollPane wishlistScrollPane;
    // End of variables declaration//GEN-END:variables
}
