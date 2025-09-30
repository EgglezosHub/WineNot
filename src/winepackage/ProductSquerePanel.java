package winepackage;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ProductSquerePanel extends javax.swing.JPanel {

    private final HomePage homePage;

    public ProductSquerePanel(HomePage homePage) {
        this.homePage = homePage;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        productSquerePanel = new javax.swing.JPanel();
        descriptionSquerePanel = new javax.swing.JPanel();
        titleSquere = new javax.swing.JLabel();
        infoSquere = new javax.swing.JLabel();
        priceSquere = new javax.swing.JLabel();
        buyButtonSquere = new javax.swing.JButton();
        likeButtonSquere = new javax.swing.JButton();
        photoCodeSquere = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productSquerePanel.setBackground(new java.awt.Color(255, 255, 255));
        productSquerePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descriptionSquerePanel.setBackground(new java.awt.Color(255, 255, 255));
        descriptionSquerePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleSquere.setFont(new java.awt.Font("Segoe UI", 1, 12));
        titleSquere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSquere.setText("Zero Sauvignon Rose");
        descriptionSquerePanel.add(titleSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 158, -1));

        infoSquere.setFont(new java.awt.Font("Liberation Sans", 0, 11));
        infoSquere.setForeground(new java.awt.Color(51, 51, 51));
        infoSquere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoSquere.setText("Ξηρός, Ήπιος, Λευκός");
        descriptionSquerePanel.add(infoSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 29, 158, -1));

        priceSquere.setFont(new java.awt.Font("Segoe UI", 3, 14)); 
        priceSquere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceSquere.setText("10,60 €");
        descriptionSquerePanel.add(priceSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 60, 20));

        buyButtonSquere.setBackground(new java.awt.Color(179, 110, 120));
        buyButtonSquere.setFont(new java.awt.Font("Segoe UI", 1, 12));
        buyButtonSquere.setText("✚");
        buyButtonSquere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonSquereActionPerformed(evt);
            }
        });
        descriptionSquerePanel.add(buyButtonSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, -1));

        likeButtonSquere.setBackground(new java.awt.Color(179, 110, 120));
        likeButtonSquere.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        likeButtonSquere.setText("♥︎");
        likeButtonSquere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                likeButtonSquereActionPerformed(evt);
            }
        });
        descriptionSquerePanel.add(likeButtonSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, -1));
        productSquerePanel.add(descriptionSquerePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        photoCodeSquere.setBackground(new java.awt.Color(255, 255, 255));
        photoCodeSquere.setForeground(new java.awt.Color(255, 255, 255));
        photoCodeSquere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photoCodeSquere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WH009.jpg")));
        photoCodeSquere.setText(" ");
        productSquerePanel.add(photoCodeSquere, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, 150, 170));
        add(productSquerePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }

    // Setter & Getters
    public JLabel getTitleSquere() {
        return titleSquere;
    }

    public void setLikeButtonSquere(JButton likeButtonSquere) {
        this.likeButtonSquere = likeButtonSquere;
    }

    public void setTitleSquere(String title) {
        titleSquere.setText(title);
    }

    public void setPriceSquere(String price) {
        priceSquere.setText(price);
    }

    public void setPhotoCodeSquere(JLabel photoCodeSquere) {
        this.photoCodeSquere = photoCodeSquere;
    }
    
    public void setinfoSquere(String info) {
        infoSquere.setText(info);
    }
    
    public void setPic(String imageName) {
        String path = "/winepackage/images/" + imageName + ".jpg";
        try {
            photoCodeSquere.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
        }
    }


    
    private void likeButtonSquereActionPerformed(java.awt.event.ActionEvent evt) {
        String title = getTitleSquere().getText() ;
        HomePage.Update("http://localhost:5000/update", homePage.getUser() , title, 2, 1);
    }

    private void buyButtonSquereActionPerformed(java.awt.event.ActionEvent evt) {
        String title = getTitleSquere().getText() ;
        HomePage.Update("http://localhost:5000/update", homePage.getUser() , title, 1, 1);
    }

    private javax.swing.JButton buyButtonSquere;
    private javax.swing.JPanel descriptionSquerePanel;
    public javax.swing.JLabel infoSquere;
    private javax.swing.JButton likeButtonSquere;
    public javax.swing.JLabel photoCodeSquere;
    public javax.swing.JLabel priceSquere;
    private javax.swing.JPanel productSquerePanel;
    public javax.swing.JLabel titleSquere;
}
