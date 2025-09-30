package winepackage;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class CartBoxPanel extends javax.swing.JPanel {

    private final HomePage homePage;

    public CartBoxPanel(HomePage homePage) {
        this.homePage = homePage;
        initComponents();
    }

    // Setters & Getters
    public JLabel getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(String cartInfo) {
        this.cartInfo.setText(cartInfo);
    }

    public JTextField getCartNumbDisplay() {
        return cartNumbDisplay;
    }

    public void setCartNumbDisplay(String cartNumbDisplay) {
        this.cartNumbDisplay.setText(cartNumbDisplay);
    }

    public JLabel getCartPhoto() {
        return cartPhoto;
    }

    public void setPic(String imageName) {
        String path = "/winepackage/images/" + imageName + ".jpg";
        try {
            cartPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
        }
    }

    public JLabel getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice.setText(cartPrice);
    }

    public JLabel getCartSumPrice() {
        return cartSumPrice;
    }

    public void setCartSumPrice(String cartSumPrice) {
        this.cartSumPrice.setText(cartSumPrice);
    }

    public JLabel getCartTitle() {
        return cartTitle;
    }

    public void setCartTitle(String cartTitle) {
        this.cartTitle.setText(cartTitle);
    }
    

    @SuppressWarnings("unchecked")
    private void initComponents() {

        cartBoxPanel = new javax.swing.JPanel();
        cartBoxCont = new javax.swing.JPanel();
        cartTitle = new javax.swing.JLabel();
        cartInfo = new javax.swing.JLabel();
        cartPrice = new javax.swing.JLabel();
        cartPhoto = new javax.swing.JLabel();
        cartMinBtn = new javax.swing.JButton();
        cartPlusBtn = new javax.swing.JButton();
        cartNumbDisplay = new javax.swing.JTextField();
        cartSumPrice = new javax.swing.JLabel();

        cartBoxPanel.setBackground(new java.awt.Color(255, 255, 255));
        cartBoxPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cartBoxCont.setBackground(new java.awt.Color(255, 255, 255));

        cartTitle.setFont(new java.awt.Font("Segoe UI", 1, 18));
        cartTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartTitle.setText("Zero Sauvignon Blanc");

        cartInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cartInfo.setForeground(new java.awt.Color(51, 51, 51));
        cartInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartInfo.setText("Ξηρός, Ήπιος, Λευκός");

        cartPrice.setBackground(new java.awt.Color(0, 0, 0));
        cartPrice.setFont(new java.awt.Font("Segoe UI", 3, 18)); 
        cartPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartPrice.setText("10,60 €");

        cartPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/winepackage/images/WH008.jpg"))); 
        if (cartPhoto == null) {
            System.err.println("Image not found");
        }

        cartMinBtn.setBackground(new java.awt.Color(179, 110, 120));
        cartMinBtn.setText("-");
        cartMinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartMinBtnActionPerformed(evt);
            }
        });

        cartPlusBtn.setBackground(new java.awt.Color(179, 110, 120));
        cartPlusBtn.setText("+");
        cartPlusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartPlusBtnActionPerformed(evt);
            }
        });

        cartNumbDisplay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cartNumbDisplay.setText(" 3");
        cartNumbDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartNumbDisplayActionPerformed(evt);
            }
        });

        cartSumPrice.setFont(new java.awt.Font("Liberation Sans", 1, 18)); 
        cartSumPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartSumPrice.setText("150,60 €");

        javax.swing.GroupLayout cartBoxContLayout = new javax.swing.GroupLayout(cartBoxCont);
        cartBoxCont.setLayout(cartBoxContLayout);
        cartBoxContLayout.setHorizontalGroup(
            cartBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartBoxContLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cartPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cartMinBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartNumbDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartPlusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartSumPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        cartBoxContLayout.setVerticalGroup(
            cartBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartBoxContLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cartTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartInfo)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartBoxContLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cartBoxContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cartPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartMinBtn)
                    .addComponent(cartPlusBtn)
                    .addComponent(cartNumbDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartSumPrice))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartBoxContLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cartPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cartBoxPanel.add(cartBoxCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartBoxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartBoxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void cartMinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartMinBtnActionPerformed
        String title = getCartTitle().getText() ;
        System.out.println("TitleMin:" + title);
        HomePage.cartManage("http://localhost:5000/cartmanage", homePage.getUser() , title, 1);
        String currentText = getCartNumbDisplay().getText().trim();
        int count;

        try {
            count = Integer.parseInt(currentText);
        } catch (NumberFormatException e) {
            count = 1; // fallback to 1 if parsing fails
        }

        // Decrement, but not below 1
        if (count > 1) {
            count--;
            String price1 = getCartPrice().getText();
            float price = Float.parseFloat(price1);
            float sum = price * count;
            setCartSumPrice("" + sum);    
        }else if(count == 1){
            this.cartBoxPanel.setVisible(false);
        }

        // Update the text field
        getCartNumbDisplay().setText(String.valueOf(count));
    }

    private void cartNumbDisplayActionPerformed(java.awt.event.ActionEvent evt) { }

    private void cartPlusBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String title = getCartTitle().getText() ;
        HomePage.cartManage("http://localhost:5000/cartmanage", homePage.getUser() , title, 2);
        String currentText = getCartNumbDisplay().getText().trim();
        int count;

        try {
            count = Integer.parseInt(currentText);
        } catch (NumberFormatException e) {
            count = 1; // fallback to 1 if parsing fails
        }
        count ++;
        String price1 = getCartPrice().getText();
        float price = Float.parseFloat(price1);
        float sum = price * count;
        setCartSumPrice("" + sum);
        
        // Update the text field
        getCartNumbDisplay().setText(String.valueOf(count));    
    }


    private javax.swing.JPanel cartBoxCont;
    private javax.swing.JPanel cartBoxPanel;
    private javax.swing.JLabel cartInfo;
    private javax.swing.JButton cartMinBtn;
    private javax.swing.JTextField cartNumbDisplay;
    private javax.swing.JLabel cartPhoto;
    private javax.swing.JButton cartPlusBtn;
    private javax.swing.JLabel cartPrice;
    private javax.swing.JLabel cartSumPrice;
    private javax.swing.JLabel cartTitle;
}
