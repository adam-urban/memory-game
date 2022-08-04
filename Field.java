/* Class that manage field containg word in the game. */

public class Field{
    /* Attributes */
    private String word;
    private boolean cover;
    private String coverSign = "X";
    
    /* Constructors */
    public Field(String word){ 
            this.word = word; 
            this.cover = true;
        }

    /* Methods */
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public boolean isCover() { return cover; }
    public void setCover() { this.cover = true; }
    public void setUnCover() {this.cover = false; }
    public int length() {return this.toString().length();}
    @Override
    public String toString() {
        return this.isCover()? coverSign : word; 
    }

    public static void main(String[] args) {
        Field f = new Field("word");
        System.out.println(f);
        f.setUnCover();
        System.out.println(f);
        f.setWord("inny");
        System.out.println(f);
        f.setCover();
        System.out.println(f);
    }


}