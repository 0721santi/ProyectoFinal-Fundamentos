public class palabra {
    private int x, y, longigtude;
    public palabra(int x, int y, int longigtude){
        this.x = x;
        this.y = y;
        this.longigtude = longigtude;
    }
    public int getLongitude(){
        return this.longigtude;
    }
    public int getXpos(){
        return this.x;
    }
    public int getYpos(){
        return this.y;
    }
    public void setLongitude(int longigtude){
        this.longigtude = longigtude;
    }
    public void setXpos(int x){
        this.x = x;
    }
    public void setYpos(int y){
        this.y = y;
    }
}
