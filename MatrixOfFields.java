import java.util.Random;
public class MatrixOfFields {
    /* Attributes */
    private int rows;
    private int columns;
    private Field[][] matrix;

    /* Constructors */
    public MatrixOfFields(String listOfWords[]){
        this.rows = 2;
        this.columns = listOfWords.length;
        this.matrix = new Field[rows][columns];
        
        for(int i=0; i<rows;i++){
            for(int j=0; j<columns; j++){
                matrix[i][j] = new Field(listOfWords[j]);
            }
        }
        this.shuffle();

    }

    /* Methods */
    public void setUnCover(int x, int y){
        this.matrix[x][y].setUnCover();
    }

    public void setCover(int x, int y){
        this.matrix[x][y].setCover();
    }

    public String getWord(int x, int y){
        return matrix[x][y].getWord();
    }

    public boolean isCover(int x, int y){
        return matrix[x][y].isCover();
    }

    public boolean isMatrixUnCover(){
        for(Field[] row: matrix){
            for(Field field: row){
                if(field.isCover()){
                    return false;
                }
            }
        }
        return true;
    }

    public void shuffle(){
        Random rand = new Random();
        int x,y;
        Field temp;
        for(int i=0; i<rows;i++){
            for(int j=0; j<columns; j++){
                x = rand.nextInt(rows);
                y = rand.nextInt(columns);
                temp=matrix[i][j];
                matrix[i][j] = matrix[x][y];
                matrix[x][y] = temp;
            }
        }
    }

    public int getSizeOfColumn(int number){                         
        if(matrix[0][number].length()>matrix[1][number].length())
            return matrix[0][number].length();
        else
            return matrix[1][number].length();
    }

    public String header(){
        StringBuffer header = new StringBuffer("  ");
        for(int i=0; i<columns; i++){
            int size = getSizeOfColumn(i);
            header.append(i+1);
            size--;
            while(size>0){
                header.append(" ");
                size--;
            }
            header.append(" ");
        }
        return header.toString();
    }

    public String rowToString(int row){
        StringBuffer buffer = new StringBuffer();
        char rowSign = (char) (((int) 'A') + row);
        buffer.append(rowSign + " ");
        for(int i=0; i<columns; i++){ 
            int size = getSizeOfColumn(i);
            buffer.append(matrix[row][i]);
            size -= matrix[row][i].length();
            while(size>0){
                buffer.append(" ");
                size--;
            }
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public int getColumns(){return this.columns;}
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.header()+"\n");
        for(int i=0; i<rows; i++){
            buffer.append(this.rowToString(i) + "\n");
        }
        return buffer.toString();

    }

    public static void main(String[] args) {
    }

}
