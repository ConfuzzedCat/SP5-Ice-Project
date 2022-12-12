public class Payment {
    // Vores payment klasse skal bede om et kreditkort nummer på 8 tal, hvis der ikke er 8 tal i kreditkortet så skal den fejle.

    public boolean creditCardInfo(){
        String cardInformationS = TextUI.getUserInput("Please type Credit Card Info (8 digits):");
        int cardInfoInt = Integer.parseInt(cardInformationS);
        if(cardInfoInt < 9999999 || cardInfoInt > 99999999 ){
           TextUI.sendMessage("Credit card info is invalid, please try again.");
            creditCardInfo();
        }
        return true;
    }
}
