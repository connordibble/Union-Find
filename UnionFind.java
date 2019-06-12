public class UnionFind {

    //union by height
    public static void union(Pair[] s, int root1, int root2 ){

        if( s[root2].height > s[root1].height )// root2 is deeper
             s[root1].parentInt = root2; // Make root2 new root

         else{
             if( s[root1].height == s[root2].height ) s[root1].height += 1; // Update height if same
             s[root2].parentInt = root1; // Make root1 new root
             }
        }

    //find method that implements path compression
    public static int find(Pair[] s, int slot){
        if (s[slot].parentInt == slot) return slot;

        s[slot].parentInt = find(s, s[slot].parentInt);

        return s[slot].parentInt;

    }

    public static void main(String[] args) {
        Pair pair1 = new Pair(0,1,false);
        Pair pair2 = new Pair(2,0,false);
        Pair pair3 = new Pair(4,0,false);
        Pair pair4 = new Pair(4,0,false);
        Pair pair5 = new Pair(4,2,false);

        Pair[] s = new Pair[5];

        s[0] = pair1;
        s[1] = pair2;
        s[2] = pair3;
        s[3] = pair4;
        s[4] = pair5;

        System.out.println("0 parent before union and find: " + s[0].parentInt);
        union(s,s[0].parentInt,s[4].parentInt); //4 becomes the parent of the parent of 0
        find(s,s[0].parentInt); //path compressed
        System.out.println("0 parent after union find: " + s[0].parentInt);


    }
}

