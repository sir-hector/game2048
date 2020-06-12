package model.tile;

/**
 * Tile Clas
 */

public class Tile {
    private boolean merged;
    private int value;
    /**
     * Contructor with parametr of value
     */
    public Tile(int val){
        value =val;
    }
    /**
     * Method getValue
     * @return value of time
     */
    public int getValue(){
        return value;
    }
    /**
     * SetMerged
     */
    public void setMerged(boolean m){
        merged =m;
    }
    /**
     * Checking if tile can merge with other
     * @param other - other tile to check
     * @return true or falses
     */
    public boolean canMergeWith(Tile other){
        return !merged && other!= null && !other.merged && value == other.getValue();
    }

    /**
     * @param other - other tile to merge
     * @return new value of merged tile - pow(2)
     */
    public int mergeWith(Tile other){
        if (canMergeWith(other)){
            value *= 2;
            merged = true;
            return value;
        }
        return -1;
    }

}
