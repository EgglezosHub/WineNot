package winepackage;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ProductBoxPanel extends javax.swing.JPanel {

    private final HomePage homePage;

    public ProductBoxPanel(HomePage homePage) {
        this.homePage = homePage;
        initComponents();
    }


    // Setters & Getters
    public JButton getProductBuyBtn() {
        return productLikeBtn;
    }

    public void setProductBuyBtn(JButton productBuyBtn) {
        this.productLikeBtn = productBuyBtn;
    }

    public JLabel getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo.setText(productInfo);
    }

    public JButton getProductLikeBtn() {
        return productBuyBtn;
    }

    public void setProductLikeBtn(JButton productLikeBtn) {
        this.productBuyBtn = productLikeBtn;
    }

    public JLabel getProductPhoto() {
        return productPhoto;
    }

    public void setPic(String imageName) {
        String path = "/winepackage/images/" + imageName + ".jpg";
        try {
            productPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
        }
    }

    public JLabel getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice.setText(productPrice);
    }

    public JLabel getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle.setText(productTitle);
    }
    
  @SuppressWarnings("unchecked")
    private void initComponents() {

        productBoxPanel = new javax.swing.JPanel();
        productBoxCont = new javax.swing.JPanel();
        productTitle = new javax.swing.JLabel();
        productInfo = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        productBuyBtn = new javax.swing.JButton();
        productPhoto = new javax.swing.JLabel();
        productLikeBtn = new javax.swing.JButton();

        productBoxPanel.setBackground(new java.awt.Color(255, 255, 255));
        productBoxPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productBoxCont.setBackground(new java.awt.Color(255, 255, 255));

        productTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        productTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productTitle.setText("Zero Sauvignon Blanc");

        productInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        productInfo.setForeground(new java.awt.Color(51, 51, 51));
        productInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productInfo.setText("Ξηρός, Ήπιος, Λευκός");

        productPrice.setBackground(new java.awt.Color(0, 0, 0));
        productPrice.setFont(new java.awt.Font("Segoe UI", 3, 14)); 
        productPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPrice.setText("10,60 €");

        productBuyBtn.setBackground(new java.awt.Color(179, 110, 120));
        productBuyBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        productBuyBtn.setText("Buy");
        productBuyBtn.setActionCommand("🛒");
        productBuyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBuyBtnActionPerformed(evt);
            }
        });

        productPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WH010.jpg"))); 
        productPhoto.setText(" ");

        productLikeBtn.setBackground(new java.awt.Color(179, 110, 120));
        productLikeBtn.setFont(new java.awt.Font("Segoe UI", 0, 36)); 
        productLikeBtn.setText("♡");
        productLikeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productLikeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productBoxContLayout = new javax.swing.GroupLayout(productBoxCont);
        productBoxCont.setLayout(productBoxContLayout);
        productBoxContLayout.setHorizontalGroup(
            productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productBoxContLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(productBoxContLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(productPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(productInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productBuyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productLikeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        productBoxContLayout.setVerticalGroup(
            productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productBoxContLayout.createSequentialGroup()
                .addGroup(productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productBoxContLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(productPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(productBoxContLayout.createSequentialGroup()
                        .addGap(0, 34, Short.MAX_VALUE)
                        .addGroup(productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(productLikeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(productBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productBoxContLayout.createSequentialGroup()
                                .addComponent(productInfo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productPrice))
                            .addComponent(productBuyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        productBoxPanel.add(productBoxCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productBoxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productBoxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );
    }

    private void productLikeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productLikeBtnActionPerformed
        String title = getProductTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", homePage.getUser() , title, 2, 2);
        this.productBoxPanel.setVisible(false);
    }

    private void productBuyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBuyBtnActionPerformed
        String title = getProductTitle().getText() ;
        HomePage.Update("http://localhost:5000/update", homePage.getUser() , title, 1, 1);
    }

    private javax.swing.JPanel productBoxCont;
    private javax.swing.JPanel productBoxPanel;
    private javax.swing.JButton productBuyBtn;
    private javax.swing.JLabel productInfo;
    private javax.swing.JButton productLikeBtn;
    private javax.swing.JLabel productPhoto;
    private javax.swing.JLabel productPrice;
    private javax.swing.JLabel productTitle;
}
