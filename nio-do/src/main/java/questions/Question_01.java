package questions;

import java.io.Serializable;

public class Question_01 {
    public static void main(String[] args) {
        DefaultMessageFactory factory = new DefaultMessageFactory();
        Message message_01 = factory.newMessage("1234");
        Message message_02 = factory.newMessage("5678");
        System.out.println(message_01);
        System.out.println(message_02);
        message_01.printMessage();
        message_02.printMessage();
    }

    interface Message extends Serializable {
        void printMessage();
    }

    static class DefaultMessage implements Message {
        private String countryCode;

        @Override
        public void printMessage() {
            System.out.println(countryCode);
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
    }

    interface MessageFactory {
        Message newMessage(String countryCode);
    }

    static class DefaultMessageFactory implements MessageFactory {
        private static Message message;

        @Override
        public Message newMessage(String countryCode) {

            if (message == null) {
                synchronized (DefaultMessageFactory.class) {
                    message = new DefaultMessage();
                    ((DefaultMessage) message).setCountryCode(countryCode);
                }
            }
            return message;
        }
    }
}
