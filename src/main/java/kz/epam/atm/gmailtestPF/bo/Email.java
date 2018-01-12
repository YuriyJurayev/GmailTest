package kz.epam.atm.gmailtestPF.bo;

public class Email {

    private final String recipients;
    private final String subject;
    private final String body;
    private final String image;

    private Email(EmailBuilder builder){
        this.recipients = builder.recipients;
        this.subject = builder.subject;
        this.body = builder.body;
        this.image = builder.image;
    }

    public String getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
    public String getImage() {
        return image;
    }

    public static class EmailBuilder{
        private final String recipients;
        private final String subject;
        private String body;
        private String image;

        public EmailBuilder(String recipients,String subject){
            this.recipients = recipients;
            this.subject = subject;
        }

        public EmailBuilder setBody(String body){
            this.body = body;
            return this;
        }
        public EmailBuilder setImage(String imageLink){
            this.image = imageLink;
            return this;
        }
        public Email build(){
            return new Email(this);
        }
    }

}
