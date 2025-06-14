package codename.model;

public class Clue implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String text;
    private Integer number;

  public Clue(String text, Integer number) {
    this.text = text;
    this.number = number;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
}
