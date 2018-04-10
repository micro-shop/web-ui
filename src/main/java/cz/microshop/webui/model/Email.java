package cz.microshop.webui.model;

public class Email {

    private String to;
    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "content='" + content + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
