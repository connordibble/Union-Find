public class Pair {
    int parentInt;
    int height; //only has a height if its a parent
    Boolean filled;
    char [] sym = {'\u25A0','\u25A2'};  //black square, white square

    Pair(int parentInt, int height, Boolean filled){
        this.parentInt = parentInt;
        this.height = height;
        this.filled = filled;
    }

    //print out the pair as either an open or closed square
    public String toString(){
        if (filled) return (sym[0] + "");
        else return (sym[1] + "");
    }
}
