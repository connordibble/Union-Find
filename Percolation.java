

public class Percolation {

    private final int SIZE = 20;
    private Pair[] pairList = new Pair[(SIZE + 2) * SIZE];
    private int totalCount = 0;

    Percolation() {
        int counter = 0;
        //create the dummy row at the top
        for (int i = 0; i < SIZE ; i++) {
            pairList[i] = new Pair(0,0, false);
        }

        //initialize the grid to filled pairs
        for (int i = 0; i < (SIZE * SIZE) ; i++) {
                pairList[counter + SIZE] = new Pair(counter + SIZE,0, true);
                counter++;
        }

        //create dummy grid at the bottom
        for (int i = 0; i < SIZE ; i++) {
            pairList[i + (SIZE * (SIZE + 1))] = new Pair(SIZE * (SIZE + 2) - 1,0, false);
        }
    }

    public void display(){
        int gridCounter = 0;
        //print the initial grid
        for (int i = 0; i < SIZE + 2 ; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(pairList[gridCounter].toString());
                gridCounter++;
            }
            System.out.println();
        }
        System.out.println();
    }

    //if true, then percolation occurs and program is finished
    public boolean monteCarlo(){
        int rand1;
        int rand2;
        int filledCounter = 0;
        int parent1 = 0;
        int parent2 = 0;

        //open 50 random slots
        while (filledCounter < 50) {
            rand1 = (int) (Math.random() * (SIZE * (SIZE + 1))); //get random spots to open
            if(pairList[rand1].filled){
                pairList[rand1].filled = false;
                totalCount++;
                parent1 = UnionFind.find(pairList,rand1);
                filledCounter++;

                if(!pairList[rand1 - 1].filled){ //check left
                    parent2 = UnionFind.find(pairList,rand1 - 1);
                    UnionFind.union(pairList,parent1,parent2);
                }

                if(!pairList[rand1 + 1].filled){ //check right
                    parent1 = UnionFind.find(pairList,rand1);
                    parent2 = UnionFind.find(pairList,rand1 + 1);
                    UnionFind.union(pairList,parent1,parent2);
                }

                if(!pairList[rand1 + SIZE].filled){ //check up
                    parent1 = UnionFind.find(pairList,rand1);
                    parent2 = UnionFind.find(pairList,rand1 + SIZE);
                    UnionFind.union(pairList,parent1,parent2);
                }

                if(!pairList[rand1 - SIZE].filled){ //check down
                    parent1 = UnionFind.find(pairList,rand1);
                    parent2 = UnionFind.find(pairList,rand1 - SIZE);
                    UnionFind.union(pairList,parent1,parent2);
                }

                if(UnionFind.find(pairList,0) == UnionFind.find(pairList, SIZE * (SIZE + 2) - 1))
                    break;
            }
        }
        display();
        return (UnionFind.find(pairList,0) == UnionFind.find(pairList, SIZE * (SIZE + 2) - 1));
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation();
        perc.display();

        while(!perc.monteCarlo()){
            perc.monteCarlo();
        }

        System.out.println("---Statistics---\n");
        System.out.println("Sites Opened: " + perc.totalCount);
        System.out.println("Percolation Threshold: " + (double)(perc.totalCount) / 400);
    }
}
