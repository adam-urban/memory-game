public enum Difficulty{
    EASY ("Easy", 4, 10),
    HARD ("Hard", 8, 15);

    final String difficulty;
    final int numberOfPairs;
    final int chances;

    Difficulty( String difficulty, int numberOfPairs, int chances){
        this.difficulty = difficulty;
        this.numberOfPairs = numberOfPairs;
        this.chances = chances;
    }
}